package com.Employee.management.system.Controller;

import com.Employee.management.system.Entity.User;
import com.Employee.management.system.Payload.Logindto;
import com.Employee.management.system.Payload.TokenResponse;
import com.Employee.management.system.Payload.Userdto;
import com.Employee.management.system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/created")
    public ResponseEntity<?>saveuser(@RequestBody Userdto usedto){
        User saveuser = userService.saveuser(usedto);
        if(saveuser!= null){
            return new ResponseEntity<>("your employee registration is successfully saved", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("something went wrong",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/verifylogin")
    public ResponseEntity<?>VerifyLogin(@RequestBody Logindto logindto){
        String token = userService.verifylogin(logindto);
        if(token!=null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("invalid credentials", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
