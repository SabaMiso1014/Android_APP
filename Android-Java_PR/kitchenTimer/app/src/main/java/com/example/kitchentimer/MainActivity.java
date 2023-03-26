package com.example.kitchentimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTimerText;
    ImageView mStartButton;
    ImageView mStopButton;
    SoundPool mSoundPool;
    int mSoundResId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mTimerText = (TextView) findViewById(R.id.timer_text);
        mStartButton = (ImageView) findViewById(R.id.timer_start);
        mStopButton = (ImageView) findViewById(R.id.timer_stop);

        final CountDownTimer timer = new CountDownTimer(3*60*1000, 100) {

            // 指定した間隔で実行したい処理
            @Override
            public void onTick(long l) {
                long minute = l / 1000 / 60;
                long second = l / 1000 % 60;

                mTimerText.setText(String.format("%1$d:%2$02d", minute, second));
            }

            // タイマー終了時の処理
            @Override
            public void onFinish() {
                mTimerText.setText("0:00");
            }
        };

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.start();
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });

    }

    // 画面が表示されたときに呼ばれるメソッド
    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build())
                    .build();
        } else {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_ALARM, 0);
        }
    }
    
    // 画面を閉じたときに呼ばれるメソッド
    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
    }

}
