package com.agarwal.arpit.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.agarwal.arpit.quizapp.Utils.DisplayUtils;
import com.agarwal.arpit.quizapp.databinding.ActivityMainBinding;
import com.agarwal.arpit.quizapp.databinding.OptionCharLayoutBinding;
import com.agarwal.arpit.quizapp.network.GsonVolleyRequest;
import com.agarwal.arpit.quizapp.response.LogoItem;
import com.agarwal.arpit.quizapp.response.LogoResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.agarwal.arpit.quizapp.Utils.DataUtils.getResponseData;

public class MainActivity extends AppCompatActivity {


    String url = "xyz";
    private static final String URL_TAG = "URL_1";
    private ActivityMainBinding binding;
    private String answerString = "";
    private String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setUpView();
        sendRequest();
    }

    private void setUpView() {
        //initialise any static views

        for (int i = 0; i < 26; i++) {
            View view = getCharaterLayout((char)(i+65));
            binding.optionViewContainer.addView(view);
        }
    }

    private void sendRequest() {

//        GsonVolleyRequest request = new GsonVolleyRequest(url, LogoResponse.class, new Response.Listener<LogoResponse>() {
//            @Override
//            public void onResponse(LogoResponse response) {
//                handleResponse(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                DisplayUtils.showToast(getApplicationContext(),error.getMessage());
//            }
//        });
//
//        request.makeRequest(this,request,URL_TAG);

        Gson gson = new Gson();
        LogoResponse response = gson.fromJson(getResponseData(getApplicationContext(),"logos.json"),LogoResponse.class);
        handleResponse(response);

    }

    private void handleResponse(LogoResponse response) {
        if (response==null || response.getList().size()==0){
            DisplayUtils.showToast(getApplicationContext(),getString(R.string.empty_response_error));
            return;
        }

        List<LogoItem> itemList = response.getList();
        for (LogoItem item :
                itemList) {
            String src = item.getImageUrl();
            name = item.getImageName();

            populateQuestionScreen(src);

        }
    }

    private void populateQuestionScreen(String src) {
        binding.setImageUrl(src);
        binding.setAnswerText("");

        binding.executePendingBindings();
    }

    private View getCharaterLayout(char c){

        OptionCharLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.option_char_layout, null, false);

        binding.setCharacter(String.valueOf(c));
        binding.optionViewCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerString = answerString+c;
                if (answerString.equalsIgnoreCase(name)){
                    DisplayUtils.showToast(getApplicationContext(),"Correct answer");
                }else if (answerString.length()>=name.length()){
                    DisplayUtils.showToast(getApplicationContext(),"Wrong answer");
                }
            }
        });

        return binding.getRoot();

    }


}
