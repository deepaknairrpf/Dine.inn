package com.example.grey_hat.dineinn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuisineFrag extends Fragment{

    private RecyclerView mrecyclerView;
    private DatabaseReference databaseReference;
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
            Toast.makeText(mview.getContext(),name,Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(),"OnStart Called",Toast.LENGTH_LONG).show();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Cuisine");
        mrecyclerView=(RecyclerView)getView().findViewById(R.id.CuisineRecylcList);
        mrecyclerView.setNestedScrollingEnabled(false);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerAdapter<Cuisine,CuisineViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cuisine, CuisineViewHolder>(Cuisine.class,R.layout.cuisine_card_view,CuisineViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(CuisineViewHolder viewHolder, Cuisine model, int position) {
                viewHolder.setName(model.getName());
            }
        };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
