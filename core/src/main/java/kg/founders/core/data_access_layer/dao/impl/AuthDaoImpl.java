package kg.founders.core.data_access_layer.dao.impl;

import kg.founders.core.data_access_layer.dao.AuthDao;
import kg.founders.core.entity.auth.LogisticAuth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Slf4j
@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthDaoImpl implements AuthDao {
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean blockAuth(String username, boolean block) {
        return jdbcTemplate.update("UPDATE " + LogisticAuth.TABLE_NAME + " SET BLOCKED = ? WHERE USERNAME = ?", block ? new Timestamp(System.currentTimeMillis()) : null, username) == 1;
    }

    @Override
    public Boolean isBlocked(Long id) {
        return jdbcTemplate.queryForObject("SELECT BLOCKED IS NOT NULL FROM " + LogisticAuth.TABLE_NAME + " WHERE ID = ?", Boolean.class, id);
    }
}