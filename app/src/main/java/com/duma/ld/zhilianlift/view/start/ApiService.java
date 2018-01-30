package com.duma.ld.zhilianlift.view.start;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.duma.ld.zhilianlift.base.MyApplication;

import static com.duma.ld.zhilianlift.util.Constants.sp_Api;

/**
 * Created by liudong on 2018/1/30.
 */

public class ApiService extends Service implements SensorEventListener {
    private SensorManager mSensorManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        if (mSensorManager != null) {
            //获取加速度传感器
            Sensor mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math
                    .abs(z) > 17)) {
                boolean aBoolean = SPUtils.getInstance().getBoolean(sp_Api, true);
                if (aBoolean) {
                    SPUtils.getInstance().put(sp_Api, false);
                    Intent intent = new Intent(MyApplication.getInstance(), ApiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
