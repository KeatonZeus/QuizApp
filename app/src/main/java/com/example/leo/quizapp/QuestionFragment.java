package com.example.leo.quizapp;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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
import com.example.leo.quizapp.Model.Question;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    TextView txt_question_text;
    CheckBox ckb_A,ckb_B,ckb_C,ckb_D;
    FrameLayout layout_image;
    ProgressBar progressBar;

    Question question;
    int questionIndex = -1;

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

}
