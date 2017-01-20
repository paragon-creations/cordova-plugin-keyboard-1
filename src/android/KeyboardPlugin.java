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

public class MyWebView implements CordovaWebView {

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (callback != null) {
            //PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+String.valueOf(keyCode));
            //result.setKeepCallback(true);
            //callback.sendPluginResult(result);
            return view.onKeyUp(keyCode, event);
        }
    }

}

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
        
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        
        if ("register".equals(action)) {
            
            /*
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                String data = "";
                if (event != null) {
                    data = String.valueOf((char)event.getUnicodeChar())
                }
                PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+data);
                result.setKeepCallback(true);
                callback.sendPluginResult(result);
                return super.dispatchKeyEvent(event);
            };
            */
            
            /*
            public boolean onKeyUp(int keyCode, KeyEvent event) {
                PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+String.valueOf(keyCode));
                result.setKeepCallback(true);
                callback.sendPluginResult(result);
                return super.onKeyUp(keyCode, event);
            }
            */
            
            /*
            view.setOnKeyListener(
                new OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
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
            */
    	    return true;
    	} else {
            callback.error(action + " is not a supported action");
            return false;
        }
    }
}
