package com.viaphone.sdk.model;


import com.google.gson.Gson;
import com.viaphone.sdk.utils.Utils;


abstract public class Request {
    protected Long ref;


    protected Request() {
        ref = Utils.nextRef();
//        ref = 32434L;
    }

    public long getRef() {
        return ref;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
