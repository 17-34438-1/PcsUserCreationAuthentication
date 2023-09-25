package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.URL;
import com.datasoft.PCSUserCreationAuthentication.Service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/url")
public class URLController {

    @Autowired
    private URLService URLService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> addUserEnlistUrl(@RequestBody URL URL) throws IOException {
        return URLService.add(URL);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<URL> userModuleList(){ return URLService.list(); }

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public @ResponseBody List<URL> userModuleList(){ return URLService.list(); }

    @RequestMapping(value = "/getURLById/{id}", method = RequestMethod.GET)
    public ResponseEntity<URL> getURLById(@PathVariable("id") Long id){
        return URLService.getUserEnlistURLById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> updateUserModule(@RequestBody URL URL) throws IOException{
        return URLService.edit(URL);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteUserModule(@PathVariable Long id) throws IOException{
        return URLService.delete(id);
    }

    @RequestMapping(value = "/urlByModule/{module_id}", method = RequestMethod.GET)
    public @ResponseBody List<URL> urlByModule(@PathVariable("module_id") Integer module_id){
        return URLService.urlByModule(module_id);
    }

    @RequestMapping(value = "/urlByRole/{user_role_id}", method = RequestMethod.GET)
    public @ResponseBody List<URL> urlByRole(@PathVariable("user_role_id") Integer user_role_id){
        return URLService.urlByRole(user_role_id);
    }
}
