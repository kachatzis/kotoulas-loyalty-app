package gr.loginet.loginetloyalty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View.OnClickListener;
import gr.loginet.loginetloyalty.models.ModelProduct;
import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private static ArrayList<ModelProduct> Products;
    private static Adapter adapter;
    static OnClickListener myOnClickListener;
    private static RecyclerView recyclerView;
    private static ArrayList<Integer> removedItems;
    private LayoutManager layoutManager;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C0429R.layout.activity_products);
        setSupportActionBar((Toolbar) findViewById(C0429R.id.toolbar));
        getSupportActionBar().setTitle((CharSequence) "ΠΡΟΪΟΝΤΑ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView) findViewById(C0429R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bundle = getIntent();
        getIntent().getExtras();
        Products = (ArrayList) bundle.getSerializableExtra("Products");
        removedItems = new ArrayList();
        adapter = new ProductAdapter(Products);
        recyclerView.setAdapter(adapter);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
