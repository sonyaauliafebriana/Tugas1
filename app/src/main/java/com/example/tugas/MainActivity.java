package com.example.tugas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etInputItem, etJumlahBarang;
    private RadioGroup radioGroupCake, radioGroupMembership;
    private Button btnProses;
    private TextView tvNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputItem = findViewById(R.id.etinputitem);
        etJumlahBarang = findViewById(R.id.etbrg);
        radioGroupCake = findViewById(R.id.radioGroupCake);
        radioGroupMembership = findViewById(R.id.radioGroup);
        btnProses = findViewById(R.id.btnProses);
        tvNota = findViewById(R.id.tvNota);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesPembelian();
            }
        });
    }

    private void prosesPembelian() {
        // Mendapatkan nama pembeli dari EditText
        String namaPembeli = etInputItem.getText().toString();

        // Mendapatkan jumlah barang dari EditText
        int jumlahBarang = Integer.parseInt(etJumlahBarang.getText().toString());

        // Mendapatkan jenis kue yang dipilih
        String jenisKue = getSelectedRadioButtonText(radioGroupCake);

        // Mendapatkan jenis membership yang dipilih
        String jenisMembership = getSelectedRadioButtonText(radioGroupMembership);

        // Mendapatkan harga per barang
        double hargaPerBarang = getHargaBarang(jenisKue);

        // Menghitung total harga
        double totalHarga = calculateTotalHarga(jumlahBarang, hargaPerBarang);

        // Menghitung diskon
        double diskon = calculateDiskon(totalHarga, jenisMembership);

        // Menghitung harga setelah diskon
        double hargaSetelahDiskon = totalHarga - diskon;

        // Menampilkan nota
        tampilkanNota(namaPembeli, jumlahBarang, totalHarga, diskon, hargaSetelahDiskon);
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton != null ? radioButton.getText().toString() : "";
    }

    private double getHargaBarang(String jenisKue) {
        // Sesuaikan harga per barang berdasarkan jenis kue
        double hargaClairmont = 180000;
        double hargaRedVelvet = 160000;
        double hargaRainbow = 145000;

        switch (jenisKue) {
            case "Clairmont":
                return hargaClairmont;
            case "Red velvet":
                return hargaRedVelvet;
            case "Rainbow":
                return hargaRainbow;
            default:
                return 0.0;
        }
    }

    private double calculateTotalHarga(int jumlahBarang, double hargaPerBarang) {
        return jumlahBarang * hargaPerBarang;
    }

    private double calculateDiskon(double totalHarga, String jenisMembership) {
        // Sesuaikan logika diskon berdasarkan jenis membership
        if ("Silver".equals(jenisMembership)) {
            if (totalHarga >= 150000) {
                return totalHarga * 0.1; // Diskon 10% untuk Silver jika total lebih dari 150000
            }
        } else if ("Gold".equals(jenisMembership)) {
            return totalHarga * 0.15; // Diskon 15% untuk Gold
        }

        return 0.0; // Tidak ada diskon
    }

    private void tampilkanNota(String namaPembeli, int jumlahBarang, double totalHarga, double diskon, double hargaSetelahDiskon) {
        String notaText = "Nota Pembelian\n\n"
                + "Nama Pembeli: " + namaPembeli + "\n"
                + "Jumlah Barang: " + jumlahBarang + "\n"
                + "Total Harga: Rp " + totalHarga + "\n"
                + "Diskon: Rp " + diskon + "\n"
                + "Harga Setelah Diskon: Rp " + hargaSetelahDiskon;

        tvNota.setText(notaText);
    }
}
