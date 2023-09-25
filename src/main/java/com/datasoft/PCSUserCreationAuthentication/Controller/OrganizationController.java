package com.datasoft.PCSUserCreationAuthentication.Controller;

import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationProfile;
import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationType;
import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Service.OrganizationProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {
    @Autowired
    private OrganizationProfileService organizationProfileService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<ResponseMessage> addUserModule(@RequestBody OrganizationProfile organizationProfile) throws IOException {
        return organizationProfileService.add(organizationProfile);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<OrganizationProfile> organizationProfileList(){
        return organizationProfileService.organizationProfileList();
    }

    @RequestMapping(value = "/organizationTypeList", method = RequestMethod.GET)
    public @ResponseBody
    List<OrganizationType> organizationTypeList(){
        return organizationProfileService.organizationTypeList();
    }

//    @RequestMapping(value = "/orgTypeListForUserCreation", method = RequestMethod.GET)
//    public @ResponseBody
//    List<OrganizationType> orgTypeListForUserCreation(){ return organizationProfileService.orgTypeListForUserCreation(); }

    @RequestMapping(value = "/getOrganizationById/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationProfile> getOrganizationById(@PathVariable("id") Long id){
        return organizationProfileService.getOrganizationById(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes="application/json")
    public ResponseEntity<ResponseMessage> updateOrganizationProfile(@RequestBody OrganizationProfile organizationProfile) throws IOException{
        return organizationProfileService.edit(organizationProfile);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteOrganization(@PathVariable Long id) throws IOException{
        return organizationProfileService.deleteOrganization(id);
    }

//    @RequestMapping(value = "/paginationList/{offset}/{pageSize}", method = RequestMethod.GET)
//    public @ResponseBody
//    Page<OrganizationProfile> paginationList(@PathVariable int offset, @PathVariable int pageSize){
//        Page<OrganizationProfile> paginationList = organizationProfileService.paginationList(offset,pageSize);
//        return paginationList;
//    }
}
