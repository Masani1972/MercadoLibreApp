package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductInfo {

    @SerializedName("title")
    @Expose
    public String name;

   @SerializedName("pictures")
    @Expose
    public List<PictureProduct> pictures;

    @SerializedName("descriptions")
    @Expose
    public List<Descriptions> descriptions;

    @SerializedName("condition")
    @Expose
    public String condition;

}
