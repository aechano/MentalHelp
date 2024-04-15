package com.example.mentalhelp.Form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalhelp.HelperFunctions.PhoneInterface;
import com.example.mentalhelp.R;
import com.google.android.material.button.MaterialButton;

public class RegistrationForm extends AppCompatActivity {

    private EditText usernameInput, passwordInput, ConfirmPasswordInput;
    private TextView bottomPrompt, BottomPromptButton;
    private MaterialButton submit_button;
    private AccountMode accountMode = AccountMode.signIn;

    private enum AccountMode{
        signIn, register

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        // Set the status bar color to #2a07df
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.deluge)); // Create a color resource

        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        ConfirmPasswordInput = findViewById(R.id.confirm_password_input);
        submit_button = findViewById(R.id.login_button);
        bottomPrompt = findViewById(R.id.bottom_prompt);
        BottomPromptButton = findViewById(R.id.bottom_prompt_button);

        submit_button.setOnClickListener(view -> {
            PhoneInterface.hideKeyboard(RegistrationForm.this);
            if (accountMode == AccountMode.register){
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = ConfirmPasswordInput.getText().toString();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Username is too short!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (username.matches(".*[.$\\[\\]#/,].*")) {
                    Toast.makeText(getApplicationContext(), "Username cannot contain the following characters: .,$[]#/", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password is too short!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Add for database
            }else if(accountMode == AccountMode.signIn){
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                username = username.replaceAll("[.$\\[\\]#/]", "");
                //Add function for database here
            }
        });

        BottomPromptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accountMode == AccountMode.signIn) {
                    accountMode = AccountMode.register;
                    submit_button.setText("Sign Up");
                    bottomPrompt.setText("Already have an account?");
                    BottomPromptButton.setText("Sign In");
                    ConfirmPasswordInput.setVisibility(View.VISIBLE);
                } else if (accountMode == AccountMode.register) {
                    accountMode = AccountMode.signIn;
                    submit_button.setText("Sign In");
                    bottomPrompt.setText("Don't have an account?");
                    BottomPromptButton.setText("Sign Up");
                    ConfirmPasswordInput.setVisibility(View.GONE);
                }

            }
        });



    }
}