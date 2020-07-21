package com.mercadolibreapp.ui.result_products;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercadolibreapp.R;
import com.mercadolibreapp.R2;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.ui.detail_products.DetailProductFragment;


import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultProductsFragment extends Fragment implements RecyclerViewAdapter.ClickListener{

    private static final String ARG_LIST_PRODUCTS = "list_products";

    private List<ProductModel> data;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            data = (List<ProductModel>) getArguments().getSerializable(ARG_LIST_PRODUCTS);
        }

        View rootView = inflater.inflate(R.layout.fragment_result_products, container, false);
        ButterKnife.bind(this,rootView);
        initRecyclerView();
        return rootView;
    }

    @Override
    public void launchIntent(String id) {
        DetailProductFragment fragmentDetail = DetailProductFragment.newInstance(id);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frProducts, fragmentDetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initRecyclerView() {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerViewAdapter.setData(data);
        recyclerViewAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}