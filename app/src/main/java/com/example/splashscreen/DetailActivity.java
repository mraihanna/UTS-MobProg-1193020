package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements
        View.OnClickListener{

    private TextView tvName, tvPass, tvPhoneNo, tvEmail, tvJenkel, tvHobi, tvUsername, tvAlamat;
    private Button btnSave;
    private UserPreference mUserPreference;
    private final int REQUEST_CODE = 100;

    private boolean isPreferenceEmpty = false;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tv_name);
        tvUsername = findViewById(R.id.tv_username);
        tvPhoneNo = findViewById(R.id.tv_telpon);
        tvEmail = findViewById(R.id.tv_email);
        tvJenkel = findViewById(R.id.tv_jenkel);
        tvHobi = findViewById(R.id.tv_hobi);
        tvPass = findViewById(R.id.tv_pass);
        tvAlamat = findViewById(R.id.tv_alamat);
        btnSave = findViewById(R.id.btn_save);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My User Preference");
        }

        mUserPreference = new UserPreference(this);

        showExistingPreference();
        btnSave.setOnClickListener(this);
    }

    private void showExistingPreference() {
        userModel = mUserPreference.getUser();
        populateView(userModel);
        checkForm(userModel);
    }

    private void populateView(UserModel userModel) {
        tvName.setText(userModel.getName().isEmpty() ? "Tidak Ada" :
                userModel.getName());
        tvUsername.setText(userModel.getUsername().isEmpty() ? "Tidak Ada" :
                userModel.getUsername());
        tvJenkel.setText(userModel.isJenkel() ? "Laki-laki" : "Perempuan");
        tvEmail.setText(userModel.getEmail().isEmpty() ? "Tidak Ada" :
                userModel.getEmail());
        tvPhoneNo.setText(userModel.getTelpon().isEmpty() ? "Tidak Ada" :
                userModel.getTelpon());
        tvHobi.setText(userModel.getHobi().isEmpty() ? "Tidak Ada" :
                userModel.getHobi());
        tvPass.setText(userModel.getPass().isEmpty() ? "Tidak Ada" :
                userModel.getPass());
        tvAlamat.setText(userModel.getAlamat().isEmpty() ? "Tidak Ada" :
                userModel.getAlamat());
    }

    private void checkForm(UserModel userModel) {
        if (!userModel.getName().isEmpty()) {
            btnSave.setText(getString(R.string.change));
            isPreferenceEmpty = false;
        } else {
            btnSave.setText(getString(R.string.save));
            isPreferenceEmpty = true;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            Intent intent = new Intent(DetailActivity.this,
                    RegisterActivity.class);
            if (isPreferenceEmpty) {
                intent.putExtra(RegisterActivity.EXTRA_TYPE_FORM,
                        RegisterActivity.TYPE_ADD);
                intent.putExtra("USER", userModel);
            } else {
                intent.putExtra(RegisterActivity.EXTRA_TYPE_FORM,
                        RegisterActivity.TYPE_EDIT);
                intent.putExtra("USER", userModel);
            }
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RegisterActivity.RESULT_CODE) {
                userModel =
                        data.getParcelableExtra(RegisterActivity.EXTRA_RESULT);
                populateView(userModel);
                checkForm(userModel);
            }
        }
    }
}

