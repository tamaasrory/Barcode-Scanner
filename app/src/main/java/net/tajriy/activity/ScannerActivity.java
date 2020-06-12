/*
 * ScannerActivity.java dibuat pada 6/27/18 9:36 AM
 * Copyright (c) 2018 All Rights Reserved.
 *
 * Dibuat oleh Tama Asrory Ridhana, S.T., MTA
 * Emali   : tamaasrory@gmail.com
 */

package net.tajriy.activity;

import android.app.Dialog;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

import net.tajriy.R;
import net.tajriy.model.BrandSalesAdapter;
import net.tajriy.model.BrandSalesModel;
import net.tajriy.presenter.*;
import net.tajriy.view.DetectionListener;

public class ScannerActivity extends AppCompatActivity {

    private static final int RC_HANDLE_GMS = 9001;
    private static final String TAG = ScannerActivity.class.getSimpleName();
    private BarcodeDetector barcodeDetector;
    private Barcode barcodeDetected;
    private CameraSource mCameraSource = null;
    private CameraSourcePreview mPreview;
    private GraphicOverlay mGraphicOverlay;
    private boolean flash = true;
    private int use_camera;
    private double delay_time;
    private TextView result;
    ListView barang;
    ArrayList<String> daftar = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.activity_scan);
        // get setting manager
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        // init layout elements
        mPreview = (CameraSourcePreview) findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) findViewById(R.id.overlay);
//        result = (TextView) findViewById(R.id.result);
        // get setting
        use_camera = SettingValue.getIntValue(this, "use_camera");
        flash = SettingValue.getBoolValue(this, "flash_mode");
        delay_time = SettingValue.getDoubleValue(this, "delay_time");
        // camera config
        configCameraSource();

    }

    ArrayList<BrandSalesModel> brandSalesModels = new ArrayList<BrandSalesModel>();
    BrandSalesAdapter brandSalesAdapter;

    public void listConfig() {
        // lisview config
        barang = (ListView) findViewById(R.id.list_barang_dibeli);
        brandSalesAdapter = new BrandSalesAdapter(brandSalesModels, this);
        barang.setAdapter(brandSalesAdapter);
        barang.setSelected(true);
        barang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        ((ArrayAdapter) barang.getAdapter()).notifyDataSetInvalidated();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void configCameraSource() {
        barcodeDetector = new BarcodeDetector.Builder(this).build();
        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(mGraphicOverlay, new DetectionListener() {
            int counter = 0;

            @Override
            public void onDetection(Object o) {
                if (o instanceof Barcode) {

                    if (barcodeDetected == null) {
                        Toast.makeText(ScannerActivity.this, "On :1: " + (counter++) + "--Value "
                                + ((Barcode) o).rawValue, Toast.LENGTH_SHORT).show();
                        barcodeDetected = (Barcode) o;
                        // updating list of brand sales
                        // proses
                        // priksa apakah barcode ada didalam database

                        // bila ada maka
                        // cek apakah barcode barang ada yang sama
                        for (int i = 0; i < brandSalesModels.size(); i++) {
                            BrandSalesModel tmpBrandSalesModels = brandSalesModels.get(i);
                            if (tmpBrandSalesModels.getBarcode().equalsIgnoreCase(barcodeDetected.rawValue)) {
                                // jika ada maka qty bertambah 1
                                String qty = "" + (Integer.parseInt(tmpBrandSalesModels.getQty()) + 1);
                                // subtotal berubah subtotal = harga dikali qty
                                String subtotal = "" + (Double.parseDouble(tmpBrandSalesModels.getHarga()) *
                                        Integer.parseInt(qty));
                                // update list
                                brandSalesModels.set(i, new BrandSalesModel(
                                        barcodeDetected.rawValue,
                                        tmpBrandSalesModels.getDescription(),
                                        qty,
                                        tmpBrandSalesModels.getHarga(),
                                        subtotal
                                ));
                                break;
                            }
                        }
                        // jika tidak maka langsung tambahkan data barang ke list penjualan

//                        brandSalesModels.intentActivity(new BrandSalesModel(
//                                barcodeDetected.rawValue,
//                                ,
//
//                                ));
//
                        // bila tidak maka

                        listConfig();
                        // detection delay
                        delay_time = SettingValue.getDoubleValue(ScannerActivity.this, "delay_time");
                        delay_time = delay_time * 1000;
                        int delay = (int) delay_time;
                        System.out.println("ondetection : delay_time = " + delay);
                        // Count Down Timer
                        new CountDownTimer(delay, delay) {
                            @Override
                            public void onFinish() {
                                barcodeDetected = null;
                                System.out.println("reset");
                            }

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }
                        }.start();

//                        finish();
                    }
//                    if (!barcodeDetected.rawValue.equals(((Barcode) o).rawValue)) {
//                        Toast.makeText(ScannerActivity.this, "On :2: " + (counter++) + "--Value " + ((Barcode) o).rawValue,
//                                Toast.LENGTH_SHORT).show();
//                        barcodeDetected = (Barcode) o;
//                        finish();
//                    }
                }
            }
        });

        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeFactory).build());
        mCameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setFacing(use_camera == CameraSource.CAMERA_FACING_BACK ?
                        CameraSource.CAMERA_FACING_BACK : CameraSource.CAMERA_FACING_FRONT)
                .setFlashMode(flash ? Camera.Parameters.FLASH_MODE_TORCH : Camera.Parameters.FLASH_MODE_OFF)
                .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
                .setRequestedPreviewSize(720, 720)
                .setRequestedFps(30.0f)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startCameraSource();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPreview.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        barcodeDetected = null;
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    private void startCameraSource() {

        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_flash) {
            flash = flash ? false : true;
            System.out.println("flash : " + flash);
            mCameraSource.release();
            mCameraSource = null;
            configCameraSource();
            startCameraSource();
            System.out.println("camera : " + mCameraSource);
//        } else if (id == R.id.account_search) {
//            startActivity(new Intent(MainActivity.this, BarcodeGenerator.class));
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
