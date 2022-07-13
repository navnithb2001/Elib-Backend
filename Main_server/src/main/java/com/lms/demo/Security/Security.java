package com.lms.demo.Security;



import com.lms.demo.data.model.Roles;
import com.lms.demo.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Security {

    @Autowired
    private UserRepository userRepository;


    public void authorizePath(Long userid, Roles role) throws IllegalAccessException {
        if(!(userRepository.getById(userid).getRole() == role)){
            throw new IllegalAccessException("No authorization");
        }
    }
}
