package com.jsphdev.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.utils.UserUtils;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Workspace.get_instance().setCurrentContext(getApplicationContext());

        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //get input from user
                        String emailId;
                        String password;
                        try {
                            String credentials = getLoginCredentials(v);
                            String credentialsParts[] = credentials.split("::");
                            emailId = credentialsParts[0];
                            password = credentialsParts[1];
                            Log.d("Login_EmailId", emailId);
                            Log.d("Login_Password", password);
                            UserUtils.get_instance().verifyUser(emailId, password);
                        } catch (Exception e) {
                            Log.d("LoginException", e.getMessage());
                        }

                    }
                }
        );

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //go to Registration page
                        Intent intent = new Intent(v.getContext(),RegistrationPage.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public String getLoginCredentials(View v) throws InvalidInputException {

        String input;
        String credentials;

        EditText givenLoginEmailId = (EditText) findViewById(R.id.loginEmail);
        input = givenLoginEmailId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLoginEmailId.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            credentials = input;

        credentials += "::";

        EditText givenLoginPassword = (EditText) findViewById(R.id.loginPassword);
        input = givenLoginPassword.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLoginPassword.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            credentials += input;

        return credentials;
    }
}
