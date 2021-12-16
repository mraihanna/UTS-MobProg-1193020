package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText edtPass, edtEmail;

    private UserPreference mUserPreference;
    private UserModel userModel;

    public final static String EXTRA_RESULT = "extra_result";
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik";
    private final String FIELD_IS_NOT_VALID = "Email tidak valid";
    public static final int RESULT_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        edtEmail = findViewById(R.id.email_login);
        edtPass = findViewById(R.id.pass_login);

        mUserPreference = new UserPreference(this);
        showPreferenceInForm();

        Button button = findViewById(R.id.button);
        Button login = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra(RegisterActivity.EXTRA_TYPE_FORM,
                        RegisterActivity.TYPE_ADD);
                intent.putExtra("USER", userModel);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    edtEmail.setError(FIELD_REQUIRED);
                    return;
                }
                if (!isValidEmail(email)) {
                    edtEmail.setError(FIELD_IS_NOT_VALID);
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    edtPass.setError(FIELD_REQUIRED);
                    return;
                }
                if (email.equals(userModel.getEmail()) && pass.equals(userModel.getPass())) {
                    Intent resultIntent = new Intent(LoginActivity.this,
                            ProfileActivity.class);
                    resultIntent.putExtra(EXTRA_RESULT, userModel);
                    setResult(RESULT_CODE, resultIntent);
                    startActivity(resultIntent);
                } else{
                    Toast.makeText(LoginActivity.this, "Username or password wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showPreferenceInForm() {
        userModel = mUserPreference.getUser();
        populateView(userModel);
    }

    private void populateView(UserModel userModel) {
        edtEmail.setText(userModel.getEmail().isEmpty() ? "Tidak Ada" :
                userModel.getEmail());
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}