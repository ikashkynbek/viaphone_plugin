package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class CreateResp extends Response {

    private Long purchaseId;
    private PurchaseStatus purchaseStatus;
    private String token;
    private String qr;

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public String getToken() {
        return token;
    }

    public String getQr() {
        return qr;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId +
                "\n\tpurchaseStatus: " + purchaseStatus +
                "\n\ttoken: " + token +
                "\n\tqr: " + qr +
                super.toString();
    }
}
