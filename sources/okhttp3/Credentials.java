package okhttp3;

import java.nio.charset.Charset;
import okio.ByteString;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Charset.forName("ISO-8859-1"));
    }

    public static String basic(String str, String str2, Charset charset) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        str = ByteString.of(stringBuilder.toString().getBytes(charset)).base64();
        str2 = new StringBuilder();
        str2.append("Basic ");
        str2.append(str);
        return str2.toString();
    }
}
