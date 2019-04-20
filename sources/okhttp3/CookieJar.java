package okhttp3;

import java.util.Collections;
import java.util.List;

public interface CookieJar {
    public static final CookieJar NO_COOKIES = new C06161();

    /* renamed from: okhttp3.CookieJar$1 */
    class C06161 implements CookieJar {
        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        }

        C06161() {
        }

        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
            return Collections.emptyList();
        }
    }

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);
}
