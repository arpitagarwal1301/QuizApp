package com.agarwal.arpit.quizapp.Utils;

import android.content.Context;
import android.widget.Toast;

public class DisplayUtils {

    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
