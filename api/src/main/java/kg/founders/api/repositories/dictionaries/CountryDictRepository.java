package kg.founders.api.repositories.dictionaries;

import kg.founders.common.entities.dictionaries.CountryDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDictRepository extends JpaRepository<CountryDict, Long> {
}
