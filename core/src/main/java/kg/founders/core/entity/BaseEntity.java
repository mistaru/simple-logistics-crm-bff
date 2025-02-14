package kg.founders.core.entity;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import kg.founders.core.model.audit.AuditModel;
import kg.founders.core.model.audit.CreatedByDetails;
import kg.founders.core.model.audit.ModifiedByDetails;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity {
    @Embedded
    @CreatedBy
    @Column(
        updatable = false
    )
    @AttributeOverrides({@AttributeOverride(
    name = "id",
    column = @Column(
    name = "created_by_id"
)
), @AttributeOverride(
    name = "table",
    column = @Column(
    name = "created_by_table"
)
)})
    protected AuditModel createdBy;
    @Embedded
    @LastModifiedBy
    @AttributeOverrides({@AttributeOverride(
    name = "id",
    column = @Column(
    name = "modified_by_id"
)
), @AttributeOverride(
    name = "table",
    column = @Column(
    name = "modified_by_table"
)
)})
    protected AuditModel modifiedBy;
    @CreatedDate
    @Column(
        updatable = false
    )
    protected Timestamp cdt;
    @LastModifiedDate
    protected Timestamp mdt;
    protected Timestamp rdt;

    public void markDeleted() {
        this.rdt = new Timestamp(System.currentTimeMillis());
    }

    public void setCreatedBy(AuditModel createdBy) {
        this.createdBy = new CreatedByDetails(createdBy.getId(), createdBy.getTable());
    }

    public void setModifiedBy(AuditModel modifiedBy) {
        this.modifiedBy = new ModifiedByDetails(modifiedBy.getId(), modifiedBy.getTable());
    }

    public void setAuditorDataById(Long id, AuditModel auditor) {
        if (id == null || this.createdBy == null || this.createdBy.getId() == null) {
            this.createdBy = new ModifiedByDetails(auditor.getId(), auditor.getTable());
        }

        this.modifiedBy = new ModifiedByDetails(auditor.getId(), auditor.getTable());
        this.mdt = new Timestamp(System.currentTimeMillis());
    }

    private static Timestamp $default$cdt() {
        return new Timestamp(System.currentTimeMillis());
    }

    private static Timestamp $default$mdt() {
        return new Timestamp(System.currentTimeMillis());
    }

    protected BaseEntity(final BaseEntityBuilder<?, ?> b) {
        this.createdBy = b.createdBy;
        this.modifiedBy = b.modifiedBy;
        if (b.cdt$set) {
            this.cdt = b.cdt$value;
        } else {
            this.cdt = $default$cdt();
        }

        if (b.mdt$set) {
            this.mdt = b.mdt$value;
        } else {
            this.mdt = $default$mdt();
        }

        this.rdt = b.rdt;
    }

    public AuditModel getCreatedBy() {
        return this.createdBy;
    }

    public AuditModel getModifiedBy() {
        return this.modifiedBy;
    }

    public Timestamp getCdt() {
        return this.cdt;
    }

    public Timestamp getMdt() {
        return this.mdt;
    }

    public Timestamp getRdt() {
        return this.rdt;
    }

    public void setCdt(final Timestamp cdt) {
        this.cdt = cdt;
    }

    public void setMdt(final Timestamp mdt) {
        this.mdt = mdt;
    }

    public void setRdt(final Timestamp rdt) {
        this.rdt = rdt;
    }

    public BaseEntity() {
        this.cdt = $default$cdt();
        this.mdt = $default$mdt();
    }

    public BaseEntity(final AuditModel createdBy, final AuditModel modifiedBy, final Timestamp cdt, final Timestamp mdt, final Timestamp rdt) {
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.cdt = cdt;
        this.mdt = mdt;
        this.rdt = rdt;
    }

    public abstract static class BaseEntityBuilder<C extends BaseEntity, B extends BaseEntityBuilder<C, B>> {
        private AuditModel createdBy;
        private AuditModel modifiedBy;
        private boolean cdt$set;
        private Timestamp cdt$value;
        private boolean mdt$set;
        private Timestamp mdt$value;
        private Timestamp rdt;

        public BaseEntityBuilder() {
        }

        public B createdBy(final AuditModel createdBy) {
            this.createdBy = createdBy;
            return this.self();
        }

        public B modifiedBy(final AuditModel modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this.self();
        }

        public B cdt(final Timestamp cdt) {
            this.cdt$value = cdt;
            this.cdt$set = true;
            return this.self();
        }

        public B mdt(final Timestamp mdt) {
            this.mdt$value = mdt;
            this.mdt$set = true;
            return this.self();
        }

        public B rdt(final Timestamp rdt) {
            this.rdt = rdt;
            return this.self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseEntity.BaseEntityBuilder(createdBy=" + this.createdBy + ", modifiedBy=" + this.modifiedBy + ", cdt$value=" + this.cdt$value + ", mdt$value=" + this.mdt$value + ", rdt=" + this.rdt + ")";
        }
    }
}
