package com.validation.strategy;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.regex.Pattern;
import com.validation.annotation.NrIndeksu;

public class NrIndeksuStrategy implements ValidationStrategy {
    private static final String NR_INDEKSU_PATTERN = "^\\d{6}$";

    @Override
    public Optional<String> validate(Field field, Object value) {
        if (field.isAnnotationPresent(NrIndeksu.class) && value != null) {
            String nrIndeksu = value.toString();

            if(!Pattern.compile(NR_INDEKSU_PATTERN).matcher(nrIndeksu).matches()) {
                NrIndeksu annotation = field.getAnnotation(NrIndeksu.class);
                String errorInfo = String.format("Pole %s: %s", field.getName(), annotation.message());
                return Optional.of(errorInfo);
            }
        }
        return Optional.empty();
    }
}