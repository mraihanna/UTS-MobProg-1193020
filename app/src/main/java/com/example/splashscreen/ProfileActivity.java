package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
        private UserPreference mUserPreference;
        private UserModel userModel;

        public final static String EXTRA_RESULT = "extra_result";
        public static final int RESULT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        mUserPreference = new UserPreference(this);
        Button profile = findViewById(R.id.btn_detail);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(ProfileActivity.this,
                        DetailActivity.class);
                resultIntent.putExtra(EXTRA_RESULT, userModel);
                setResult(RESULT_CODE, resultIntent);
                startActivity(resultIntent);
            }
        });


    }
}