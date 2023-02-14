package com.zkiss.proman.controller;

import com.zkiss.proman.modal.AppUser;
import com.zkiss.proman.modal.DTO.userDTO.UserLoginRequest;
import com.zkiss.proman.modal.DTO.userDTO.UserRegisterRequest;
import com.zkiss.proman.modal.DTO.userDTO.UserUpdateRequest;
import com.zkiss.proman.service.SessionService;
import com.zkiss.proman.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private SessionService sessionService;



    @Autowired
    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    //TODO Consultation about methods not just changing state but returning object
    @PostMapping("/register")
    public ResponseEntity<List<String>> registerUser(@RequestBody UserRegisterRequest userRequest) {
        try{
            userService.registerUser(userRequest);
            return ResponseEntity.ok().build();
        }catch (org.springframework.dao.DataIntegrityViolationException ignore){
            return createResponseEntityWithErrorMessagesForUserRegister(userRequest);
        }
    }

    private ResponseEntity<List<String>> createResponseEntityWithErrorMessagesForUserRegister(UserRegisterRequest userRequest){
       List<String> errorMessage = userService.gatherErrorMessagesForRegisterUser(userRequest);
       return ResponseEntity.status(400).body(errorMessage);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginRequest loginRequest){
        if(userService.login(loginRequest)){
            putUserIdToSession(loginRequest);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(401).body("Wrong E-mail/Password combination");
        }
    }


    private void putUserIdToSession(UserLoginRequest loginRequest){
        Long userId = userService.getIdByEmail(loginRequest.getEmail());
        sessionService.put("userId", userId);
    }

    @GetMapping("/login/guest")
    public ResponseEntity<String> loginGuest(){
        if(sessionService.get("userId") == null){
            putGuestIdToSession();
            return ResponseEntity.ok().build();
        }else {
           return ResponseEntity.status(400).body("Already logged in");
        }
    }

    private void putGuestIdToSession(){
        sessionService.put("userId",-1L);
    }


    @GetMapping("/logout")
    public void userLogout(){
        sessionService.clear();
    }

    //TODO figure out the secure way
    @PutMapping("/update")
    public void userUpdate(@RequestBody AppUser user){
        user.setId(sessionService.get("userId"));
        userService.updateUser(user);
    }
}