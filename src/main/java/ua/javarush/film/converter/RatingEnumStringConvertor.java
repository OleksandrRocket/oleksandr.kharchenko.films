package ua.javarush.film.converter;

import jakarta.persistence.Converter;
import org.hibernate.type.StandardConverter;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.java.StringJavaType;
import ua.javarush.film.entity.FilmRating;

import java.util.EnumSet;

@Converter
public class RatingEnumStringConvertor implements StandardConverter<FilmRating, String> {
    @Override
    public String convertToDatabaseColumn(FilmRating attribute) {
        return toRelationalValue(attribute);
    }

    @Override
    public FilmRating convertToEntityAttribute(String dbData) {
        return toDomainValue(dbData);
    }

    @Override
    public FilmRating toDomainValue(String relationalForm) {
        if (relationalForm == null || relationalForm.isEmpty()){
            return null;
        }
       return EnumSet.allOf(FilmRating.class).stream()
                .filter(filmRating -> filmRating.getValue().equals(relationalForm))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid enum value: " + relationalForm));
    }

    @Override
    public String toRelationalValue(FilmRating domainForm) {
        return domainForm == null ? null : domainForm.getValue();
    }

    @Override
    public JavaType<FilmRating> getDomainJavaType() {
        return EnumStringJavaDescriptor.INSTANCE;
    }

    @Override
    public JavaType<String> getRelationalJavaType() {
       return StringJavaType.INSTANCE;
    }
}
