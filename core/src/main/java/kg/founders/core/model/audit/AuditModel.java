package kg.founders.core.model.audit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class AuditModel {
    private Long id;
    private String table;

    public AuditModel() {
    }

    public AuditModel(final Long id, final String table) {
        this.id = id;
        this.table = table;
    }
}