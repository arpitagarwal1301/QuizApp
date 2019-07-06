package com.agarwal.arpit.quizapp;

import android.app.Application;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

import static android.util.Log.INFO;


public class QuizApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else {
            Timber.plant(new CustomLogTree());
        }
    }

    private static final class CustomLogTree extends Timber.Tree{



        @Override
        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
            if (priority > INFO){
                //send data to crashlytics
            }
        }

    }
}
