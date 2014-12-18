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

    protected List<String> extractErrors(T response){
        if(response == null){
            return Arrays.asList("UnknownError");
        } else {
            return Arrays.asList();
        }
    }

    protected T preProcessResponse(T response){
        return response;
    }

    protected void delegateResponseToHandler(T response){
        if(this.mResponseHandler != null){
            List<String> errors = this.extractErrors(response);
            if(errors == null || errors.isEmpty()){
                this.mResponseHandler.onSuccess(this.preProcessResponse(response));
            } else {
                this.mResponseHandler.onFailure(errors);
            }
        }
    }

    protected abstract String getTag();

    public void executeAsync(MMSResponseHandler<T> handler){
        Log.d(MMSApiManager.TAG, "Starting request asynchronously: " + this.getTag());
        this.mResponseHandler = handler;
        this.mAsyncRequest = new MMSApiAsyncRequest();
        this.mAsyncRequest.execute();
    }

    public T executeOnThread(){
        Log.d(MMSApiManager.TAG, "Starting request synchronously: " + this.getTag());
        T response = null;
        try {
            response = this.performRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.preProcessResponse(response);
    }

    protected abstract T performRequest() throws Exception;

    public void cancelRequest(){
        if(this.mAsyncRequest != null){
            this.mAsyncRequest.cancel(true);
            this.mAsyncRequest = null;
        }
    }

    private class MMSApiAsyncRequest extends AsyncTask<Void, Integer, T> {

        @Override
        protected T doInBackground(Void... params) {
            T response = null;
            try {
                response = performRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
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
