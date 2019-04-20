package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.Address;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal.Util;

public final class RouteSelector {
    private final Address address;
    private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
    private InetSocketAddress lastInetSocketAddress;
    private Proxy lastProxy;
    private int nextInetSocketAddressIndex;
    private int nextProxyIndex;
    private final List<Route> postponedRoutes = new ArrayList();
    private List<Proxy> proxies = Collections.emptyList();
    private final RouteDatabase routeDatabase;

    private void resetNextInetSocketAddress(java.net.Proxy r8) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x00e6 in {4, 7, 9, 10, 17, 22, 24, 26, 28} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.visit(BlockProcessor.java:38)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r7 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r7.inetSocketAddresses = r0;
        r0 = r8.type();
        r1 = java.net.Proxy.Type.DIRECT;
        if (r0 == r1) goto L_0x0046;
    L_0x000f:
        r0 = r8.type();
        r1 = java.net.Proxy.Type.SOCKS;
        if (r0 != r1) goto L_0x0018;
    L_0x0017:
        goto L_0x0046;
    L_0x0018:
        r0 = r8.address();
        r1 = r0 instanceof java.net.InetSocketAddress;
        if (r1 == 0) goto L_0x002b;
    L_0x0020:
        r0 = (java.net.InetSocketAddress) r0;
        r1 = getHostString(r0);
        r0 = r0.getPort();
        goto L_0x005a;
    L_0x002b:
        r8 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Proxy.address() is not an InetSocketAddress: ";
        r1.append(r2);
        r0 = r0.getClass();
        r1.append(r0);
        r0 = r1.toString();
        r8.<init>(r0);
        throw r8;
    L_0x0046:
        r0 = r7.address;
        r0 = r0.url();
        r1 = r0.host();
        r0 = r7.address;
        r0 = r0.url();
        r0 = r0.port();
    L_0x005a:
        r2 = 1;
        if (r0 < r2) goto L_0x00c2;
    L_0x005d:
        r2 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        if (r0 > r2) goto L_0x00c2;
    L_0x0062:
        r8 = r8.type();
        r2 = java.net.Proxy.Type.SOCKS;
        r3 = 0;
        if (r8 != r2) goto L_0x0075;
    L_0x006b:
        r8 = r7.inetSocketAddresses;
        r0 = java.net.InetSocketAddress.createUnresolved(r1, r0);
        r8.add(r0);
        goto L_0x009f;
    L_0x0075:
        r8 = r7.address;
        r8 = r8.dns();
        r8 = r8.lookup(r1);
        r2 = r8.isEmpty();
        if (r2 != 0) goto L_0x00a2;
    L_0x0085:
        r1 = r8.size();
        r2 = 0;
    L_0x008a:
        if (r2 >= r1) goto L_0x009f;
    L_0x008c:
        r4 = r8.get(r2);
        r4 = (java.net.InetAddress) r4;
        r5 = r7.inetSocketAddresses;
        r6 = new java.net.InetSocketAddress;
        r6.<init>(r4, r0);
        r5.add(r6);
        r2 = r2 + 1;
        goto L_0x008a;
    L_0x009f:
        r7.nextInetSocketAddressIndex = r3;
        return;
    L_0x00a2:
        r8 = new java.net.UnknownHostException;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = r7.address;
        r2 = r2.dns();
        r0.append(r2);
        r2 = " returned no addresses for ";
        r0.append(r2);
        r0.append(r1);
        r0 = r0.toString();
        r8.<init>(r0);
        throw r8;
    L_0x00c2:
        r8 = new java.net.SocketException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No route to ";
        r2.append(r3);
        r2.append(r1);
        r1 = ":";
        r2.append(r1);
        r2.append(r0);
        r0 = "; port is out of range";
        r2.append(r0);
        r0 = r2.toString();
        r8.<init>(r0);
        throw r8;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RouteSelector.resetNextInetSocketAddress(java.net.Proxy):void");
    }

    public RouteSelector(Address address, RouteDatabase routeDatabase) {
        this.address = address;
        this.routeDatabase = routeDatabase;
        resetNextProxy(address.url(), address.proxy());
    }

    public boolean hasNext() {
        if (!(hasNextInetSocketAddress() || hasNextProxy())) {
            if (!hasNextPostponed()) {
                return false;
            }
        }
        return true;
    }

    public Route next() throws IOException {
        if (!hasNextInetSocketAddress()) {
            if (hasNextProxy()) {
                this.lastProxy = nextProxy();
            } else if (hasNextPostponed()) {
                return nextPostponed();
            } else {
                throw new NoSuchElementException();
            }
        }
        this.lastInetSocketAddress = nextInetSocketAddress();
        Route route = new Route(this.address, this.lastProxy, this.lastInetSocketAddress);
        if (!this.routeDatabase.shouldPostpone(route)) {
            return route;
        }
        this.postponedRoutes.add(route);
        return next();
    }

    public void connectFailed(Route route, IOException iOException) {
        if (!(route.proxy().type() == Type.DIRECT || this.address.proxySelector() == null)) {
            this.address.proxySelector().connectFailed(this.address.url().uri(), route.proxy().address(), iOException);
        }
        this.routeDatabase.failed(route);
    }

    private void resetNextProxy(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.proxies = Collections.singletonList(proxy);
        } else {
            List select = this.address.proxySelector().select(httpUrl.uri());
            if (select == null || select.isEmpty() != null) {
                httpUrl = Util.immutableList(Proxy.NO_PROXY);
            } else {
                httpUrl = Util.immutableList(select);
            }
            this.proxies = httpUrl;
        }
        this.nextProxyIndex = 0;
    }

    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private Proxy nextProxy() throws IOException {
        if (hasNextProxy()) {
            List list = this.proxies;
            int i = this.nextProxyIndex;
            this.nextProxyIndex = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            resetNextInetSocketAddress(proxy);
            return proxy;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No route to ");
        stringBuilder.append(this.address.url().host());
        stringBuilder.append("; exhausted proxy configurations: ");
        stringBuilder.append(this.proxies);
        throw new SocketException(stringBuilder.toString());
    }

    static String getHostString(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    private boolean hasNextInetSocketAddress() {
        return this.nextInetSocketAddressIndex < this.inetSocketAddresses.size();
    }

    private InetSocketAddress nextInetSocketAddress() throws IOException {
        if (hasNextInetSocketAddress()) {
            List list = this.inetSocketAddresses;
            int i = this.nextInetSocketAddressIndex;
            this.nextInetSocketAddressIndex = i + 1;
            return (InetSocketAddress) list.get(i);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No route to ");
        stringBuilder.append(this.address.url().host());
        stringBuilder.append("; exhausted inet socket addresses: ");
        stringBuilder.append(this.inetSocketAddresses);
        throw new SocketException(stringBuilder.toString());
    }

    private boolean hasNextPostponed() {
        return this.postponedRoutes.isEmpty() ^ 1;
    }

    private Route nextPostponed() {
        return (Route) this.postponedRoutes.remove(0);
    }
}
