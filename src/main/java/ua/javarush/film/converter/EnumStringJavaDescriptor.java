package ua.javarush.film.converter;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import ua.javarush.film.entity.FilmRating;

public class EnumStringJavaDescriptor extends AbstractClassJavaType<FilmRating> {
    public static final EnumStringJavaDescriptor INSTANCE = new EnumStringJavaDescriptor();

    public EnumStringJavaDescriptor() {
        super(FilmRating.class, ImmutableMutabilityPlan.INSTANCE);
    }
    @Override
    public <X> X unwrap(FilmRating value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) value.toString();
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> FilmRating wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isAssignableFrom(value.getClass())) {
            return Enum.valueOf(FilmRating.class, (String) value);
        }
        throw unknownUnwrap(value.getClass());
    }
}
