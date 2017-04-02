package com.example.grey_hat.dineinn;

import android.content.Context;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by grey-hat on 2/4/17.
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {
    private Context mContext;
    private ArrayList<FoodItem> basket;
    private Button orderButton;
    public BasketAdapter(ArrayList<FoodItem> basket) {
        this.basket=basket;
    }
    public BasketAdapter(ArrayList<FoodItem> basket,Context context,Button orderButton) {
        this.mContext=context;
        this.basket=basket;
        this.orderButton=orderButton;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card_view,parent,false);
        BasketViewHolder viewHolder = new BasketViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasketViewHolder holder, int position) {
        holder.setName(basket.get(position).getName());
        holder.setImage(mContext,basket.get(position).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return basket.size();
    }
    public class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
         View mview;
        RecyclerView.LayoutParams params ;
        public BasketViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            params=(RecyclerView.LayoutParams)itemView.getLayoutParams();
            mview.setOnLongClickListener(this);
        }
        public void setName(String name)
        {
            TextView foodName = (TextView)mview.findViewById(R.id.FoodItemName);
            foodName.setText(name);
        }
        public void setImage(Context ctx, String img) {
            ImageView foodImg = (ImageView)mview.findViewById(R.id.FoodCardImg);
            Picasso.with(ctx).load(img).into(foodImg);

        }
        public void setPrice(String price) {
            TextView priceText = (TextView)mview.findViewById(R.id.FoodPrice);
            priceText.setText(price);
        }
        public void setInVisibility() {
            mview.setVisibility(View.GONE);
            params.height=0;
            params.width=0;
        }

        @Override
        public boolean onLongClick(View v) {
            int newPosition = this.getAdapterPosition();
            basket.remove(newPosition);
            notifyItemRemoved(newPosition);
            notifyItemRangeChanged(newPosition,basket.size());
            return  true;
        }
    }
}
