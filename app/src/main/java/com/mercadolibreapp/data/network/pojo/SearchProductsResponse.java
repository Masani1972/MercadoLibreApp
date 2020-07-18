package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchProductsResponse {
    @SerializedName("results")
    @Expose
    public List<ProductModel> products;
}
