package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class ReplyActivity extends AppCompatActivity {


    private final int REQUEST_CODE = 20;
    private TextView sms_count;
    private EditText etComposeo;

    // TwitterClient client = new TwitterClient(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        sms_count = (TextView) findViewById(R.id.tvChar);
        etComposeo = (EditText) findViewById(R.id.etComposeo);

        final TextWatcher txwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sms_count.setText(String.valueOf(140 - s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        etComposeo.addTextChangedListener(txwatcher);
    }

    }
