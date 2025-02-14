package kg.founders.core.model.audit;

import javax.persistence.Embeddable;

@Embeddable
public class ModifiedByDetails extends AuditModel {
    public ModifiedByDetails(Long id, String table) {
        super(id, table);
    }

    public ModifiedByDetails() {
    }
}