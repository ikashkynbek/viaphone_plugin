package com.viaphone.sdk.model.customer;

import com.viaphone.sdk.model.Branch;
import com.viaphone.sdk.model.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchResp extends Response {
    private List<Branch> branches = new ArrayList<>();

    public BranchResp(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }


}
