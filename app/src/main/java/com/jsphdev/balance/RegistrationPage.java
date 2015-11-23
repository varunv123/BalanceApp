package com.jsphdev.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jsphdev.exception.InvalidInputException;

public class RegistrationPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //get input from user
                        try {
                            getRegistrationCredentials(v);
                            Intent intent = new Intent(v.getContext(), Profile.class);
                            startActivity(intent);
                        } catch (InvalidInputException e) {
                            Log.d("RegisterException", e.getMessage());
                        }

                    }
                }
        );
    }

    public void getRegistrationCredentials(View v) throws InvalidInputException {
        String firstName;
        String lastName;
        String andrewId;
        String emailId;
        String password;

        String input;

        EditText givenFirstName = (EditText) findViewById(R.id.RegisterfirstName);
        input = givenFirstName.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenFirstName.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            firstName = input;

        EditText givenLastName = (EditText) findViewById(R.id.RegisterLastName);
        input = givenLastName.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLastName.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            lastName = input;

        EditText givenAndrewId = (EditText) findViewById(R.id.RegisterAndrewId);
        input = givenAndrewId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenAndrewId.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            andrewId = input;

        EditText givenEmailId = (EditText) findViewById(R.id.RegisterEmailId);
        input = givenEmailId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenEmailId.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            emailId = input;

        EditText givenPassword = (EditText) findViewById(R.id.RegisterPassword);
        input = givenPassword.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            password = input;
    }

}
