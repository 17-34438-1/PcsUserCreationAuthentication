package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationType;
import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationProfile;
import com.datasoft.PCSUserCreationAuthentication.Repository.OrganizationProfileRepository;
import com.datasoft.PCSUserCreationAuthentication.Repository.OrganizationTypeRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrganizationProfileService {
    @Autowired
    private OrganizationProfileRepository organizationProfileRepository;

    @Autowired
    private OrganizationTypeRepository organizationTypeRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<ResponseMessage> add(OrganizationProfile organizationProfile){
        Integer Org_Type_id = organizationProfile.getOrg_Type_id();
        String Organization_Name = organizationProfile.getOrganization_Name();
        String AIN_No = organizationProfile.getAIN_No_New();
        String License_No = organizationProfile.getLicense_No();
        String Licence_Validity_Date = organizationProfile.getLicence_Validity_Date();
        String Address_1 = organizationProfile.getAddress_1();
        String Address_2 = organizationProfile.getAddress_2();
        String Telephone_No_Land = organizationProfile.getTelephone_No_Land();
        String Cell_No_1 = organizationProfile.getCell_No_1();
        String Cell_No_2 = organizationProfile.getCell_No_2();
        String Fax_No = organizationProfile.getFax_No();
        String email = organizationProfile.getEmail();
        String logo = organizationProfile.getLogo();
        String URL = organizationProfile.getURL();

        if(Org_Type_id == null || Org_Type_id.equals("") || Org_Type_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization type can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Organization_Name == null || Organization_Name.equals("") || Organization_Name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(AIN_No == null || AIN_No.equals("") || AIN_No.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! AIN no can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(License_No == null || License_No.equals("") || License_No.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! License no can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Address_1 == null || Address_1.equals("") || Address_1.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! First address line can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Cell_No_1 == null || Cell_No_1.equals("") || Cell_No_1.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Cell phone no(1) can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else {
            Integer isOrgTypeExists = organizationTypeRepository.isOrgTypeExists(Org_Type_id);
            if(isOrgTypeExists == 1){
                Integer isExists = organizationProfileRepository.isExist(Org_Type_id,AIN_No);
                if(isExists == 0) {
                    Integer responseStatus = organizationProfileRepository.insertOrganizationProfile(Org_Type_id,Organization_Name,AIN_No,License_No,Licence_Validity_Date,Address_1,Address_2,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,URL);
                    if (responseStatus == 1){
                        responseMessage = new ResponseMessage( "Organization added Successfully");
                        return new ResponseEntity(responseMessage, HttpStatus.CREATED);
                    } else {
                        responseMessage = new ResponseMessage( "Sorry! Could not add organization info");
                        return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Organization already exists..");
                    return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Invalid Organization Type.");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public List<OrganizationProfile> organizationProfileList(){
        List<OrganizationProfile> organizationProfileList = new ArrayList<OrganizationProfile>();
        String exception = null;
        try
        {
            organizationProfileList = organizationProfileRepository.organizationProfileList();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return organizationProfileList ;
    }

    public List<OrganizationType> organizationTypeList(){
        List<OrganizationType> organizationTypeList = new ArrayList<OrganizationType>();
        String exception = null;
        try
        {
            organizationTypeList = organizationTypeRepository.organizationTypeList();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return organizationTypeList ;
    }

    public List<OrganizationType> orgTypeListForUserCreation(){
        List<OrganizationType> organizationTypeList = new ArrayList<OrganizationType>();
        String exception = null;
        try
        {
            organizationTypeList = organizationTypeRepository.orgTypeListForUserCreation();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return organizationTypeList ;
    }

    public ResponseEntity<OrganizationProfile> getOrganizationById(Long id){
        OrganizationProfile organizationProfile = organizationProfileRepository.getOrganizationById(id);
        return new ResponseEntity<>(organizationProfile,HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody OrganizationProfile organizationProfile){
        Long id = organizationProfile.getId();
        Integer Org_Type_id = organizationProfile.getOrg_Type_id();
        String Organization_Name = organizationProfile.getOrganization_Name();
        String AIN_No = organizationProfile.getAIN_No_New();
        String License_No = organizationProfile.getLicense_No();
        String Licence_Validity_Date = organizationProfile.getLicence_Validity_Date();
        String Address_1 = organizationProfile.getAddress_1();
        String Address_2 = organizationProfile.getAddress_2();
        String Telephone_No_Land = organizationProfile.getTelephone_No_Land();
        String Cell_No_1 = organizationProfile.getCell_No_1();
        String Cell_No_2 = organizationProfile.getCell_No_2();
        String Fax_No = organizationProfile.getFax_No();
        String email = organizationProfile.getEmail();
        String logo = organizationProfile.getLogo();
        String URL = organizationProfile.getURL();
        String login_id = " ";

        if(Org_Type_id == null || Org_Type_id.equals("") || Org_Type_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization type can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Organization_Name == null || Organization_Name.equals("") || Organization_Name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(AIN_No == null || AIN_No.equals("") || AIN_No.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! AIN no can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(License_No == null || License_No.equals("") || License_No.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! License no can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Address_1 == null || Address_1.equals("") || Address_1.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! First address line can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(Cell_No_1 == null || Cell_No_1.equals("") || Cell_No_1.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Cell phone no(1) can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
        else {
            if(Org_Type_id == 1 || Org_Type_id == 2 || Org_Type_id == 4){
                if(Org_Type_id == 1){
                    System.out.println("...................MLO.....................");
                    login_id = AIN_No+"M"; //MLO
                } else if (Org_Type_id == 2){
                    login_id = AIN_No+"CF"; //C&F
                    System.out.println("...................C&F.....................");
                } else if (Org_Type_id == 4){
                    login_id = AIN_No+"FF"; //FF
                    System.out.println("...................FF.....................");
                }
            }
            Integer chkUser = organizationProfileRepository.chkUser(login_id,id);
            if(chkUser != 0){
                Integer user_id = organizationProfileRepository.getUserId(login_id,id);
                System.out.println(user_id + ".................");
                Integer userUpdateResponse = organizationProfileRepository.updateUserName(user_id,Organization_Name);
            }
            Integer isOrgTypeExists = organizationTypeRepository.isOrgTypeExists(Org_Type_id);
            if(isOrgTypeExists == 1){
                Integer isUnique = organizationProfileRepository.isUnique(id,Org_Type_id,AIN_No);
                if(isUnique == 0){
                    Integer responseStatus = organizationProfileRepository.editOrganizationProfile(id,Org_Type_id,Organization_Name,AIN_No,License_No,Licence_Validity_Date,Address_1,Address_2,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,URL);
                    if (responseStatus == 1){
                        responseMessage = new ResponseMessage( "Organization Profile Updated Successfully");
                        return new ResponseEntity(responseMessage, HttpStatus.OK);
                    } else {
                        responseMessage = new ResponseMessage( "Sorry! Could not edit organization info");
                        return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Organization Already Exists.");
                    return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Invalid Organization Type.");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<ResponseMessage> deleteOrganization(@PathVariable Long id) {
        Integer chkUsersInfo = organizationProfileRepository.chkUsersInfo(id);
        if(chkUsersInfo == 0){
            Integer delete = organizationProfileRepository.deleteOrganizationById(id);
            if(delete == 1) {
                responseMessage = new ResponseMessage( "Organization Deleted Successfully.");
                return new ResponseEntity(responseMessage, HttpStatus.OK);
            } else {
                responseMessage = new ResponseMessage( "Sorry! Could not delete data.");
                return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            responseMessage = new ResponseMessage( "Sorry! Organization info already exists in user info.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    public Page<OrganizationProfile> paginationList(int offset, int pageSize){
        Page<OrganizationProfile> paginationList = organizationProfileRepository.paginationList(PageRequest.of(offset,pageSize));
        return paginationList;
    }
}
