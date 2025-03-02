package kg.founders.core.services.impl;

import kg.founders.core.repo.CargoRepo;
import kg.founders.core.services.CargoService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;


@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CargoServiceImpl implements CargoService {
    CargoRepo repo;
}
