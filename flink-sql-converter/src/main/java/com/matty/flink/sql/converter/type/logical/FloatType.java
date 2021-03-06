package com.matty.flink.sql.converter.type.logical;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Description:
 *
 * @author mwt
 * @version 1.0
 * @date 2019-10-09
 */
public final class FloatType extends LogicalType {

    private static final String FORMAT = "FLOAT";

    private static final Set<String> NULL_OUTPUT_CONVERSION = conversionSet(
            Float.class.getName());

    private static final Set<String> NOT_NULL_INPUT_OUTPUT_CONVERSION = conversionSet(
            Float.class.getName(),
            float.class.getName());

    private static final Class<?> DEFAULT_CONVERSION = Float.class;

    public FloatType(boolean isNullable) {
        super(isNullable, LogicalTypeRoot.FLOAT);
    }

    public FloatType() {
        this(true);
    }

    @Override
    public LogicalType copy(boolean isNullable) {
        return new FloatType(isNullable);
    }

    @Override
    public String asSerializableString() {
        return withNullability(FORMAT);
    }

    @Override
    public boolean supportsInputConversion(Class<?> clazz) {
        return NOT_NULL_INPUT_OUTPUT_CONVERSION.contains(clazz.getName());
    }

    @Override
    public boolean supportsOutputConversion(Class<?> clazz) {
        if (isNullable()) {
            return NULL_OUTPUT_CONVERSION.contains(clazz.getName());
        }
        return NOT_NULL_INPUT_OUTPUT_CONVERSION.contains(clazz.getName());
    }

    @Override
    public Class<?> getDefaultConversion() {
        return DEFAULT_CONVERSION;
    }

    @Override
    public List<LogicalType> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public <R> R accept(LogicalTypeVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
