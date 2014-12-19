package com.mms.networking;

import android.os.AsyncTask;
import android.util.Log;

import com.mms.networking.model.ContentType;
import com.mms.networking.model.MMSModel;
import com.mms.networking.model.MMSResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public abstract class MMSRequest<T extends MMSModel> {

    protected MMSResponseHandler<T> mResponseHandler;
    private MMSApiAsyncRequest mAsyncRequest;

    protected List<String> extractErrors(MMSResponse response){
        if(response == null || response.getType().equals(ContentType.error)){
            return response == null ? Arrays.asList("UnknownError")
                    : Arrays.asList(response.getMessage());
        } else {
            return Arrays.asList();
        }
    }

    protected T delegate(MMSResponse response){
        T responseContent = null;
        List<String> errors = this.extractErrors(response);

        if(this.mResponseHandler != null){
            if(errors != null && !errors.isEmpty()){
                this.mResponseHandler.onFailure(errors);
            } else {
                responseContent = (T) response.getContent().asResponseModel(response.getType());
                this.mResponseHandler.onSuccess(responseContent);
            }
        }

        return responseContent;
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
        return this.delegate(this.launchRequest());
    }

    public void cancelRequest(){
        if(this.mAsyncRequest != null){
            this.mAsyncRequest.cancel(true);
            this.mAsyncRequest = null;
        }
    }

    private MMSResponse launchRequest(){
        MMSResponse response = null;
        try {
            response = this.perform();
        } catch (Exception e) {
            Log.d(MMSApiManager.TAG, "Error: " + e.getClass());
            //e.printStackTrace();
        }
        return response;
    }

    protected abstract MMSResponse perform() throws Exception;

    private class MMSApiAsyncRequest extends AsyncTask<Void, Integer, MMSResponse> {

        @Override
        protected MMSResponse doInBackground(Void... params) {
            return launchRequest();
        }

        @Override
        protected void onPostExecute(MMSResponse response) {
            delegate(response);
        }

        @Override
        protected void onCancelled() {
            Log.d(MMSApiManager.TAG, "Request canceled: " + getTag());
        }
    }

}
