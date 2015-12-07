package com.jsphdev.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jsphdev.entities.model.Profile;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.utils.UserUtils;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Workspace.get_instance().setCurrentContext(getApplicationContext());

        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String emailId;
                        String password;
                        try {
                            String credentials = getLoginCredentials(v);
                            String credentialsParts[] = credentials.split("::");
                            emailId = credentialsParts[0];
                            password = credentialsParts[1];
                            Log.d("Login_EmailId", emailId);
                            Log.d("Login_Password", password);
                            if (Workspace.get_instance().isOnline()) {
                                System.out.println("Online");
                                UserUtils.get_instance().verifyUser(emailId, password);
                            }
                            else {
                                System.out.println("Offline");
                                Student student = new Student();
                                Workspace.get_instance().setCurrentUser(student);
                                boolean isUserVerified = UserUtils.get_instance().verifyUserLocal(emailId, password);
                                if (isUserVerified) {
                                    Workspace.get_instance().getCurrentUser().setProfile(UserUtils.get_instance().getProfile(student.getIdentifier(), getApplicationContext()));
                                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Local login failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
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
                        if(!Workspace.get_instance().isOnline())
                            Toast.makeText(getBaseContext(), "Registeration only allowed in online mode", Toast.LENGTH_LONG).show();
                        else{
                            Intent intent = new Intent(v.getContext(), RegistrationPage.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        ToggleButton toggleOffline = (ToggleButton) findViewById(R.id.offlineToggle);
        toggleOffline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Workspace.get_instance().setIsOnline(true);
                } else {
                    Workspace.get_instance().setIsOnline(false);
                }
            }
        });
    }

    public String getLoginCredentials(View v) throws InvalidInputException {

        String input;
        String credentials;

        EditText givenLoginEmailId = (EditText) findViewById(R.id.loginEmail);
        input = givenLoginEmailId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLoginEmailId.setError("Invalid input");
            throw new InvalidInputException("Invalid username input");
        }
        else
            credentials = input;

        credentials += "::";

        EditText givenLoginPassword = (EditText) findViewById(R.id.loginPassword);
        input = givenLoginPassword.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLoginPassword.setError("Invalid input");
            throw new InvalidInputException("Invalid password input");
        }
        else
            credentials += input;

        return credentials;
    }
}
