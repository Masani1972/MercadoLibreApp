package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductModel {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("title")
    @Expose
    public String name;

    @SerializedName("price")
    @Expose
    public String price;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

}
