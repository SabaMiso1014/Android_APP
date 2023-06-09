package com.example.accball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class MainActivity extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {


    SensorManager mSensorManager;
    Sensor mAccSensor;

    SurfaceHolder mHolder;
    int mSurfaceWidth;           // サーフェスビューの幅
    int mSurfaceHeight;          // サーフェスビューの高さ

    static final float RADIUS = 150.0f;     // ボールを描画するときの半径を表す定数
    static final int DIA = (int)RADIUS * 2;
    static final float COEF = 1000.0f;      // ボールの移動量を調整するための係数

    float mBallX;           // ボールの現在のx座標
    float mBallY;           // ボールの現在のy座標
    float mVX;              // ボールのx軸方向への加速度
    float mVY;              // ボールのy軸方向への加速度

    long mT0;               // 前回センサーから加速度を取得した時間

    Bitmap mBallBitmap;     // ボールの画像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        setContentView( R.layout.activity_main );

        mSensorManager = (SensorManager) getSystemService( Context.SENSOR_SERVICE );

        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER );

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mHolder = surfaceView.getHolder();

        mHolder.addCallback(this);

        // サーフェスビューを透明にする
        mHolder.setFormat( PixelFormat.TRANSLUCENT);
        surfaceView.setZOrderOnTop(true);

        // ボールの画像を用意する
        Bitmap ball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        mBallBitmap = Bitmap.createScaledBitmap(ball, DIA, DIA, false);

    }

    // 加速度センサーの値に変化があるとき呼ばれる
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = -sensorEvent.values[0];
            float y = sensorEvent.values[1];

            // 時間tを求める
            if(mT0 == 0) {
                mT0 = sensorEvent.timestamp;
                return;
            }

            float t = sensorEvent.timestamp -mT0;
            mT0 = sensorEvent.timestamp;
            t = t/ 1000000000.0f;

            // 移動距離を求める
            float dx = (mVX * t) + (x * t * t / 2.0f);
            float dy = (mVY * t) + (y * t * t / 2.0f);

            // 移動距離から、ボールの今の位置を判断
            mBallX = mBallX + dx * COEF;
            mBallY = mBallY + dy * COEF;

            // 現在のボールの移動速度を更新
            mVX = mVX + (x * t);
            mVY = mVY + (y * t);

            // ボールが画面の外に出ないようにする処理
            if(mBallX - RADIUS < 0 && mVX < 0) {
                mVX = -mVX / 1.5f;
                mBallX = RADIUS;
            } else if(mBallX + RADIUS > mSurfaceWidth && mVX > 0) {
                mVX = -mVX / 1.5f;
                mBallX = mSurfaceWidth - RADIUS;
            }

            if(mBallY - RADIUS < 0 && mVY < 0) {
                mVY = -mVY / 1.5f;
                mBallX = RADIUS;
            } else if(mBallY + RADIUS > mSurfaceHeight && mVY > 0) {
                mVY = -mVY / 1.5f;
                mBallY = mSurfaceHeight - RADIUS;
            }

            // 加速度から算出したボールの現在位置で、ボールキャンパスに描画し直す
            drawCanvas();

        }

    }

    private void drawCanvas() {

        // 画面にボールを表示する処理
        Canvas c = mHolder.lockCanvas();
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        c.drawBitmap(mBallBitmap, mBallX - RADIUS, mBallY - RADIUS, paint);

        mHolder.unlockCanvasAndPost(c);
    }


    // 加速度センサーの精度が変更されたとき呼ばれる
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // 画面が表示されたとき呼ばれる
    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    // Surfaceが作成されたときに呼ばれる
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSensorManager.registerListener(this,mAccSensor,SensorManager.SENSOR_DELAY_GAME);

    }

    // Surfaceに変更があったときに呼ばれる
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceWidth = width;
        mSurfaceHeight = height;

        // ボールの最初の位置を設定する
        mBallX = mSurfaceWidth / 2;
        mBallY = mSurfaceHeight / 2;

        // 最初の速度、時間を初期化
        mVX = 0;
        mVY = 0;
        mT0 = 0;

    }

    // Surfaceが削除されたときに呼ばれる
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSensorManager.unregisterListener(this);

    }



}
