package com.example.leo.quizapp.Common;

import android.os.CountDownTimer;
import android.widget.CheckBox;

import com.example.leo.quizapp.Model.Category;
import com.example.leo.quizapp.Model.CurrentQuestion;
import com.example.leo.quizapp.Model.Question;
import com.example.leo.quizapp.QuestionFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Common {
    public static final int TOTAL_TIME = 20*60*1000; // 20min
    public static List<Question> questionList = new ArrayList<Question>();
    public static List<CurrentQuestion> answerSheetList = new ArrayList<CurrentQuestion>();
    public static Category selectedCategory = new Category();

    public static CountDownTimer countDownTimer;
    public static int right_answer_count = 0;
    public static int wrong_answer_count = 0;
    public static ArrayList<QuestionFragment> fragmentsList = new ArrayList<>();
    public static TreeSet<String> selected_values = new TreeSet<>();

    public enum ANSWER_TYPE{
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }
}
