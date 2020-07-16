package com.mercadolibreapp.ui.searchproducts;

import com.mercadolibreapp.data.network.pojo.ProductModel;

import java.util.List;

public interface SearchActivityContract {

    interface View{
        void showData(List<ProductModel> data);
        void showError(String statusMessage);
        void showProgress();
        void hideProgress();
        void showComplete();
    }

    interface Presenter{
        void searchProduct(String q);
    }
}
