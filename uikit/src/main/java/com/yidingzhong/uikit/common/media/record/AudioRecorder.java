package com.yidingzhong.uikit.common.media.record;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import com.yidingzhong.uikit.R;
import com.yidingzhong.uikit.common.util.file.FileUtil;
import com.yidingzhong.uikit.common.util.log.LogUtil;
import com.yidingzhong.uikit.common.util.storage.StorageType;
import com.yidingzhong.uikit.common.util.storage.StorageUtil;
import com.yidingzhong.uikit.common.util.string.StringUtil;
import com.mp3recorder.DataEncodeThread;
import com.mp3recorder.util.LameUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2017/2/3 0003.
 */

public final class AudioRecorder {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    //=======================AudioRecord Default Settings=======================
    private static final int DEFAULT_AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
    /**
     * 以下三项为默认配置参数。Google Android文档明确表明只有以下3个参数是可以在所有设备上保证支持的。
     */
    private static final int DEFAULT_SAMPLING_RATE = 44100;//模拟器仅支持从麦克风输入8kHz采样率
    private static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    /**
     * 下面是对此的封装
     */
    private static final int DEFAULT_AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    //======================Lame Default Settings=====================
    private static final int DEFAULT_LAME_MP3_QUALITY = 7;
    /**
     * 与DEFAULT_CHANNEL_CONFIG相关，因为是mono单声，所以是1
     */
    private static final int DEFAULT_LAME_IN_CHANNEL = 1;
    /**
     * Encoded bit rate. MP3 file will be encoded with bit rate 32kbps
     */
    private static final int DEFAULT_LAME_MP3_BIT_RATE = 64;
//    private static final int DEFAULT_LAME_MP3_BIT_RATE = 32;

    //==================================================================

    private static final int FRAME_COUNT = 160;

    public static final int DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND = 60;
    private static final String TAG = "AudioRecordManager";
    private AudioRecord mAudioRecorder;
    private AudioManager audioManager;
    private Handler mUiHandler = new Handler();
    private Context context;
    private File audioFile;
    private long audioDuration;
    private RecordType recordType;
    private int maxDuration;
    private final AtomicBoolean isRecording = new AtomicBoolean(false);
    private boolean cancelRecord = false;
    private int mBufferSize;
    private short[] mShortBuffer;
    private byte[] mMp3Buffer;
    private FileOutputStream mFileOutputStream;
    private int mVolume;
    private DataEncodeThread mEncodeThread;

    private IAudioRecordCallback cb;
    private IAudioRecordInfoListener infoListener = new IAudioRecordInfoListener() {
        public void onInfo(int var1, int var2, int var3) {
            if (var2 == 2) {
                handleEndRecord(false, 0, false);
                mUiHandler.removeCallbacks(AudioRecorder.this.recordingUpdateUI);
            } else {
                if (var2 == 1) {
                    isRecording.compareAndSet(true,false);
                    handleReachedMaxRecordTime();
                    mUiHandler.removeCallbacks(AudioRecorder.this.recordingUpdateUI);
                }
            }
        }
    };
    private Runnable recordingUpdateUI = new Runnable() {
        public void run() {
            if (mAudioRecorder != null) {
                mUiHandler.postDelayed(this, 100L);
            }

        }
    };

    CountDownTimer timer;

    private ExecutorService mExecutorService;

    private class AudioRecordRunnable implements Runnable {


        AudioRecordRunnable() {
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
            if (mAudioRecorder.getState() == AudioRecord.STATE_INITIALIZED) {

                while (isRecording.get()) {
                    int ret = mAudioRecorder.read(mShortBuffer, 0, mBufferSize);
                    if (ret > 0) {
                        //mEncodeThread.addTask(mShortBuffer, ret);
                        int encodedSize = LameUtil.encode(mShortBuffer, mShortBuffer, ret, mMp3Buffer);
                        if (encodedSize > 0){
                            try {
                                mFileOutputStream.write(mMp3Buffer, 0, encodedSize);
                            } catch (IOException e) {
                                e.printStackTrace();
                                CrashReport.postCatchedException(e);
                            }
                        }
                        calculateRealVolume(mShortBuffer, ret);
                    } else {
                        onError(ret);
                        break;
                    }
                }
                final int flushResult = LameUtil.flush(mMp3Buffer);
                if (flushResult > 0) {
                    try {
                        mFileOutputStream.write(mMp3Buffer, 0, flushResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                        CrashReport.postCatchedException(e);
                    }finally{
                        if (mFileOutputStream != null) {
                            try {
                                mFileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                CrashReport.postCatchedException(e);
                            }
                        }
                        LameUtil.close();
                    }
                }
            }
        }

        private void calculateRealVolume(short[] buffer, int readSize) {
            double sum = 0;
            for (int i = 0; i < readSize; i++) {
                sum += buffer[i] * buffer[i];
            }
            if (readSize > 0) {
                double amplitude = sum / readSize;
                mVolume = (int) Math.sqrt(amplitude);
            }
        }

        private void onError(int errorCode) {
            if (errorCode == AudioRecord.ERROR_INVALID_OPERATION) {
                Log.w(TAG, "record fail: ERROR_INVALID_OPERATION");
            } else if (errorCode == AudioRecord.ERROR_BAD_VALUE) {
                Log.w(TAG, "record fail: ERROR_BAD_VALUE");
            }
            isRecording.compareAndSet(true,false);
        }
    }


    public AudioRecorder(Context context, RecordType var2, final IAudioRecordCallback callback) {
        this.context = context.getApplicationContext();
        recordType = var2;
        maxDuration = DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND;
        //计时存在10ms左右的误差，减100ms保证其不超过60s
        timer = new CountDownTimer(maxDuration * 1000 - 300, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                handleReachedMaxRecordTime();
            }
        };
        cb = callback;
        mBufferSize = AudioRecord.getMinBufferSize(DEFAULT_SAMPLING_RATE, DEFAULT_CHANNEL_CONFIG, DEFAULT_AUDIO_FORMAT);
        int bytesPerFrame = DEFAULT_AUDIO_FORMAT;
        int frameSize = mBufferSize / bytesPerFrame;
        if (frameSize % FRAME_COUNT != 0) {
            frameSize += (FRAME_COUNT - frameSize % FRAME_COUNT);
            mBufferSize = frameSize * bytesPerFrame;
        }

        mShortBuffer = new short[mBufferSize];
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public boolean startRecord() {
        //audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 0, 2);
        if (isRecording.get()) {
            LogUtil.v(TAG, "AudioRecordManager startRecord false, as current state is isRecording");
            return false;
        } else {
            if (!StorageUtil.hasEnoughSpaceForWrite(context, StorageType.TYPE_AUDIO, false)) {
                LogUtil.d(TAG, "AudioRecordManager startRecord false, as has no enough space to write");
                return false;
            } else {
                try {
                    cancelRecord = false;
                    if(mAudioRecorder == null){
                        mAudioRecorder = new AudioRecord(DEFAULT_AUDIO_SOURCE,
                                DEFAULT_SAMPLING_RATE, DEFAULT_CHANNEL_CONFIG, DEFAULT_AUDIO_FORMAT, mBufferSize);
                    }
                    LameUtil.init(DEFAULT_SAMPLING_RATE, DEFAULT_LAME_IN_CHANNEL, DEFAULT_SAMPLING_RATE, DEFAULT_LAME_MP3_BIT_RATE, DEFAULT_LAME_MP3_QUALITY);
                    String outputFilePath = StorageUtil.getWritePath(StringUtil.get36UUID() + recordType.getFileSuffix(), StorageType.TYPE_AUDIO);
                    audioFile = new File(outputFilePath);
                    mFileOutputStream = new FileOutputStream(audioFile);
                    mMp3Buffer = new byte[(int) (7200 + (mBufferSize * 2 * 1.25))];
                    //mEncodeThread = new DataEncodeThread(audioFile, mBufferSize);
                    //mEncodeThread.start();
                    //mAudioRecorder.setRecordPositionUpdateListener(mEncodeThread, mEncodeThread.getHandler());
                    //mAudioRecorder.setPositionNotificationPeriod(FRAME_COUNT);
                    timer.start();
                    mAudioRecorder.startRecording();
                    if (mAudioRecorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
                        Log.v(TAG, "start recording ok");
                        if(mExecutorService == null || mExecutorService.isShutdown()){
                            mExecutorService = Executors.newSingleThreadExecutor();
                        }
                        if (!cancelRecord) {
                            cb.onRecordReady();
                            isRecording.compareAndSet(false,true);
                            cb.onRecordStart(audioFile, recordType);
                            mUiHandler.post(recordingUpdateUI);
                        }
                        mExecutorService.execute(new AudioRecordRunnable());
                    } else {
                        //audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
                        if (mExecutorService != null) {
                            mExecutorService.shutdown();
                            mExecutorService = null;
                        }
                        if (timer != null) timer.cancel();
                        if (mAudioRecorder != null) {
                            mAudioRecorder.release();
                            handleEndRecord(false, 0, false);
                            mAudioRecorder = null;
                        }

                        mUiHandler.removeCallbacks(recordingUpdateUI);
                        isRecording.compareAndSet(true,false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CrashReport.postCatchedException(e);
                    return false;
                }
            }

            return isRecording.get();
        }
    }

    public void completeRecord(boolean cancel, long duration, boolean recordNext) {
        if (isRecording.get()) {
            cancelRecord = cancel;
            //audioManager.abandonAudioFocus(( .OnAudioFocusChangeListener) null);

            try {
                if (mExecutorService != null && !recordNext) {
                    mExecutorService.shutdown();
                    mExecutorService = null;
                }
                if (timer != null) timer.cancel();
                isRecording.compareAndSet(true,false);
                if (mAudioRecorder != null) {
                    mAudioRecorder.stop();
                    mAudioRecorder.release();
                    mAudioRecorder = null;
                }
                handleEndRecord(true, duration, recordNext);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            mUiHandler.removeCallbacks(recordingUpdateUI);
        }
    }

    public boolean isRecording() {
        return isRecording.get();
    }

    //毫秒
    public void handleEndRecord(boolean success, long duration, boolean recordNext) {
        isRecording.compareAndSet(true,false);
        if (!success) {
            FileUtil.deleteFile(audioFile);
            cb.onRecordFail();
        } else if (cancelRecord || duration < 2000) {
            FileUtil.deleteFile(audioFile);
            String message;
            if (cancelRecord) {
                message = context.getResources().getString(R.string.record_cancel);
            } else {
                message = context.getResources().getString(R.string.audio_less_than_2000);
            }
            cb.onRecordCancel(message);
        } else if (audioFile != null && audioFile.exists() && audioFile.length() > 0L) {
            audioDuration = duration;
            cb.onRecordSuccess(audioFile, duration, recordType, recordNext);
        } else {
            cb.onRecordFail();
        }
    }

    /*public int getCurrentRecordMaxAmplitude() {
        return mAudioRecorder != null?mAudioRecorder.():0;
    }*/

    public File getAudioFile() {
        return audioFile;
    }

    public long getAudioDuration() {
        return audioDuration;
    }

    public int getRecordState() {
        return mAudioRecorder.getState();
    }

    public void deleteFile() {
        if (audioFile != null) {
            audioFile.delete();
            audioFile = null;
        }
    }

    private void handleReachedMaxRecordTime() {
        cb.onRecordReachedMaxTime();
    }
}
