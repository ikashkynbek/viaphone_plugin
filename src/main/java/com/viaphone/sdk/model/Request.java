package com.viaphone.sdk.model;


import com.viaphone.sdk.utils.Utils;


abstract public class Request {

    protected Long ref;

    protected Request() {
        ref = Utils.nextRef();
    }

    public long getRef() {
        return ref;
    }
}
