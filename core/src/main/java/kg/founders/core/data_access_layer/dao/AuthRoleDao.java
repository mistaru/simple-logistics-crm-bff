package kg.founders.core.data_access_layer.dao;

public interface AuthRoleDao {
    void updateActiveRole(Long authId, Long roleId);
}