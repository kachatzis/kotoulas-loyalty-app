package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpDate;

public final class Headers {
    private final String[] namesAndValues;

    public static final class Builder {
        final List<String> namesAndValues = new ArrayList(20);

        private void checkNameAndValue(java.lang.String r10, java.lang.String r11) {
            /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:30:0x00a4 in {9, 11, 18, 20, 22, 23, 25, 27, 29} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
            /*
            r9 = this;
            if (r10 == 0) goto L_0x009c;
        L_0x0002:
            r0 = r10.isEmpty();
            if (r0 != 0) goto L_0x0094;
        L_0x0008:
            r0 = r10.length();
            r1 = 0;
            r2 = 0;
        L_0x000e:
            r3 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
            r4 = 2;
            r5 = 3;
            r6 = 1;
            if (r2 >= r0) goto L_0x003e;
        L_0x0015:
            r7 = r10.charAt(r2);
            r8 = 32;
            if (r7 <= r8) goto L_0x0022;
        L_0x001d:
            if (r7 >= r3) goto L_0x0022;
        L_0x001f:
            r2 = r2 + 1;
            goto L_0x000e;
        L_0x0022:
            r11 = new java.lang.IllegalArgumentException;
            r0 = new java.lang.Object[r5];
            r3 = java.lang.Integer.valueOf(r7);
            r0[r1] = r3;
            r1 = java.lang.Integer.valueOf(r2);
            r0[r6] = r1;
            r0[r4] = r10;
            r10 = "Unexpected char %#04x at %d in header name: %s";
            r10 = okhttp3.internal.Util.format(r10, r0);
            r11.<init>(r10);
            throw r11;
        L_0x003e:
            if (r11 == 0) goto L_0x0078;
        L_0x0040:
            r0 = r11.length();
            r2 = 0;
        L_0x0045:
            if (r2 >= r0) goto L_0x0077;
        L_0x0047:
            r7 = r11.charAt(r2);
            r8 = 31;
            if (r7 > r8) goto L_0x0053;
        L_0x004f:
            r8 = 9;
            if (r7 != r8) goto L_0x0058;
        L_0x0053:
            if (r7 >= r3) goto L_0x0058;
        L_0x0055:
            r2 = r2 + 1;
            goto L_0x0045;
        L_0x0058:
            r0 = new java.lang.IllegalArgumentException;
            r3 = 4;
            r3 = new java.lang.Object[r3];
            r7 = java.lang.Integer.valueOf(r7);
            r3[r1] = r7;
            r1 = java.lang.Integer.valueOf(r2);
            r3[r6] = r1;
            r3[r4] = r10;
            r3[r5] = r11;
            r10 = "Unexpected char %#04x at %d in %s value: %s";
            r10 = okhttp3.internal.Util.format(r10, r3);
            r0.<init>(r10);
            throw r0;
        L_0x0077:
            return;
        L_0x0078:
            r11 = new java.lang.NullPointerException;
            r0 = new java.lang.StringBuilder;
            r0.<init>();
            r1 = "value for name ";
            r0.append(r1);
            r0.append(r10);
            r10 = " == null";
            r0.append(r10);
            r10 = r0.toString();
            r11.<init>(r10);
            throw r11;
        L_0x0094:
            r10 = new java.lang.IllegalArgumentException;
            r11 = "name is empty";
            r10.<init>(r11);
            throw r10;
        L_0x009c:
            r10 = new java.lang.NullPointerException;
            r11 = "name == null";
            r10.<init>(r11);
            throw r10;
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.Headers.Builder.checkNameAndValue(java.lang.String, java.lang.String):void");
        }

        Builder addLenient(String str) {
            int indexOf = str.indexOf(":", 1);
            if (indexOf != -1) {
                return addLenient(str.substring(0, indexOf), str.substring(indexOf + 1));
            }
            if (str.startsWith(":")) {
                return addLenient("", str.substring(1));
            }
            return addLenient("", str);
        }

        public Builder add(String str) {
            int indexOf = str.indexOf(":");
            if (indexOf != -1) {
                return add(str.substring(0, indexOf).trim(), str.substring(indexOf + 1));
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected header: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder add(String str, String str2) {
            checkNameAndValue(str, str2);
            return addLenient(str, str2);
        }

        Builder addLenient(String str, String str2) {
            this.namesAndValues.add(str);
            this.namesAndValues.add(str2.trim());
            return this;
        }

        public Builder removeAll(String str) {
            int i = 0;
            while (i < this.namesAndValues.size()) {
                if (str.equalsIgnoreCase((String) this.namesAndValues.get(i))) {
                    this.namesAndValues.remove(i);
                    this.namesAndValues.remove(i);
                    i -= 2;
                }
                i += 2;
            }
            return this;
        }

        public Builder set(String str, String str2) {
            checkNameAndValue(str, str2);
            removeAll(str);
            addLenient(str, str2);
            return this;
        }

        public String get(String str) {
            for (int size = this.namesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equalsIgnoreCase((String) this.namesAndValues.get(size))) {
                    return (String) this.namesAndValues.get(size + 1);
                }
            }
            return null;
        }

        public Headers build() {
            return new Headers(this);
        }
    }

    public static okhttp3.Headers of(java.util.Map<java.lang.String, java.lang.String> r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:23:0x0091 in {14, 16, 18, 20, 22} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        if (r7 == 0) goto L_0x0089;
    L_0x0002:
        r0 = r7.size();
        r0 = r0 * 2;
        r0 = new java.lang.String[r0];
        r7 = r7.entrySet();
        r7 = r7.iterator();
        r1 = 0;
        r2 = 0;
    L_0x0014:
        r3 = r7.hasNext();
        if (r3 == 0) goto L_0x0083;
    L_0x001a:
        r3 = r7.next();
        r3 = (java.util.Map.Entry) r3;
        r4 = r3.getKey();
        if (r4 == 0) goto L_0x007b;
    L_0x0026:
        r4 = r3.getValue();
        if (r4 == 0) goto L_0x007b;
    L_0x002c:
        r4 = r3.getKey();
        r4 = (java.lang.String) r4;
        r4 = r4.trim();
        r3 = r3.getValue();
        r3 = (java.lang.String) r3;
        r3 = r3.trim();
        r5 = r4.length();
        if (r5 == 0) goto L_0x005c;
    L_0x0046:
        r5 = r4.indexOf(r1);
        r6 = -1;
        if (r5 != r6) goto L_0x005c;
    L_0x004d:
        r5 = r3.indexOf(r1);
        if (r5 != r6) goto L_0x005c;
    L_0x0053:
        r0[r2] = r4;
        r4 = r2 + 1;
        r0[r4] = r3;
        r2 = r2 + 2;
        goto L_0x0014;
    L_0x005c:
        r7 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unexpected header: ";
        r0.append(r1);
        r0.append(r4);
        r1 = ": ";
        r0.append(r1);
        r0.append(r3);
        r0 = r0.toString();
        r7.<init>(r0);
        throw r7;
    L_0x007b:
        r7 = new java.lang.IllegalArgumentException;
        r0 = "Headers cannot be null";
        r7.<init>(r0);
        throw r7;
    L_0x0083:
        r7 = new okhttp3.Headers;
        r7.<init>(r0);
        return r7;
    L_0x0089:
        r7 = new java.lang.NullPointerException;
        r0 = "headers == null";
        r7.<init>(r0);
        throw r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Headers.of(java.util.Map):okhttp3.Headers");
    }

    public static okhttp3.Headers of(java.lang.String... r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x007e in {8, 10, 20, 22, 24, 26, 28} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        if (r6 == 0) goto L_0x0076;
    L_0x0002:
        r0 = r6.length;
        r0 = r0 % 2;
        if (r0 != 0) goto L_0x006e;
    L_0x0007:
        r6 = r6.clone();
        r6 = (java.lang.String[]) r6;
        r0 = 0;
        r1 = 0;
    L_0x000f:
        r2 = r6.length;
        if (r1 >= r2) goto L_0x0029;
    L_0x0012:
        r2 = r6[r1];
        if (r2 == 0) goto L_0x0021;
    L_0x0016:
        r2 = r6[r1];
        r2 = r2.trim();
        r6[r1] = r2;
        r1 = r1 + 1;
        goto L_0x000f;
    L_0x0021:
        r6 = new java.lang.IllegalArgumentException;
        r0 = "Headers cannot be null";
        r6.<init>(r0);
        throw r6;
    L_0x0029:
        r1 = 0;
    L_0x002a:
        r2 = r6.length;
        if (r1 >= r2) goto L_0x0068;
    L_0x002d:
        r2 = r6[r1];
        r3 = r1 + 1;
        r3 = r6[r3];
        r4 = r2.length();
        if (r4 == 0) goto L_0x0049;
    L_0x0039:
        r4 = r2.indexOf(r0);
        r5 = -1;
        if (r4 != r5) goto L_0x0049;
    L_0x0040:
        r4 = r3.indexOf(r0);
        if (r4 != r5) goto L_0x0049;
    L_0x0046:
        r1 = r1 + 2;
        goto L_0x002a;
    L_0x0049:
        r6 = new java.lang.IllegalArgumentException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unexpected header: ";
        r0.append(r1);
        r0.append(r2);
        r1 = ": ";
        r0.append(r1);
        r0.append(r3);
        r0 = r0.toString();
        r6.<init>(r0);
        throw r6;
    L_0x0068:
        r0 = new okhttp3.Headers;
        r0.<init>(r6);
        return r0;
    L_0x006e:
        r6 = new java.lang.IllegalArgumentException;
        r0 = "Expected alternating header names and values";
        r6.<init>(r0);
        throw r6;
    L_0x0076:
        r6 = new java.lang.NullPointerException;
        r0 = "namesAndValues == null";
        r6.<init>(r0);
        throw r6;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Headers.of(java.lang.String[]):okhttp3.Headers");
    }

    Headers(Builder builder) {
        this.namesAndValues = (String[]) builder.namesAndValues.toArray(new String[builder.namesAndValues.size()]);
    }

    private Headers(String[] strArr) {
        this.namesAndValues = strArr;
    }

    @Nullable
    public String get(String str) {
        return get(this.namesAndValues, str);
    }

    @Nullable
    public Date getDate(String str) {
        str = get(str);
        return str != null ? HttpDate.parse(str) : null;
    }

    public int size() {
        return this.namesAndValues.length / 2;
    }

    public String name(int i) {
        return this.namesAndValues[i * 2];
    }

    public String value(int i) {
        return this.namesAndValues[(i * 2) + 1];
    }

    public Set<String> names() {
        Set treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i = 0; i < size; i++) {
            treeSet.add(name(i));
        }
        return Collections.unmodifiableSet(treeSet);
    }

    public List<String> values(String str) {
        int size = size();
        List list = null;
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(name(i))) {
                if (list == null) {
                    list = new ArrayList(2);
                }
                list.add(value(i));
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        Collections.addAll(builder.namesAndValues, this.namesAndValues);
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        return (!(obj instanceof Headers) || Arrays.equals(((Headers) obj).namesAndValues, this.namesAndValues) == null) ? null : true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.namesAndValues);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(name(i));
            stringBuilder.append(": ");
            stringBuilder.append(value(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<String, List<String>> toMultimap() {
        Map<String, List<String>> treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        int size = size();
        for (int i = 0; i < size; i++) {
            String toLowerCase = name(i).toLowerCase(Locale.US);
            List list = (List) treeMap.get(toLowerCase);
            if (list == null) {
                list = new ArrayList(2);
                treeMap.put(toLowerCase, list);
            }
            list.add(value(i));
        }
        return treeMap;
    }

    private static String get(String[] strArr, String str) {
        for (int length = strArr.length - 2; length >= 0; length -= 2) {
            if (str.equalsIgnoreCase(strArr[length])) {
                return strArr[length + 1];
            }
        }
        return null;
    }
}
