package com.codespurt.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codespurt.eventbus.fragment.SampleFragment;
import com.codespurt.eventbus.model.SamplePojo;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.et_activity);
        button = (Button) findViewById(R.id.btn_activity);
        textView = (TextView) findViewById(R.id.tv_activity);

        // add fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new SampleFragment()).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity:
                String data = editText.getText().toString().trim();
                if (TextUtils.isEmpty(data)) {
                    Toast.makeText(this, "Enter some data to show in fragment", Toast.LENGTH_SHORT).show();
                } else {
                    // set data in pojo
                    SamplePojo.ActivityToFragmentData pojo = new SamplePojo.ActivityToFragmentData();
                    pojo.setMessage(data);
                    GlobalBus.getBus().post(pojo);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(SamplePojo.FragmentToActivityData pojo) {
        textView.setText("Data from fragment: " + pojo.getMessage());
    }

    @Override
    protected void onPause() {
        super.onPause();
        button.setOnClickListener(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}