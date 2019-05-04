package com.example.leo.quizapp.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.leo.quizapp.QuestionFragment;

import java.util.List;

public class QuestionFragmentAdapter extends FragmentPagerAdapter {

    Context context;
    List<QuestionFragment> questionFragmentList;

    public QuestionFragmentAdapter(FragmentManager fm, Context context, List<QuestionFragment> questionFragmentList) {
        super(fm);
        this.context = context;
        this.questionFragmentList = questionFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return questionFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return questionFragmentList.size();
    }

    // Ctrl+O

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return new StringBuilder("Question  ").append(position+1).toString();
    }
}
