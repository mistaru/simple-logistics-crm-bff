package kg.founders.core.services.impl.dictionaries;

import kg.founders.core.repo.dictionaries.CountryDictRepo;
import kg.founders.core.services.dictionaries.CountryDictService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CountryDictServiceImpl implements CountryDictService {
    CountryDictRepo countryDictRepo;
}
