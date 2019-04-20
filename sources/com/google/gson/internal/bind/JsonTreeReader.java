package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;

public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED = new Object();
    private static final Reader UNREADABLE_READER = new C03811();
    private int[] pathIndices = new int[32];
    private String[] pathNames = new String[32];
    private Object[] stack = new Object[32];
    private int stackSize = 0;

    /* renamed from: com.google.gson.internal.bind.JsonTreeReader$1 */
    static class C03811 extends Reader {
        C03811() {
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    }

    public JsonTreeReader(JsonElement jsonElement) {
        super(UNREADABLE_READER);
        push(jsonElement);
    }

    public void beginArray() throws IOException {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    public void endArray() throws IOException {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
    }

    public void beginObject() throws IOException {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    public void endObject() throws IOException {
        expect(JsonToken.END_OBJECT);
        popStack();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
    }

    public boolean hasNext() throws IOException {
        JsonToken peek = peek();
        return (peek == JsonToken.END_OBJECT || peek == JsonToken.END_ARRAY) ? false : true;
    }

    public JsonToken peek() throws IOException {
        if (this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object peekStack = peekStack();
        if (peekStack instanceof Iterator) {
            boolean z = this.stack[this.stackSize - 2] instanceof JsonObject;
            Iterator it = (Iterator) peekStack;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            } else if (z) {
                return JsonToken.NAME;
            } else {
                push(it.next());
                return peek();
            }
        } else if (peekStack instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else {
            if (peekStack instanceof JsonArray) {
                return JsonToken.BEGIN_ARRAY;
            }
            if (peekStack instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) peekStack;
                if (jsonPrimitive.isString()) {
                    return JsonToken.STRING;
                }
                if (jsonPrimitive.isBoolean()) {
                    return JsonToken.BOOLEAN;
                }
                if (jsonPrimitive.isNumber()) {
                    return JsonToken.NUMBER;
                }
                throw new AssertionError();
            } else if (peekStack instanceof JsonNull) {
                return JsonToken.NULL;
            } else {
                if (peekStack == SENTINEL_CLOSED) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] objArr = this.stack;
        int i = this.stackSize - 1;
        this.stackSize = i;
        Object obj = objArr[i];
        objArr[this.stackSize] = null;
        return obj;
    }

    private void expect(JsonToken jsonToken) throws IOException {
        if (peek() != jsonToken) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected ");
            stringBuilder.append(jsonToken);
            stringBuilder.append(" but was ");
            stringBuilder.append(peek());
            stringBuilder.append(locationString());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public String nextName() throws IOException {
        expect(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) peekStack()).next();
        String str = (String) entry.getKey();
        this.pathNames[this.stackSize - 1] = str;
        push(entry.getValue());
        return str;
    }

    public String nextString() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.STRING) {
            if (peek != JsonToken.NUMBER) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected ");
                stringBuilder.append(JsonToken.STRING);
                stringBuilder.append(" but was ");
                stringBuilder.append(peek);
                stringBuilder.append(locationString());
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        String asString = ((JsonPrimitive) popStack()).getAsString();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
        return asString;
    }

    public boolean nextBoolean() throws IOException {
        expect(JsonToken.BOOLEAN);
        boolean asBoolean = ((JsonPrimitive) popStack()).getAsBoolean();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
        return asBoolean;
    }

    public void nextNull() throws IOException {
        expect(JsonToken.NULL);
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
    }

    public double nextDouble() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER) {
            if (peek != JsonToken.STRING) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected ");
                stringBuilder.append(JsonToken.NUMBER);
                stringBuilder.append(" but was ");
                stringBuilder.append(peek);
                stringBuilder.append(locationString());
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        double asDouble = ((JsonPrimitive) peekStack()).getAsDouble();
        if (!isLenient()) {
            if (Double.isNaN(asDouble) || Double.isInfinite(asDouble)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("JSON forbids NaN and infinities: ");
                stringBuilder2.append(asDouble);
                throw new NumberFormatException(stringBuilder2.toString());
            }
        }
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
        return asDouble;
    }

    public long nextLong() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER) {
            if (peek != JsonToken.STRING) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected ");
                stringBuilder.append(JsonToken.NUMBER);
                stringBuilder.append(" but was ");
                stringBuilder.append(peek);
                stringBuilder.append(locationString());
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        long asLong = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
        return asLong;
    }

    public int nextInt() throws IOException {
        JsonToken peek = peek();
        if (peek != JsonToken.NUMBER) {
            if (peek != JsonToken.STRING) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Expected ");
                stringBuilder.append(JsonToken.NUMBER);
                stringBuilder.append(" but was ");
                stringBuilder.append(peek);
                stringBuilder.append(locationString());
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
        int asInt = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        int i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
        return asInt;
    }

    public void close() throws IOException {
        this.stack = new Object[]{SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    public void skipValue() throws IOException {
        int i;
        if (peek() == JsonToken.NAME) {
            nextName();
            this.pathNames[this.stackSize - 2] = "null";
        } else {
            popStack();
            i = this.stackSize;
            if (i > 0) {
                this.pathNames[i - 1] = "null";
            }
        }
        i = this.stackSize;
        if (i > 0) {
            int[] iArr = this.pathIndices;
            i--;
            iArr[i] = iArr[i] + 1;
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void promoteNameToValue() throws IOException {
        expect(JsonToken.NAME);
        Entry entry = (Entry) ((Iterator) peekStack()).next();
        push(entry.getValue());
        push(new JsonPrimitive((String) entry.getKey()));
    }

    private void push(Object obj) {
        int i = this.stackSize;
        Object obj2 = this.stack;
        if (i == obj2.length) {
            Object obj3 = new Object[(i * 2)];
            Object obj4 = new int[(i * 2)];
            Object obj5 = new String[(i * 2)];
            System.arraycopy(obj2, 0, obj3, 0, i);
            System.arraycopy(this.pathIndices, 0, obj4, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, obj5, 0, this.stackSize);
            this.stack = obj3;
            this.pathIndices = obj4;
            this.pathNames = obj5;
        }
        Object[] objArr = this.stack;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        objArr[i2] = obj;
    }

    public String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('$');
        int i = 0;
        while (i < this.stackSize) {
            Object[] objArr = this.stack;
            if (objArr[i] instanceof JsonArray) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    stringBuilder.append('[');
                    stringBuilder.append(this.pathIndices[i]);
                    stringBuilder.append(']');
                }
            } else if (objArr[i] instanceof JsonObject) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    stringBuilder.append('.');
                    String[] strArr = this.pathNames;
                    if (strArr[i] != null) {
                        stringBuilder.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return stringBuilder.toString();
    }

    private String locationString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at path ");
        stringBuilder.append(getPath());
        return stringBuilder.toString();
    }
}
