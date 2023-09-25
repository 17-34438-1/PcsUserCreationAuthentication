package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.URLAssign;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface URLAssignRepository extends CrudRepository<URLAssign, Long> {
    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_role WHERE id=:user_role_id", nativeQuery = true)
    Integer chkRoleId(@Param("user_role_id") Integer user_role_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url WHERE id=:url_id", nativeQuery = true)
    Integer chkURLId(@Param("url_id") Integer url_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE user_role_id=:user_role_id AND user_url_id=:user_url_id", nativeQuery = true)
    Integer cntURLAssignment(@Param("user_role_id") Integer user_role_id,
                             @Param("user_url_id") Integer user_url_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE (user_role_id=:user_role_id AND user_url_id=:user_url_id) AND (id!=:id)", nativeQuery = true)
    Integer isURLAssignmentUnique(@Param("user_role_id") Integer user_role_id,@Param("user_url_id") Integer user_url_id,@Param("id") Long id);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_assign_role(user_role_id,user_url_id,created_at,created_ip) " +
            "VALUES(:user_role_id,:url_id,NOW(),:ip_address)", nativeQuery = true)
    @Transactional
    Integer insertURLAssignment(@Param("user_role_id") Integer user_role_id,
                                @Param("url_id") Integer url_id,
                                @Param("ip_address") String ip_address);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_assign_role WHERE user_role_id=:user_role_id", nativeQuery = true)
    @Transactional
    Integer deleteURLAssignmentByRole(@Param("user_role_id") Integer user_role_id);

    @Query(value = "SELECT DISTINCT cchaportdb.user_assign_role.user_role_id FROM user_assign_role", nativeQuery = true)
    public List[] assignedRoleList();

    @Query(value = "SELECT * FROM cchaportdb.user_assign_role WHERE user_role_id=:user_role_id", nativeQuery = true)
    public List[] urlAssignmentByRole(@Param("user_role_id") Integer user_role_id);

    @Query(value = "SELECT role_name FROM cchaportdb.user_role WHERE id=:user_role_id", nativeQuery = true)
    String getRoleName(@Param("user_role_id") Integer user_role_id);

    @Query(value = "SELECT label_name FROM cchaportdb.user_enlist_url WHERE id=:user_url_id", nativeQuery = true)
    String getURLLabel(@Param("user_url_id") Integer user_url_id);
}
