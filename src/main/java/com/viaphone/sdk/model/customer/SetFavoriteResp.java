package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Response;

/**
 * Created by dansharky on 1/19/17.
 */
public class SetFavoriteResp extends Response {

    private boolean isFavorite;
    private Long campaignId;

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public Long getCampaignId() { return campaignId; }

    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }
}
