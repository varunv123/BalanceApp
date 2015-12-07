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

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Student;
import com.jsphdev.entities.model.Workspace;
import com.jsphdev.exception.InvalidInputException;
import com.jsphdev.utils.UserUtils;

public class RegistrationPage extends Activity {

    private String firstName;
    private String lastName;
    private String andrewId;
    private String department;
    private String emailId;
    private String password;
    private String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        Button registerButton = (Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            getRegistrationCredentials(v);
                            User user = UserUtils.get_instance().createUser(firstName, lastName, andrewId, department, emailId, phoneNo);
                            Workspace.get_instance().setCurrentUser(user);
                            UserUtils.get_instance().registerUser(emailId, password);
                        } catch (Exception e) {
                            Log.d("RegisterException", e.getMessage());
                        }
                    }
                }
        );
    }

    public void getRegistrationCredentials(View v) throws InvalidInputException {
        String input;
        EditText givenFirstName = (EditText) findViewById(R.id.RegisterfirstName);
        input = givenFirstName.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenFirstName.setError("Invalid input");
            throw new InvalidInputException("Invalid First Name input");
        }
        else
            firstName = input;

        EditText givenLastName = (EditText) findViewById(R.id.RegisterLastName);
        input = givenLastName.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenLastName.setError("Invalid input");
            throw new InvalidInputException("Invalid Last Name input");
        }
        else
            lastName = input;

        EditText givenAndrewId = (EditText) findViewById(R.id.RegisterAndrewId);
        input = givenAndrewId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenAndrewId.setError("Invalid input");
            throw new InvalidInputException("Invalid andrew id input");
        }
        else
            andrewId = input;

        EditText givenEmailId = (EditText) findViewById(R.id.RegisterEmailId);
        input = givenEmailId.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenEmailId.setError("Invalid input");
            throw new InvalidInputException("Invalid Email input");
        }
        else
            emailId = input;

        EditText givenPassword = (EditText) findViewById(R.id.RegisterPassword);
        input = givenPassword.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException("Invalid password input");
        }
        else
            password = input;

        EditText givenDepartment = (EditText) findViewById(R.id.RegisterDepartment);
        input = givenDepartment.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException("Invalid department input");
        }
        else
            department = input;

        EditText givenPhoneNo = (EditText) findViewById(R.id.RegisterPhoneno);
        input = givenPhoneNo.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException("Invalid Phone no input");
        }
        else
            phoneNo = input;
    }

}
