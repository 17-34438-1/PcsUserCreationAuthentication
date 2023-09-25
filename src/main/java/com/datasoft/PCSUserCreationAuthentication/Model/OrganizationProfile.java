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
public class OrganizationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer Org_Type_id;
    private String Organization_Name;
    private String Org_Type;

    @JsonIgnore
    private String ain_No;

    private String AIN_No_New;
    private String License_No;

    @JsonIgnore
    private String license_issue_Date;

    private String Licence_Validity_Date;
    private String Address_1;
    private String Address_2;

    @JsonIgnore
    private String address_3;

    private String Telephone_No_Land;
    private String Cell_No_1;
    private String Cell_No_2;
    private String Fax_No;
    private String email;
    private String logo;

    @JsonIgnore
    private String signature;

    private String URL;

    @JsonIgnore
    private String last_update_by_id;

    @JsonIgnore
    private String user_action;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date last_update;

    @JsonIgnore
    private String license_no_dh;

    @JsonIgnore
    private Integer dummy;

    @JsonIgnore
    private String agent_Code;

    @JsonIgnore
    private String payment_status;

    //@JsonFormat(pattern="yyyy-MM-dd")
    @JsonIgnore
    private Date last_payment_date;

    //@JsonFormat(pattern="yyyy-MM-dd")
    @JsonIgnore
    private Date last_working_dt;


    @JsonIgnore
    private String last_workin_comment;

    @JsonIgnore
    private String block_open;

    @JsonIgnore
    private String block_open_by;

    @JsonIgnore
    private String block_open_comment;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date block_date;

    @JsonIgnore
    private Long bizu_gkey;

    @JsonIgnore
    private String entered_by;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date entry_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrg_Type_id() {
        return Org_Type_id;
    }

    public void setOrg_Type_id(Integer org_Type_id) {
        Org_Type_id = org_Type_id;
    }

    public String getOrganization_Name() {
        return Organization_Name;
    }

    public void setOrganization_Name(String organization_Name) {
        Organization_Name = organization_Name;
    }

    public String getOrg_Type() {
        return Org_Type;
    }

    public void setOrg_Type(String org_Type) {
        Org_Type = org_Type;
    }

    public String getAin_No() {
        return ain_No;
    }

    public void setAin_No(String ain_No) {
        this.ain_No = ain_No;
    }

    public String getAIN_No_New() {
        return AIN_No_New;
    }

    public void setAIN_No_New(String AIN_No_New) {
        this.AIN_No_New = AIN_No_New;
    }

    public String getLicense_No() {
        return License_No;
    }

    public void setLicense_No(String license_No) {
        License_No = license_No;
    }

    public String getLicense_issue_Date() {
        return license_issue_Date;
    }

    public void setLicense_issue_Date(String license_issue_Date) {
        this.license_issue_Date = license_issue_Date;
    }

    public String getLicence_Validity_Date() {
        return Licence_Validity_Date;
    }

    public void setLicence_Validity_Date(String licence_Validity_Date) {
        Licence_Validity_Date = licence_Validity_Date;
    }

    public String getAddress_1() {
        return Address_1;
    }

    public void setAddress_1(String address_1) {
        Address_1 = address_1;
    }

    public String getAddress_2() {
        return Address_2;
    }

    public void setAddress_2(String address_2) {
        Address_2 = address_2;
    }

    public String getAddress_3() {
        return address_3;
    }

    public void setAddress_3(String address_3) {
        this.address_3 = address_3;
    }

    public String getTelephone_No_Land() {
        return Telephone_No_Land;
    }

    public void setTelephone_No_Land(String telephone_No_Land) {
        Telephone_No_Land = telephone_No_Land;
    }

    public String getCell_No_1() {
        return Cell_No_1;
    }

    public void setCell_No_1(String cell_No_1) {
        Cell_No_1 = cell_No_1;
    }

    public String getCell_No_2() {
        return Cell_No_2;
    }

    public void setCell_No_2(String cell_No_2) {
        Cell_No_2 = cell_No_2;
    }

    public String getFax_No() {
        return Fax_No;
    }

    public void setFax_No(String fax_No) {
        Fax_No = fax_No;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getLast_update_by_id() {
        return last_update_by_id;
    }

    public void setLast_update_by_id(String last_update_by_id) {
        this.last_update_by_id = last_update_by_id;
    }

    public String getUser_action() {
        return user_action;
    }

    public void setUser_action(String user_action) {
        this.user_action = user_action;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getLicense_no_dh() {
        return license_no_dh;
    }

    public void setLicense_no_dh(String license_no_dh) {
        this.license_no_dh = license_no_dh;
    }

    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer dummy) {
        this.dummy = dummy;
    }

    public String getAgent_Code() {
        return agent_Code;
    }

    public void setAgent_Code(String agent_Code) {
        this.agent_Code = agent_Code;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public Date getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(Date last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    public Date getLast_working_dt() {
        return last_working_dt;
    }

    public void setLast_working_dt(Date last_working_dt) {
        this.last_working_dt = last_working_dt;
    }

    public String getLast_workin_comment() {
        return last_workin_comment;
    }

    public void setLast_workin_comment(String last_workin_comment) {
        this.last_workin_comment = last_workin_comment;
    }

    public String getBlock_open() {
        return block_open;
    }

    public void setBlock_open(String block_open) {
        this.block_open = block_open;
    }

    public String getBlock_open_by() {
        return block_open_by;
    }

    public void setBlock_open_by(String block_open_by) {
        this.block_open_by = block_open_by;
    }

    public String getBlock_open_comment() {
        return block_open_comment;
    }

    public void setBlock_open_comment(String block_open_comment) {
        this.block_open_comment = block_open_comment;
    }

    public Date getBlock_date() {
        return block_date;
    }

    public void setBlock_date(Date block_date) {
        this.block_date = block_date;
    }

    public Long getBizu_gkey() {
        return bizu_gkey;
    }

    public void setBizu_gkey(Long bizu_gkey) {
        this.bizu_gkey = bizu_gkey;
    }

    public String getEntered_by() {
        return entered_by;
    }

    public void setEntered_by(String entered_by) {
        this.entered_by = entered_by;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }
}
