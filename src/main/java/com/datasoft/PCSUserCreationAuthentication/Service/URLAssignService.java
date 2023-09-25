package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.URLAssign;
import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Repository.URLAssignRepository;
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
public class URLAssignService {
    @Autowired
    private URLAssignRepository urlAssignRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    List<URLAssign> urlAssignList;

    public List<URLAssign> listBackup(){
        urlAssignList =new ArrayList<>();
        return urlAssignList;
    }

    public List<URLAssign> list(){
        urlAssignList =new ArrayList<>();

        List assignedRoles[] = urlAssignRepository.assignedRoleList();
        for (int i = 0; i < assignedRoles.length; i++) {
            URLAssign url_assign = new URLAssign();
            String user_role_id = assignedRoles[i].get(0).toString();
            url_assign.setUser_role_id(Integer.parseInt(user_role_id));
            String role_name = urlAssignRepository.getRoleName(Integer.parseInt(user_role_id));
            url_assign.setRole_name(role_name);

            List urlAssignmentByRole[] = urlAssignRepository.urlAssignmentByRole(Integer.parseInt(user_role_id));
            Integer[] x = new Integer[urlAssignmentByRole.length];
            String[] urls = new String[urlAssignmentByRole.length];
            for (int j = 0; j < urlAssignmentByRole.length; j++) {
                String user_url_id = urlAssignmentByRole[j].get(2).toString();
                x[j]=Integer.parseInt(user_url_id);
                url_assign.setUser_url_id(x);

                String label = urlAssignRepository.getURLLabel(Integer.parseInt(user_url_id));
                urls[j]=label;
                url_assign.setUrl_label(urls);
            }
            urlAssignList.add(url_assign);
        }
        return urlAssignList;
    }

    public ResponseEntity<ResponseMessage> add(URLAssign urlAssign){
        Integer user_role_id = urlAssign.getUser_role_id();
        Integer user_url_id[] = urlAssign.getUser_url_id();
        String ip_address = request.getRemoteAddr();

        if(user_role_id == null || user_role_id.equals("") || user_role_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! User Role can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer chkRoleId = urlAssignRepository.chkRoleId(user_role_id);
            if(chkRoleId == 0) {
                responseMessage = new ResponseMessage( "Sorry! Invalid Role Selected.");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            } else {
                for(int i=0;i<user_url_id.length;i++){
                    Integer url_id = user_url_id[i];
                    Integer chkURLId = urlAssignRepository.chkURLId(url_id);
                    if(chkURLId !=0 ){
                        Integer cntURLAssignment = urlAssignRepository.cntURLAssignment(user_role_id,url_id);
                        if(cntURLAssignment == 0){
                            Integer responseStatus = urlAssignRepository.insertURLAssignment(user_role_id,url_id,ip_address);
                        }
                    }
                }
                responseMessage = new ResponseMessage( "URL assigned successfully!");
                return new ResponseEntity(responseMessage, HttpStatus.CREATED);
            }
        }
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody URLAssign urlAssign){
        Integer user_role_id = urlAssign.getUser_role_id();
        Integer user_url_id[] = urlAssign.getUser_url_id();
        String ip_address = request.getRemoteAddr();

        if(user_role_id == null || user_role_id.equals("") || user_role_id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! User Role can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else {
            Integer chkRoleId = urlAssignRepository.chkRoleId(user_role_id);
            if(chkRoleId == 0) {
                responseMessage = new ResponseMessage( "Sorry! Invalid Role Selected.");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            } else {
                urlAssignRepository.deleteURLAssignmentByRole(user_role_id);
                for(int i=0;i<user_url_id.length;i++){
                    Integer url_id = user_url_id[i];
                    Integer chkURLId = urlAssignRepository.chkURLId(url_id);
                    if(chkURLId !=0 ){
                        Integer cntURLAssignment = urlAssignRepository.cntURLAssignment(user_role_id,url_id);
                        if(cntURLAssignment == 0){
                            Integer responseStatus = urlAssignRepository.insertURLAssignment(user_role_id,url_id,ip_address);
                        }
                    }
                }
                responseMessage = new ResponseMessage( "Data updated successfully!");
                return new ResponseEntity(responseMessage, HttpStatus.OK);
            }
        }
    }

}
