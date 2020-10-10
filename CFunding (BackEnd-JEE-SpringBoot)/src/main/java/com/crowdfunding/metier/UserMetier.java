package com.crowdfunding.metier;

import com.crowdfunding.entities.User;

import java.util.List;

public interface UserMetier {

    public User saveUser(User u);
    public List<User> listUser();
    public User updateUser(User u);
    public User getUserByEmail(String email);
    public User getUserById(int id);
    public User changeState(User us);

}
