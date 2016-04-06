package com.moonstub.kline.micah.headsup;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Micah on 4/5/2016.
 */
public class HeadsupService extends Service {

    private ImageView mImageview;
    private WindowManager mWindowManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //Not Used
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mImageview = new ImageView(this);
        mImageview.setImageResource(R.mipmap.ic_launcher);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        mWindowManager.addView(mImageview, params);

        mImageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mImageview.setX(event.getX());
                return true;
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mImageview != null) mWindowManager.removeView(mImageview);
    }
}
