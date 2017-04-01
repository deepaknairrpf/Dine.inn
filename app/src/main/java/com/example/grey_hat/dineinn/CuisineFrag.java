package com.example.grey_hat.dineinn;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuisineFrag extends Fragment {

    private RecyclerView mrecyclerView;
    private DatabaseReference databaseReference;
    private  ProgressBar progressBar;
    public CuisineFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cuisine, container, false);
    }

    public static class CuisineViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public CuisineViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
        }
        public void setName(String name)
        {
            TextView CuisineName = (TextView)mview.findViewById(R.id.FoodName);
            CuisineName.setText(name);
        }
        public void setImage(Context ctx, String img) {
            ImageView cuisineImg = (ImageView)mview.findViewById(R.id.cardImg);
            Picasso.with(ctx).load(img).into(cuisineImg);

        }


    }
    @Override
    public void onStart() {
        super.onStart();
       progressBar = (ProgressBar)getView().findViewById(R.id.pbLoading);
        progressBar.setVisibility(View.VISIBLE);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Cuisine");
        mrecyclerView=(RecyclerView)getView().findViewById(R.id.CuisineRecylcList);
        mrecyclerView.setNestedScrollingEnabled(false);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<Cuisine,CuisineViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder>(Cuisine.class,R.layout.cuisine_card_view,CuisineViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(CuisineViewHolder viewHolder, Cuisine model, int position) {
                final String post_key=getRef(position).getKey();
                viewHolder.setName(model.getName());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImgUrl());
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment food = new Food();
                        Bundle bundle = new Bundle();
                        bundle.putString("food",post_key);
                        food.setArguments(bundle);
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.cuisine_container,food);
                        ft.addToBackStack(null);
                        ft.commit();


                    }
                });
            }
        };

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);


    }

}
