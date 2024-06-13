package com.example.bmicalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bmicalculator.core.Health;

public class KalkulatorMulai extends AppCompatActivity {
    EditText etHeight, etWeight, etNama;
    Button btCalculate;
    ImageView ivAboutus, ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etNama = findViewById(R.id.etNama);
        btCalculate = findViewById(R.id.btCalculate);
        ivAboutus = findViewById(R.id.ivAboutus);
        ivLogout = findViewById(R.id.ivLogout);

        btCalculate.setOnClickListener(this::onCalculateButtonClick);

        ivAboutus.setOnClickListener(v -> {
            Intent intent = new Intent(KalkulatorMulai.this, About.class);
            startActivity(intent);
        });

        ivLogout.setOnClickListener(v -> {
            new AlertDialog.Builder(KalkulatorMulai.this)
                    .setTitle("Konfirmasi Logout")
                    .setMessage("Apakah Anda ingin logout?")
                    .setPositiveButton("Iya", (dialog, which) -> {
                        Intent intent = new Intent(KalkulatorMulai.this, Beranda.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
        });
    }

    public void onCalculateButtonClick(View view) {
        double height = 0;
        double weight = 0;
        String nama = etNama.getText().toString();

        if (nama.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Nama tidak boleh kosong")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        if (!etHeight.getText().toString().equals("") && Double.parseDouble(etHeight.getText().toString()) != 0) {
            height = Double.parseDouble(etHeight.getText().toString());
        }
        if (!etWeight.getText().toString().equals("") && Double.parseDouble(etWeight.getText().toString()) != 0) {
            weight = Double.parseDouble(etWeight.getText().toString());
        }

        Health health = new Health();
        double bmiResult = health.calculateBMI(height, weight);

        String resultText;

        if (bmiResult != -1) {
            String bmiCat = health.determineCategory(bmiResult);
            resultText = "Halo " + nama + ", Skor BMI Anda adalah " + String.format("%.2f", bmiResult) + "\nKategori BMI: " + bmiCat;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Hasil BMI");
            alertDialogBuilder.setMessage(resultText);
            alertDialogBuilder.setPositiveButton("SELESAI", (dialogInterface, i) -> {
                etHeight.setText("");
                etWeight.setText("");
                etNama.setText("");
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            // Set background color based on BMI category
            runOnUiThread(() -> {
                View alertDialogView = alertDialog.getWindow().getDecorView();
                switch (bmiCat) {
                    case "Sangat Kurus":
                        alertDialogView.setBackgroundColor(Color.RED);
                        break;
                    case "Kurus Sedang":
                        alertDialogView.setBackgroundColor(Color.parseColor("#FFA500")); // Orange
                        break;
                    case "Kurus Ringan":
                        alertDialogView.setBackgroundColor(Color.YELLOW);
                        break;
                    case "Normal":
                        alertDialogView.setBackgroundColor(Color.GREEN);
                        break;
                    case "Kelebihan Berat Badan":
                        alertDialogView.setBackgroundColor(Color.parseColor("#FFFF00")); // Yellow
                        break;
                    case "Obesitas Kelas 1":
                        alertDialogView.setBackgroundColor(Color.parseColor("#FFA07A")); // Light Salmon
                        break;
                    case "Obesitas Kelas 2":
                        alertDialogView.setBackgroundColor(Color.parseColor("#FF4500")); // Orange Red
                        break;
                    case "Obesitas Kelas 3":
                        alertDialogView.setBackgroundColor(Color.RED);
                        break;
                }
            });

        } else {
            Toast.makeText(this, health.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
