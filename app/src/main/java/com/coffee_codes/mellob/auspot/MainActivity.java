package com.coffee_codes.mellob.auspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bekkostudio.compactWebview.DefaultSetting;
import com.bekkostudio.compactWebview.SmartWebViewCompact;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    SmartWebViewCompact smartWebViewCompact = new SmartWebViewCompact();
    private static final String ONESIGNAL_APP_ID = "6c105719-a866-42eb-9ad6-88c1f3c43592";
    String user_id, session;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        smartWebViewCompact.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
        setContentView(R.layout.activity_main);

        //Permission variables
        smartWebViewCompact.ASWP_JSCRIPT     = true;     //enable JavaScript for webview
        smartWebViewCompact.ASWP_FUPLOAD     = true;     //upload file from webview
        smartWebViewCompact.ASWP_CAMUPLOAD   = true;     //enable upload from camera for photos
        smartWebViewCompact.ASWP_ONLYCAM     = false;   //incase you want only camera files to upload
        smartWebViewCompact.ASWP_MULFILE     = true;    //upload multiple files in webview
        smartWebViewCompact.ASWP_LOCATION    = true;     //track GPS locations
        smartWebViewCompact.ASWP_RATINGS     = true;     //show ratings dialog; auto configured, edit method get_rating() for customizations
        smartWebViewCompact.ASWP_PBAR        = true;     //show progress bar in app
        smartWebViewCompact.ASWP_ZOOM        = false;    //zoom control for webpages view
        smartWebViewCompact.ASWP_SFORM       = true;    //save form cache and auto-fill information
        smartWebViewCompact.ASWP_OFFLINE     = false;    //whether the loading webpages are offline or online
        smartWebViewCompact.ASWP_EXTURL      = true;     //open external url with default browser instead of app webview
        smartWebViewCompact.ASWP_ROOT        = true;    //False if you need to use webview in other intent activity
        smartWebViewCompact.ASWP_SPLASH      = true;    //enable splash screen

        //Configuration variables
        smartWebViewCompact.ASWV_URL          = "https://auspot.mellob.co"; //complete URL of your website or webpage
        smartWebViewCompact.ASWV_F_TYPE       = "*/*";  //to upload any file type using "*/*"; check file type references for more

        //Rating system variables
        DefaultSetting.ASWR_DAYS            = 3;        //after how many days of usage would you like to show the dialoge
        DefaultSetting.ASWR_TIMES           = 10;       //overall request launch times being ignored
        DefaultSetting.ASWR_INTERVAL        = 2;        //reminding users to rate after days interval

        user_id = smartWebViewCompact.getCookie("https://auspot.mellob.co", "user_id");
        session = smartWebViewCompact.getCookie("https://auspot.mellob.co", "PHPSESSID");
        Toast.makeText(getApplicationContext(), "USERID is: "+user_id, Toast.LENGTH_LONG).show();

        WebView webView = (WebView) findViewById(R.id.msw_view);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.msw_progress);
        RelativeLayout splashScreen = (RelativeLayout) findViewById(R.id.logosplash); //logosplash
        smartWebViewCompact.onCreate(this,webView,progressBar,splashScreen);
    }


    @Override
    public void onBackPressed() {
        smartWebViewCompact.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState ){
        super.onSaveInstanceState(outState);
        smartWebViewCompact.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        smartWebViewCompact.onRestoreInstanceState(savedInstanceState);
    }
}