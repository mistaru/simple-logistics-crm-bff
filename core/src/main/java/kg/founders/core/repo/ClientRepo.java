package kg.founders.core.repo;

import kg.founders.core.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByClientCode(String clientCode);

    List<Client> findAllByManagerId(Long managerId);
}