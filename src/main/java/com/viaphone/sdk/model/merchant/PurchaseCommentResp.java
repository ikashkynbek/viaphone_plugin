package com.viaphone.sdk.model.merchant;


import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class PurchaseCommentResp extends Response {

    private List<String> comments = new ArrayList<>();

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "\n\tcomments: " + comments + super.toString();
    }
}
