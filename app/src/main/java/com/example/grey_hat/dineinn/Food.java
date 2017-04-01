package com.example.grey_hat.dineinn;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grey_hat.signedIn;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class Food extends Fragment {

    private String food;

    private RecyclerView mrecyclerView;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    public Food() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            food = bundle.getString("food");
        }
        return inflater.inflate(R.layout.fragment_food, container, false);
    }
    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        View mview;
        RecyclerView.LayoutParams params ;
        public FoodViewHolder(View itemView) {
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
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Food").child(food);
        mrecyclerView=(RecyclerView)getView().findViewById(R.id.FoodRecylcList);
        Log.e("Database",databaseReference.getKey());
        mrecyclerView.setNestedScrollingEnabled(false);

          mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<FoodItem,FoodViewHolder> foodAdapter = new FirebaseRecyclerAdapter<FoodItem, FoodViewHolder>(
                FoodItem.class,R.layout.food_card_view,FoodViewHolder.class,databaseReference
        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final FoodItem model, final int position) {
                int count =(int)model.getCounter();

                if(count>0) {
                    viewHolder.setName(model.getName());
                    viewHolder.setImage(getActivity().getApplicationContext(), model.getImgUrl());
                    viewHolder.setPrice("Price : " + Long.toString(model.getPrice()));
                }
                else {
                    viewHolder.setInVisibility();
                }
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((signedIn)getActivity()).addToBasket(model);
                    }
                });
            }
        };

       mrecyclerView.setAdapter(foodAdapter);
    }
}
