package com.agarwal.arpit.quizapp.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyRequest  {

    private static VolleyRequest mInstance = new VolleyRequest();
    private RequestQueue mRequestQueue;

    public static VolleyRequest getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(Context context){
        return mRequestQueue ==  null ? mRequestQueue = Volley.newRequestQueue(context) : mRequestQueue;
    }

    public void addRequestToQueue(Context context, Request request,String requestTag){
        request.setTag(TextUtils.isEmpty(requestTag) ? context.getClass().getSimpleName() : requestTag);
        getRequestQueue(context).add(request);
    }

    public void cancelPendingRequest(Context context,String tag) {
        VolleyRequest.getInstance().getRequestQueue(context).cancelAll(tag);
    }
}
