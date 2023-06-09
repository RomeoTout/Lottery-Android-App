package com.rtlab.numerenorocoase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AppRater {

    private static final String PREF_NAME = "apprater";
    private final static String APP_TITLE = "Numere Norocoase";// App Name
    private final static String APP_PNAME = "com.rtlab.numerenorocoase";// Package Name
    private final static String APP_LOCATION = "https://play.google.com/store/apps/details?id=com.rtlab.numerenorocoase";
    private final static int DAYS_UNTIL_PROMPT = 5;//Min number of days
    private final static int LAUNCHES_UNTIL_PROMPT = 5;//Min number of launches

    public static void app_launched(Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(PREF_NAME, 0);
        if (prefs.getBoolean("dontshowagain", false)) { return ; }

        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }

        editor.commit();
    }

    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(mContext);
        dialog.setTitle("Evalueaza " + APP_TITLE);

        LinearLayout ll = new LinearLayout(mContext);
        ll.setBackgroundColor(Color.parseColor("#000000"));
        ll.setPadding(55, 55, 55, 55);
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(mContext);
        tv.setText(new StringBuilder().append("Daca aplicatia ").append(APP_TITLE).append(" ti-a fost de folos, te rugam sa o evaluezi. Multumim!").toString());
        tv.setWidth(540);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        ll.addView(tv);

        Button b1 = new Button(mContext);
        b1.setText(R.string.evaluate);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(APP_LOCATION)));
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(b1);

        Button b2 = new Button(mContext);
        b2.setText("Mai tarziu");
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ll.addView(b2);

        Button b3 = new Button(mContext);
        b3.setText("Nu!");
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(b3);

        dialog.setContentView(ll);
        dialog.show();
    }

}
