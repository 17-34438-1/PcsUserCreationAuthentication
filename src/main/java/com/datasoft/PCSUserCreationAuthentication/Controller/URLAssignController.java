package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.URLAssign;
import com.datasoft.PCSUserCreationAuthentication.Service.URLAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/urlAssign")
public class URLAssignController {
    @Autowired
    private URLAssignService urlAssignService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> add(@RequestBody URLAssign urlAssign) throws IOException {
        return urlAssignService.add(urlAssign);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> edit(@RequestBody URLAssign urlAssign) throws IOException{
        return urlAssignService.edit(urlAssign);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<URLAssign> list(){ return urlAssignService.list(); }
}
