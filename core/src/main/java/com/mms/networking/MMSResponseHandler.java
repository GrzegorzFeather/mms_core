package com.mms.networking;

import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public abstract class MMSResponseHandler<T> {

    public abstract void onSuccess(T response);
    public abstract void onFailure(List<String> errors);

}
