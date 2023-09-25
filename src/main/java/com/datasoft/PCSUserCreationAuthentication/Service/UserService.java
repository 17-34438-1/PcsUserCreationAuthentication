package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.User;
import com.datasoft.PCSUserCreationAuthentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<ResponseMessage> add(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Integer role_id = user.getRole_id();
        String login_id = user.getLogin_id();
        String address = user.getAddress();
        String telephone_no = user.getTelephone_no();
        String mobile_no = user.getMobile_no();
        String email = user.getEmail();
        String image_path = user.getImage_path();
        Integer org_id = user.getOrg_id();
        String ptext = user.getPtext();
        String entry_ip = request.getRemoteAddr();
        String login_password = "";
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);

        Date date = new Date();
        String strDateFormat = "YYYY-MM-dd hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 12);
        java.util.Date dt = cal.getTime();
        String expire_date= dateFormat.format(cal.getTime());
        System.out.println("12 months from now... " + expire_date );

        if(role_id == null || role_id.equals("") || role_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Role name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(login_id == null || login_id.equals("") || login_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Login id can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(address == null || address.equals("") || address.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! User address can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(mobile_no == null || mobile_no.equals("") || mobile_no.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Mobile number can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(email == null || email.equals("") || email.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Email can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(org_id == null || org_id.equals("") || org_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(ptext == null || ptext.equals("") || ptext.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Password can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer isLoginIdExists = userRepository.isLoginIdExists(login_id);
            if(isLoginIdExists == 0){
                Integer isRoleIdExists = userRepository.isRoleIdExists(role_id);
                if(isRoleIdExists == 0){
                    responseMessage = new ResponseMessage( "Sorry! Invalid User Role");
                    return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                } else {
                    Integer isOrganizationExists = userRepository.isOrganizationExists(org_id);
                    if(isOrganizationExists == 0){
                        responseMessage = new ResponseMessage( "Sorry! Invalid Organization");
                        return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                    } else {
                        String organization_name = userRepository.organizationName(org_id);
                        MessageDigest digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(ptext.getBytes("utf8"));
                        login_password = String.format("%040x", new BigInteger(1, digest.digest()));

                        Integer insertResponse = userRepository.insertUser(role_id,organization_name,login_id,login_password,address,telephone_no,mobile_no,email,image_path,org_id,ptext,expire_date,entry_ip);
                        if(insertResponse == 1){
                            responseMessage = new ResponseMessage( "User Created Successfully.");
                            return new ResponseEntity(responseMessage, HttpStatus.OK);
                        } else {
                            responseMessage = new ResponseMessage( "Sorry! Could not create user.");
                            return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                        }
                    }
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Login ID already exists");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }

    public List<User> list(){
        List<User> users = new ArrayList<User>();
        String exception = null;
        try
        {
            users = userRepository.list();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return users ;
    }

    public ResponseEntity<User> getUserById(Long id){
        User user = userRepository.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Long id = user.getId();
        Integer role_id = user.getRole_id();
        String login_id = user.getLogin_id();
        String address = user.getAddress();
        String telephone_no = user.getTelephone_no();
        String mobile_no = user.getMobile_no();
        String email = user.getEmail();
        String image_path = user.getImage_path();
        Integer org_id = user.getOrg_id();
        String ptext = user.getPtext();
        String update_ip = request.getRemoteAddr();
        String login_password = "";
        LocalTime timeNow = LocalTime.now();
        System.out.println(timeNow);

        Date date = new Date();
        String strDateFormat = "YYYY-MM-dd hh:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 12);
        java.util.Date dt = cal.getTime();
        String expire_date= dateFormat.format(cal.getTime());
        System.out.println("12 months from now... " + expire_date );

        if(role_id == null || role_id.equals("") || role_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Role name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(login_id == null || login_id.equals("") || login_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Login id can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(address == null || address.equals("") || address.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! User address can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(mobile_no == null || mobile_no.equals("") || mobile_no.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Mobile number can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(email == null || email.equals("") || email.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Email can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(org_id == null || org_id.equals("") || org_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Organization can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(ptext == null || ptext.equals("") || ptext.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Password can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer isLoginIdExists = userRepository.isLoginIdUnique(id,login_id);
            if(isLoginIdExists == 0){
                Integer isRoleIdExists = userRepository.isRoleIdExists(role_id);
                if(isRoleIdExists == 0){
                    responseMessage = new ResponseMessage( "Sorry! Invalid User Role");
                    return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                } else {
                    Integer isOrganizationExists = userRepository.isOrganizationExists(org_id);
                    if(isOrganizationExists == 0){
                        responseMessage = new ResponseMessage( "Sorry! Invalid Organization");
                        return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
                    } else {
                        String organization_name = userRepository.organizationName(org_id);
                        MessageDigest digest = MessageDigest.getInstance("SHA-1");
                        digest.reset();
                        digest.update(ptext.getBytes("utf8"));
                        login_password = String.format("%040x", new BigInteger(1, digest.digest()));

                        Integer editResponse = userRepository.editUser(id,role_id,organization_name,login_id,login_password,address,telephone_no,mobile_no,email,image_path,org_id,ptext,expire_date,update_ip);
                        if(editResponse == 1){
                            responseMessage = new ResponseMessage( "User Updated Successfully.");
                            return new ResponseEntity(responseMessage, HttpStatus.OK);
                        } else {
                            responseMessage = new ResponseMessage( "Sorry! Could not update user.");
                            return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                        }
                    }
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Login ID already exists");
                return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
            }
        }
    }

    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {
        Integer deleteResponse = userRepository.delete(id);
        if(deleteResponse == 1) {
            responseMessage = new ResponseMessage( "User Deleted Successfully.");
            return new ResponseEntity(responseMessage, HttpStatus.OK);
        } else {
            responseMessage = new ResponseMessage( "Sorry! Could not delete data.");
            return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
