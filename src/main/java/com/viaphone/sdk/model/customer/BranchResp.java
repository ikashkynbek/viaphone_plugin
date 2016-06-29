package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Branch;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class BranchResp extends Response {

    private List<Branch> branches = new ArrayList<>();

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
