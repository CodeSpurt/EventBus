package com.codespurt.eventbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codespurt.eventbus.GlobalBus;
import com.codespurt.eventbus.R;
import com.codespurt.eventbus.model.SamplePojo;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by CodeSpurt on 09-08-2017.
 */

public class SampleFragment extends Fragment implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GlobalBus.getBus().register(this);
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = (EditText) view.findViewById(R.id.et_fragment);
        button = (Button) view.findViewById(R.id.btn_fragment);
        textView = (TextView) view.findViewById(R.id.tv_fragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fragment:
                String data = editText.getText().toString().trim();
                if (TextUtils.isEmpty(data)) {
                    Toast.makeText(getContext(), "Enter some data to show in activity", Toast.LENGTH_SHORT).show();
                } else {
                    // set data in pojo
                    SamplePojo.FragmentToActivityData pojo = new SamplePojo.FragmentToActivityData();
                    pojo.setMessage(data);
                    GlobalBus.getBus().post(pojo);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(SamplePojo.ActivityToFragmentData pojo) {
        textView.setText("Data from activity: " + pojo.getMessage());
    }

    @Override
    public void onPause() {
        super.onPause();
        button.setOnClickListener(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GlobalBus.getBus().unregister(this);
    }
}