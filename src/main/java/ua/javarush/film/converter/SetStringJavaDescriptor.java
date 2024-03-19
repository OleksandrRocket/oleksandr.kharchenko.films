package ua.javarush.film.converter;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;
import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;
import ua.javarush.film.entity.SpecialFeatures;

import java.util.Set;

public class SetStringJavaDescriptor extends AbstractClassJavaType<Set<SpecialFeatures>> {
    public static final SetStringJavaDescriptor INSTANCE = new SetStringJavaDescriptor();

    protected SetStringJavaDescriptor() {
        super((Class<Set<SpecialFeatures>>) (Class<?>) Set.class, ImmutableMutabilityPlan.INSTANCE);
    }

    @Override
    public <X> X unwrap(Set<SpecialFeatures> value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        FeaturesSetStringConverter converter = new FeaturesSetStringConverter();
        return (X) converter.toRelationalValue(value);
    }

    @Override
    public <X> Set<SpecialFeatures> wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }

        FeaturesSetStringConverter converter = new FeaturesSetStringConverter();
        return converter.toDomainValue(String.valueOf(value));
    }
}


