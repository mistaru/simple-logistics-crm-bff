package kg.founders.api.services.dictionaries;

import kg.founders.common.models.dictionaries.countryDict.CountryDictModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryDictService {
    Page<CountryDictModel> get(Pageable pageable);

    CountryDictModel create(CountryDictModel countryDictModel) throws Exception ;

    CountryDictModel update(CountryDictModel countryDictModel);
}
