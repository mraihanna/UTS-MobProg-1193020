package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements
        View.OnClickListener {

    private EditText edtName, edtEmail, edtPhone, edtPass, edtHobi,edtUsername, edtAlamat;
    private RadioGroup rgJenkel;
    private RadioButton rbLaki, rbPerempuan;
    private Button btnSave;

    public static final String EXTRA_TYPE_FORM = "extra_type_form";
    public final static String EXTRA_RESULT = "extra_result";
    public static final int RESULT_CODE = 101;

    public static final int TYPE_ADD = 1;
    public static final int TYPE_EDIT = 2;

    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik";
    private final String FIELD_IS_NOT_VALID = "Email tidak valid";

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edt_name);
        edtUsername = findViewById(R.id.edt_username);
        edtPass = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_telpon);
        rgJenkel = findViewById(R.id.rg_jenkel);
        rbLaki = findViewById(R.id.rb_laki);
        rbPerempuan = findViewById(R.id.rb_perempuan);
        edtHobi = findViewById(R.id.edt_hobi); //in yg dari xml
        edtAlamat = findViewById(R.id.edt_alamat);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        userModel = getIntent().getParcelableExtra("USER");
        int formType = getIntent().getIntExtra(EXTRA_TYPE_FORM, 0);

        String actionBarTitle = "";
        String btnTitle = "";

        switch (formType) {
            case TYPE_ADD:
                actionBarTitle = "Register";
                btnTitle = "Simpan";
                break;
            case TYPE_EDIT:
                actionBarTitle = "Ubah";
                btnTitle = "Update";
                showPreferenceInForm();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSave.setText(btnTitle);

    }

    private void showPreferenceInForm() {
        edtName.setText(userModel.getName());
        edtUsername.setText(userModel.getUsername());
        edtEmail.setText(userModel.getEmail());
        edtPass.setText(userModel.getPass());
        edtPhone.setText(userModel.getTelpon());
        edtHobi.setText(userModel.getHobi());
        edtAlamat.setText(userModel.getAlamat());
        if (userModel.isJenkel()) {
            rbLaki.setChecked(true);
        } else {
            rbPerempuan.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            String name = edtName.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String pass = edtPass.getText().toString().trim();
            String phoneNo = edtPhone.getText().toString().trim();
            String alamat = edtAlamat.getText().toString().trim();
            boolean jenkel = rgJenkel.getCheckedRadioButtonId() == R.id.rb_laki;
            String hobi = edtHobi.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                edtUsername.setError(FIELD_REQUIRED);
                return;
            }
            if (TextUtils.isEmpty(name)) {
                edtName.setError(FIELD_REQUIRED);
                return;
            }
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
            if (TextUtils.isEmpty(alamat)) {
                edtAlamat.setError(FIELD_REQUIRED);
                return;
            }
            if (TextUtils.isEmpty(phoneNo)) {
                edtPhone.setError(FIELD_REQUIRED);
                return;
            }
            if (!TextUtils.isDigitsOnly(phoneNo)) {
                edtPhone.setError(FIELD_DIGIT_ONLY);
                return;
            }
            if (TextUtils.isEmpty(hobi)) {
                edtHobi.setError(FIELD_REQUIRED);
                return;
            }

            saveUser(username, name, email, pass, phoneNo, jenkel, hobi, alamat);

            Intent resultIntent = new Intent(RegisterActivity.this,
                    LoginActivity.class);
            resultIntent.putExtra(EXTRA_RESULT, userModel);
            setResult(RESULT_CODE, resultIntent);
            startActivity(resultIntent);
            //finish();
        }
    }
    /*
    Save data ke dalam preferences
    */
    private void saveUser(String username, String name, String email, String pass, String phoneNo,
                          boolean jenkel, String hobi, String alamat) {
        com.example.splashscreen.UserPreference userPreference = new com.example.splashscreen.UserPreference(this);
        userModel = new UserModel();
        String nama1= name;

        userModel.setName(name);
        userModel.setUsername(username);
        userModel.setEmail(email);
        userModel.setPass(pass);
        userModel.setTelpon(phoneNo);
        userModel.setJenkel(jenkel);
        userModel.setHobi(hobi);
        userModel.setAlamat(alamat);

        userPreference.setUser(userModel);
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }
    /**
     * Cek apakah emailnya valid
     *
     * @param email inputan email
     * @return true jika email valid
     */
    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
