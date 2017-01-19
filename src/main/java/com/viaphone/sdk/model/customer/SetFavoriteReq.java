package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Request;

/**
 * Created by dansharky on 1/18/17.
 */
public class SetFavoriteReq extends Request {

    private Long campaignId;

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }
}
