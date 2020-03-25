package com.example.trivia.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

        public static final String TAG = AppController.class.getSimpleName();
        private static AppController mAppController;
        private RequestQueue mRequestQueue;

        public static synchronized AppController getInstance() {
            if (mAppController == null) {
                mAppController = new AppController();
            }
            return mAppController;
        }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }



}
