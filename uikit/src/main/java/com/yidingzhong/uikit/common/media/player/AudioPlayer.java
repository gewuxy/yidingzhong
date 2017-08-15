package com.yidingzhong.uikit.common.media.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.yidingzhong.uikit.common.util.log.LogUtil;

import java.io.File;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public final class AudioPlayer {
    public static final String TAG = "AudioPlayer";
    private MediaPlayer mPlayer;
    private String mAudioFile;
    private long mIntervalTime;
    private AudioManager audioManager;
    private OnPlayListener mListener;
    private int audioStreamType;
    private static final int WHAT_COUNT_PLAY = 0;
    private static final int WHAT_DECODE_SUCCEED = 1;
    private static final int WHAT_DECODE_FAILED = 2;
    private Handler mHandler;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;

    public AudioPlayer(Context var1) {
        this(var1, (String)null, (OnPlayListener)null);
    }

    public AudioPlayer(Context var1, String var2, OnPlayListener var3) {
        this.mIntervalTime = 500L;
        this.audioStreamType = 0;
        this.mHandler = new Handler() {
            public void handleMessage(Message var1) {
                switch(var1.what) {
                    case 0:
                        if(AudioPlayer.this.mListener != null) {
                            AudioPlayer.this.mListener.onPlaying((long)AudioPlayer.this.mPlayer.getCurrentPosition());
                        }

                        this.sendEmptyMessageDelayed(0, AudioPlayer.this.mIntervalTime);
                        return;
                    case 1:
                        AudioPlayer.this.startInner();
                    default:
                        return;
                    case 2:
                        LogUtil.e("AudioPlayer", "convert() error: " + AudioPlayer.this.mAudioFile);
                }
            }
        };
        this.onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int var1) {
                switch(var1) {
                    case -3:
                        if(AudioPlayer.this.isPlaying()) {
                            AudioPlayer.this.mPlayer.setVolume(0.1F, 0.1F);
                        }
                        break;
                    case -2:
                        AudioPlayer.this.stop();
                        return;
                    case -1:
                        AudioPlayer.this.stop();
                        return;
                    case 0:
                    default:
                        break;
                    case 1:
                        if(AudioPlayer.this.isPlaying()) {
                            AudioPlayer.this.mPlayer.setVolume(1.0F, 1.0F);
                            return;
                        }
                }

            }
        };
        this.audioManager = (AudioManager)var1.getSystemService(Context.AUDIO_SERVICE);
        this.mAudioFile = var2;
        this.mListener = var3;
    }

    public final void setDataSource(String var1) {
        if(!TextUtils.equals(var1, this.mAudioFile)) {
            this.mAudioFile = var1;
        }

    }

    public final void setOnPlayListener(OnPlayListener var1) {
        this.mListener = var1;
    }

    public final OnPlayListener getOnPlayListener() {
        return this.mListener;
    }

    public final void start(int var1) {
        this.audioStreamType = var1;
        this.startPlay();
    }

    public final void stop() {
        if(this.mPlayer != null) {
            this.endPlay();
            if(this.mListener != null) {
                this.mListener.onInterrupt();
            }
        }

    }

    public final boolean isPlaying() {
        return this.mPlayer != null && this.mPlayer.isPlaying();
    }

    public final long getDuration() {
        return this.mPlayer != null?(long)this.mPlayer.getDuration():0L;
    }

    public final long getCurrentPosition() {
        return this.mPlayer != null?(long)this.mPlayer.getCurrentPosition():0L;
    }

    public final void seekTo(int var1) {
        this.mPlayer.seekTo(var1);
    }

    private void startPlay() {
        LogUtil.v("AudioPlayer", "start() called");
        this.endPlay();
        this.startInner();
    }

    private void endPlay() {
        this.audioManager.abandonAudioFocus(this.onAudioFocusChangeListener);
        if(this.mPlayer != null) {
            this.mPlayer.stop();
            this.mPlayer.release();
            this.mPlayer = null;
            this.mHandler.removeMessages(0);
        }

    }

    private void startInner() {
        mPlayer = new MediaPlayer();
        mPlayer.setLooping(false);
        mPlayer.setAudioStreamType(this.audioStreamType);
        if(audioStreamType == 3) {
            audioManager.setSpeakerphoneOn(true);
        } else {
            audioManager.setSpeakerphoneOn(false);
        }

        audioManager.requestAudioFocus(this.onAudioFocusChangeListener, this.audioStreamType, 2);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer var1) {
                LogUtil.v("AudioPlayer", "player:onPrepared");
                mHandler.sendEmptyMessage(0);
                if(mListener != null) {
                    mListener.onPrepared();
                    mPlayer.start();
                }

            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer var1) {
                LogUtil.v("AudioPlayer", "player:onCompletion");
                endPlay();
                if(mListener != null) {
                    mListener.onCompletion();
                }
            }
        });
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer var1, int var2, int var3) {
                LogUtil.e("AudioPlayer", "player:onOnError");
                endPlay();
                if(mListener != null) {
                    mListener.onError(String.format("OnErrorListener what:%d extra:%d", new Object[]{Integer.valueOf(var2), Integer.valueOf(var3)}));
                }
                return true;
            }
        });

        try {
            if(mAudioFile != null) {
                mPlayer.setDataSource(mAudioFile);
                mPlayer.prepareAsync();
                //mPlayer.start();
                LogUtil.v("AudioPlayer", "player:start ok---->" + mAudioFile);
            } else {
                if(mListener != null) {
                    mListener.onError("no datasource");
                }
            }
        } catch (Exception var2) {
            var2.printStackTrace();
            LogUtil.e("AudioPlayer", "player:onOnError Exception\n" + var2.toString());
            endPlay();
            if(mListener != null) {
                mListener.onError("Exception\n" + var2.toString());
            }

        }
    }

    private void deleteOnExit() {
        File var1;
        if((var1 = new File(this.mAudioFile)).exists()) {
            var1.deleteOnExit();
        }

    }
}
