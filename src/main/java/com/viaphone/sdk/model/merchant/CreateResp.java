package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class CreateResp extends Response {

    private Long purchaseId;
    private PurchaseStatus purchaseStatus;
    private String token;
    private ConfirmType confirmType;
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

    public void setConfirmType(ConfirmType confirmType) {
        this.confirmType = confirmType;
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

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId +
                "\n\tpurchaseStatus: " + purchaseStatus +
                "\n\ttoken: " + token +
                "\n\tconfirmType: " + confirmType +
                "\n\tqr: " + qr +
                super.toString();
    }

    public enum ConfirmType {
        SMS, PUSH, QR
    }
}
