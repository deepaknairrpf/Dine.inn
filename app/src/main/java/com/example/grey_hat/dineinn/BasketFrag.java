package com.example.grey_hat.dineinn;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFrag extends Fragment {

    private RecyclerView mrecyclerView;
    private ArrayList<FoodItem> basket;
    public BasketFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            basket = bundle.getParcelableArrayList("Basket");
        }
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }
    public static class BasketViewHolder extends RecyclerView.ViewHolder{
        View mview;
        RecyclerView.LayoutParams params ;
        public BasketViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            params=(RecyclerView.LayoutParams)itemView.getLayoutParams();
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

    }
    @Override
    public void onStart() {
        super.onStart();
        mrecyclerView=(RecyclerView)getView().findViewById(R.id.BasketRecylcList);
        mrecyclerView.setNestedScrollingEnabled(false);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerView.setAdapter(new RecyclerView.Adapter<BasketViewHolder>() {
            @Override
            public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card_view,parent,false);
                BasketViewHolder viewHolder = new BasketViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(BasketViewHolder holder, int position) {
                    holder.setName(basket.get(position).getName());
                    holder.setImage(getContext(),basket.get(position).getImgUrl());

            }

            @Override
            public int getItemCount() {
                return basket.size();
            }
        });




    }
}
