package com.mercadolibreapp.data.network;

import com.mercadolibreapp.data.network.pojo.SearchProductsResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search?")
    Observable<SearchProductsResponse> getProductForSearch(
            @Query("status") String status,
            @Query("site_id") String site_id,
            @Query("q") String q);
}


