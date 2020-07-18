package com.mercadolibreapp.ui.detail_products;

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
import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.pojo.PictureProduct;
import com.mercadolibreapp.data.network.pojo.ProductInfo;
import com.mercadolibreapp.di.component.ApplicationComponent;
import com.mercadolibreapp.di.component.DaggerDetailFragmentComponent;
import com.mercadolibreapp.di.component.DetailFragmentComponent;
import com.mercadolibreapp.di.component.SearchActivityComponent;
import com.mercadolibreapp.di.module.DetailFragmentMvpModule;
import com.mercadolibreapp.di.module.ResultProductsFragmentModule;
import com.mercadolibreapp.di.module.SearchActivityContextModule;
import com.mercadolibreapp.di.module.SearchActivityMvpModule;
import com.mercadolibreapp.ui.detail_products.adapter.ViewPagerAdapter;
import com.mercadolibreapp.ui.result_products.ResultProductsFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailProductFragment extends Fragment  implements DetailProductContract.View{

    @BindView(R2.id.view_pager)
    ViewPager view_pager;

    @BindView(R2.id.txtCountPhotos)
    TextView txtCountPhotos;

@Inject
        DetailPresenterImpl presenter;

    DetailFragmentComponent detailFragmentComponent;

    private static final String ARG_ID_PRODUCT = "id";

    private List<PictureProduct> listImages;
    private ViewPagerAdapter adapter;


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
        ButterKnife.bind(this,rootView);
        adapter = new ViewPagerAdapter(rootView.getContext());
        return rootView;
    }


    private void initViews() {
        txtCountPhotos.setText(listImages.size()+ "  " + getResources().getString(R.string.txtPhotos));
    }

    @Override
    public void showData(ProductInfo productInfo) {
        listImages = productInfo.pictures;
        adapter.setImages(listImages);
        view_pager.setAdapter(adapter);
        initViews();
    }

    @Override
    public void showError( String statusMessage) {

    }

}