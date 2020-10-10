package com.crowdfunding.metier;

import com.crowdfunding.dao.UserRepository;
import com.crowdfunding.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserMetierImpl implements UserMetier {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository ;
    @Override
    public User saveUser(User u) {
        if (getUserByEmail(u.getEmailUser())!=null){  return null; };
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User u) {
        Optional<User> user = userRepository.findById(u.getIdUser());
        user.get().setActivation(u.isActivation());
        user.get().setCommantaires(u.getCommantaires());
        user.get().setNomUser(u.getNomUser());
        user.get().setPreUser(u.getPreUser());
        user.get().setEmailUser(u.getEmailUser());
        user.get().setProblems(u.getProblems());
        user.get().setTeleUser(u.getTeleUser());
        user.get().setProjets(u.getProjets());
        return saveUser(user.get());
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public User changeState(User us) {
        Optional<User> user = userRepository.findById(us.getIdUser());
        System.err.println("----BEFORE----"+user.get());
        user.get().setActivation(us.isActivation());
        userRepository.save(user.get());
//        System.err.println("----AFTER----"+user.get());


        return user.get();
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users =userRepository.findAll();
        for(int i = 0;i<users.size();i++){
            System.err.println(users.get(i));
            if (users.get(i).getEmailUser().equals(email)){
                return users.get(i);
            }
        }

        return null;
    }
}
