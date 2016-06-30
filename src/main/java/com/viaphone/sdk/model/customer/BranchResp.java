package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.CustomerBranch;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.List;

public class BranchResp extends Response {

    private List<CustomerBranch> branches = new ArrayList<>();

    public List<CustomerBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<CustomerBranch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "\n\tbranches: " + branches + super.toString();
    }
}
