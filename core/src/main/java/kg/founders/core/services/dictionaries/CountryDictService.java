package kg.founders.core.services.dictionaries;

import kg.founders.core.model.dictionaries.CountryDictModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryDictService {

    List<CountryDictModel> getAll();

    Page<CountryDictModel> get(Pageable pageable);

    CountryDictModel create(CountryDictModel countryDictModel) throws Exception ;

    CountryDictModel update(CountryDictModel countryDictModel);

    void delete(Long id);
}
