package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        // テキストビューのインスタンスの参照情報を取得
        final TextView tv1 = (TextView) findViewById(R.id.textView1);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);

        //　シークバーのイベントリスナーを登録
        ((SeekBar) findViewById(R.id.seekBar))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    // シークバーの状態が変更されたときに呼び出されるメソッド
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        // テキストビューにシークバーの値を出力
                        tv2.setText(String.format(getString(R.string.level) + "%d", progress));
                    }

                    // タッチジェスチャーが開始されたときに呼び出されるメソッド
                    @Override
                    public void onStartTrackingTouch(SeekBar seekbar) {
                        tv1.setText(R.string.touch);
                    }

                    // シークバーがリリースされたときに呼び出されるメソッド
                    @Override
                    public void onStopTrackingTouch(SeekBar seekbar) {
                        tv1.setText(R.string.msg);
                    }
                });
    }
}
