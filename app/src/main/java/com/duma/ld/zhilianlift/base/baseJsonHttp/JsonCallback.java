package com.duma.ld.zhilianlift.base.baseJsonHttp;

import com.duma.ld.zhilianlift.model.HttpResModel;
import com.duma.ld.zhilianlift.model.HttpSimpleResModel;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

public abstract class JsonCallback<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
            response.close();
            throw new IllegalStateException("没有填写泛型参数");
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        if (typeArgument == Void.class) {
            HttpSimpleResModel simpleResponse = Convert.fromJson(jsonReader, HttpSimpleResModel.class);
            response.close();
            return (T) simpleResponse.toLzyResponse();
        } else if (rawType == HttpResModel.class) {
            HttpResModel httpResModel = null;
            try {
                httpResModel = Convert.fromJson(jsonReader, type);
            } catch (JsonIOException | JsonSyntaxException e) {
                throw new IllegalStateException("json 解析错误!可能数据类型不匹配");
            } finally {
                response.close();
            }
            String code = httpResModel.getStatus();
            /**
             * 200成功 100重新登陆 400读msg
             */
//            if (code.equals("200")){
//                return (T) httpResModel;
//            }else {
//                throw new IllegalStateException(httpResModel.message);
//            }
            switch (code) {
                case "200":
                    return (T) httpResModel;
                case "400":
                    String msg = httpResModel.getMsg();
                    if (msg == null) {
                        throw new IllegalStateException("服务器异常!");
                    } else {
                        throw new IllegalStateException(msg);
                    }
                default:
                    throw new IllegalStateException(httpResModel.getStatus());
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
    }
}