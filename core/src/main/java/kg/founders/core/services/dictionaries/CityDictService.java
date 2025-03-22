package kg.founders.core.services.dictionaries;

import kg.founders.core.model.dictionaries.CityDictModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityDictService {

    List<CityDictModel> getAll();

    Page<CityDictModel> get(Pageable pageable);

    CityDictModel create(CityDictModel cityDictModel) throws Exception ;

    CityDictModel update(CityDictModel cityDictModel);

    void delete(Long id);
}
