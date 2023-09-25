package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.Sidebar;
import com.datasoft.PCSUserCreationAuthentication.Service.SidebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sidebar")
public class SidebarController {
    @Autowired
    private SidebarService sidebarService;

    @RequestMapping(value = "/getSidebarByLoginId/{login_id}", method = RequestMethod.GET)
    public @ResponseBody List<Sidebar> list(@PathVariable("login_id") String login_id){
        return sidebarService.sidebars(login_id);
    }

    @RequestMapping(value = "/sidebarByLoginId/{login_id}", method = RequestMethod.GET)
    public @ResponseBody List<Sidebar> listTest(@PathVariable("login_id") String login_id){
        return sidebarService.sidebarsTest(login_id);
    }
}
