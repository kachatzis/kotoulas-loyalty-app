package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public interface Authenticator {
    public static final Authenticator NONE = new C06141();

    /* renamed from: okhttp3.Authenticator$1 */
    class C06141 implements Authenticator {
        public Request authenticate(Route route, Response response) {
            return null;
        }

        C06141() {
        }
    }

    @Nullable
    Request authenticate(Route route, Response response) throws IOException;
}
