package gr.loginet.loginetloyalty;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import gr.loginet.loginetloyalty.models.ModelProduct;
import java.util.ArrayList;

public class ProductAdapter extends Adapter<MyViewHolder> {
    private ArrayList<ModelProduct> dataSet;

    public static class MyViewHolder extends ViewHolder {
        ImageView ImageViewPortal_photo;
        TextView textViewPortal_big_description;
        TextView textViewPortal_price;
        TextView textViewPortal_small_description;
        TextView textViewPortal_title;

        public MyViewHolder(View view) {
            super(view);
            this.textViewPortal_title = (TextView) view.findViewById(C0429R.id.textViewPortal_title);
            this.textViewPortal_price = (TextView) view.findViewById(C0429R.id.textViewPortal_price);
            this.textViewPortal_small_description = (TextView) view.findViewById(C0429R.id.textViewPortal_small_description);
            this.ImageViewPortal_photo = (ImageView) view.findViewById(C0429R.id.ImageViewPortal_photo);
        }
    }

    public ProductAdapter(ArrayList<ModelProduct> arrayList) {
        this.dataSet = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        viewGroup = LayoutInflater.from(viewGroup.getContext()).inflate(C0429R.layout.product_item_card_view, viewGroup, false);
        viewGroup.setOnClickListener(ProductsActivity.myOnClickListener);
        return new MyViewHolder(viewGroup);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        TextView textView = myViewHolder.textViewPortal_title;
        TextView textView2 = myViewHolder.textViewPortal_price;
        TextView textView3 = myViewHolder.textViewPortal_small_description;
        myViewHolder = myViewHolder.ImageViewPortal_photo;
        textView.setText(((ModelProduct) this.dataSet.get(i)).getPortal_title());
        textView2.setText(((ModelProduct) this.dataSet.get(i)).getPortal_price());
        textView3.setText(((ModelProduct) this.dataSet.get(i)).getPortal_small_description());
        i = ((ModelProduct) this.dataSet.get(i)).getPortal_photo();
        if (i != 0) {
            i = Base64.decode(i, 0);
            myViewHolder.setImageBitmap(BitmapFactory.decodeByteArray(i, 0, i.length));
        }
    }

    public int getItemCount() {
        return this.dataSet.size();
    }
}
