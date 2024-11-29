package com.example.File_Hidding_Project.service;

import com.example.File_Hidding_Project.dao.UserDAO;
import com.example.File_Hidding_Project.model.User;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user){
        try {
            if (UserDAO.isExists(user.getEmail())){
                return 0;
            } else {
                return UserDAO.saveUser(user);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
