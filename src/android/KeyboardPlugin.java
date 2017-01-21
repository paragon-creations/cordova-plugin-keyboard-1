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
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
       super.initialize(cordova, webView);
       
       @Override
       public boolean onKeyUp(int keyCode, KeyEvent event) {
           return webView.getView().onKeyUp(keyCode, event);
       }
   }
}
