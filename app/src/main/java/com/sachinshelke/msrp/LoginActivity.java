package com.sachinshelke.msrp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sachinshelke.msrp.core.MsrpSharedPref;
import com.sachinshelke.msrp.db.wrapper.UserWrapper;

public class LoginActivity extends AppCompatActivity {


    EditText etUsername, etPassword;

    UserWrapper userWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_Password);
        userWrapper = new UserWrapper(this);

    }

    public void login(View view) {


        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();


        if (isValidInput(username, password)) {


            boolean dbValidation = userWrapper.validateUser(username, password);
            if (dbValidation) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

                MsrpSharedPref.addLoggedUser(username);
                Intent intent = new Intent(this, LandingActivity.class);
                startActivity(intent);
                finish();


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error!!!")
                        .setMessage("Username or Password invalid")
                        .setPositiveButton("Ok", null);

                builder.create().show();
            }
        } else {

        }

    }


    private boolean isValidInput(String username, String password) {


        boolean isValidate = true;

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Enter Username");
            isValidate = false;
        } else if (username.length() < 5) {
            etUsername.setError("Username should be min 6 letter");
            isValidate = false;
        } else if (username.length() > 15) {
            etUsername.setError("Username should be max 15 letter");
            isValidate = false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter Password");
            isValidate = false;
        } else if (password.length() < 5) {
            etPassword.setError("Password should be min 6 letter");
            isValidate = false;
        }


        return isValidate;
    }

}
