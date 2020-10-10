package com.crowdfunding.security;

import com.crowdfunding.entities.User;
import com.crowdfunding.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMetier userMetier;
    static int idUser;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u =userMetier.getUserByEmail(username);
        idUser = u.getIdUser();
        if(u==null) throw new UsernameNotFoundException(username);
        List<GrantedAuthority> authorities =new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return new org.springframework.security.core.userdetails.User(u.getEmailUser(),u.getPassword(),authorities);
    }
}
