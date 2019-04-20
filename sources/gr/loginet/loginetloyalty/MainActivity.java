package gr.loginet.loginetloyalty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import gr.loginet.loginetloyalty.models.ModelCustomer;
import gr.loginet.loginetloyalty.models.ModelCustomerResponse;
import gr.loginet.loginetloyalty.models.ModelGift;
import gr.loginet.loginetloyalty.models.ModelGiftResponse;
import gr.loginet.loginetloyalty.models.ModelLogin;
import gr.loginet.loginetloyalty.models.ModelLoginResponse;
import gr.loginet.loginetloyalty.models.ModelProduct;
import gr.loginet.loginetloyalty.models.ModelProductResponse;
import gr.loginet.loginetloyalty.models.ModelStore;
import gr.loginet.loginetloyalty.models.ModelStoreResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    public String ContentType = "application/x-www-form-urlencoded";
    public ModelCustomer Customer = new ModelCustomer();
    public ModelCustomer CustomerInfo;
    public List<ModelGift> Gifts = new ArrayList();
    Button LoginButton;
    EditText Pass;
    public List<ModelProduct> Products = new ArrayList();
    public List<ModelStore> Stores = new ArrayList();
    EditText User;
    public String XDreamFactoryAPIKey = "#";
    public String XDreamFactorySessionToken;
    public String base_url = "https://#/api/v2/#/";
    public String endpoint_product = "product/";
    public String endpoint_store = "store/";
    public String endpoint_transaction = "transaction/";
    ProgressDialog progressDialog;
    TextInputLayout til;

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$1 */
    class C04231 implements OnClickListener {
        C04231() {
        }

        public void onClick(View view) {
            MainActivity.this.LoginButton.setEnabled(false);
            MainActivity.this.login();
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$2 */
    class C04242 implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C04242() {
        }

        public void afterTextChanged(Editable editable) {
            try {
                if (MainActivity.this.Pass.getText().length() == null && MainActivity.this.Pass != null) {
                    MainActivity.this.til.setPasswordVisibilityToggleEnabled(true);
                }
            } catch (Editable editable2) {
                PrintStream printStream = System.out;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error ");
                stringBuilder.append(editable2.getMessage());
                printStream.println(stringBuilder.toString());
            }
        }
    }

    public interface GetCustomerRetrofit2 {
        @GET("customer/")
        Call<ModelCustomerResponse> GetCustomerService();
    }

    public interface GetGiftRetrofit2 {
        @GET("gift/")
        Call<ModelGiftResponse> GetGiftService();
    }

    public interface GetProductRetrofit2 {
        @GET("product/")
        Call<ModelProductResponse> GetProductService();
    }

    public interface GetStoreRetrofit2 {
        @GET("store/")
        Call<ModelStoreResponse> GetStoreService();
    }

    public interface LoginRetrofit2 {
        @POST("login/")
        Call<ModelLoginResponse> UserLoginService(@Body ModelLogin modelLogin);
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$3 */
    class C06033 implements Interceptor {
        C06033() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainActivity.this.XDreamFactoryAPIKey).addHeader("Content-Type", MainActivity.this.ContentType).method(request.method(), request.body()).build());
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$4 */
    class C06044 implements Callback<ModelLoginResponse> {
        C06044() {
        }

        public void onResponse(Call<ModelLoginResponse> call, retrofit2.Response<ModelLoginResponse> response) {
            ModelLoginResponse modelLoginResponse = (ModelLoginResponse) response.body();
            if (response.isSuccessful()) {
                MainActivity.this.XDreamFactorySessionToken = modelLoginResponse.getToken();
                MainActivity.this.savecred();
                ((LoginnetLoyalty) MainActivity.this.getApplication()).setToken(MainActivity.this.XDreamFactorySessionToken);
                ((LoginnetLoyalty) MainActivity.this.getApplication()).setApiKey(MainActivity.this.XDreamFactoryAPIKey);
                ((LoginnetLoyalty) MainActivity.this.getApplication()).setBaseUrl(MainActivity.this.base_url);
                MainActivity.this.getCustomer();
                return;
            }
            try {
                MainActivity.this.onLoginFailed(response.errorBody().string());
            } catch (Call<ModelLoginResponse> call2) {
                call2.printStackTrace();
            }
        }

        public void onFailure(Call<ModelLoginResponse> call, Throwable th) {
            MainActivity.this.onLoginFailed(th.getMessage());
            MainActivity.this.hide_dialog();
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$5 */
    class C06055 implements Interceptor {
        C06055() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainActivity.this.XDreamFactoryAPIKey).addHeader("X-DreamFactory-Session-Token", MainActivity.this.XDreamFactorySessionToken).method(request.method(), request.body()).build());
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$6 */
    class C06066 implements Callback<ModelCustomerResponse> {
        C06066() {
        }

        public void onResponse(Call<ModelCustomerResponse> call, retrofit2.Response<ModelCustomerResponse> response) {
            ModelCustomerResponse modelCustomerResponse = (ModelCustomerResponse) response.body();
            if (response.isSuccessful()) {
                MainActivity.this.Customer = (ModelCustomer) modelCustomerResponse.getResults().get(0);
                MainActivity.this.getStore();
                return;
            }
            try {
                MainActivity.this.onLoginFailed(response.errorBody().string());
            } catch (Call<ModelCustomerResponse> call2) {
                call2.printStackTrace();
            }
        }

        public void onFailure(Call<ModelCustomerResponse> call, Throwable th) {
            MainActivity.this.onLoginFailed(th.getMessage());
            MainActivity.this.hide_dialog();
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$7 */
    class C06077 implements Interceptor {
        C06077() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainActivity.this.XDreamFactoryAPIKey).addHeader("X-DreamFactory-Session-Token", MainActivity.this.XDreamFactorySessionToken).method(request.method(), request.body()).build());
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$8 */
    class C06088 implements Callback<ModelStoreResponse> {
        C06088() {
        }

        public void onResponse(Call<ModelStoreResponse> call, retrofit2.Response<ModelStoreResponse> response) {
            ModelStoreResponse modelStoreResponse = (ModelStoreResponse) response.body();
            if (response.isSuccessful()) {
                MainActivity.this.Stores = modelStoreResponse.getResults();
                MainActivity.this.getProduct();
                return;
            }
            try {
                MainActivity.this.onLoginFailed(response.errorBody().string());
            } catch (Call<ModelStoreResponse> call2) {
                call2.printStackTrace();
            }
        }

        public void onFailure(Call<ModelStoreResponse> call, Throwable th) {
            MainActivity.this.onLoginFailed(th.getMessage());
            MainActivity.this.hide_dialog();
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainActivity$9 */
    class C06099 implements Interceptor {
        C06099() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainActivity.this.XDreamFactoryAPIKey).addHeader("X-DreamFactory-Session-Token", MainActivity.this.XDreamFactorySessionToken).method(request.method(), request.body()).build());
        }
    }

    protected void onCreate(android.os.Bundle r5) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/156856360.run(Unknown Source)
*/
        /*
        r4 = this;
        super.onCreate(r5);
        r5 = 2131558429; // 0x7f0d001d float:1.8742174E38 double:1.053129792E-314;
        r4.setContentView(r5);
        r5 = new android.os.Handler;
        r5.<init>();
        r5 = new android.app.ProgressDialog;
        r5.<init>(r4);
        r4.progressDialog = r5;
        r5 = new gr.loginet.loginetloyalty.models.ModelCustomer;
        r5.<init>();
        r4.CustomerInfo = r5;
        r5 = 2131362080; // 0x7f0a0120 float:1.834393E38 double:1.0530327826E-314;
        r5 = r4.findViewById(r5);
        r5 = (android.widget.EditText) r5;
        r4.User = r5;
        r5 = 2131361987; // 0x7f0a00c3 float:1.8343742E38 double:1.0530327366E-314;
        r5 = r4.findViewById(r5);
        r5 = (android.widget.EditText) r5;
        r4.Pass = r5;
        r5 = 2131361834; // 0x7f0a002a float:1.8343432E38 double:1.053032661E-314;
        r5 = r4.findViewById(r5);
        r5 = (android.widget.Button) r5;
        r4.LoginButton = r5;
        r5 = r4.LoginButton;
        r0 = new gr.loginet.loginetloyalty.MainActivity$1;
        r0.<init>();
        r5.setOnClickListener(r0);
        r5 = 0;
        r0 = r4.getApplicationContext();	 Catch:{ Exception -> 0x008c }
        r1 = "cred";	 Catch:{ Exception -> 0x008c }
        r0 = r0.getSharedPreferences(r1, r5);	 Catch:{ Exception -> 0x008c }
        r1 = r4.User;	 Catch:{ Exception -> 0x008c }
        r2 = "username";	 Catch:{ Exception -> 0x008c }
        r3 = 0;	 Catch:{ Exception -> 0x008c }
        r2 = r0.getString(r2, r3);	 Catch:{ Exception -> 0x008c }
        r1.setText(r2);	 Catch:{ Exception -> 0x008c }
        r1 = r4.Pass;	 Catch:{ Exception -> 0x008c }
        r2 = "password";	 Catch:{ Exception -> 0x008c }
        r0 = r0.getString(r2, r3);	 Catch:{ Exception -> 0x008c }
        r1.setText(r0);	 Catch:{ Exception -> 0x008c }
        r0 = 2131361900; // 0x7f0a006c float:1.8343565E38 double:1.0530326936E-314;	 Catch:{ Exception -> 0x008c }
        r0 = r4.findViewById(r0);	 Catch:{ Exception -> 0x008c }
        r0 = (android.support.design.widget.TextInputLayout) r0;	 Catch:{ Exception -> 0x008c }
        r4.til = r0;	 Catch:{ Exception -> 0x008c }
        r0 = r4.til;	 Catch:{ Exception -> 0x008c }
        r0.setPasswordVisibilityToggleEnabled(r5);	 Catch:{ Exception -> 0x008c }
        r0 = r4.Pass;	 Catch:{ Exception -> 0x008c }
        r0 = r0.getText();	 Catch:{ Exception -> 0x008c }
        r0 = r0.length();	 Catch:{ Exception -> 0x008c }
        if (r0 != 0) goto L_0x00ab;	 Catch:{ Exception -> 0x008c }
    L_0x0085:
        r0 = r4.til;	 Catch:{ Exception -> 0x008c }
        r1 = 1;	 Catch:{ Exception -> 0x008c }
        r0.setPasswordVisibilityToggleEnabled(r1);	 Catch:{ Exception -> 0x008c }
        goto L_0x00ab;
    L_0x008c:
        r0 = r4.getApplicationContext();
        r1 = "cred";
        r5 = r0.getSharedPreferences(r1, r5);
        r5 = r5.edit();
        r0 = "username";
        r1 = "";
        r5.putString(r0, r1);
        r0 = "password";
        r1 = "";
        r5.putString(r0, r1);
        r5.commit();
    L_0x00ab:
        r5 = r4.Pass;
        r0 = new gr.loginet.loginetloyalty.MainActivity$2;
        r0.<init>();
        r5.addTextChangedListener(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: gr.loginet.loginetloyalty.MainActivity.onCreate(android.os.Bundle):void");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0429R.menu.drawer_view, menu);
        return true;
    }

    private void login() {
        Log.d("POST", "Authenticating...");
        show_dialog("Authenticating...");
        Builder builder = new Builder();
        builder.addInterceptor(new C06033());
        LoginRetrofit2 loginRetrofit2 = (LoginRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(LoginRetrofit2.class);
        ModelLogin modelLogin = new ModelLogin();
        modelLogin.username = this.User.getText().toString();
        modelLogin.password = this.Pass.getText().toString();
        loginRetrofit2.UserLoginService(modelLogin).enqueue(new C06044());
    }

    private void savecred() {
        Editor edit = getApplicationContext().getSharedPreferences("cred", 0).edit();
        edit.putString("username", this.User.getText().toString());
        edit.putString("password", this.Pass.getText().toString());
        edit.commit();
    }

    public void getCustomer() {
        Log.d("GET", "Fetching customer data...");
        show_dialog("Fetching customer data...");
        Builder builder = new Builder();
        builder.addInterceptor(new C06055());
        ((GetCustomerRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(GetCustomerRetrofit2.class)).GetCustomerService().enqueue(new C06066());
    }

    public void getStore() {
        Log.d("GET", "Fetching store data...");
        show_dialog("Fetching store data...");
        Builder builder = new Builder();
        builder.addInterceptor(new C06077());
        ((GetStoreRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(GetStoreRetrofit2.class)).GetStoreService().enqueue(new C06088());
    }

    public void getProduct() {
        Log.d("GET", "Fetching product data...");
        show_dialog("Fetching product data...");
        Builder builder = new Builder();
        builder.addInterceptor(new C06099());
        ((GetProductRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(GetProductRetrofit2.class)).GetProductService().enqueue(new Callback<ModelProductResponse>() {
            public void onResponse(Call<ModelProductResponse> call, retrofit2.Response<ModelProductResponse> response) {
                ModelProductResponse modelProductResponse = (ModelProductResponse) response.body();
                if (response.isSuccessful()) {
                    MainActivity.this.Products = modelProductResponse.getResults();
                    MainActivity.this.getGift();
                    return;
                }
                try {
                    MainActivity.this.onLoginFailed(response.errorBody().string());
                } catch (Call<ModelProductResponse> call2) {
                    call2.printStackTrace();
                }
            }

            public void onFailure(Call<ModelProductResponse> call, Throwable th) {
                MainActivity.this.onLoginFailed(th.getMessage());
                MainActivity.this.hide_dialog();
            }
        });
    }

    public void getGift() {
        Log.d("GET", "Fetching gift data...");
        show_dialog("Fetching gift data...");
        Builder builder = new Builder();
        builder.addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainActivity.this.XDreamFactoryAPIKey).addHeader("X-DreamFactory-Session-Token", MainActivity.this.XDreamFactorySessionToken).method(request.method(), request.body()).build());
            }
        });
        ((GetGiftRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(GetGiftRetrofit2.class)).GetGiftService().enqueue(new Callback<ModelGiftResponse>() {
            public void onResponse(Call<ModelGiftResponse> call, retrofit2.Response<ModelGiftResponse> response) {
                ModelGiftResponse modelGiftResponse = (ModelGiftResponse) response.body();
                if (response.isSuccessful()) {
                    MainActivity.this.Gifts = modelGiftResponse.getResults();
                    MainActivity.this.SwitchToScreenMain();
                    return;
                }
                try {
                    MainActivity.this.onLoginFailed(response.errorBody().string());
                } catch (Call<ModelGiftResponse> call2) {
                    call2.printStackTrace();
                }
            }

            public void onFailure(Call<ModelGiftResponse> call, Throwable th) {
                MainActivity.this.onLoginFailed(th.getMessage());
                MainActivity.this.hide_dialog();
            }
        });
    }

    public void onLoginFailed(String str) {
        hide_dialog();
        Intent intent = new Intent(this, MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("image", C0429R.drawable.ic_alert_circle_grey600_48dp);
        bundle.putString("message", str);
        intent.putExtras(bundle);
        startActivity(intent);
        this.LoginButton.setEnabled(true);
    }

    private void SwitchToScreenMain() {
        Intent intent = new Intent(this, MainScreen.class);
        Bundle bundle = new Bundle();
        intent.putExtra("Customer", this.Customer);
        intent.putExtra("Stores", (Serializable) this.Stores);
        intent.putExtra("Products", (Serializable) this.Products);
        intent.putExtra("Gifts", (Serializable) this.Gifts);
        intent.putExtras(bundle);
        startActivity(intent);
        hide_dialog();
        finish();
    }

    private void show_dialog(String str) {
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage(str);
        this.progressDialog.show();
    }

    private void hide_dialog() {
        this.progressDialog.dismiss();
        this.LoginButton.setEnabled(true);
    }
}
