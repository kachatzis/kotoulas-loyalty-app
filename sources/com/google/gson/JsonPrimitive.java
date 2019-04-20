package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] PRIMITIVE_TYPES = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    public JsonPrimitive deepCopy() {
        return this;
    }

    public JsonPrimitive(Boolean bool) {
        setValue(bool);
    }

    public JsonPrimitive(Number number) {
        setValue(number);
    }

    public JsonPrimitive(String str) {
        setValue(str);
    }

    public JsonPrimitive(Character ch) {
        setValue(ch);
    }

    JsonPrimitive(Object obj) {
        setValue(obj);
    }

    void setValue(Object obj) {
        if (obj instanceof Character) {
            this.value = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z;
        if (!(obj instanceof Number)) {
            if (!isPrimitiveOrString(obj)) {
                z = false;
                C$Gson$Preconditions.checkArgument(z);
                this.value = obj;
            }
        }
        z = true;
        C$Gson$Preconditions.checkArgument(z);
        this.value = obj;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    Boolean getAsBooleanWrapper() {
        return (Boolean) this.value;
    }

    public boolean getAsBoolean() {
        if (isBoolean()) {
            return getAsBooleanWrapper().booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Number getAsNumber() {
        Object obj = this.value;
        return obj instanceof String ? new LazilyParsedNumber((String) obj) : (Number) obj;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return getAsBooleanWrapper().toString();
        }
        return (String) this.value;
    }

    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    public BigDecimal getAsBigDecimal() {
        Object obj = this.value;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(obj.toString());
    }

    public BigInteger getAsBigInteger() {
        Object obj = this.value;
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        return new BigInteger(obj.toString());
    }

    public float getAsFloat() {
        return isNumber() ? getAsNumber().floatValue() : Float.parseFloat(getAsString());
    }

    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    public short getAsShort() {
        return isNumber() ? getAsNumber().shortValue() : Short.parseShort(getAsString());
    }

    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    public byte getAsByte() {
        return isNumber() ? getAsNumber().byteValue() : Byte.parseByte(getAsString());
    }

    public char getAsCharacter() {
        return getAsString().charAt(0);
    }

    private static boolean isPrimitiveOrString(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        obj = obj.getClass();
        for (Class isAssignableFrom : PRIMITIVE_TYPES) {
            if (isAssignableFrom.isAssignableFrom(obj)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            long longValue = getAsNumber().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        }
        Object obj = this.value;
        if (!(obj instanceof Number)) {
            return obj.hashCode();
        }
        longValue = Double.doubleToLongBits(getAsNumber().doubleValue());
        return (int) ((longValue >>> 32) ^ longValue);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
                if (this.value == null) {
                    if (jsonPrimitive.value != null) {
                        z = false;
                    }
                    return z;
                } else if (isIntegral(this) && isIntegral(jsonPrimitive)) {
                    if (getAsNumber().longValue() != jsonPrimitive.getAsNumber().longValue()) {
                        z = false;
                    }
                    return z;
                } else if (!(this.value instanceof Number) || !(jsonPrimitive.value instanceof Number)) {
                    return this.value.equals(jsonPrimitive.value);
                } else {
                    double doubleValue = getAsNumber().doubleValue();
                    double doubleValue2 = jsonPrimitive.getAsNumber().doubleValue();
                    if (doubleValue != doubleValue2) {
                        if (Double.isNaN(doubleValue) == null || Double.isNaN(doubleValue2) == null) {
                            z = false;
                        }
                    }
                    return z;
                }
            }
        }
        return false;
    }

    private static boolean isIntegral(JsonPrimitive jsonPrimitive) {
        jsonPrimitive = jsonPrimitive.value;
        boolean z = false;
        if (!(jsonPrimitive instanceof Number)) {
            return false;
        }
        Number number = (Number) jsonPrimitive;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) != null) {
            z = true;
        }
        return z;
    }
}
