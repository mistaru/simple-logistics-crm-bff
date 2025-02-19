package kg.founders.core.repo.dictionaries;

import kg.founders.core.entity.dictionaries.CountryDict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDictRepo extends JpaRepository<CountryDict, Long> {
}
