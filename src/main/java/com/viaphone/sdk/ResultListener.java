package com.viaphone.sdk;

import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.enums.PurchaseStatus;

@Deprecated
public interface ResultListener {

    void onAuthorized(CustomerPurchase purchase);

    void onConfirmed(PurchaseStatus status);

    void onCancel(PurchaseStatus status);

//    void onError(Response.Status status);
}
