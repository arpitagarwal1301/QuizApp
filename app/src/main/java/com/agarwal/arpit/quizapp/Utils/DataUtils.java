package com.agarwal.arpit.quizapp.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class DataUtils {

    public static String getResponseData(Context context,String inFile) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}
