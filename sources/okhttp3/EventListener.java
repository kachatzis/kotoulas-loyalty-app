package okhttp3;

import java.net.InetAddress;
import java.util.List;

abstract class EventListener {
    public static final EventListener NONE = new C06181();

    public interface Factory {
        EventListener create(Call call);
    }

    /* renamed from: okhttp3.EventListener$1 */
    class C06181 extends EventListener {
        C06181() {
        }
    }

    /* renamed from: okhttp3.EventListener$2 */
    class C06192 implements Factory {
        final /* synthetic */ EventListener val$listener;

        C06192(EventListener eventListener) {
            this.val$listener = eventListener;
        }

        public EventListener create(Call call) {
            return this.val$listener;
        }
    }

    public void connectEnd(Call call, InetAddress inetAddress, int i, String str, Throwable th) {
    }

    public void connectStart(Call call, InetAddress inetAddress, int i) {
    }

    public void dnsEnd(Call call, String str, List<InetAddress> list, Throwable th) {
    }

    public void dnsStart(Call call, String str) {
    }

    public void fetchEnd(Call call, Throwable th) {
    }

    public void fetchStart(Call call) {
    }

    public void requestBodyEnd(Call call, Throwable th) {
    }

    public void requestBodyStart(Call call) {
    }

    public void requestHeadersEnd(Call call, Throwable th) {
    }

    public void requestHeadersStart(Call call) {
    }

    public void responseBodyEnd(Call call, Throwable th) {
    }

    public void responseBodyStart(Call call) {
    }

    public void responseHeadersEnd(Call call, Throwable th) {
    }

    public void responseHeadersStart(Call call) {
    }

    public void secureConnectEnd(Call call, Handshake handshake, Throwable th) {
    }

    public void secureConnectStart(Call call) {
    }

    EventListener() {
    }

    static Factory factory(EventListener eventListener) {
        return new C06192(eventListener);
    }
}
