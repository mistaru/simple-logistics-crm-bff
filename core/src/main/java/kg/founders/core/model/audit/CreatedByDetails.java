package kg.founders.core.model.audit;

import javax.persistence.Embeddable;

@Embeddable
public class CreatedByDetails extends AuditModel {
    public CreatedByDetails(Long id, String table) {
        super(id, table);
    }

    public CreatedByDetails() {
    }
}
