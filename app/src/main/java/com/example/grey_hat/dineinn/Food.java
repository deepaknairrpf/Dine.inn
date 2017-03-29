package com.example.grey_hat.dineinn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Food extends Fragment {

    private String food;
    private RecyclerView foodRecView;
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

    @Override
    public void onStart() {
        super.onStart();
        TextView foodText= (TextView)getView().findViewById(R.id.FoodText);
        foodText.setText(food);
    }
}
