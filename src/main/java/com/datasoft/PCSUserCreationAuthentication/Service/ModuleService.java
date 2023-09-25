package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.Module;
import com.datasoft.PCSUserCreationAuthentication.Repository.ModuleRepository;
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
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<ResponseMessage> add(Module userModule){
        String module_name = userModule.getModule_name();
        userModule.setCreated_ip(request.getRemoteAddr());

        if(module_name == null || module_name.equals("") || module_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Module name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(module_name.length()>50){
            responseMessage = new ResponseMessage( "Sorry! Maximum Module Name length is 50.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer isExists = moduleRepository.isExists(userModule.getModule_name());
            if(isExists == 0){
                Integer responseStatus = moduleRepository.insertUserModule(userModule.getModule_name(),userModule.getCreated_ip());
                if (responseStatus == 1){
                    responseMessage = new ResponseMessage( "Module Name Added Successfully");
                    return new ResponseEntity(responseMessage, HttpStatus.CREATED);
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Could not add module name");
                    return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Module name already exists.");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }


    public List<Module> UserModuleList(){
        List<Module> userModules = new ArrayList<Module>();
        String exception = null;
        try
        {
            userModules = moduleRepository.UserModuleList();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return userModules ;
    }

    public ResponseEntity<Module> getUserModuleById(Long id){
        Module userModule = moduleRepository.getUserModuleById(id);
        return new ResponseEntity<>(userModule,HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody Module userModule){
        Long id = userModule.getId();
        String module_name = userModule.getModule_name();
        String update_ip = request.getRemoteAddr();

        if(id == null || id.equals("") || id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! ID can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else if(module_name == null || module_name.equals("") || module_name.equals(" ") || module_name.isEmpty()){
            responseMessage = new ResponseMessage( "Module Name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else if(module_name.length()>50){
            responseMessage = new ResponseMessage( "Sorry! Maximum Module Name length is 50.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else {
            Integer isUnique = moduleRepository.isUnique(id,module_name);
            if(isUnique == 0){
                Integer response = moduleRepository.editUserModule(id,module_name,update_ip);
                if(response==1){
                    responseMessage = new ResponseMessage( "User Module Updated Successfully");
                    return new ResponseEntity(responseMessage, HttpStatus.OK);
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Could not Update Data");
                    return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! User Module Info already exists.");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }


    public ResponseEntity<ResponseMessage> deleteUserModule(@PathVariable Long id) {
        Integer chkEnlistedURL = moduleRepository.chkEnlistedURL(id);
        if(chkEnlistedURL == 0){
            Integer delete = moduleRepository.deleteUserModuleById(id);
            if(delete == 1) {
                responseMessage = new ResponseMessage( "User Module Deleted Successfully.");
                return new ResponseEntity(responseMessage, HttpStatus.OK);
            } else {
                responseMessage = new ResponseMessage( "Sorry! Could not delete data.");
                return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            responseMessage = new ResponseMessage( "Sorry! User Module Info already exists in URL enlistment.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
