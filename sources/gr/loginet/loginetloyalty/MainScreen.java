package gr.loginet.loginetloyalty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import gr.loginet.loginetloyalty.models.ModelCustomer;
import gr.loginet.loginetloyalty.models.ModelCustomerResponse;
import gr.loginet.loginetloyalty.models.ModelGift;
import gr.loginet.loginetloyalty.models.ModelProduct;
import gr.loginet.loginetloyalty.models.ModelStore;
import java.io.IOException;
import java.io.Serializable;
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
import retrofit2.http.GET;

public class MainScreen extends AppCompatActivity {
    String ApiKey;
    public ModelCustomer Customer = new ModelCustomer();
    List<ModelGift> Gifts;
    ImageView ImageViewBarcode;
    ImageView ImageViewBigBarcode;
    List<ModelProduct> Products;
    List<ModelStore> Stores;
    String Token;
    TextView barcodetext;
    String base_url;
    TextView big_barcodetext;
    Button btn_offers;
    Button btn_products;
    Button btn_stores;
    TextView credits;
    TextView customer_name;
    private DrawerLayout mDrawerLayout;
    ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeContainer;

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$3 */
    class C04253 implements OnClickListener {
        C04253() {
        }

        public void onClick(View view) {
            view = new Intent(MainScreen.this, GiftsActivity.class);
            Bundle bundle = new Bundle();
            view.putExtra("Gifts", (Serializable) MainScreen.this.Gifts);
            view.putExtras(bundle);
            MainScreen.this.startActivity(view);
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$4 */
    class C04264 implements OnClickListener {
        C04264() {
        }

        public void onClick(View view) {
            view = new Intent(MainScreen.this, ProductsActivity.class);
            Bundle bundle = new Bundle();
            view.putExtra("Products", (Serializable) MainScreen.this.Products);
            view.putExtras(bundle);
            MainScreen.this.startActivity(view);
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$5 */
    class C04275 implements OnClickListener {
        C04275() {
        }

        public void onClick(View view) {
            view = new Intent(MainScreen.this, StoresActivity.class);
            Bundle bundle = new Bundle();
            view.putExtra("Stores", (Serializable) MainScreen.this.Stores);
            view.putExtras(bundle);
            MainScreen.this.startActivity(view);
        }
    }

    public interface GetCustomerRetrofit2 {
        @GET("customer/")
        Call<ModelCustomerResponse> GetCustomerService();
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$1 */
    class C06101 implements OnNavigationItemSelectedListener {
        C06101() {
        }

        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            menuItem.setChecked(true);
            if (itemId != C0429R.id.nav_home) {
                if (itemId != C0429R.id.nav_account) {
                    if (itemId != C0429R.id.nav_messages) {
                        if (itemId != C0429R.id.nav_news) {
                            if (itemId != C0429R.id.nav_help) {
                                if (itemId != C0429R.id.nav_opinion) {
                                    if (itemId == C0429R.id.nav_logout) {
                                        menuItem = MainScreen.this.getBaseContext().getPackageManager().getLaunchIntentForPackage(MainScreen.this.getBaseContext().getPackageName());
                                        menuItem.addFlags(67108864);
                                        menuItem.addFlags(268435456);
                                        MainScreen.this.startActivity(menuItem);
                                        MainScreen.this.finish();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            MainScreen.this.mDrawerLayout.closeDrawers();
            return true;
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$2 */
    class C06112 implements OnRefreshListener {
        C06112() {
        }

        public void onRefresh() {
            MainScreen.this.getCustomer();
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$6 */
    class C06126 implements Interceptor {
        C06126() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request.newBuilder().addHeader("X-DreamFactory-API-Key", MainScreen.this.ApiKey).addHeader("X-DreamFactory-Session-Token", MainScreen.this.Token).method(request.method(), request.body()).build());
        }
    }

    /* renamed from: gr.loginet.loginetloyalty.MainScreen$7 */
    class C06137 implements Callback<ModelCustomerResponse> {
        C06137() {
        }

        public void onResponse(Call<ModelCustomerResponse> call, retrofit2.Response<ModelCustomerResponse> response) {
            ModelCustomerResponse modelCustomerResponse = (ModelCustomerResponse) response.body();
            if (response.isSuccessful()) {
                MainScreen.this.Customer = (ModelCustomer) modelCustomerResponse.getResults().get(0);
                MainScreen.this.loadCustomerData();
                return;
            }
            try {
                MainScreen.this.OnFail(response.errorBody().string());
            } catch (Call<ModelCustomerResponse> call2) {
                call2.printStackTrace();
            }
        }

        public void onFailure(Call<ModelCustomerResponse> call, Throwable th) {
            MainScreen.this.OnFail(th.getMessage());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0429R.layout.screen_main);
        setSupportActionBar((Toolbar) findViewById(C0429R.id.toolbar));
        bundle = getSupportActionBar();
        bundle.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bundle.setHomeAsUpIndicator((int) C0429R.drawable.ic_menu_black_24dp);
        this.mDrawerLayout = (DrawerLayout) findViewById(C0429R.id.drawer_layout);
        ((NavigationView) findViewById(C0429R.id.nav_view)).setNavigationItemSelectedListener(new C06101());
        this.swipeContainer = (SwipeRefreshLayout) findViewById(C0429R.id.swiperefresh);
        this.swipeContainer.setOnRefreshListener(new C06112());
        this.swipeContainer.setColorSchemeResources(17170459, 17170452, 17170456, 17170454);
        this.Token = ((LoginnetLoyalty) getApplication()).getToken();
        this.ApiKey = ((LoginnetLoyalty) getApplication()).getApiKey();
        this.base_url = ((LoginnetLoyalty) getApplication()).getBaseUrl();
        this.customer_name = (TextView) findViewById(C0429R.id.customer_name);
        this.credits = (TextView) findViewById(C0429R.id.credits);
        this.ImageViewBarcode = (ImageView) findViewById(C0429R.id.barcode);
        this.barcodetext = (TextView) findViewById(C0429R.id.barcodetext);
        this.ImageViewBigBarcode = (ImageView) findViewById(C0429R.id.big_barcode);
        this.big_barcodetext = (TextView) findViewById(C0429R.id.big_barcodetext);
        this.btn_offers = (Button) findViewById(C0429R.id.btn_offers);
        this.btn_products = (Button) findViewById(C0429R.id.btn_products);
        this.btn_stores = (Button) findViewById(C0429R.id.btn_stores);
        this.btn_offers.setOnClickListener(new C04253());
        this.btn_products.setOnClickListener(new C04264());
        this.btn_stores.setOnClickListener(new C04275());
        bundle = getIntent();
        getIntent().getExtras();
        this.Customer = (ModelCustomer) bundle.getSerializableExtra("Customer");
        this.Stores = (List) bundle.getSerializableExtra("Stores");
        this.Products = (List) bundle.getSerializableExtra("Products");
        this.Gifts = (List) bundle.getSerializableExtra("Gifts");
        loadCustomerData();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mDrawerLayout.openDrawer(8388611);
        return true;
    }

    public void getCustomer() {
        Log.d("GET", "Fetching customer data...");
        Builder builder = new Builder();
        builder.addInterceptor(new C06126());
        ((GetCustomerRetrofit2) new Retrofit.Builder().baseUrl(this.base_url).addConverterFactory(GsonConverterFactory.create()).client(builder.readTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).connectTimeout((long) getResources().getInteger(C0429R.integer.timeouts), TimeUnit.SECONDS).build()).build().create(GetCustomerRetrofit2.class)).GetCustomerService().enqueue(new C06137());
    }

    private void loadCustomerData() {
        try {
            TextView textView = this.customer_name;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.Customer.getFirst_name());
            stringBuilder.append(" ");
            stringBuilder.append(this.Customer.getLast_name());
            textView.setText(stringBuilder.toString());
            this.credits.setText(String.valueOf(this.Customer.getCredit()));
            this.barcodetext.setText(String.valueOf(this.Customer.getCustomer_id()));
            this.ImageViewBarcode.setImageBitmap(new BarcodeEncoder().createBitmap(new MultiFormatWriter().encode(String.valueOf(this.Customer.getCustomer_id()), BarcodeFormat.CODE_128, 300, 100)));
            this.big_barcodetext.setText(String.valueOf(this.Customer.getCustomer_id()));
            this.ImageViewBigBarcode.setImageBitmap(new BarcodeEncoder().createBitmap(new MultiFormatWriter().encode(String.valueOf(this.Customer.getCustomer_id()), BarcodeFormat.CODE_128, 800, 300)));
        } catch (WriterException e) {
            e.printStackTrace();
            OnFail(e.getMessage());
        }
        this.swipeContainer.setRefreshing(false);
    }

    public void OnFail(String str) {
        Intent intent = new Intent(this, MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("image", C0429R.drawable.ic_alert_circle_grey600_48dp);
        bundle.putString("message", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
