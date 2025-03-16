package kg.founders.bff.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.founders.core.enums.permission.PermissionType;
import kg.founders.core.enums.permission.ScreenType;
import lombok.Value;

import java.util.List;

@Value
public class PermissionGroup {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    ScreenType screen;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    List<PermissionType> permissions;
}
