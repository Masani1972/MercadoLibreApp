package com.mercadolibreapp.ui.search_products;


import com.mercadolibreapp.data.network.ApiService;
import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;
import com.mercadolibreapp.utils.TypeError;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenterImpl implements SearchActivityContract.Presenter {
    private ApiService apiService;
    private SearchActivityContract.View mView;

    @Inject
    public SearchPresenterImpl(ApiService apiService, SearchActivityContract.View mView) {
        this.apiService = apiService;
        this.mView = mView;
    }


    @Override
    public void searchProduct(String q) {
        if(q != null && !q.isEmpty()) {
            mView.showProgress();

            apiService.getProductForSearch(q)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SearchProductsResponse>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SearchProductsResponse searchProductsResponse) {
                            mView.showData(searchProductsResponse.products);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(TypeError.ERROR_DATA_SERVICE, e.getMessage());
                            mView.hideProgress();
                        }

                        @Override
                        public void onComplete() {
                            mView.showComplete();
                            mView.hideProgress();
                        }
                    });
        }else{
            mView.showError(TypeError.ERROR_VALIDATION_DATA,"");
        }
    }
}