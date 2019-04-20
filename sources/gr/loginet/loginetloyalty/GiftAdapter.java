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
import gr.loginet.loginetloyalty.models.ModelGift;
import java.util.ArrayList;

public class GiftAdapter extends Adapter<MyViewHolder> {
    private ArrayList<ModelGift> dataSet;

    public static class MyViewHolder extends ViewHolder {
        ImageView ImageViewPortal_photo;
        TextView textViewPortal_big_description;
        TextView textViewPortal_credits_cost;
        TextView textViewPortal_small_description;
        TextView textViewPortal_title;

        public MyViewHolder(View view) {
            super(view);
            this.textViewPortal_title = (TextView) view.findViewById(C0429R.id.textViewPortal_title);
            this.textViewPortal_small_description = (TextView) view.findViewById(C0429R.id.textViewPortal_small_description);
            this.textViewPortal_big_description = (TextView) view.findViewById(C0429R.id.textViewPortal_big_description);
            this.textViewPortal_credits_cost = (TextView) view.findViewById(C0429R.id.textViewCredits_cost);
            this.ImageViewPortal_photo = (ImageView) view.findViewById(C0429R.id.imageView);
        }
    }

    public GiftAdapter(ArrayList<ModelGift> arrayList) {
        this.dataSet = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        viewGroup = LayoutInflater.from(viewGroup.getContext()).inflate(C0429R.layout.gift_item_card_view, viewGroup, false);
        viewGroup.setOnClickListener(GiftsActivity.myOnClickListener);
        return new MyViewHolder(viewGroup);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        TextView textView = myViewHolder.textViewPortal_title;
        TextView textView2 = myViewHolder.textViewPortal_small_description;
        TextView textView3 = myViewHolder.textViewPortal_big_description;
        TextView textView4 = myViewHolder.textViewPortal_credits_cost;
        myViewHolder = myViewHolder.ImageViewPortal_photo;
        CharSequence portal_title = ((ModelGift) this.dataSet.get(i)).getPortal_title();
        CharSequence portal_small_description = ((ModelGift) this.dataSet.get(i)).getPortal_small_description();
        CharSequence portal_big_description = ((ModelGift) this.dataSet.get(i)).getPortal_big_description();
        CharSequence valueOf = String.valueOf(((ModelGift) this.dataSet.get(i)).getCredits_cost());
        if (portal_title != null) {
            textView.setText(portal_title);
        }
        if (portal_small_description != null) {
            textView2.setText(portal_small_description);
        }
        if (portal_big_description != null) {
            textView3.setText(portal_big_description);
        }
        if (valueOf != null) {
            textView4.setText(valueOf);
        }
        i = ((ModelGift) this.dataSet.get(i)).getPortal_photo();
        if (i != 0 && i != 0) {
            i = Base64.decode(i, 0);
            myViewHolder.setImageBitmap(BitmapFactory.decodeByteArray(i, 0, i.length));
        }
    }

    public int getItemCount() {
        return this.dataSet.size();
    }
}
