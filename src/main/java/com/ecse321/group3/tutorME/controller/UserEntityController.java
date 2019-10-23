package com.ecse321.group3.tutorME.controller;

import com.ecse321.group3.tutorME.domain.Course;
import com.ecse321.group3.tutorME.domain.UserEntity;
import com.ecse321.group3.tutorME.service.UserEntityServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class UserEntityController {

    @Autowired
    private UserEntityServiceIF userEntityService;

    @RequestMapping(value = "/api/user", method = POST)
    public ResponseEntity<UserEntity> createUserEntity(@RequestBody UserEntity user){
        if(user == null || user.getEmail() == null || user.getEmail().isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = null;
        try{
            userEntity = userEntityService.createUserEntity(user);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/user", method = GET)
    public ResponseEntity<UserEntity> getUserEntity(@RequestParam String email){
        if(email == null || email.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = null;

        try{
            userEntity = userEntityService.getUserEntity(email);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/user/getall", method = GET)
    public ResponseEntity<List<UserEntity>> getUserEntities(){
        List<UserEntity> users = null;
        try{
            users = userEntityService.getUserEntities();
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = DELETE)
    public ResponseEntity<UserEntity> deleteUserEntity(@RequestParam String email){
        if(email == null || email.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try{
            userEntityService.deleteUserEntity(email);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}
