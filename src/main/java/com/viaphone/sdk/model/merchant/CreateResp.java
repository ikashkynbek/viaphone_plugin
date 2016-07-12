package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.enums.ConfirmType;
import com.viaphone.sdk.model.enums.PurchaseStatus;
import com.viaphone.sdk.model.Response;

public class CreateResp extends Response {

    private Long purchaseId;
    private PurchaseStatus purchaseStatus;
    private String confirmCode;
    private ConfirmType confirmType;

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public void setConfirmCode(String token) {
        this.confirmCode = token;
    }

    public ConfirmType getConfirmType() {
        return confirmType;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmType(ConfirmType confirmType) {
        this.confirmType = confirmType;
    }

    @Override
    public String toString() {
        return "\n\tpurchaseId: " + purchaseId +
                "\n\tpurchaseStatus: " + purchaseStatus +
                "\n\tconfirmCode: " + confirmCode +
                "\n\tconfirmType: " + confirmType +
                super.toString();
    }
}
