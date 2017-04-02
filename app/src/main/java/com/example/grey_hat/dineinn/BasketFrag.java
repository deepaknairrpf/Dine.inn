package com.example.grey_hat.dineinn;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFrag extends Fragment {

    private RecyclerView mrecyclerView;
    private ArrayList<FoodItem> basket;
    private Button orderButton;
    private Context mContext;
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
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
       orderButton=(Button)view.findViewById(R.id.orderButton);
        if(basket==null || basket.isEmpty()) {
            orderButton.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Basket is empty", Toast.LENGTH_SHORT).show();
        }
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
            mContext=getContext();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mrecyclerView=(RecyclerView)getView().findViewById(R.id.BasketRecylcList);
        mrecyclerView.setNestedScrollingEnabled(false);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BasketAdapter adapter = new BasketAdapter(basket,getContext(),orderButton);
        mrecyclerView.setAdapter(adapter);
        Log.e("Basket size",Integer.toString(basket.size()));


    }
}
