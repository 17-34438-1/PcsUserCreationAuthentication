package com.datasoft.PCSUserCreationAuthentication.Service;

import com.datasoft.PCSUserCreationAuthentication.Model.ResponseMessage;
import com.datasoft.PCSUserCreationAuthentication.Model.Sidebar;
import com.datasoft.PCSUserCreationAuthentication.Model.URL;
import com.datasoft.PCSUserCreationAuthentication.Repository.SidebarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SidebarService {
    @Autowired
    private SidebarRepository sidebarRepository;

    ResponseMessage responseMessage;

    List<Sidebar> sidebarList;

    public List<Sidebar> sidebars(String login_id){
        sidebarList =new ArrayList<>();

        Integer role_id = sidebarRepository.getRoleId(login_id);
        List modules[] = sidebarRepository.moduleListByRole(role_id);
        for (int i = 0; i < modules.length; i++) {

            String module_id = modules[i].get(0).toString();

            String module_name = sidebarRepository.getModuleName(Integer.parseInt(module_id));

            Sidebar sidebar = new Sidebar();

            sidebar.setModule_name(module_name);

            List labelInfo[] = sidebarRepository.getLabelInfo(role_id,Integer.parseInt(module_id));
            String[] label = new String[labelInfo.length];
            for (int j = 0; j < labelInfo.length; j++) {
                String label_name = labelInfo[j].get(3).toString();

                label[j]=label_name;
                sidebar.setUrls(label);
            }

            sidebarList.add(sidebar);
        }

        return sidebarList;
    }

    public List<Sidebar> sidebarsTest(String login_id){

        sidebarList =new ArrayList<>();
        Integer role_id = sidebarRepository.getRoleId(login_id);
        List modules[] = sidebarRepository.moduleListByRole(role_id);
        for (int i = 0; i < modules.length; i++) {
            String module_id = modules[i].get(0).toString();
            String module_name = sidebarRepository.getModuleName(Integer.parseInt(module_id));

            Sidebar sidebar = new Sidebar();

            sidebar.setModule_name(module_name);

            List labelInfo[] = sidebarRepository.getLabelInfo(role_id,Integer.parseInt(module_id));
            String[] label = new String[labelInfo.length];
            for (int j = 0; j < labelInfo.length; j++) {
                String label_name = labelInfo[j].get(3).toString();

                label[j]=label_name;
                sidebar.setUrls(label);
            }

            //sidebar.setU(null);

            sidebarList.add(sidebar);
        }

        return sidebarList;
    }
}
