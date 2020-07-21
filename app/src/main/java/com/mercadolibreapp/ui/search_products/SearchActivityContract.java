package com.mercadolibreapp.ui.search_products;

import com.mercadolibreapp.data.network.pojo.ProductModel;
import com.mercadolibreapp.utils.TypeAlert;

import java.util.List;

public interface SearchActivityContract {

    interface View{
        void showData(List<ProductModel> data);
        void showError(TypeAlert typeAlert, int stringResource,String message);
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void searchProduct(String q);
    }
}
