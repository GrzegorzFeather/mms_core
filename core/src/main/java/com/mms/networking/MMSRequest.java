package com.mms.networking;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public abstract class MMSRequest<T> {

    protected MMSResponseHandler<T> mResponseHandler;
    private MMSApiAsyncRequest mAsyncRequest;

    protected MMSRequest(MMSResponseHandler<T> responseHandler){
        this.mResponseHandler = responseHandler;
    }

    protected List<String> preProcessNotification(T response){
        if(response == null){
            return Arrays.asList("UnknownError");
        } else {
            return Arrays.asList();
        }
    }

    protected void delegateResponseToHandler(T response){
        if(this.mResponseHandler != null){
            List<String> errors = this.preProcessNotification(response);
            if(errors == null || errors.isEmpty()){
                this.mResponseHandler.onSuccess(response);
            } else {
                this.mResponseHandler.onFailure(errors);
            }
        }
    }

    protected abstract String getTag();

    public void executeAsync(){
        Log.d(MMSApiManager.TAG, "Starting request asynchronously: " + this.getTag());
        this.mAsyncRequest = new MMSApiAsyncRequest();
        this.mAsyncRequest.execute();
    }

    public T executeOnThread(){
        Log.d(MMSApiManager.TAG, "Starting request synchronously: " + this.getTag());
        return this.performRequest();
    }

    protected abstract T performRequest();

    public void cancelRequest(){
        if(this.mAsyncRequest != null){
            this.mAsyncRequest.cancel(true);
            this.mAsyncRequest = null;
        }
    }

    private class MMSApiAsyncRequest extends AsyncTask<Void, Integer, T> {

        @Override
        protected T doInBackground(Void... params) {
            return performRequest();
        }

        @Override
        protected void onPostExecute(T response) {
            delegateResponseToHandler(response);
        }

        @Override
        protected void onCancelled() {
            Log.d(MMSApiManager.TAG, "Request canceled: " + getTag());
        }
    }

}
