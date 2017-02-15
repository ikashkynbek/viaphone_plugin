package com.viaphone.sdk.model.customer;


public class SetFavoriteReq {

    private Long campaignId;
    private boolean isFavorite;

    public SetFavoriteReq(Long campaignId, boolean isFavorite) {
        this.campaignId = campaignId;
        this.isFavorite = isFavorite;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public boolean isFavorite() { return isFavorite; }

    @Override
    public String toString() {
        return "\n\tcampaignId: " + campaignId +
                "\n\tisFavorite: " + isFavorite;
    }
}
