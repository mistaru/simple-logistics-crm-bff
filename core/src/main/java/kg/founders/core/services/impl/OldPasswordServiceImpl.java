package kg.founders.core.services.impl;

import kg.founders.core.entity.LogisticOldPassword;
import kg.founders.core.repo.OldPasswordRepo;
import kg.founders.core.services.OldPasswordService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OldPasswordServiceImpl implements OldPasswordService {

    OldPasswordRepo repo;

    @Override
    public LogisticOldPassword save(LogisticOldPassword logisticOldPassword) {
        return repo.save(logisticOldPassword);
    }

    @Override
    public List<LogisticOldPassword> getLast5RowsByAuthId(Long authId) {
        return repo.findTop5ByLogisticAuthIdOrderByIdDesc(authId);
    }
}