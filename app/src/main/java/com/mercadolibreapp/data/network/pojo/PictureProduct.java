package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictureProduct {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("secure_url")
    @Expose
    public String url;

}
