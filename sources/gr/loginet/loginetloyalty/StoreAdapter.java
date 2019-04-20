package gr.loginet.loginetloyalty;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import gr.loginet.loginetloyalty.models.ModelStore;
import java.util.ArrayList;

public class StoreAdapter extends Adapter<MyViewHolder> {
    private ArrayList<ModelStore> dataSet;

    public static class MyViewHolder extends ViewHolder {
        TextView textViewCode;
        TextView textViewDescription;
        TextView textViewName;

        public MyViewHolder(View view) {
            super(view);
            this.textViewName = (TextView) view.findViewById(C0429R.id.textViewName);
            this.textViewDescription = (TextView) view.findViewById(C0429R.id.textViewDescription);
            this.textViewCode = (TextView) view.findViewById(C0429R.id.textViewCode);
        }
    }

    public StoreAdapter(ArrayList<ModelStore> arrayList) {
        this.dataSet = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        viewGroup = LayoutInflater.from(viewGroup.getContext()).inflate(C0429R.layout.store_item_card_view, viewGroup, false);
        viewGroup.setOnClickListener(StoresActivity.myOnClickListener);
        return new MyViewHolder(viewGroup);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        TextView textView = myViewHolder.textViewName;
        TextView textView2 = myViewHolder.textViewDescription;
        myViewHolder = myViewHolder.textViewCode;
        textView.setText(((ModelStore) this.dataSet.get(i)).getName());
        textView2.setText(((ModelStore) this.dataSet.get(i)).getDescription());
        myViewHolder.setText(((ModelStore) this.dataSet.get(i)).getCode());
    }

    public int getItemCount() {
        return this.dataSet.size();
    }
}
