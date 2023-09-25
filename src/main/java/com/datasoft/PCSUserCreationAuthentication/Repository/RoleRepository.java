package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_role WHERE role_name=:role_name", nativeQuery = true)
    Integer isExists(@Param("role_name") String role_name);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_role(role_name,created_ip,created_at) " +
            "VALUES(:role_name,:ip_address,NOW())", nativeQuery = true)
    @Transactional
    Integer insertUserRole(@Param("role_name") String role_name, @Param("ip_address") String ip_address);

    @Query(value = "SELECT * FROM cchaportdb.user_role", nativeQuery = true)
    public List<Role> UserRoleList();

    @Query(value = "SELECT * FROM cchaportdb.user_role WHERE id=:id", nativeQuery = true)
    public Role getUserRoleById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_role " +
            "WHERE (role_name=:role_name) AND (id!=:id)", nativeQuery = true)
    Integer isUnique(@Param("id") Long id,@Param("role_name") String role_name);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.user_role SET role_name=:role_name, " +
            "updated_at=NOW(),update_ip=:update_ip WHERE id=:id", nativeQuery = true)
    @Transactional

    Integer editUserRole(@Param("id") Long id,
                           @Param("role_name") String role_name,
                         @Param("update_ip") String update_ip);


    @Query(value = "SELECT COUNT(*) FROM cchaportdb.users_info WHERE role_id=:id", nativeQuery = true)
    Integer chkUserInfo(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE user_role_id=:id", nativeQuery = true)
    Integer chkUserAssignRole(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_role WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer deleteUserRoleById(@Param("id") Long id);
}
