package com.viaphone.sdk;

import com.viaphone.sdk.model.CustomerPurchase;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.merchant.PurchaseStatusResp;

public interface ResultListener {

    void onAuthorized(CustomerPurchase purchase);

    void onConfirmed(PurchaseStatus status);

    void onCancel(PurchaseStatus status);

    void onError(PurchaseStatusResp.Status status);
}
