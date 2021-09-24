package com.example.engineerexercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    private static EditText mEmail;
    private static EditText mPassword;
    private static EditText mRenterPwd;
    private static TextView mCheckPwd;
    private static ImageButton mNextCreateBtn;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String emailInput = mEmail.getText().toString();
            if(!emailInput.trim().matches(emailPattern)){
                mCheckPwd.setText("Invalid email format");
            } else if(mPassword.getText().toString().isEmpty()){
                mCheckPwd.setText("Invalid password format");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String passwordInput = mPassword.getText().toString();
            String emailInput = mEmail.getText().toString();
            String passwordVal = "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    ".{8,}" +               //at least 8 characters
                    "$";

            if(!mPassword.getText().toString().trim().matches(passwordVal)) {
                mCheckPwd.setText("Passwords don't match!");
            }
            if(!emailInput.isEmpty() && !passwordInput.isEmpty()){
                mNextCreateBtn.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mEmail = findViewById(R.id.email_address_et);
        mPassword = findViewById(R.id.password_et);
        mRenterPwd = findViewById(R.id.repeat_password_et);
        mCheckPwd = findViewById(R.id.check_pwd_tv);
        mNextCreateBtn = findViewById(R.id.next_create_btn);


        mEmail.addTextChangedListener(textWatcher);
        mPassword.addTextChangedListener(textWatcher);
        mRenterPwd.addTextChangedListener(textWatcher);


        mNextCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}