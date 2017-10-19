package com.savio.hp.virtualnoticeboard;

/**
 * Created by HP on 10-09-2017.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by HP on 03-12-2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static  final String TAG="MyFirebaseInsIDService";
    public void onTokenRefresh(){
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token:"+refreshedToken);
    }
}
