package com.mercadolibreapp.ui.detail_products;

import com.mercadolibreapp.data.network.pojo.ProductInfo;

public interface DetailProductContract {

    interface View{
        void showData(ProductInfo productInfo);
        void showError(String statusMessage);
    }

    interface Presenter{
        void getProductInfo(String id);
    }
}
