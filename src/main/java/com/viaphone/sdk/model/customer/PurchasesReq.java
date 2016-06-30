package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;
import com.viaphone.sdk.model.enums.PurchaseStatus;

public class PurchasesReq extends Request {

    private final PurchaseStatus status;

    public PurchasesReq(PurchaseStatus status) {
        this.status = status;
    }

    public PurchaseStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "\n\tstatus: " + status + super.toString();
    }
}
