package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;
import com.viaphone.sdk.model.enums.CampaignStatus;

public class OffersReq extends Request {

    private CampaignStatus status;

    public OffersReq(CampaignStatus status) {
        this.status = status;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "\n\tstatus: " + status.name() +
                super.toString();
    }
}
