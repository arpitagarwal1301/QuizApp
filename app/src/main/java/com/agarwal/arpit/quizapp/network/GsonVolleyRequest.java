package com.agarwal.arpit.quizapp.network;

import android.app.DownloadManager;
import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class GsonVolleyRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    private final Response.Listener<T> listener;
    private final Class<T> clazz;

    public GsonVolleyRequest(String url,Class<T> clazz,
                             Response.Listener<T> responseListener, @Nullable Response.ErrorListener listener) {
        super(Method.GET, url, listener);
        this.listener = responseListener;
        this.clazz = clazz;
    }


    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            //By default this cache takes the expiry provided by the server response .
            Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
            return Response.success(
                    gson.fromJson(json, clazz), cacheEntry);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    public void makeRequest(Context context, GsonVolleyRequest request, String tag){
        VolleyRequest.getInstance().addRequestToQueue(context,request,tag);
    }
}
