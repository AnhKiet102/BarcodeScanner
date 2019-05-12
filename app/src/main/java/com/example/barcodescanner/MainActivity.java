package com.example.barcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnscan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnscan=(Button)findViewById(R.id.btnScan);
        btnscan.setOnClickListener(this);

    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null)
        {
            if( result.getContents()==null)
            {
                Toast.makeText(this, "Result not found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                AlertDialog.Builder arlertdialogbuilder = new AlertDialog.Builder(this);
                arlertdialogbuilder.setMessage(result.getContents()+"\n\nScan Again?");
                arlertdialogbuilder.setTitle("Scan Success ! Result Scanned:");
                arlertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    Scannow();
                    }
                });
                arlertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog= arlertdialogbuilder.create();
                alertDialog.show();
            }

        }
        else {
         super.onActivityResult(requestCode,resultCode,data);
        }
    }
    public  void Scannow(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(Portrait.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Please forcus on BarCode or QRCode");
        integrator.initiateScan();
    }
    @Override
    public  void onClick(View v){
        Scannow();
    }
}
