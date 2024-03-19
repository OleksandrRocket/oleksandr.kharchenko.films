package ua.javarush.film.converter;

import jakarta.persistence.Converter;
import org.hibernate.type.StandardConverter;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.java.StringJavaType;
import ua.javarush.film.entity.SpecialFeatures;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class FeaturesSetStringConverter implements StandardConverter<Set<SpecialFeatures>, String> {
    @Override
    public String convertToDatabaseColumn(Set<SpecialFeatures> attribute) {
        return toRelationalValue(attribute);
    }

    @Override
    public Set<SpecialFeatures> convertToEntityAttribute(String dbData) {
        return toDomainValue(dbData);
    }

    @Override
    public Set<SpecialFeatures> toDomainValue(String relationalForm) {
        if (relationalForm == null || relationalForm.isEmpty()) {
            return null;
        }
        Set<SpecialFeatures> specialFeaturesToEntity = EnumSet.noneOf(SpecialFeatures.class);

        String[] featuresFromBd = relationalForm.split(",");

        for (String featureFromBd : featuresFromBd) {
            SpecialFeatures feature = EnumSet.allOf(SpecialFeatures.class).stream()
                    .filter(specialFeatures1 -> specialFeatures1.getValue().equals(featureFromBd))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid enum value: " + featureFromBd));
            specialFeaturesToEntity.add(feature);
        }
        return specialFeaturesToEntity;
    }


    @Override
    public String toRelationalValue(Set<SpecialFeatures> domainForm) {
        return (domainForm == null || domainForm.isEmpty()) ? null :
                domainForm.stream()
                        .map(SpecialFeatures::getValue)
                        .collect(Collectors.joining(","));
    }

    @Override
    public JavaType<Set<SpecialFeatures>> getDomainJavaType() {
        return  SetStringJavaDescriptor.INSTANCE;
    }

    @Override
    public JavaType<String> getRelationalJavaType() {
        return StringJavaType.INSTANCE;
    }
}
