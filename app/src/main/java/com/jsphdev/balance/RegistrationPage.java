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
    boolean online = false;

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
                            if (online)
                                UserUtils.get_instance().registerUser(emailId, password, user);
                            else {
                                Student student = new Student();
                                boolean userExists = UserUtils.get_instance().checkUsernameLocal(student, emailId, getApplicationContext());
                                if (!userExists) {
                                    UserUtils.get_instance().registerUser(emailId, password, user, getApplicationContext());
                                    UserUtils.get_instance().getUserId(emailId, user, getApplicationContext());
                                    System.out.println(user.getIdentifier());
                                    UserUtils.get_instance().registerProfile(user, user.getProfile(), getApplicationContext());
                                    Workspace.get_instance().setCurrentUser(user);
                                    Intent intent = new Intent(Workspace.get_instance().getCurrContext(), ProfileActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Local register failed. Username exists.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Log.d("RegisterException", e.getMessage());
                        }

                    }
                }
        );

        ToggleButton toggleOffline = (ToggleButton) findViewById(R.id.offlineToggle);
        toggleOffline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    online = true;
                } else {
                    online = false;
                }
                System.out.println(online);
            }
        });
    }

    public void getRegistrationCredentials(View v) throws InvalidInputException {

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

        EditText givenDepartment = (EditText) findViewById(R.id.RegisterDepartment);
        input = givenDepartment.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            department = input;

        EditText givenPhoneNo = (EditText) findViewById(R.id.RegisterPhoneno);
        input = givenPhoneNo.getText().toString();
        if ((input == null) || input.isEmpty()){
            givenPassword.setError("Invalid input");
            throw new InvalidInputException();
        }
        else
            phoneNo = input;
    }

}
