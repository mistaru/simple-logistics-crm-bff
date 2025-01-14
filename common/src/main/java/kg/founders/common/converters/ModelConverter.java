package kg.founders.common.converters;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelConverter<Model, Entity> {
    protected Function<Model, Entity> fromModel;
    protected Function<Entity, Model> fromEntity;

    public final Entity convertFromModel(final Model model) {
        return fromModel.apply(model);
    }

    public final Model convertFromEntity(final Entity entity) {
        return fromEntity.apply(entity);
    }

    public final List<Entity> createFromModels(final Collection<Model> models) {
        return models == null ? null : models.stream().map(this::convertFromModel).collect(Collectors.toList());
    }

    public final List<Model> createFromEntities(final Collection<Entity> entities) {
        return entities == null ? null : entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
