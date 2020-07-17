package com.mercadolibreapp.ui.searchproducts.fragments;

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
import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.ui.searchproducts.adapter.RecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultProductsFragment extends Fragment implements RecyclerViewAdapter.ClickListener{

    private static final String ARG_LIST_PRODUCTS = "list_products";

    private List<ProductModel> data;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;


    public ResultProductsFragment() {
    }


    public static ResultProductsFragment newInstance() {
        ResultProductsFragment fragment = new ResultProductsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = (List<ProductModel>) getArguments().getSerializable(ARG_LIST_PRODUCTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_result_products, container, false);
        ButterKnife.bind(this,rootView);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerViewAdapter.setData(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void launchIntent( List<PictureProduct> listImages) {
        DetailProductFragment fragmentDetail = DetailProductFragment.newInstance(listImages);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frProducts, fragmentDetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}