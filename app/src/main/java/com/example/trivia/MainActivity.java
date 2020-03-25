package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trivia.data.AnswerListAsyncResponse;
import com.example.trivia.data.QuestionBank;
import com.example.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private QuestionBank mQuestionBank;

    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private TextView mTotalQuestionsTextView;
    private TextView mCurrentQuestionTextView;

    private Button mTrueButton;
    private Button mFalseButton;

    private int mScore;
    private int mTotalQuestions;

    private List<Question> mQuestions;

    private int mQuestionIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mScoreTextView = findViewById(R.id.score_text_view);
        mTotalQuestionsTextView = findViewById(R.id.total_questions_text_view);
        mCurrentQuestionTextView = findViewById(R.id.current_question_text_view);




        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mQuestions.get(mQuestionIndex).isTrue()) {
                    mScore = mScore + 100;


                }
                updateQuestion();
                updateScore();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mQuestions.get(mQuestionIndex).isTrue()) {
                    mScore = mScore - 100;

                }
                updateQuestion();
                updateScore();
            }
        });


        mQuestionBank = new QuestionBank();
        mQuestions = mQuestionBank.getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                mQuestionTextView.setText(questionArrayList.get(mQuestionIndex).getQuestion());
                mTotalQuestionsTextView.setText(" " + mQuestions.size());
            }
        });

    }

    private void updateScore() {
        mScoreTextView.setText(" " + mScore);
    }


    private void updateQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % mQuestions.size();
        mQuestionTextView.setText(mQuestions.get(mQuestionIndex).getQuestion());
        mCurrentQuestionTextView.setText(mQuestionIndex + " ");

    }



}
