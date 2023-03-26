package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String[] texts = {
            // BUMP OF CHICKEN の楽曲リストより
            "Stage of the ground",
            "天体観測",
            "Title of mine",
            "キャッチボール",
            "ハルジオン",
            "べンチとコーヒー",
            "メロディーフラッグ",
            "ベル",
            "ダイヤモンド",
            "ダンデライオン",
            "asgard",
            "オンリー ロンリー グローリー",
            "乗車権",
            "ギルド",
            "emabrance",
            "sailing day",
            "同じドアをくぐれたら",
            "車輪の唄",
            "スノースマイル",
            "レム",
            "fire sign",
            "太陽",
            "ロストマン",
            "midgard"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        ListView listView = new ListView(this);
        setContentView(listView);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, R.layout.list, texts);

        listView.setAdapter(arrayAdapter);
    }
}
