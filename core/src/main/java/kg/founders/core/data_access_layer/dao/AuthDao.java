package kg.founders.core.data_access_layer.dao;

public interface AuthDao {

    boolean blockAuth(String username, boolean block);

    Boolean isBlocked(Long id);
}