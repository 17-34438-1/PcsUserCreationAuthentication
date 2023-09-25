package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationTypeRepository extends CrudRepository<OrganizationType, Long> {
    @Query(value = "SELECT id,Org_Type FROM cchaportdb.tbl_org_types", nativeQuery = true)
    public List<OrganizationType> organizationTypeList();

    @Query(value = "SELECT id,Org_Type FROM tbl_org_types " +
            "WHERE id IN(1,2,3,5,6,30,57,59,4,64,66,67,70,28,72,73,74,75,76)", nativeQuery = true)
    public List<OrganizationType> orgTypeListForUserCreation();

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.tbl_org_types WHERE id=:Org_Type_id", nativeQuery = true)
    Integer isOrgTypeExists(@Param("Org_Type_id") Integer Org_Type_id);
}
