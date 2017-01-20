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
    private CallbackContext keyup_callback = null;
    private CallbackContext keydown_callback = null;
    private CallbackContext allkeys_callback = null;
    private View currentView = null;
    
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.currentView = webView.getView();
        this.currentView.setOnKeyListener(
            new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    return doKey(view, keyCode, event);
                }
            }
        );
    }
    
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
        result.setKeepCallback(true);
        
        if (action.equalsIgnoreCase("keyUp")) {
            this.keyup_callback = callbackContext;
        } else {
            return false;
        }
        
        callbackContext.sendPluginResult(result);

        return true;
    }
    public boolean doKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+keyCode);
            this.keyup_callback.sendPluginResult(result);
        }
        return false;
    }
}
