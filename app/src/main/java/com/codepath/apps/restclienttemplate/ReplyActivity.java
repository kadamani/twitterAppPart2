package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

public class ReplyActivity extends AppCompatActivity {


    private final int REQUEST_CODE = 20;
    private TextView sms_count;
    private EditText etComposeo;
    Tweet tweet;

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

//    public void onReply(View v) {
//
//        Log.d("Value: " , etComposeo.getText().toString());
//        Log.d("screen name ", tweet.user.screenName);
//
//        TwitterApp.getRestClient().replyTweet(etComposeo.getText().toString(), tweet.user.screenName, tweet.uid, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    tweet = Tweet.fromJSON(response);
//                    Intent i = new Intent(ReplyActivity.this, TimelineActivity.class);
//                    i.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
//
//                    setResult(RESULT_OK, i); // set result code and bundle data for response
//                    finish(); // closes the activity, pass data to parent
//
//
//                } catch (JSONException e) {
//                    Log.e("Reply Activity", "Error forming new tweet", e);
//                }
//                // super.onSuccess(statusCode, headers, response);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);
//            }
//        });


    }
