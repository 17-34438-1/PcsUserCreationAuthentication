package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.URL;
import com.datasoft.PCSUserCreationAuthentication.Repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class URLService {

    @Autowired
    private URLRepository URLRepository;

    ResponseMessage responseMessage;

    @Autowired
    private HttpServletRequest request;

    List<URL> urls;

    public ResponseEntity<ResponseMessage> add(URL URL){
        Integer user_module_id = URL.getUser_module_id();
        String label_name = URL.getLabel_name();
        String component_name = URL.getComponent_name();
        String action_name = URL.getAction_name();
        Integer no_of_param = URL.getNo_of_param();
        String param_1 = null;
        String param_2 = null;
        String param_3 = null;
        String param_4 = null;
        String url_for = URL.getUrl_for();
        Integer editable = URL.getEditable();

//        String parameters[] = URL.getParams();
//        Integer no_of_param = parameters.length;
//        System.out.println(no_of_param);
//        String params = Arrays.toString(parameters).replaceAll(", ",",").replace("[","").replace("]","");

        URL.setCreated_ip(request.getRemoteAddr());
        String created_ip = URL.getCreated_ip();

        if(label_name == null || label_name.equals("") || label_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Label name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(label_name.length()>30){
            responseMessage = new ResponseMessage( "Sorry! Maximum Label Name length is 30.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(component_name == null || component_name.equals("") || component_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Component name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(component_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Component Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(action_name == null || action_name.equals("") || action_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Action name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(action_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Action Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
//        else if(no_of_param == null || no_of_param.equals("") || no_of_param.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Number of Parameter can not be null or empty.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(no_of_param < 0 || no_of_param > 4){
//            responseMessage = new ResponseMessage( "Sorry! Invalid Number of Parameter.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(url_for == null || url_for.equals("") || url_for.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Please specify the user of the url.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(editable == null || editable.equals("") || editable.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Please specify the type(Read/Write) of the URL.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
        else {
            Integer isUserModuleExists = URLRepository.isUserModuleExists(user_module_id);
            if(isUserModuleExists == 1) {
                Integer isExists = URLRepository.isExists(user_module_id,label_name);
                if(isExists == 0){
//                    if(no_of_param==1){
//                        param_1 = URL.getParam_1();
//                    } else if(no_of_param==2){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                    } else if(no_of_param==3){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                        param_3 = URL.getParam_3();
//                    } else if(no_of_param==4){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                        param_3 = URL.getParam_3();
//                        param_4 = URL.getParam_4();
//                    }
                    Integer responseStatus = URLRepository.insertUserEnlistURL(user_module_id,label_name,component_name,action_name,no_of_param,param_1,param_2,param_3,param_4,url_for,editable,created_ip);
                    if (responseStatus == 1){
                        responseMessage = new ResponseMessage( "URL Added Successfully");
                        return new ResponseEntity(responseMessage, HttpStatus.CREATED);
                    } else {
                        responseMessage = new ResponseMessage( "Sorry! Could not add URL");
                        return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Given label name is already assigned to this user module.");
                    return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Invalid User Module ID");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public List<URL> list(){
        List<URL> URL = new ArrayList<URL>();

        String exception = null;
        try
        {
            URL = URLRepository.UserEnlistUrlList();
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return URL;
    }

//    public List<URL> list(){
//        urls =new ArrayList<>();
//
//        URL url_list = new URL();
//        List URLs[] = URLRepository.UrlList();
//        String exception = null;
//        try
//        {
//            for (int cnt = 0; cnt < URLs.length; cnt++) {
//                URL url = new URL();
//                System.out.println("id..." + URLs[cnt].get(0).getClass().getName());
//                String id = URLs[cnt].get(0).toString();
//                String user_module_id = URLs[cnt].get(1).toString();
//                String label_name = URLs[cnt].get(2).toString();
//                String component_name = URLs[cnt].get(3).toString();
//                String action_name = URLs[cnt].get(4).toString();
//                String no_of_param = URLs[cnt].get(5).toString();
//                String param_1 = URLs[cnt].get(6).toString();
//                String param_2 = URLs[cnt].get(7).toString();
//                String param_3 = URLs[cnt].get(8).toString();
//                String param_4 = URLs[cnt].get(9).toString();
//
//                url.setId(Long.parseLong(id, 10));
//                url.setUser_module_id(Integer.parseInt(user_module_id));
//                url.setLabel_name(label_name);
//                url.setComponent_name(component_name);
//                url.setAction_name(action_name);
//                url.setNo_of_param(Integer.parseInt(no_of_param));
//                url.setParam_1(param_1);
//                url.setParam_2(param_2);
//                url.setParam_3(param_3);
//                url.setParam_4(param_4);
//                urls.add(url);
//
//
//                if (URLs[cnt].get(10) != null) {
//                    String[] parameters = URLs[cnt].get(10).toString().split(",");
//                    if(parameters.length > 0){
//                        List<String> allparams = new ArrayList<String>();
//                        for(String parameter: parameters){
//                            //System.out.println(parameter+" ");
//                            allparams.add(parameter);
//                        }
//                        System.out.println(allparams);
//                    }
//
//                    //System.out.println(URLs[cnt].get(10).toString() + "................................" ); //First Column
//                }
//            }
//
//            /*for(int i=1;i<=URLlist.size();i++){
//                System.out.println(URLlist.get(i).getParams().getClass().getName());
//            }
//            System.out.println(URLlist.size());*/
//        }catch(Exception ex)
//        {
//            ex.printStackTrace();
//            exception = ex.getMessage();
//        }
//        return urls;
//    }

    public ResponseEntity<URL> getUserEnlistURLById(Long id){
        URL URL = URLRepository.getUserEnlistURLById(id);
        return new ResponseEntity<>(URL,HttpStatus.OK);
    }

    public ResponseEntity<ResponseMessage> edit(@RequestBody URL URL){
        Long id = URL.getId();
        Integer user_module_id = URL.getUser_module_id();
        String label_name = URL.getLabel_name();
        String component_name = URL.getComponent_name();
        String action_name = URL.getAction_name();
        Integer no_of_param = URL.getNo_of_param();
        String param_1 = null;
        String param_2 = null;
        String param_3 = null;
        String param_4 = null;
        String url_for = URL.getUrl_for();
        Integer editable = URL.getEditable();



//        String parameters[] = URL.getParams();
//        Integer no_of_param = parameters.length;
//        String params = Arrays.toString(parameters).replaceAll(", ",",").replace("[","").replace("]","");

        URL.setUpdate_ip(request.getRemoteAddr());
        String update_ip = URL.getUpdate_ip();

        if(id == null || id.equals("") || id.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! ID can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(label_name == null || label_name.equals("") || label_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Label name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(label_name.length()>30){
            responseMessage = new ResponseMessage( "Sorry! Maximum Label Name length is 30.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(component_name == null || component_name.equals("") || component_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Component name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(component_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Component Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(action_name == null || action_name.equals("") || action_name.equals(" ")){
            responseMessage = new ResponseMessage( "Sorry! Action name can not be null or empty.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        } else if(action_name.length()>20){
            responseMessage = new ResponseMessage( "Sorry! Maximum Action Name length is 20.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
//        else if(no_of_param == null || no_of_param.equals("") || no_of_param.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Number of Parameter can not be null or empty.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(no_of_param < 0 || no_of_param > 4){
//            responseMessage = new ResponseMessage( "Sorry! Invalid Number of Parameter.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(url_for == null || url_for.equals("") || url_for.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Please specify the user of the url.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
//        else if(editable == null || editable.equals("") || editable.equals(" ")){
//            responseMessage = new ResponseMessage( "Sorry! Please specify the type(Read/Write) of the url.");
//            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
//        }
        else {
            Integer isUserModuleExists = URLRepository.isUserModuleExists(user_module_id);
            if(isUserModuleExists == 1) {
                Integer isExists = URLRepository.isUnique(id,user_module_id,label_name);
                if(isExists == 0){
//                    if(no_of_param==1){
//                        param_1 = URL.getParam_1();
//                    } else if(no_of_param==2){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                    } else if(no_of_param==3){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                        param_3 = URL.getParam_3();
//                    } else if(no_of_param==4){
//                        param_1 = URL.getParam_1();
//                        param_2 = URL.getParam_2();
//                        param_3 = URL.getParam_3();
//                        param_4 = URL.getParam_4();
//                    }
                    Integer responseStatus = URLRepository.editUserEnlistURL(id,user_module_id,label_name,component_name,action_name,no_of_param,param_1,param_2,param_3,param_4,url_for,editable,update_ip);
                    if (responseStatus == 1){
                        responseMessage = new ResponseMessage( "Data Updated Successfully");
                        return new ResponseEntity(responseMessage, HttpStatus.OK);
                    } else {
                        responseMessage = new ResponseMessage( "Sorry! Could not update URL");
                        return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    responseMessage = new ResponseMessage( "Sorry! Given label name is already assigned to this user module.");
                    return new ResponseEntity(responseMessage, HttpStatus.ALREADY_REPORTED);
                }
            } else {
                responseMessage = new ResponseMessage( "Sorry! Invalid User Module ID");
                return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
            }
        }
    }

    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {
        Integer cntUserAssignRole = URLRepository.chkUserAssignRole(id);
        if(cntUserAssignRole == 0){
            Integer deleteResponse = URLRepository.deleteUserEnlistUrlById(id);
            if(deleteResponse == 1) {
                responseMessage = new ResponseMessage( "Data Deleted Successfully.");
                return new ResponseEntity(responseMessage, HttpStatus.OK);
            } else {
                responseMessage = new ResponseMessage( "Sorry! Could not delete data.");
                return new ResponseEntity(responseMessage, HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            responseMessage = new ResponseMessage( "Sorry! This URL has already been assigned to some roles.");
            return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    public List<URL> urlByModule(Integer module_id){
        List<URL> urlByModule = new ArrayList<URL>();
        String exception = null;
        try
        {
            urlByModule = URLRepository.urlByModule(module_id);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return urlByModule ;
    }

    public List<URL> urlByRole(Integer user_role_id){
        List<URL> urlByRole = new ArrayList<URL>();
        String exception = null;
        try
        {
            urlByRole = URLRepository.urlByRole(user_role_id);
        }catch(Exception ex)
        {
            ex.printStackTrace();
            exception = ex.getMessage();
        }
        return urlByRole ;
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<ResponseMessage> numberFormatExceptionHandler() throws IOException {
        responseMessage = new ResponseMessage( "Sorry! User Module ID must be a number.");
        return new ResponseEntity(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
