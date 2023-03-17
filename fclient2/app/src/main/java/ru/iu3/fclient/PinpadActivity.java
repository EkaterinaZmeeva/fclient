package ru.iu3.fclient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class PinpadActivity extends AppCompatActivity {
    TextView tvPin;
    String pin = "";
    final int MAX_KEYS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpad);
        tvPin = findViewById(R.id.txtPin);

        ShuffleKeys();

        findViewById(R.id.btnOK).setOnClickListener((View) -> {
            finish();
        });

        findViewById(R.id.btnReset).setOnClickListener((View) -> {
            String pin = "";
            tvPin.setText("");
        });

        findViewById(R.id.btnOK).setOnClickListener((View) -> {
            Intent it = new Intent();
            it.putExtra("pin", pin);
            setResult(RESULT_OK, it);
            finish();
        });

    }


    public void keyClick(View v)
    {
        String key = ((TextView)v).getText().toString();
        String pin = null;
        int sz = pin.length();
        if (sz < 4)
        {
            pin += key;
            BreakIterator tvPin;
            tvPin = null;
            tvPin.setText("****".substring(3 - sz));
        }
    }


    protected void ShuffleKeys()
    {
        Button keys[] = new Button[] {
                findViewById(R.id.btnKey0),
                findViewById(R.id.btnKey1),
                findViewById(R.id.btnKey2),
                findViewById(R.id.btnKey3),
                findViewById(R.id.btnKey4),
                findViewById(R.id.btnKey5),
                findViewById(R.id.btnKey6),
                findViewById(R.id.btnKey7),
                findViewById(R.id.btnKey8),
                findViewById(R.id.btnKey9),
        };

        byte[] rnd = MainActivity.randomBytes(MAX_KEYS);
        for(int i = 0; i < MAX_KEYS; i++)
        {
            int idx = (rnd[i] & 0xFF) % 10;
            CharSequence txt = keys[idx].getText();
            keys[idx].setText(keys[i].getText());
            keys[i].setText(txt);
        }
    }


    public void onButtonClick(View v)
    {
        Intent it = new Intent(this, PinpadActivity.class);
        //startActivity(it);
        ActivityResultLauncher<Intent> activityResultLauncher = null;
        activityResultLauncher.launch(it);
    }

}
