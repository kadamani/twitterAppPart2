package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.fragments.UserTimelineFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;
    Tweet tweet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String screenName;
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        if (tweet != null) {
             screenName = tweet.user.screenName;
        }
        else {
             screenName = getIntent().getStringExtra("screen_name");
        }
        // create the user fragment
        UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(screenName);
        // display the user timeline fragment inside the container (dynamically)
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // make all changes here
        ft.replace(R.id.flContainer, userTimelineFragment);
        // commit
        ft.commit();


        client = TwitterApp.getRestClient();

        client.getUserInfo (new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // deserialize the User object
                try {
                    if (tweet == null) {
                        User user = User.fromJSON(response);
                        // set the title of the ActionBar based on the user information
                        // populate the user headline
                        populateUserHeadline(user);
                    }
                    else {
                        populateUserHeadline(tweet.user);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void populateUserHeadline(User user) {
        TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);

        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

        tvName.setText(user.name);
        getSupportActionBar().setTitle(user.screenName);

         tvTagline.setText(user.tagLine);
         tvFollowers.setText(user.followersCount + " Followers");
         tvFollowing.setText(user.followingCount + " Following");

        // load profile image
        Glide.with(this).load(user.profileImageUrl).into(ivProfileImage);
    }
}
