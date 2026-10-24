
package com.qrscanner.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scanButton = findViewById(R.id.scanButton);
        resultText = findViewById(R.id.resultText);

        scanButton.setOnClickListener(v -> {
            IntentIntegrator integrator =
                    new IntentIntegrator(MainActivity.this);

            integrator.setDesiredBarcodeFormats(
                    IntentIntegrator.QR_CODE);
            integrator.setPrompt("Point camera at QR code");
            integrator.setBeepEnabled(true);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result =
                IntentIntegrator.parseActivityResult(
                        requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                resultText.setText("Scan cancelled.");
            } else {
                resultText.setText(
                        "Scanned result:\n\n" + result.getContents());
            }
        }
    }
}
