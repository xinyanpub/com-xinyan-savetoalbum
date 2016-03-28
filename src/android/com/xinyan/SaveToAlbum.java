package com.xinyan;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.ContentResolver;

import java.io.FileNotFoundException;

public class SaveToAlbum extends CordovaPlugin {
    public static String TAG = "SaveToAlbum";

    private ContentResolver getContentResolver(){
        return cordova.getActivity().getContentResolver();
    }

    /**
     * @param source : image url
     * @param filePath
     */
    public boolean transferAndSave(String source, String filePath) {
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(), source, filePath, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        cordova.getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(filePath)));

        return true;
    }

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.e(TAG, "SaveToAlbum execute begin");

        final String url = args.getString(0);
        final String filePath = args.getString(1);
        if (action.equals("saveToAlbum")) {
            if (this.cordova != null) {
                cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        boolean result = transferAndSave(url, filePath);
                        if (result) {
                            Log.e(TAG, "Save Success");
                            callbackContext.success("Save success");
                        } else {
                            callbackContext.error("Save failed");
                        }
                    }
                });
            }
        }
        return true;
    }
}
