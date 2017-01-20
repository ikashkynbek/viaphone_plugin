package com.viaphone.sdk;

import com.viaphone.sdk.model.customer.SignupReq;
import com.viaphone.sdk.model.customer.SignupResp;

import static com.viaphone.sdk.utils.Constants.DEFAULT_HOST;

@Deprecated
public class RestSdk {

    private final String URL_SIGN_UP;

    public RestSdk() throws Exception {
        this(DEFAULT_HOST);
    }

    public RestSdk(String host) throws Exception {
        this.URL_SIGN_UP = host + "/customer/login";
    }

    public SignupResp signUp(String phone) throws Exception {
        return signUp(new SignupReq(phone));
    }

    public SignupResp signUp(SignupReq req) throws Exception {
        return (SignupResp) HttpClient.sendRequest(URL_SIGN_UP, null, req);
    }
}
