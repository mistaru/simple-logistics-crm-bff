package kg.founders.core.repo.dictionaries;

import kg.founders.core.entity.dictionaries.CityDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDictRepo extends JpaRepository<CityDict, Long> {
}
