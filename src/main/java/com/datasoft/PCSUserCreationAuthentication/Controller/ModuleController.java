package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.Module;
import com.datasoft.PCSUserCreationAuthentication.Service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> add(@RequestBody Module userModule) throws IOException {
        return moduleService.add(userModule);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Module> list(){
        return moduleService.UserModuleList();
    }

    @RequestMapping(value = "/getModuleById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Module> getModuleById(@PathVariable("id") Long id){
        return moduleService.getUserModuleById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> edit(@RequestBody Module userModule) throws IOException{
        return moduleService.edit(userModule);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) throws IOException{
        return moduleService.deleteUserModule(id);
    }

}
