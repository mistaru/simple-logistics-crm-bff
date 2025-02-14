package kg.founders.core.data_access_layer.dao.impl;

import kg.founders.core.data_access_layer.dao.AuthRoleDao;
import kg.founders.core.entity.auth.role.LogisticAuthRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthRoleDaoImpl implements AuthRoleDao {

    JdbcTemplate jdbcTemplate;

    @Override
    public void updateActiveRole(Long authId, Long roleId) {
        jdbcTemplate.update("UPDATE " + LogisticAuthRole.TABLE_NAME + " " +
                        " SET ACTIVE = (CASE WHEN BANK_ROLE_ID = ? THEN 1 ELSE 0 END) " +
                        " WHERE BANK_AUTH_ID = ?"
                , roleId, authId);
    }
}