package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.URL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface URLRepository extends CrudRepository<URL, Long> {

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_module WHERE id=:user_module_id", nativeQuery = true)
    Integer isUserModuleExists(@Param("user_module_id") Integer user_module_id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url " +
            "WHERE user_module_id=:user_module_id AND label_name=:label_name", nativeQuery = true)
    Integer isExists(@Param("user_module_id") Integer user_module_id,@Param("label_name") String label_name);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_enlist_url(user_module_id,label_name,component_name,action_name,no_of_param,param_1,param_2,param_3,param_4,url_for,editable,created_at,created_ip) " +
            "VALUES(:user_module_id,:label_name,:component_name,:action_name,:no_of_param,:param_1,:param_2,:param_3,:param_4,:url_for,:editable,NOW(),:created_ip)", nativeQuery = true)
    @Transactional
    Integer insertUserEnlistURL(@Param("user_module_id") Integer user_module_id,
                             @Param("label_name") String label_name,
                             @Param("component_name") String component_name,
                             @Param("action_name") String action_name,
                             @Param("no_of_param") Integer no_of_param,
                             @Param("param_1") String param_1,
                             @Param("param_2") String param_2,
                             @Param("param_3") String param_3,
                             @Param("param_4") String param_4,
                             @Param("url_for") String url_for,
                             @Param("editable") Integer editable,
                             @Param("created_ip") String created_ip);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id", nativeQuery = true)
    public List<URL> UserEnlistUrlList();

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id", nativeQuery = true)
    public List[] UrlList();

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_enlist_url\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id = cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_enlist_url.id=:id", nativeQuery = true)
    public URL getUserEnlistURLById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url " +
            "WHERE (user_module_id=:user_module_id AND label_name=:label_name) AND (id!=:id)", nativeQuery = true)
    Integer isUnique(@Param("id") Long id,@Param("user_module_id") Integer user_module_id,@Param("label_name") String label_name);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.user_enlist_url SET user_module_id=:user_module_id,label_name=:label_name,component_name=:component_name," +
            "action_name=:action_name,no_of_param=:no_of_param,param_1=:param_1,param_2=:param_2,param_3=:param_3,param_4=:param_4," +
            "url_for=:url_for,editable=:editable,updated_at=NOW(),update_ip=:update_ip WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer editUserEnlistURL(@Param("id") Long id,
                           @Param("user_module_id") Integer user_module_id,
                           @Param("label_name") String label_name,
                           @Param("component_name") String component_name,
                           @Param("action_name") String action_name,
                           @Param("no_of_param") Integer no_of_param,
                           @Param("param_1") String param_1,
                           @Param("param_2") String param_2,
                           @Param("param_3") String param_3,
                           @Param("param_4") String param_4,
                           @Param("url_for") String url_for,
                           @Param("editable") Integer editable,
                           @Param("update_ip") String update_ip);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_assign_role WHERE user_url_id=:id", nativeQuery = true)
    Integer chkUserAssignRole(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_enlist_url WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer deleteUserEnlistUrlById(@Param("id") Long id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM user_enlist_url \n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id=cchaportdb.user_module.id\n" +
            "WHERE user_module_id=:module_id", nativeQuery = true)
    public List<URL> urlByModule(@Param("module_id") Integer module_id);

    @Query(value = "SELECT cchaportdb.user_enlist_url.*,cchaportdb.user_module.module_name\n" +
            "FROM cchaportdb.user_assign_role\n" +
            "INNER JOIN cchaportdb.user_enlist_url ON cchaportdb.user_assign_role.user_url_id=cchaportdb.user_enlist_url.id\n" +
            "INNER JOIN cchaportdb.user_module ON cchaportdb.user_enlist_url.user_module_id=cchaportdb.user_module.id\n" +
            "WHERE cchaportdb.user_assign_role.user_role_id=:user_role_id", nativeQuery = true)
    public List<URL> urlByRole(@Param("user_role_id") Integer user_role_id);
}
