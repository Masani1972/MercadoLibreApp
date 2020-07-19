package com.mercadolibreapp.data.network;

import com.mercadolibreapp.data.network.pojo.ProductInfo;
import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/sites/MLA/search?")
    Observable<SearchProductsResponse> getProductForSearch(
            @Query("q") String q);

    @GET("/items/{id}")
    Observable<ProductInfo> getProductInfo
            (@Path("id") String id);

}


