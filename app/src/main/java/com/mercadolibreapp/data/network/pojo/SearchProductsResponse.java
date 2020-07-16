package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchProductsResponse {
    @SerializedName("keywords")
    @Expose
    public String keywords;

    @SerializedName("results")
    @Expose
    public List<ProductModel> products;
}
