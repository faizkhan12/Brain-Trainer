package com.example.faizkhan.braintrainer;

import android.os.CountDownTimer;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView pointsTextView;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    TextView textView;

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();
        new CountDownTimer(30000,1000){


            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();
        int inCorrectAnswer;
        for(int i = 0;i<4;i++) {
            if(i == locationOfCorrectAnswer) {
                answer.add(a + b);
            }
            else {
                inCorrectAnswer = rand.nextInt(41);
                while(inCorrectAnswer == a + b) {
                    inCorrectAnswer = rand.nextInt(41);
                }
                answer.add(inCorrectAnswer);
            }
        }
        button.setText(Integer.toString(answer.get(0)));
        button2.setText(Integer.toString(answer.get(1)));
        button3.setText(Integer.toString(answer.get(2)));
        button4.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!!");
        }else {
            resultTextView.setText(("Incorrect!!"));
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility((RelativeLayout.VISIBLE));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);

         button = (Button) findViewById(R.id.button);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
         button4 = (Button) findViewById(R.id.button4);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        textView = (TextView) findViewById(R.id.textView);

        playAgain(findViewById(R.id.playAgainButton));

    }
}
