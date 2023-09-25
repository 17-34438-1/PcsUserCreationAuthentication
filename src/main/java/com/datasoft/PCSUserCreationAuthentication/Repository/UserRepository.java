package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_role WHERE id=:role_id", nativeQuery = true)
    Integer isRoleIdExists(@Param("role_id") Integer role_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.organization_profiles_test WHERE id=:org_id", nativeQuery = true)
    Integer isOrganizationExists(@Param("org_id") Integer org_id);

    @Query(value = "SELECT Organization_Name FROM cchaportdb.organization_profiles_test WHERE id=:org_id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public String organizationName(@Param("org_id") Integer org_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.users_info WHERE login_id=:login_id", nativeQuery = true)
    Integer isLoginIdExists(@Param("login_id") String login_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.users_info WHERE login_id=:login_id AND (id!=:id)", nativeQuery = true)
    Integer isLoginIdUnique(@Param("id") Long id,@Param("login_id") String login_id);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.users_info(role_id,u_name,login_id,login_password,address,telephone_no,mobile_no,email,image_path,org_id,ptext,expire_date,entry_at,entry_ip) " +
            "VALUES(:role_id,:Organization_Name,:login_id,:login_password,:address,:telephone_no,:mobile_no,:email,:image_path,:org_id,:ptext,:expire_date,NOW(),:entry_ip)", nativeQuery = true)
    @Transactional
    Integer insertUser(@Param("role_id") Integer role_id,
                        @Param("Organization_Name") String Organization_Name,
                        @Param("login_id") String login_id,
                        @Param("login_password") String login_password,
                        @Param("address") String address,
                        @Param("telephone_no") String telephone_no,
                        @Param("mobile_no") String mobile_no,
                        @Param("email") String email,
                        @Param("image_path") String image_path,
                        @Param("org_id") Integer org_id,
                        @Param("ptext") String ptext,
                        @Param("expire_date") String expire_date,
                        @Param("entry_ip") String entry_ip);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.users_info SET role_id=:role_id,u_name=:Organization_Name,login_id=:login_id,login_password=:login_password,\n" +
            "address=:address,telephone_no=:telephone_no,mobile_no=:mobile_no,email=:email,image_path=:image_path,org_id=:org_id,ptext=:ptext,\n" +
            "expire_date=:expire_date,update_at=NOW(),update_ip=:update_ip\n" +
            "WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer editUser(@Param("id") Long id,
                    @Param("role_id") Integer role_id,
                    @Param("Organization_Name") String Organization_Name,
                    @Param("login_id") String login_id,
                    @Param("login_password") String login_password,
                    @Param("address") String address,
                    @Param("telephone_no") String telephone_no,
                    @Param("mobile_no") String mobile_no,
                    @Param("email") String email,
                    @Param("image_path") String image_path,
                    @Param("org_id") Integer org_id,
                    @Param("ptext") String ptext,
                    @Param("expire_date") String expire_date,
                    @Param("update_ip") String update_ip);

    @Query(value = "SELECT cchaportdb.users_info.*,cchaportdb.user_role.role_name,cchaportdb.organization_profiles_test.Organization_Name\n" +
            "FROM cchaportdb.users_info\n" +
            "INNER JOIN cchaportdb.user_role ON cchaportdb.users_info.role_id=cchaportdb.user_role.id\n" +
            "INNER JOIN cchaportdb.organization_profiles_test ON cchaportdb.users_info.org_id=cchaportdb.organization_profiles_test.id", nativeQuery = true)
    public List<User> list();

    @Query(value = "SELECT cchaportdb.users_info.*,cchaportdb.user_role.role_name,cchaportdb.organization_profiles_test.Organization_Name\n" +
            "FROM cchaportdb.users_info\n" +
            "INNER JOIN cchaportdb.user_role ON cchaportdb.users_info.role_id=cchaportdb.user_role.id\n" +
            "INNER JOIN cchaportdb.organization_profiles_test ON cchaportdb.users_info.org_id=cchaportdb.organization_profiles_test.id\n" +
            "WHERE cchaportdb.users_info.id=:id", nativeQuery = true)
    public User getUserById(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.users_info WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer delete(@Param("id") Long id);
}
