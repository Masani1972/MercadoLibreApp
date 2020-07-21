package com.mercadolibreapp.ui.detail_products;

import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.pojo.ProductInfo;


import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenterImpl implements DetailProductContract.Presenter {
    private ApiService apiService;
    private DetailProductContract.View mView;


    @Inject
    public DetailPresenterImpl(ApiService apiService, DetailProductContract.View mView) {
        this.apiService = apiService;
        this.mView = mView;
    }


    @Override
    public void getProductInfo(String id) {
        apiService.getProductInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductInfo>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        // Do nothing
                    }

                    @Override
                    public void onNext(ProductInfo productInfo) {
                        mView.showData(productInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        // Do nothing
                    }
                });
    }
}

