package com.example.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Main_Scanner extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 4;
    private Spinner mEnglishSpinner;
    private Spinner mThaiSpinner;
    TextView textContent;
    String EmID = "";

    private ArrayList<String> mThaiClub = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_scanner);

        mEnglishSpinner = (Spinner) findViewById(R.id.Emprocess);
        mThaiSpinner = (Spinner) findViewById(R.id.Emgroup);

        createThaiClubData();

        // Adapter ตัวแรก
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mThaiClub);
        mThaiSpinner.setAdapter(adapterThai);


        // Adapter ตัวที่สอง
        String[] englishClub = getResources().getStringArray(R.array.club);
        ArrayAdapter<String> adapterEnglish = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, englishClub);
        mEnglishSpinner.setAdapter(adapterEnglish);

        textContent = (TextView) findViewById(R.id.textContent);

        Button buttonIntent = (Button)findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(Intent.createChooser(intent
                        , "Scan with"), REQUEST_QR_SCAN);
            }
        });

        //*** Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //*** Button Next
        final Button btnNext = (Button) findViewById(R.id.btnSvae);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Goto Activity 2
                Intent newActivity = new Intent(Main_Scanner.this, UserIndex.class);
                startActivity(newActivity);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            String Code = intent.getStringExtra("SCAN_RESULT");
            textContent.setText(Code);
        }
    }

    private void createThaiClubData() {
        int result = 0;
        for (byte i = 1; i <= 100; i++) {
            result = i;

            mThaiClub.add(result+"");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
