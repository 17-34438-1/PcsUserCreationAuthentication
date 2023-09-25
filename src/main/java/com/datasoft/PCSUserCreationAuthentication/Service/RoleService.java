package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.Role;
import com.datasoft.PCSUserCreationAuthentication.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<ResponseMessage> add(Role role){
        String role_name = role.getRole_name();
        role.setCreated_ip(request.getRemoteAddr());

        if(role_name == null || role_name.equals("") || role_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Role name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(role_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Role Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer isExists = roleRepository.isExists(role_name);
            if(isExists == 0){
                Integer responseStatus = roleRepository.insertUserRole(role_name, role.getCreated_ip());
                if (responseStatus == 1){
                    responseMessage = new ResponseMessage( "User Role Name Added Successfully");
                    return new ResponseEntity(responseMessage, HttpStatus.CREATED);
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Could not add role name");
                    return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Role name already exists.");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }

    public List<Role> UserRoleList(){
        List<Role> roles = new ArrayList<Role>();
        String exception = null;
        try
        {
            roles = roleRepository.UserRoleList();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return roles;
    }

    public ResponseEntity<Role> getUserRoleById(Long id){
        Role role = roleRepository.getUserRoleById(id);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody Role role){
        Long id = role.getId();
        String role_name = role.getRole_name();
        String update_ip = request.getRemoteAddr();

        if(id == null || id.equals("") || id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! ID can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else if(role_name == null || role_name.equals("") || role_name.equals(" ") || role_name.isEmpty()){
            responseMessage = new ResponseMessage( "Role Name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else if(role_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Role Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else {
            Integer isUnique = roleRepository.isUnique(id,role_name);
            if(isUnique == 0){
                Integer response = roleRepository.editUserRole(id,role_name,update_ip);
                if(response==1){
                    responseMessage = new ResponseMessage( "User Role Updated Successfully");
                    return new ResponseEntity(responseMessage, HttpStatus.OK);
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Could not Update Data");
                    return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! User Role Info already exists.");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }

    public ResponseEntity<ResponseMessage> deleteUserRole(@PathVariable Long id) {
        Integer chkUserInfo = roleRepository.chkUserInfo(id);
        Integer chkUserAssignRole = roleRepository.chkUserAssignRole(id);
        if(chkUserInfo == 0){
            if(chkUserAssignRole == 0){
                Integer delete = roleRepository.deleteUserRoleById(id);
                if(delete == 1) {
                    responseMessage = new ResponseMessage( "User Role Deleted Successfully.");
                    return new ResponseEntity(responseMessage, HttpStatus.OK);
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Could not delete data.");
                    return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! User Role Info already exists in User-Role-Assign-Info.");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseMessage = new ResponseMessage( "Sorry! User Role Info already exists in User-Info.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
