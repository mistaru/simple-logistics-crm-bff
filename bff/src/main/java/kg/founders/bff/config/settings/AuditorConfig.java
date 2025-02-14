package kg.founders.bff.config.settings;

import kg.founders.core.model.audit.AuditModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorConfig implements AuditorAware<AuditModel> {
    @Override
    public @NotNull Optional<AuditModel> getCurrentAuditor() {
        return TokenContextHolder.currentOptional().map(authenticated ->
                new AuditModel(authenticated.getPrincipal().getId(), "AUTH")
        );
    }
}
