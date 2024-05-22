package com.example.newquiz.fragment.category;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.newquiz.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private GridView gridView;
    private List<CategoryModel> categryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        gridView = view.findViewById(R.id.categoryGrid);
        loadCategories();

        CategoryAdapter adapter = new CategoryAdapter(categryList);
        gridView.setAdapter(adapter);

        return view;
    }

    private void loadCategories() {
        categryList.clear();
        categryList.add(new CategoryModel("1", "Nur", 20));
        categryList.add(new CategoryModel("2", "Nurasyl", 25));
        categryList.add(new CategoryModel("3", "Beka", 23));
        categryList.add(new CategoryModel("4", "History", 5));
        categryList.add(new CategoryModel("5", "English", 22));
        categryList.add(new CategoryModel("6", "English", 22));
        categryList.add(new CategoryModel("7", "English", 22));
        categryList.add(new CategoryModel("7", "English", 22));
        categryList.add(new CategoryModel("7", "English", 22));
        categryList.add(new CategoryModel("7", "English", 22));
    }
}