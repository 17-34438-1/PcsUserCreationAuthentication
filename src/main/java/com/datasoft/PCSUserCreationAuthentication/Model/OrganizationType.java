package com.datasoft.PCSUserCreationAuthentication.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrganizationType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Org_Type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrg_Type() {
        return Org_Type;
    }

    public void setOrg_Type(String org_Type) {
        Org_Type = org_Type;
    }
}
