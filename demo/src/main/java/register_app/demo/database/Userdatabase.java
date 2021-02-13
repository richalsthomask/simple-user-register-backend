/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register_app.demo.database;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author richa
 */
public interface Userdatabase extends MongoRepository<Userdata,String>{
    
}
