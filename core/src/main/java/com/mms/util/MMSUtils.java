package com.mms.util;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

/**
 * Created by GrzegorzFeathers on 12/17/14.
 */
public class MMSUtils {

    public static void notifyErrors(Context ctx, List<String> errors){
        Toast.makeText(ctx, "" + errors, Toast.LENGTH_SHORT).show();
    }

}
