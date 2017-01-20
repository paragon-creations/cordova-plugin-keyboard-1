package com.otb.cordova.keyboard;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.view.*;

public class KeyboardPlugin extends CordovaPlugin {
    private static CallbackContext callback = null;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	Activity activity = this.cordova.getActivity();
    	View view;
    	try {
    	    view = (View)webView.getClass().getMethod("getView").invoke(webView);
    	} catch (Exception e) {
    	    view = (View)webView;
    	}
        
        callback = callbackContext;
        
        if ("register".equals(action)) {
            view.setOnKeyListener(
                new OnKeyListener() {
                    @Override
                    //public boolean onKey(View v, int keyCode, KeyEvent event) {
                    public boolean onKey(int keyCode, KeyEvent event) {
                        //if (event.getAction() == KeyEvent.ACTION_UP) {
                            //callbackContext.success("KeyCode: "+(String.valueOf(keyCode)));
                            PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+String.valueOf(keyCode));
                            result.setKeepCallback(true);
                            callback.sendPluginResult(result);
                        //}
                        return true;
                    };
                }
            );
    	    return true;
    	} else {
            callback.error(action + " is not a supported action");
            return false;
        }
    }
}
