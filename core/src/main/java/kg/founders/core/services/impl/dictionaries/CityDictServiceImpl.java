package kg.founders.core.services.impl.dictionaries;

import kg.founders.core.repo.dictionaries.CityDictRepo;
import kg.founders.core.services.dictionaries.CityDictService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CityDictServiceImpl implements CityDictService {
    CityDictRepo cityDictRepo;
}
