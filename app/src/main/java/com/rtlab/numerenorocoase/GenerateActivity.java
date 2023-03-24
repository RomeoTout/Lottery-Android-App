package com.rtlab.numerenorocoase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GenerateActivity extends AppCompatActivity {
    //put it in the menu bar or somewhere else?
    private Button shareBtn;

    //number display textviews
    private TextView fromTextView;
    private TextView toTextView;
    private TextView numberTextView;

    //limits for the generated number
    private int fromInput;
    private int toInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        //Rate app if user hasn't yet
        AppRater.app_launched(this);

        Button generateButton = findViewById(R.id.generateButton);
        Button sixButton = findViewById(R.id.sixButton);
        Button fiveButton = findViewById(R.id.fiveButton);
        Button jokerButton = findViewById(R.id.jokerButton);
        fromTextView = findViewById(R.id.fromInputTextView);
        toTextView = findViewById(R.id.toInputTextView);
        numberTextView = findViewById(R.id.numberTextView);

        //applies a push effect on the generate button
        Effect.buttonEffect(generateButton);
        //best practices
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    fromInput = Integer.parseInt(fromTextView.getText().toString());
                    toInput = Integer.parseInt(toTextView.getText().toString());
                } catch (NumberFormatException e){
                    fromInput = 1;
                    toInput = 40;
                }
                //output generated numbers between chosen limits
                numberTextView.setText(Integer.toString(Randomizer.randomWithRange(fromInput, toInput)));
            }
        });

        Effect.buttonEffect(sixButton);
        //the loto6/49 button. Generates a set of numbers for that game
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberTextView.setText(Randomizer.randomNoCopy(49, 6));
            }
        });

        Effect.buttonEffect(fiveButton);
        //the loto 5/40 button. Generates a set of numbers for that game
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberTextView.setText(Randomizer.randomNoCopy(40, 5));
            }
        });

        Effect.buttonEffect(jokerButton);
        //joker button. Generates a set of numbers for that game
        jokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = Randomizer.randomNoCopy(45, 5);
                String joker = Integer.toString(Randomizer.randomWithRange(1, 20)) + " ";
                numberTextView.setText(result + "_" + joker);
            }
        });

    }
}
