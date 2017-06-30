package cn.iven.mydemo.utils;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

public class ExceptionUtils {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static String handleException(Throwable e) {
        String ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                    ex = "网络不给力！";  //均视为网络错误
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = "服务器异常，请稍后再试";  //均视为网络错误
                    break;
            }
        } else if (e instanceof UnknownHostException) {
            ex = "网络中断，请检查您的网络状态";  //均视为网络错误
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = "数据解析错误！";            //均视为解析错误
        } else if (e instanceof SocketTimeoutException) {
            ex = "网络中断，请检查您的网络状态";
        } else if (e instanceof ConnectException) {
            ex = "网络中断，请检查您的网络状态";  //均视为网络错误
        } else if (e.getMessage().contains("Unable to resolve host")) {
            ex = "网络中断，请检查您的网络状态";
        } else {
            ex = e.getMessage();         //未知错误或服务器返回
        }
        return ex;
    }
}