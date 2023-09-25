package com.datasoft.PCSUserCreationAuthentication.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer role_id;
    private String u_name;
    private String login_id;
    private String login_password;
    private String address;
    private String telephone_no;
    private String mobile_no;
    private String email;
    private String image_path;
    private Integer org_id;
    private String ptext;

    private String role_name;
    private String organization_name;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private String expire_date;

    @JsonIgnore
    private Integer first_login_track;

    @JsonIgnore
    private Integer two_stp_st;

    @JsonIgnore
    private String otp_code;

    @JsonIgnore
    private String two_stp_verify_st;

    @JsonIgnore
    private Date entry_at;

    @JsonIgnore
    private String entry_by;

    @JsonIgnore
    private String entry_ip;

    @JsonIgnore
    private Date update_at;

    @JsonIgnore
    private String update_by;

    @JsonIgnore
    private String update_ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public String getPtext() {
        return ptext;
    }

    public void setPtext(String ptext) {
        this.ptext = ptext;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    public Integer getFirst_login_track() {
        return first_login_track;
    }

    public void setFirst_login_track(Integer first_login_track) {
        this.first_login_track = first_login_track;
    }

    public Integer getTwo_stp_st() {
        return two_stp_st;
    }

    public void setTwo_stp_st(Integer two_stp_st) {
        this.two_stp_st = two_stp_st;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }

    public String getTwo_stp_verify_st() {
        return two_stp_verify_st;
    }

    public void setTwo_stp_verify_st(String two_stp_verify_st) {
        this.two_stp_verify_st = two_stp_verify_st;
    }

    public Date getEntry_at() {
        return entry_at;
    }

    public void setEntry_at(Date entry_at) {
        this.entry_at = entry_at;
    }

    public String getEntry_by() {
        return entry_by;
    }

    public void setEntry_by(String entry_by) {
        this.entry_by = entry_by;
    }

    public String getEntry_ip() {
        return entry_ip;
    }

    public void setEntry_ip(String entry_ip) {
        this.entry_ip = entry_ip;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_ip() {
        return update_ip;
    }

    public void setUpdate_ip(String update_ip) {
        this.update_ip = update_ip;
    }
}
