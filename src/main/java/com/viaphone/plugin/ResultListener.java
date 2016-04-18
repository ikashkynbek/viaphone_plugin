package com.viaphone.plugin;

import com.viaphone.plugin.model.PurchaseStatus;
import com.viaphone.plugin.model.PurchaseStatusResp;

public interface ResultListener {

    void onAuthorized(double discountPrice);

    void onConfirmed(PurchaseStatus status);

    void onCancel(PurchaseStatus status);

    void onError(PurchaseStatusResp.Status status);
}
