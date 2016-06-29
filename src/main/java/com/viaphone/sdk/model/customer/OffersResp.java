package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Offer;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class OffersResp extends Response {

    private List<Offer> offers = new ArrayList<>();

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
