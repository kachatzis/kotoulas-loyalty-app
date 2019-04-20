package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, TimeUnit.SECONDS).build();
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    @Nullable
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    public static final class Builder {
        boolean immutable;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                i = timeUnit.toSeconds((long) i);
                this.maxAgeSeconds = i > 2147483647L ? ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) i;
                return this;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("maxAge < 0: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                i = timeUnit.toSeconds((long) i);
                this.maxStaleSeconds = i > 2147483647L ? ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) i;
                return this;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("maxStale < 0: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                i = timeUnit.toSeconds((long) i);
                this.minFreshSeconds = i > 2147483647L ? ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) i;
                return this;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("minFresh < 0: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder immutable() {
            this.immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    public static CacheControl parse(Headers headers) {
        Headers headers2 = headers;
        int size = headers.size();
        int i = 0;
        Object obj = 1;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        int i2 = -1;
        int i3 = -1;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i4 = -1;
        int i5 = -1;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while (i < size) {
            String name = headers2.name(i);
            String value = headers2.value(i);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str != null) {
                    obj = null;
                } else {
                    str = value;
                }
            } else if (name.equalsIgnoreCase("Pragma")) {
                obj = null;
            } else {
                i++;
                headers2 = headers;
            }
            int i6 = 0;
            while (i6 < value.length()) {
                String trim;
                int skipUntil = HttpHeaders.skipUntil(value, i6, "=,;");
                name = value.substring(i6, skipUntil).trim();
                if (!(skipUntil == value.length() || value.charAt(skipUntil) == ',')) {
                    if (value.charAt(skipUntil) != ';') {
                        int skipWhitespace = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                        if (skipWhitespace >= value.length() || value.charAt(skipWhitespace) != '\"') {
                            skipUntil = HttpHeaders.skipUntil(value, skipWhitespace, ",;");
                            trim = value.substring(skipWhitespace, skipUntil).trim();
                        } else {
                            skipWhitespace++;
                            skipUntil = HttpHeaders.skipUntil(value, skipWhitespace, "\"");
                            trim = value.substring(skipWhitespace, skipUntil);
                            skipUntil++;
                        }
                        if ("no-cache".equalsIgnoreCase(name)) {
                            z = true;
                        } else if ("no-store".equalsIgnoreCase(name)) {
                            z2 = true;
                        } else if ("max-age".equalsIgnoreCase(name)) {
                            i2 = HttpHeaders.parseSeconds(trim, -1);
                        } else if ("s-maxage".equalsIgnoreCase(name)) {
                            i3 = HttpHeaders.parseSeconds(trim, -1);
                        } else if ("private".equalsIgnoreCase(name)) {
                            z3 = true;
                        } else if ("public".equalsIgnoreCase(name)) {
                            z4 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(name)) {
                            z5 = true;
                        } else if ("max-stale".equalsIgnoreCase(name)) {
                            i4 = HttpHeaders.parseSeconds(trim, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                        } else if ("min-fresh".equalsIgnoreCase(name)) {
                            i5 = HttpHeaders.parseSeconds(trim, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(name)) {
                            z6 = true;
                        } else if ("no-transform".equalsIgnoreCase(name)) {
                            z7 = true;
                        } else if ("immutable".equalsIgnoreCase(name)) {
                            z8 = true;
                        }
                        i6 = skipUntil;
                        headers2 = headers;
                    }
                }
                skipUntil++;
                trim = null;
                if ("no-cache".equalsIgnoreCase(name)) {
                    z = true;
                } else if ("no-store".equalsIgnoreCase(name)) {
                    z2 = true;
                } else if ("max-age".equalsIgnoreCase(name)) {
                    i2 = HttpHeaders.parseSeconds(trim, -1);
                } else if ("s-maxage".equalsIgnoreCase(name)) {
                    i3 = HttpHeaders.parseSeconds(trim, -1);
                } else if ("private".equalsIgnoreCase(name)) {
                    z3 = true;
                } else if ("public".equalsIgnoreCase(name)) {
                    z4 = true;
                } else if ("must-revalidate".equalsIgnoreCase(name)) {
                    z5 = true;
                } else if ("max-stale".equalsIgnoreCase(name)) {
                    i4 = HttpHeaders.parseSeconds(trim, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                } else if ("min-fresh".equalsIgnoreCase(name)) {
                    i5 = HttpHeaders.parseSeconds(trim, -1);
                } else if ("only-if-cached".equalsIgnoreCase(name)) {
                    z6 = true;
                } else if ("no-transform".equalsIgnoreCase(name)) {
                    z7 = true;
                } else if ("immutable".equalsIgnoreCase(name)) {
                    z8 = true;
                }
                i6 = skipUntil;
                headers2 = headers;
            }
            i++;
            headers2 = headers;
        }
        return new CacheControl(z, z2, i2, i3, z3, z4, z5, i4, i5, z6, z7, z8, obj == null ? null : str);
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        str = headerValue();
        this.headerValue = str;
        return str;
    }

    private String headerValue() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.noCache) {
            stringBuilder.append("no-cache, ");
        }
        if (this.noStore) {
            stringBuilder.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            stringBuilder.append("max-age=");
            stringBuilder.append(this.maxAgeSeconds);
            stringBuilder.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            stringBuilder.append("s-maxage=");
            stringBuilder.append(this.sMaxAgeSeconds);
            stringBuilder.append(", ");
        }
        if (this.isPrivate) {
            stringBuilder.append("private, ");
        }
        if (this.isPublic) {
            stringBuilder.append("public, ");
        }
        if (this.mustRevalidate) {
            stringBuilder.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            stringBuilder.append("max-stale=");
            stringBuilder.append(this.maxStaleSeconds);
            stringBuilder.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            stringBuilder.append("min-fresh=");
            stringBuilder.append(this.minFreshSeconds);
            stringBuilder.append(", ");
        }
        if (this.onlyIfCached) {
            stringBuilder.append("only-if-cached, ");
        }
        if (this.noTransform) {
            stringBuilder.append("no-transform, ");
        }
        if (this.immutable) {
            stringBuilder.append("immutable, ");
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
