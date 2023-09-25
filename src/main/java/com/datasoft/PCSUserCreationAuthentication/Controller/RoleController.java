package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.Role;
import com.datasoft.PCSUserCreationAuthentication.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> add(@RequestBody Role role) throws IOException {
        return roleService.add(role);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Role> list(){
        return roleService.UserRoleList();
    }

    @RequestMapping(value = "/getRoleById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return roleService.getUserRoleById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> edit(@RequestBody Role role) throws IOException{
        return roleService.edit(role);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) throws IOException{
        return roleService.deleteUserRole(id);
    }
}
