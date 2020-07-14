package com.unam.mismascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CambiarCuenta extends AppCompatActivity {
    Context context;
    Button btn;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_cuenta);
        context = getBaseContext();
        btn = findViewById(R.id.btnCuenta);
        et = findViewById(R.id.etCuenta);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CambiarCuenta.this, "Se cambió la cuenta", Toast.LENGTH_LONG).show();
                et.setText("");
            }
        });
    }
}