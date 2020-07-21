package com.mercadolibreapp.ui.detail_products;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercadolibreapp.R;
import com.mercadolibreapp.R2;
import com.mercadolibreapp.common.MyApplication;
import com.mercadolibreapp.data.network.pojo.Descriptions;
import com.mercadolibreapp.data.network.pojo.ProductInfo;
import com.mercadolibreapp.di.component.ApplicationComponent;
import com.mercadolibreapp.di.component.DaggerDetailFragmentComponent;
import com.mercadolibreapp.di.component.DetailFragmentComponent;
import com.mercadolibreapp.di.module.DetailFragmentMvpModule;
import com.mercadolibreapp.ui.detail_products.adapter.ViewPagerAdapter;
import com.mercadolibreapp.utils.TypeAlert;
import com.mercadolibreapp.utils.UtilAlertClassBuilder;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailProductFragment extends Fragment implements DetailProductContract.View {

    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    @BindView(R2.id.txtCountPhotos)
    TextView txtCountPhotos;

    @BindView(R2.id.txtName)
    TextView txtName;

    @BindView(R2.id.txtCondition)
    TextView txtCondition;

    @BindView(R2.id.txtDescription)
    TextView txtDescription;

    @Inject
    DetailPresenterImpl presenter;

    DetailFragmentComponent detailFragmentComponent;

    private static final String ARG_ID_PRODUCT = "id";
    private ViewPagerAdapter adapter;
    private ProductInfo productInfo;

    public static DetailProductFragment newInstance(String id) {
        DetailProductFragment fragment = new DetailProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID_PRODUCT, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailFragmentComponent = DaggerDetailFragmentComponent.builder()
                .detailFragmentMvpModule(new DetailFragmentMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();
        detailFragmentComponent.injectDetailFragment(this);
        if (getArguments() != null) {
            String id = getArguments().getString(ARG_ID_PRODUCT);
            presenter.getProductInfo(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail_product, container, false);
        ButterKnife.bind(this, rootView);
        adapter = new ViewPagerAdapter(rootView.getContext());
        return rootView;
    }

    private void initViews() {
        txtCountPhotos.setText(productInfo.pictures.size() + "  " + getResources().getString(R.string.txtPhotos));
        txtName.setText(productInfo.name);
        txtCondition.setText(getResources().getString(R.string.txtCondition) + " " +productInfo.condition);
        txtDescription.setText(getDescription(productInfo));
    }

    @Override
    public void showData(ProductInfo productInfo) {
        this.productInfo =  productInfo;
        adapter.setImages(productInfo.pictures);
        viewPager.setAdapter(adapter);
        initViews();
    }

    @Override
    public void showError(String message) {
        AlertDialog alertDialog = new UtilAlertClassBuilder(getActivity())
                .setTitle(getResources().getString(R.string.tittleAlert))
                .setMessage(message)
                .setType(TypeAlert.TYPE_ERROR).build();

        alertDialog.show();
    }

    private String getDescription(ProductInfo productInfo) {
        String descriptionText = "";
        if (!productInfo.descriptions.isEmpty()) {
            for (Descriptions description : productInfo.descriptions) {
                descriptionText += description.id ;
            }
        }
        return descriptionText;
    }

}