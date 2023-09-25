package com.datasoft.PCSUserCreationAuthentication.Repository;

import com.datasoft.PCSUserCreationAuthentication.Model.Module;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Long>{

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_module WHERE module_name=:module_name", nativeQuery = true)
    Integer isExists(@Param("module_name") String module_name);

    @Modifying()
    @Query(value = "INSERT INTO cchaportdb.user_module(module_name,created_ip,created_at) " +
            "VALUES(:module_name,:ip_address,NOW())", nativeQuery = true)
    @Transactional
    Integer insertUserModule(@Param("module_name") String module_name, @Param("ip_address") String ip_address);

    @Query(value = "SELECT * FROM cchaportdb.user_module", nativeQuery = true)
    public List<Module> UserModuleList();

    @Query(value = "SELECT * FROM cchaportdb.user_module WHERE id=:id", nativeQuery = true)
    public Module getUserModuleById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_module " +
            "WHERE (module_name=:module_name) AND (id!=:id)", nativeQuery = true)
    Integer isUnique(@Param("id") Long id,@Param("module_name") String module_name);

    @Modifying()
    @Query(value = "UPDATE cchaportdb.user_module SET module_name=:module_name, " +
            "updated_at=NOW(),update_ip=:update_ip WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer editUserModule(@Param("id") Long id,
                            @Param("module_name") String module_name,
                            @Param("update_ip") String update_ip);

    @Query(value = "SELECT COUNT(*) FROM cchaportdb.user_enlist_url WHERE user_module_id=:id", nativeQuery = true)
    Integer chkEnlistedURL(@Param("id") Long id);

    @Modifying()
    @Query(value = "DELETE FROM cchaportdb.user_module WHERE id=:id", nativeQuery = true)
    @Transactional
    Integer deleteUserModuleById(@Param("id") Long id);
}
