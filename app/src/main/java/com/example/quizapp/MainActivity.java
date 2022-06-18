package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   private TextView questionText,questionStatus;
   private Button trueBtn,falseBtn;
   private ProgressBar progressBar;
   private int index;
   private int questionMember;
   private int score;
   private Question[] questions = new Question[]{
           new Question(R.string.q1,false),
           new Question(R.string.q2,true),
           new Question(R.string.q3,false),
           new Question(R.string.q4,true),
           new Question(R.string.q5,false),
           new Question(R.string.q6,false),
           new Question(R.string.q7,true),
           new Question(R.string.q8,true),
           new Question(R.string.q9,false),
           new Question(R.string.q10,true),
   };
    private final int INCREMENT_VALUE = (int) Math.ceil( 100/questions.length);
    private final String SCORE_KEY = "score";
    private final String Index_KEY = "index";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        if(savedInstanceState != null){
            score = savedInstanceState.getInt(SCORE_KEY);
            index = savedInstanceState.getInt(Index_KEY);

        }else{
            index = 0;
        }
        questionMember = questions[index].getQuestion();
        questionText.setText(questionMember);
        questionStatus.setText(score + "\t / 10");

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUserAnswer(true);
                displayQuestion();
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateUserAnswer(false);
                displayQuestion();
            }
        });

    }
    private void initWidget(){
        questionText = findViewById(R.id.question_text);
        questionStatus = findViewById(R.id.question_status);
        trueBtn = findViewById(R.id.true_btn);
        falseBtn = findViewById(R.id.false_btn);
        progressBar = findViewById(R.id.progress_par);

    }
    private void displayQuestion(){
        // 1 % 10 = 1
        // 2 % 10 = 2
        // ... 10 % 10 = 0 it will re init the index 0 and reDisplay the questions
        index = (index + 1) % questions.length;
        questionMember = questions[index].getQuestion();
        if(index == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Finish the quiz");
            builder.setMessage("your score is " + score);
            builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();

        }
        if(score >= (int) Math.ceil(questions.length/2)){
            questionStatus.setTextColor(Color.GREEN);
        }else{
            questionStatus.setTextColor(Color.RED);
        }
        questionText.setText(questionMember);
        progressBar.incrementProgressBy(INCREMENT_VALUE);
        questionStatus.setText(score + " \t / 10");



    }
    private void evaluateUserAnswer(boolean answer){
        boolean questionAnswer = questions[index].isAnswer();
        if(answer == questionAnswer){
            score++;
            Toast.makeText(MainActivity.this,R.string.correct_text,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,R.string.incorrect_text,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY,score);
        outState.putInt(Index_KEY,index);
    }
}