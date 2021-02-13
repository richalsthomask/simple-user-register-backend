/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register_app.demo.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author richa
 */
@Document(collection="Userdata")
public class Userdata {
    
    @Id
    String username;
    String name;
    private int password;
    
    public void setusername(String username)
    {
        this.username=username;
    }
    
    public String getusername()
    {
        return this.username;
    }
    
    public void setname(String firstname)
    {
        this.name=firstname;
    }
    
    public String getname()
    {
        return this.name;
    }
    
    public void setpassword(String temp_password)
    {
        this.password=temp_password.hashCode();
    }
    
    public Boolean testpassword(String temp_password)
    {
        return this.password==temp_password.hashCode();
            
    }
    
    
}
