package com.rtlab.numerenorocoase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.play.core.review.ReviewManager;

public class MainActivity extends AppCompatActivity {

    //the 3 buttons in the main menu
    private Button shareBtn;
    private Button generateActivityBtn;
    private Button resultActivityBtn;

    //for sharing the app: title and body of message
    public static final String URL_TO_SHARE = "https://play.google.com/store/apps/details?id=com.rtlab.numerenorocoase";
    public static final String SHARE_MY_APP_MESSAGE = "Numere norocoase: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //go to number generator activity and add push effect to button
        generateActivityBtn = findViewById(R.id.generateActivityButton);
        Effect.buttonEffect(generateActivityBtn);
        generateActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GenerateActivity.class);
                startActivity(intent);
            }
        });

        //go to results activity and add push effect to button
        resultActivityBtn = findViewById(R.id.resultActivityButton);
        Effect.buttonEffect(resultActivityBtn);
        resultActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });



        //share via other apps
        shareBtn = findViewById(R.id.shareButton);
        Effect.buttonEffect(shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, SHARE_MY_APP_MESSAGE);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, URL_TO_SHARE);
                    startActivity(Intent.createChooser(shareIntent, "Share"));
                }catch (Exception e){
                    Log.e("Main Activity", "Error sharing app" + e.getMessage());
                }
            }
        });

    }

}
