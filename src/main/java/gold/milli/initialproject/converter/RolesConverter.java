package gold.milli.initialproject.converter;

import gold.milli.initialproject.entity.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<List<Role>,String > {


    @Override
    public String convertToDatabaseColumn(List<Role> attribute) {
        return attribute.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public List<Role> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(",")).map(Role::valueOf).collect(Collectors.toList());
    }
}
