package com.jsphdev.adapter;

import com.jsphdev.abstrct.User;
import com.jsphdev.entities.model.Student;

/**
 * Created by vikramn on 11/13/15.
 */
public interface IAdmin {

    public void createGroupEvent();
    public boolean blockUser(Student student);
    public boolean deleteUser(Student student);

}
