package kg.founders.core.services;


import kg.founders.core.entity.LogisticOldPassword;

import java.util.List;

public interface OldPasswordService {
    LogisticOldPassword save(LogisticOldPassword logisticOldPassword);

    List<LogisticOldPassword> getLast5RowsByAuthId(Long authId);
}