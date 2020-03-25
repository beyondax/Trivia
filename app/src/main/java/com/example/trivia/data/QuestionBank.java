package com.example.trivia.data;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.trivia.controller.AppController.TAG;

public class QuestionBank {

    ArrayList<Question> mQuestions = new ArrayList<>();
    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(final AnswerListAsyncResponse callback) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        Question question = new Question(response.getJSONArray(i).get(0).toString(), response.getJSONArray(i).getBoolean(1));
                        mQuestions.add(question);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (callback != null) callback.processFinished(mQuestions);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return mQuestions;
    }

}
