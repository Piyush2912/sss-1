package com.devsebastian.gtbit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {

    private ArrayList<BillItem> items;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    Context context;
    TextView totalCostTv;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, title, quantity, cost;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.item_id);
            title = (TextView) view.findViewById(R.id.item_title);
            quantity = (TextView) view.findViewById(R.id.item_quantity);
            cost = (TextView) view.findViewById(R.id.item_cost);
            image = view.findViewById(R.id.bill_image);
        }
    }


    public BillAdapter(Context context, ArrayList<BillItem> items, TextView totalCostTv) {
        this.context = context;
        this.totalCostTv = totalCostTv;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_row, parent, false);

        return new MyViewHolder(itemView);
    }

    void getTotal() {
        double total = 0;
        for (BillItem item : items) {
            if (item.getCost() != null)
                total += item.getCost();
        }
        totalCostTv.setText("₹ " + total);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final BillItem item = items.get(position);

        databaseReference.child("items").child(item.getId().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.title.setText(dataSnapshot.child("name").getValue(String.class));
                Double cost = dataSnapshot.child("Price").getValue(Double.class);
                holder.cost.setText("₹ " + cost);
                BillItem item = items.get(position);
                item.setCost(cost);
                items.set(position, item);
                getTotal();

                Glide.with(context).load(dataSnapshot.child("imgurl").getValue(String.class)).into(holder.image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        holder.id.setText("019003118-" + item.getId().toString());
        holder.quantity.setText(item.getQuantity().toString() + " units");
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}