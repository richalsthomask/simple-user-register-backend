/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register_app.demo;

import register_app.demo.database.Userdata;
import register_app.demo.database.Userdatabase;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author richals
 */

@RestController
@RequestMapping(value="/")

public class user_enter {
    
    @Autowired
    private Userdatabase userdatabase;
    
    @PostMapping("/registeruser")
    public String registeruser(@RequestBody Map<String,String> userdata,@RequestHeader String password)
    {
        Optional<Userdata> user;
        
        if(password.equals("")||((String)userdata.get("username")).equals("")||((String)userdata.get("name")).equals(""))
            return "username,name ans password are mandatory";
        
        user=userdatabase.findById((String)userdata.get("username"));
        if(user.isPresent())
            return "username already exist";
        
        Userdata user1=new Userdata();
        user1.setusername((String)userdata.get("username"));
        user1.setname((String)userdata.get("name"));
        user1.setpassword(password);
        
        userdatabase.save(user1);
        
        return "user is successfully created";
    }
    
    @GetMapping(value="/login")
    public Boolean login(@RequestHeader String username,@RequestHeader String password)
    {
        
    
            //i didn't return a key to user after loging in .
            //I thought that would be going outside what you asked.
            //Because i already started working on this a bit late ,I'm just going to return a boolean true or false indicating successfull loging in or not .
            //(well within what you asked.but very unorthadox from standerd practices as i understand).
            //I used a key system in my other NutrieNX project if it makes you fell better.
        
        Optional<Userdata> user;
    
        user=userdatabase.findById(username);
        
        if(user.isEmpty())
            return false;
        
        return user.get().testpassword(password);
    }
    
    @GetMapping (value="/resetpassword")
    public String resetpassword(@RequestHeader String username,@RequestHeader String password,@RequestHeader String newpassword)
    {
        
        Optional<Userdata> user;
        
        user=userdatabase.findById(username);
        
        if(user.isEmpty())
             return "username or password is incorrect";
        
         if(! user.get().testpassword(password))
            return "username or password is incorrect";
         
         user.get().setpassword(newpassword);
         
         return "password successfully changed";
        
    }
}
