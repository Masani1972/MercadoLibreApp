package com.mercadolibreapp.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttributeProduct {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("value_id")
    @Expose
    public String value_id;

    @SerializedName("value_name")
    @Expose
    public String value_name;
}
