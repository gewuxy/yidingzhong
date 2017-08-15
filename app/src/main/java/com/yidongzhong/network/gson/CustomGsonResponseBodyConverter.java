package com.yidongzhong.network.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.yidingzhong.uikit.common.util.string.DESUtil;
import com.yidongzhong.network.exception.ApiException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zex on 2017/8/15.
 */

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }
    /**
     * 这里统一对json数据做了处理
     * 通过gson获取httpstatus对象 判断ret_code码 判断是否有异常
     * 有异常 抛出异常和异常信息
     * 没异常 单独获取data的json字符串 再由okhttp3 转换为我们需要的object
     *
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        //获取json字符串，此处由于API原因 字符串只能获取一次
        String response = null;
        try {
            response = DESUtil.decode(value.string());
            JsonReader jsonReader = gson.newJsonReader(new StringReader(response));

            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
