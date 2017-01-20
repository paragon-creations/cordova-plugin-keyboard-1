package org.apache.cordova.labs.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class KeyboardPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	Activity activity = this.cordova.getActivity();
    	View view;
    	try {
    	    view = (View)webView.getClass().getMethod("getView").invoke(webView);
    	} catch (Exception e) {
    	    view = (View)webView;
    	}
        
        if ("register".equals(action)) {
            view.setOnKeyListener(
                new OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() == KeyEvent.ACTION_UP) {
                            //callbackContext.success("KeyCode: "+(String.valueOf(keyCode)));
                            PluginResult result = new PluginResult(PluginResult.Status.OK, "KeyCode: "+(String.valueOf(keyCode)));
                            callbackContext.sendPluginResult(result);
                        }
                        return true;
                    };
                }
            );
    	    return true;
    	} else {
            callbackContext.error(action + " is not a supported action");
            return false;
        }
    }
}
