package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductModel {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("domain_id")
    @Expose
    public String domain_id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("attributes")
    @Expose
    public List<AttributeProduct> attributes;

    @SerializedName("pictures")
    @Expose
    public List<PictureProduct> pictures;
}
