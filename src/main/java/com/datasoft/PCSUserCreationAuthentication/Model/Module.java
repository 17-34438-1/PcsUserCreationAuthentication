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
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public String getModule_name() { return module_name; }

    public void setModule_name(String module_name) { this.module_name = module_name; }

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
}
