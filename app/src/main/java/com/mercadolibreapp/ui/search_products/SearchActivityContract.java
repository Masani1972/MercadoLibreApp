package com.mercadolibreapp.ui.search_products;

import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.utils.TypeError;

import java.util.List;

public interface SearchActivityContract {

    interface View{
        void showData(List<ProductModel> data);
        void showError(TypeError typeError, String statusMessage);
        void showProgress();
        void hideProgress();
        void showComplete();
    }

    interface Presenter{
        void searchProduct(String q);
    }
}
