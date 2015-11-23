package com.jsphdev.entities.model;

import com.jsphdev.abstrct.Event;
import com.jsphdev.abstrct.User;
import com.jsphdev.adapter.IAdmin;

/**
 * Created by vikramn on 11/13/15.
 */
public class Admin extends User implements IAdmin {


    @Override
    public void createGroupEvent() {

    }

    @Override
    public boolean blockUser(Student student) {
        return false;
    }

    @Override
    public boolean deleteUser(Student student) {
        return false;
    }

    @Override
    public void deleteEvent(Event event) {

    }
}
