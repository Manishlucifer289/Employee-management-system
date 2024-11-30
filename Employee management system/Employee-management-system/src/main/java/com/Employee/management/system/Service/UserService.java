package com.Employee.management.system.Service;

import com.Employee.management.system.Entity.User;
import com.Employee.management.system.Payload.Logindto;
import com.Employee.management.system.Payload.Userdto;

public interface UserService {
   public User saveuser(Userdto userdto);

   public String verifylogin(Logindto logindto);
}
