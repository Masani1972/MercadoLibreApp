package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductInfo {

    @SerializedName("title")
    @Expose
    public String name;

    @SerializedName("garantía")
    @Expose
    public String garantía;


   @SerializedName("pictures")
    @Expose
    public List<PictureProduct> pictures;
}
