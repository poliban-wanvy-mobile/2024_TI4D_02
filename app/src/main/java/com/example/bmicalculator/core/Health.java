package com.example.bmicalculator.core;

public class Health {

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public double calculateBMI(double heightCm, double weightKg) {
        double bmiIndex = -1;
        if (heightCm <= 0 || weightKg <= 0) {
            errorMessage = "Tinggi dan Berat badan tidak boleh nol atau kurang";
        } else {
            bmiIndex = weightKg / ((heightCm / 100) * (heightCm / 100));
        }
        return bmiIndex;
    }

    public String determineCategory(double bmiIndex) {
        String bmiCategory = "";

        if (bmiIndex < 16) {
            bmiCategory = "Sangat Kurus";
        } else if (bmiIndex >= 16 && bmiIndex < 17) {
            bmiCategory = "Kurus Sedang";
        } else if (bmiIndex >= 17 && bmiIndex < 18.5) {
            bmiCategory = "Kurus Ringan";
        } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
            bmiCategory = "Normal";
        } else if (bmiIndex >= 25 && bmiIndex < 30) {
            bmiCategory = "Kelebihan Berat Badan";
        } else if (bmiIndex >= 30 && bmiIndex < 35) {
            bmiCategory = "Obesitas Kelas 1";
        } else if (bmiIndex >= 35 && bmiIndex < 40) {
            bmiCategory = "Obesitas Kelas 2";
        } else {
            bmiCategory = "Obesitas Kelas 3";
        }
        return bmiCategory;
    }
}
