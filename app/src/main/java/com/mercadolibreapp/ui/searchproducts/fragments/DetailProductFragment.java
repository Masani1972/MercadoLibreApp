package com.mercadolibreapp.ui.searchproducts.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercadolibreapp.R;
import com.mercadolibreapp.R2;
import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.mercadolibreapp.ui.searchproducts.adapter.ViewPagerAdapter;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailProductFragment extends Fragment {

    @BindView(R2.id.view_pager)
    ViewPager view_pager;

    private static final String ARG_PARAM1 = "param1";

    private List<PictureProduct> listImages;


    public DetailProductFragment() {
        // Required empty public constructor
    }

    public static DetailProductFragment newInstance(List<PictureProduct> listURLImages) {
        DetailProductFragment fragment = new DetailProductFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) listURLImages);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listImages = (List<PictureProduct>) getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_product, container, false);
        ButterKnife.bind(this,rootView);

        ViewPagerAdapter adapter = new ViewPagerAdapter(rootView.getContext(), listImages);
        view_pager.setAdapter(adapter);
        return rootView;
    }

}