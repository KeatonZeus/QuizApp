package com.example.leo.quizapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.quizapp.Common.Common;
import com.example.leo.quizapp.Interface.IQuestion;
import com.example.leo.quizapp.Model.CurrentQuestion;
import com.example.leo.quizapp.Model.Question;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment implements IQuestion {

    TextView txt_question_text;
    CheckBox ckb_A,ckb_B,ckb_C,ckb_D;
    FrameLayout layout_image;
    ProgressBar progressBar;

    Question question;
    int questionIndex = -1;
    String countDebug = "countDebug";

    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.fragment_question,container,false);

        // Get question
        questionIndex = getArguments().getInt("index",-1);
        question = Common.questionList.get(questionIndex);

        if (question!=null){
        // View
            layout_image = (FrameLayout)itemView.findViewById(R.id.layout_image);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
            // If有圖片
            if(question.isImageQuestion()){
                ImageView img_question = (ImageView)itemView.findViewById(R.id.img_question);
                Picasso.get().load(question.getQuestionImage()).into(img_question, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),"" + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{
                layout_image.setVisibility(View.GONE);
            }

            txt_question_text = (TextView)itemView.findViewById(R.id.txt_question_text);
            txt_question_text.setText(question.getQuestionText());

            ckb_A = (CheckBox)itemView.findViewById(R.id.ckb_A);
            ckb_A.setText(question.getAnswerA());
            ckb_A.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        Common.selected_values.add(ckb_A.getText().toString()); // isCheck 就加入
                    else
                        Common.selected_values.remove(ckb_A.getText().toString()); // unCheck 就刪除
                }
            });
            ckb_B = (CheckBox)itemView.findViewById(R.id.ckb_B);
            ckb_B.setText(question.getAnswerB());
            ckb_B.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        Common.selected_values.add(ckb_B.getText().toString()); // isCheck 就加入
                    else
                        Common.selected_values.remove(ckb_B.getText().toString()); // unCheck 就刪除
                }
            });
            ckb_C = (CheckBox)itemView.findViewById(R.id.ckb_C);
            ckb_C.setText(question.getAnswerC());
            ckb_C.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        Common.selected_values.add(ckb_C.getText().toString()); // isCheck 就加入
                    else
                        Common.selected_values.remove(ckb_C.getText().toString()); // unCheck 就刪除
                }
            });
            ckb_D = (CheckBox)itemView.findViewById(R.id.ckb_D);
            ckb_D.setText(question.getAnswerD());
            ckb_D.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                        Common.selected_values.add(ckb_D.getText().toString()); // isCheck 就加入
                    else
                        Common.selected_values.remove(ckb_D.getText().toString()); // unCheck 就刪除
                }
            });

        }
        return itemView;
    }

    @Override
    public CurrentQuestion getSelectedAnswer() {
        // This function will return state of answer
        // Right , Wrong , Normal
        CurrentQuestion currentQuestion = new CurrentQuestion(questionIndex,Common.ANSWER_TYPE.NO_ANSWER); // Default is no answer
        StringBuilder result = new StringBuilder();
        if (Common.selected_values.size() > 1){ //複選
            // If multi choice
            // Spilt answer to array
            // Ex: arr[0] = A. New York
            // Ex: arr[1] = B. Paris
            Object[] arrayAnswer = Common.selected_values.toArray();
            for (int i = 0 ; i < arrayAnswer.length; i++){
                if(i < arrayAnswer.length-1)
                    result.append(new StringBuilder(((String)arrayAnswer[i]).substring(0,1)).append(",")); // Take first letter of answer. Ex: arrayAnswer[0] = A. NewYork , we take A to result
                else
                    result.append(new StringBuilder(((String)arrayAnswer[i])).substring(0,1));
            }
        }
        else if (Common.selected_values.size() == 1){ //單選
            // If only one choice
            Object[] arrayAnswer = Common.selected_values.toArray();
            result.append(new StringBuilder(((String)arrayAnswer[0]).substring(0,1)));
        }

        if (question != null){
            // Compare correct answer with user answer
            if(!TextUtils.isEmpty(result)){
                Log.d(countDebug,"result is : "+result.toString());
                if (result.toString().equals(question.getCorrectAnswer())){
                    currentQuestion.setType(Common.ANSWER_TYPE.RIGHT_ANSWER);
                    Log.d(countDebug,"getCorrectAnswer QFragment  RIGHT");
                }
                else{
                    currentQuestion.setType(Common.ANSWER_TYPE.WRONG_ANSWER);
                    Log.d(countDebug,"getCorrectAnswer QFragment  WRONG");
                }
            }
            else
                currentQuestion.setType(Common.ANSWER_TYPE.NO_ANSWER);
        }
        else{
            Toast.makeText(getContext(),"Cannot get question",Toast.LENGTH_SHORT).show();
            currentQuestion.setType(Common.ANSWER_TYPE.NO_ANSWER);
        }
        Common.selected_values.clear(); // Always clear selected_value when compare done

        return currentQuestion;
    }

    @Override
    public void showCorrectAnswer() {
        // Bold correct Answer
        //Pattern: A,B
        String[] correctAnswer = question.getCorrectAnswer().split(",");
        for (String answer : correctAnswer){
            if (answer.equals("A")){
                ckb_A.setTypeface(null,Typeface.BOLD);
                ckb_A.setTextColor(Color.RED);
            }
            else if (answer.equals("B")){
                ckb_B.setTypeface(null,Typeface.BOLD);
                ckb_B.setTextColor(Color.RED);
            }
            else if (answer.equals("C")){
                ckb_C.setTypeface(null,Typeface.BOLD);
                ckb_C.setTextColor(Color.RED);
            }
            else if (answer.equals("D")){
                ckb_D.setTypeface(null,Typeface.BOLD);
                ckb_D.setTextColor(Color.RED);
            }
        }
    }

    @Override
    public void disableAnswer() {
        // Unable checkBox
        ckb_A.setEnabled(false);
        ckb_B.setEnabled(false);
        ckb_C.setEnabled(false);
        ckb_D.setEnabled(false);
    }

    @Override
    public void resetQuestion() {
        // Enable checkBox
        ckb_A.setEnabled(true);
        ckb_B.setEnabled(true);
        ckb_C.setEnabled(true);
        ckb_D.setEnabled(true);

        //Remove all selected checkBox
        ckb_A.setChecked(false);
        ckb_B.setChecked(false);
        ckb_C.setChecked(false);
        ckb_D.setChecked(false);

        // Remove all bold text
        ckb_A.setTypeface(null, Typeface.NORMAL);
        ckb_A.setTextColor(Color.BLACK);
        ckb_B.setTypeface(null, Typeface.NORMAL);
        ckb_B.setTextColor(Color.BLACK);
        ckb_C.setTypeface(null, Typeface.NORMAL);
        ckb_C.setTextColor(Color.BLACK);
        ckb_D.setTypeface(null, Typeface.NORMAL);
        ckb_D.setTextColor(Color.BLACK);
    }
}
