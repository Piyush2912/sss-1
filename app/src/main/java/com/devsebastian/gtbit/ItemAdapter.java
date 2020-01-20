package com.devsebastian.gtbit;

import android.content.Context;
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

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<Item> items;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    Context context;

    void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, cost, deal;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.feed_item_title);
            deal = (TextView) view.findViewById(R.id.feed_item_deal);
            cost = (TextView) view.findViewById(R.id.feed_item_cost);
            image = view.findViewById(R.id.feed_item_image);
        }
    }


    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Item item = items.get(position);

        if (item.getId() != null)
            databaseReference.child("items").child(item.getId().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    holder.title.setText(item.getTitle());
                    holder.deal.setText(item.getDeal());
                    holder.cost.setText("₹ " + item.getCost());
                    Glide.with(context).load(item.getImgUrl()).into(holder.image);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}