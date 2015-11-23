package com.jsphdev.entities.model;

/**
 * Created by vikramn on 11/13/15.
 */

public class Profile {
    private String profilePicture;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNo;
    private int identifier;

    public String getFirstName() {
        return firstName;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Profile[firstName="+this.firstName+",lastName="
                +this.lastName+",address="+this.address+",profilepic="+this.profilePicture+","
                +"email="+this.email+",phoneno="+this.phoneNo+"]";
    }

}
