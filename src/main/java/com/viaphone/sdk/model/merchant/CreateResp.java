package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class CreateResp extends Response {

    private Long purchaseId;
    private PurchaseStatus purchaseStatus;
    private String token;
    private ConfirmType confirmType;
    private String qr;


    public CreateResp(long purchaseId, PurchaseStatus purchaseStatus, String token, ConfirmType confirmType, String qr) {
        this.purchaseId = purchaseId;
        this.purchaseStatus = purchaseStatus;
        this.token = token;
        this.confirmType = confirmType;
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


    public ConfirmType getConfirmType() {
        return confirmType;
    }

    @Override
    public String toString() {
        return "ref: " + ref + " purchaseId: " + purchaseId + " token: " + token
                + " purchaseStatus:" + purchaseStatus + " status: " + status;
    }


    public enum ConfirmType {
        SMS, PUSH, QR
    }
}
