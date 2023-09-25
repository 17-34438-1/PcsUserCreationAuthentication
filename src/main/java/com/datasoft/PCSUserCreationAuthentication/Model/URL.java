package com.datasoft.PCSUserCreationAuthentication.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer user_module_id;
    private String label_name;
    private String component_name;
    private String action_name;
    private Integer no_of_param;
    private String param_1;
    private String param_2;
    private String param_3;
    private String param_4;
    private String url_for;
    private Integer editable;

//    String[] params;

    private String module_name;

    @JsonIgnore
    private Date created_at;

    @JsonIgnore
    private String created_by;

    @JsonIgnore
    private String created_ip;

    @JsonIgnore
    private Date updated_at;

    @JsonIgnore
    private String updated_by;

    @JsonIgnore
    private String update_ip;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Integer getUser_module_id() { return user_module_id; }

    public void setUser_module_id(Integer user_module_id) { this.user_module_id = user_module_id; }

    public String getLabel_name() { return label_name; }

    public void setLabel_name(String label_name) { this.label_name = label_name; }

    public String getComponent_name() { return component_name; }

    public void setComponent_name(String component_name) { this.component_name = component_name; }

    public String getAction_name() { return action_name; }

    public void setAction_name(String action_name) { this.action_name = action_name; }

    public Integer getNo_of_param() { return no_of_param; }

    public void setNo_of_param(Integer no_of_param) { this.no_of_param = no_of_param; }

    public String getParam_1() { return param_1; }

    public void setParam_1(String param_1) { this.param_1 = param_1; }

    public String getParam_2() { return param_2; }

    public void setParam_2(String param_2) { this.param_2 = param_2; }

    public String getParam_3() { return param_3; }

    public void setParam_3(String param_3) { this.param_3 = param_3; }

    public String getParam_4() { return param_4; }

    public void setParam_4(String param_4) { this.param_4 = param_4; }

    public String getUrl_for() { return url_for; }

    public void setUrl_for(String url_for) { this.url_for = url_for; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public String getCreated_by() { return created_by; }

    public void setCreated_by(String created_by) { this.created_by = created_by; }

    public String getCreated_ip() { return created_ip; }

    public void setCreated_ip(String created_ip) { this.created_ip = created_ip; }

    public Date getUpdated_at() { return updated_at; }

    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public String getUpdated_by() { return updated_by; }

    public void setUpdated_by(String updated_by) { this.updated_by = updated_by; }

    public String getUpdate_ip() { return update_ip; }

    public void setUpdate_ip(String update_ip) { this.update_ip = update_ip; }

    public Integer getEditable() { return editable; }

    public void setEditable(Integer editable) { this.editable = editable; }

    public String getModule_name() { return module_name; }

    public void setModule_name(String module_name) { this.module_name = module_name; }
}