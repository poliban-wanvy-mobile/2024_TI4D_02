package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnKembali = findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(this::mulaiKalkulator);
    }

    private void mulaiKalkulator(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Pilihan")
                .setMessage("Apakah Anda Mau Kembali ke Halaman Utama?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Iya", (dialogInterface, i) -> {
                    Intent intent = new Intent(About.this, KalkulatorMulai.class);
                    startActivity(intent);
                })
                .show();
    }
}
