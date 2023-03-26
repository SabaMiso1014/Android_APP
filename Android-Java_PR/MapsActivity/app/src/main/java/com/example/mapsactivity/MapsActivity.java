package com.example.mapsactivity;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ginza = new LatLng(35.671241,139.765041);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(ginza, 15);
        mMap.moveCamera(cu);
    }

    // アプリケーションメニューを生成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "衛星写真");
        menu.add(0, 1, 1, "地形ビュー");
        menu.add(0, 2, 2, "ハイブリッド");
        menu.add(0, 3, 3, "ノーマル");
        menu.add(0, 4, 4, "渋滞情報オン");
        menu.add(0, 5, 5, "渋滞情報オフ");
        return true;
    }

    // メニューアイテムが選択されたときの処理
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemID = item.getItemId();

        switch(itemID) {
            // 衛星写真を表示
            case 0:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            // 地形ビューを表示
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            // ハイブリッドビューを表示
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            // ノーマルのビューを表示
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            // 渋滞助y法を表示
            case 4:
                mMap.setTrafficEnabled(true);
                break;
            // 渋滞情報を解除
            case 5:
                mMap.setTrafficEnabled(false);
                break;
            default:
                break;
        }
        return true;
    }
}