package com.whdev.garagem;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.whdev.garagem.activits.LoginActivity.pref;

public class ConfigActivity extends AppCompatActivity {

    private EditText edtIp;
    private EditText edtPlaca;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        edtIp = (EditText) findViewById(R.id.edt_ip);
        edtPlaca = (EditText) findViewById(R.id.edt_placa);
        btnSave = (Button) findViewById(R.id.btn_save);
        edtPlaca.setAllCaps(Boolean.TRUE);

        if (pref.getString("ip",null) != null) {
            edtIp.setText(pref.getString("ip",null));
        }
        if (pref.getString("placa",null) != null) {
            edtPlaca.setText(pref.getString("placa",null));
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salvarConfiguracoes()) {
                    onBackPressed();
                }
            }
        });
    }

    public boolean salvarConfiguracoes(){
        SharedPreferences.Editor editor = pref.edit();

        if (!TextUtils.isEmpty(edtPlaca.getText()) && !TextUtils.isEmpty(edtIp.getText())) {

            if (edtPlaca.length() != 7) {
                Toast.makeText(ConfigActivity.this, "A placa deve ter 7 caractéres", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                editor.putString("ip", edtIp.getText().toString());
                editor.putString("placa", edtPlaca.getText().toString());
                editor.commit();
                onBackPressed();
                return true;
            }
        } else {
            Toast.makeText(ConfigActivity.this, "Preencha os dois campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
