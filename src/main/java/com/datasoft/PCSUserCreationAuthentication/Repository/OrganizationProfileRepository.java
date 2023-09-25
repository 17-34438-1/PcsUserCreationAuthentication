package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.OrganizationProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrganizationProfileRepository extends JpaRepository<OrganizationProfile, Long> {
    @Query(value = "SELECT cchaportdb.organization_profiles_test.id,Org_Type_id,Organization_Name,AIN_No,AIN_No_New,License_No,\n" +
            "IF(License_issue_Date=\"0000-00-00\", (NULL), License_issue_Date) AS License_issue_Date,\n" +
            "IF(Licence_Validity_Date=\"0000-00-00\", (NULL), Licence_Validity_Date) AS Licence_Validity_Date,\n" +
            "Address_1,Address_2,Address_3,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,signature,URL,\n" +
            "Last_Update_By_id,user_action,last_update,License_No_Dh,dummy,Agent_Code,payment_status,\n" +
            "IF(last_payment_date=\"0000-00-00\", (NULL), last_payment_date) AS last_payment_date,\n" +
            "IF(last_working_dt=\"0000-00-00\", (NULL), last_working_dt) AS last_working_dt,\n" +
            "last_workin_comment,block_open,block_open_by,block_open_comment,\n" +
            "IF(block_date=\"0000-00-00 00:00:00\", (NULL), block_date) AS block_date,\n" +
            "bizu_gkey,entered_by,entry_time,\n" +
            "cchaportdb.tbl_org_types.Org_Type\n" +
            "FROM cchaportdb.organization_profiles_test\n" +
            "INNER JOIN cchaportdb.tbl_org_types ON cchaportdb.organization_profiles_test.Org_Type_id=cchaportdb.tbl_org_types.id\n" +
            "ORDER BY cchaportdb.organization_profiles_test.id DESC", nativeQuery = true)
    public List<OrganizationProfile> organizationProfileList();


    @Query(value = "SELECT cchaportdb.organization_profiles_test.id,Org_Type_id,Organization_Name,AIN_No,AIN_No_New,License_No,\n" +
            "IF(License_issue_Date=\"0000-00-00\", (NULL), License_issue_Date) AS License_issue_Date,\n" +
            "IF(Licence_Validity_Date=\"0000-00-00\", (NULL), Licence_Validity_Date) AS Licence_Validity_Date,\n" +
            "Address_1,Address_2,Address_3,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,signature,URL,\n" +
            "Last_Update_By_id,user_action,last_update,License_No_Dh,dummy,Agent_Code,payment_status,\n" +
            "IF(last_payment_date=\"0000-00-00\", (NULL), last_payment_date) AS last_payment_date,\n" +
            "IF(last_working_dt=\"0000-00-00\", (NULL), last_working_dt) AS last_working_dt,\n" +
            "last_workin_comment,block_open,block_open_by,block_open_comment,\n" +
            "IF(block_date=\"0000-00-00 00:00:00\", (NULL), block_date) AS block_date,\n" +
            "bizu_gkey,entered_by,entry_time\n" +
            "FROM cchaportdb.organization_profiles_test", nativeQuery = true)
    public Page<OrganizationProfile> paginationList(PageRequest of);

    @Query(value = "SELECT cchaportdb.organization_profiles_test.id,Org_Type_id,Organization_Name,AIN_No,AIN_No_New,License_No,\n" +
            "IF(License_issue_Date=\"0000-00-00\", (NULL), License_issue_Date) AS License_issue_Date,\n" +
            "IF(Licence_Validity_Date=\"0000-00-00\", (NULL), Licence_Validity_Date) AS Licence_Validity_Date,\n" +
            "Address_1,Address_2,Address_3,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,signature,URL,\n" +
            "Last_Update_By_id,user_action,last_update,License_No_Dh,dummy,Agent_Code,payment_status,\n" +
            "IF(last_payment_date=\"0000-00-00\", (NULL), last_payment_date) AS last_payment_date,\n" +
            "IF(last_working_dt=\"0000-00-00\", (NULL), last_working_dt) AS last_working_dt,\n" +
            "last_workin_comment,block_open,block_open_by,block_open_comment,\n" +
            "IF(block_date=\"0000-00-00 00:00:00\", (NULL), block_date) AS block_date,\n" +
            "bizu_gkey,entered_by,entry_time,\n" +
            "cchaportdb.tbl_org_types.Org_Type\n" +
            "FROM cchaportdb.organization_profiles_test\n" +
            "INNER JOIN cchaportdb.tbl_org_types ON cchaportdb.organization_profiles_test.Org_Type_id=cchaportdb.tbl_org_types.id\n" +
            "WHERE cchaportdb.organization_profiles_test.id =:id", nativeQuery = true)
    public OrganizationProfile getOrganizationById(@Param("id") Long id);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.organization_profiles_test(Org_Type_id,Organization_Name,AIN_No,AIN_No_New,License_No,\n" +
            "Licence_Validity_Date,Address_1,Address_2,Telephone_No_Land,Cell_No_1,Cell_No_2,Fax_No,email,logo,URL,entry_time) \n" +
            "VALUES(:Org_Type_id,:Organization_Name,:AIN_No,:AIN_No,:License_No,:Licence_Validity_Date,:Address_1,:Address_2,\n" +
            ":Telephone_No_Land,:Cell_No_1,:Cell_No_2,:Fax_No,:email,:logo,:URL,NOW())", nativeQuery = true)
    @Transactional
    Integer insertOrganizationProfile(@Param("Org_Type_id") Integer Org_Type_id,
                                      @Param("Organization_Name") String Organization_Name,
                                      @Param("AIN_No") String AIN_No,
                                      @Param("License_No") String License_No,
                                      @Param("Licence_Validity_Date") String Licence_Validity_Date,
                                      @Param("Address_1") String Address_1,
                                      @Param("Address_2") String Address_2,
                                      @Param("Telephone_No_Land") String Telephone_No_Land,
                                      @Param("Cell_No_1") String Cell_No_1,
                                      @Param("Cell_No_2") String Cell_No_2,
                                      @Param("Fax_No") String Fax_No,
                                      @Param("email") String email,
                                      @Param("logo") String logo,
                                      @Param("URL") String URL);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.organization_profiles_test " +
            "WHERE (Org_Type_id=:Org_Type_id) AND (AIN_No=:AIN_No OR AIN_No_New=:AIN_No)", nativeQuery = true)
    Integer isExist(@Param("Org_Type_id") Integer Org_Type_id, @Param("AIN_No") String AIN_No);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.users_info WHERE users_info.login_id=:login_id AND users_info.org_id=:org_id", nativeQuery = true)
    Integer chkUser(@Param("login_id") String login_id, @Param("org_id") Long org_id);

    @Query(value = "SELECT id FROM cchaportdb.users_info " +
            "WHERE users_info.login_id=:login_id AND users_info.org_id=:org_id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Integer getUserId(@Param("login_id") String login_id, @Param("org_id") Long org_id);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.users_info SET users_info.u_name=:Organization_Name WHERE users_info.id=:user_id", nativeQuery = true)
    @Transactional
    Integer updateUserName(@Param("user_id") Integer user_id,@Param("Organization_Name") String Organization_Name);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.organization_profiles_test " +
            "WHERE (Org_Type_id=:Org_Type_id) AND (AIN_No=:AIN_No OR AIN_No_New=:AIN_No) AND (id!=:id)", nativeQuery = true)
    Integer isUnique(@Param("id") Long id, @Param("Org_Type_id") Integer Org_Type_id, @Param("AIN_No") String AIN_No);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.organization_profiles_test SET Org_Type_id=:Org_Type_id,Organization_Name=:Organization_Name,\n" +
            "AIN_No=:AIN_No,AIN_No_New=:AIN_No,License_No=:License_No,\n" +
            "Licence_Validity_Date=:Licence_Validity_Date,Address_1=:Address_1,Address_2=:Address_2,\n" +
            "Telephone_No_Land=:Telephone_No_Land,Cell_No_1=:Cell_No_1,Cell_No_2=:Cell_No_2,Fax_No=:Fax_No,\n" +
            "email=:email,logo=:logo,URL=:URL,last_update=NOW()\n" +
            "WHERE cchaportdb.organization_profiles_test.id=:id", nativeQuery = true)
    @Transactional
    Integer editOrganizationProfile(@Param("id") Long id,
                                    @Param("Org_Type_id") Integer Org_Type_id,
                                    @Param("Organization_Name") String Organization_Name,
                                    @Param("AIN_No") String AIN_No,
                                    @Param("License_No") String License_No,
                                    @Param("Licence_Validity_Date") String Licence_Validity_Date,
                                    @Param("Address_1") String Address_1,
                                    @Param("Address_2") String Address_2,
                                    @Param("Telephone_No_Land") String Telephone_No_Land,
                                    @Param("Cell_No_1") String Cell_No_1,
                                    @Param("Cell_No_2") String Cell_No_2,
                                    @Param("Fax_No") String Fax_No,
                                    @Param("email") String email,
                                    @Param("logo") String logo,
                                    @Param("URL") String URL);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.users_info WHERE org_id=:id", nativeQuery = true)
    Integer chkUsersInfo(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.organization_profiles_test WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer deleteOrganizationById(@Param("id") Long id);
}
