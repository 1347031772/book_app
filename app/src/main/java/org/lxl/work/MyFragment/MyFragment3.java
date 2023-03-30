package org.lxl.work.MyFragment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.lxl.work.R;

public class MyFragment3 extends Fragment {
    public MyFragment3() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_me,null);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences preferences = getActivity().getSharedPreferences("remember_account", getActivity().MODE_PRIVATE);
        String user = preferences.getString("name", "");
        TextView tv = getActivity().findViewById(R.id.tv_账号);
        tv.setText(user);
    }
}
