package com.datasoft.PCSUserCreationAuthentication.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class URLAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer user_role_id;
    Integer[] user_url_id;

    private String role_name;
    String[] url_label;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    public Integer[] getUser_url_id() {
        return user_url_id;
    }

    public void setUser_url_id(Integer[] user_url_id) {
        this.user_url_id = user_url_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String[] getUrl_label() {
        return url_label;
    }

    public void setUrl_label(String[] url_label) {
        this.url_label = url_label;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_ip() {
        return created_ip;
    }

    public void setCreated_ip(String created_ip) {
        this.created_ip = created_ip;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getUpdate_ip() {
        return update_ip;
    }

    public void setUpdate_ip(String update_ip) {
        this.update_ip = update_ip;
    }
}
