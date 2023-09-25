package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.User;
import com.datasoft.PCSUserCreationAuthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> add(@RequestBody User user) throws IOException, NoSuchAlgorithmException {
        return userService.add(user);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<User> list(){
        return userService.list();
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){ return userService.getUserById(id); }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> edit(@RequestBody User user) throws IOException, NoSuchAlgorithmException {
        return userService.edit(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) throws IOException { return userService.delete(id); }

}
