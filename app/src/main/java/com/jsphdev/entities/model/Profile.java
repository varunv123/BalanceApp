package com.jsphdev.entities.model;

/**
 * Created by vikramn on 11/13/15.
 */

public class Profile {
    private String profilePicture;
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String phoneNo;
    private String andrewId;

    public Profile(){

    }

    public Profile(String firstName,String lastName,String department,String email,String phoneNo,int identifier){
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.phoneNo = phoneNo;
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

<<<<<<< HEAD
    public String getAndrewId() {
        return andrewId;
=======
    @Override
    public String toString() {
        return "ProfileActivity[firstName="+this.firstName+",lastName="
                +this.lastName+",department="+this.department+",profilepic="+this.profilePicture+","
                +"email="+this.email+",phoneno="+this.phoneNo+"]";
>>>>>>> f98046478d0899d0f691b4d495580d4a6c384800
    }

    public void setAndrewId(String andrewId) {
        this.andrewId = andrewId;
    }
}
