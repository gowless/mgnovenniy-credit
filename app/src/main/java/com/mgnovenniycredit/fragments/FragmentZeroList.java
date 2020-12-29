package com.mgnovenniycredit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mgnovenniycredit.R;
import com.mgnovenniycredit.activities.SplashActivity;
import com.mgnovenniycredit.adapters.RecyclerAdapterWithZeroList;


public class FragmentZeroList extends Fragment {
    RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_zero_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //enabling cache for better view experience
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerAdapterWithZeroList recyclerAdapterWithZeroList = new RecyclerAdapterWithZeroList(getContext(), SplashActivity.listDataZero);
        recyclerAdapterWithZeroList.setDataList(SplashActivity.listDataZero);
        recyclerView.setAdapter(recyclerAdapterWithZeroList);
        return view;
    }


}