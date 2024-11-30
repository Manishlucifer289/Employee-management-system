package com.Employee.management.system.Service.impl;

import com.Employee.management.system.Entity.User;
import com.Employee.management.system.Payload.Logindto;
import com.Employee.management.system.Payload.Userdto;
import com.Employee.management.system.Repository.UserRepository;
import com.Employee.management.system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
  private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Override
    public User saveuser(Userdto userdto) {
        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setUsername(userdto.getUsername());
        user.setPassword(BCrypt.hashpw(userdto.getPassword(),BCrypt.gensalt(10)));
        user.setUserRole(userdto.getUserRole());
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public String verifylogin(Logindto logindto) {
        Optional<User> byUsername = userRepository.findByUsername(logindto.getUsername());
        if(byUsername.isPresent()){
            User user = byUsername.get();
            if(BCrypt.checkpw(logindto.getPassword(),user.getPassword())){
                return jwtService.generateJWTToken(user);
            }
        }

        return null;
    }
}
