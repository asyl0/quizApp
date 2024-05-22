package com.example.newquiz.fragment.category;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newquiz.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<CategoryModel> categoryList;
    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView;
        if (view == null){
            myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_layout,  viewGroup, false);
        }else {
            myView = view;
        }
        TextView catName = myView.findViewById(R.id.catName);
        TextView noOfTests = myView.findViewById(R.id.no_of_tests);

        catName.setText(categoryList.get(i).getName());
        noOfTests.setText(categoryList.get(i).getNoOfTests() + " Tests");

        return myView;
    }
}
