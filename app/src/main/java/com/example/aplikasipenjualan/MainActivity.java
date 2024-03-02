package com.example.aplikasipenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etNamaPelanggan, etJmlBarang, etJmlUang, etMembership;
    TextView tvNamaPembeli, tvNamaBarang, tvJmlBarang, tvHargaBarang, tvBiayaAdmin, tvTotal, tvKembalian, tvKeterangan;
    Button btnProses;
    RadioGroup rgBarang;
    RadioButton rbKipas, rbSetrika, rbMagicom, radioButton;

    String namaPelanggan, namaBarang;
    double jmlBarang, hrgBarang, biayaAdmin, uangByr, total, kembalian;
    double biayaAdminKipas = 2000;
    double biayaAdminSetrika = 2000;
    double biayaAdminMagicom = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Aplikasi Penjualan");

        etNamaPelanggan = findViewById(R.id.etNamaPelanggan);
        etJmlBarang = findViewById(R.id.etJmlBarang);
        etJmlUang = findViewById(R.id.etJmlUang);
        etMembership = findViewById(R.id.etMembership);

        tvNamaPembeli = findViewById(R.id.tvNamaPembeli);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvJmlBarang = findViewById(R.id.tvJmlBarang);
        tvHargaBarang = findViewById(R.id.tvHargaBarang);
        tvBiayaAdmin = findViewById(R.id.tvBiayaAdmin);
        tvTotal = findViewById(R.id.tvTotal);
        tvKembalian = findViewById(R.id.tvKembalian);
        tvKeterangan = findViewById(R.id.tvKeterangan);

        rgBarang = findViewById(R.id.rgBarang);
        rbKipas = findViewById(R.id.rbKipas);
        rbSetrika = findViewById(R.id.rbSetrika);
        rbMagicom = findViewById(R.id.rbMagicom);
        radioButton = findViewById(R.id.radioButton);

        btnProses = findViewById(R.id.btnProses);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaPelanggan = etNamaPelanggan.getText().toString().trim();
                jmlBarang = Double.parseDouble(etJmlBarang.getText().toString().trim());
                uangByr = Double.parseDouble(etJmlUang.getText().toString().trim());

                int selectedRadioButtonId = rgBarang.getCheckedRadioButtonId();
                switch (selectedRadioButtonId) {
                    case R.id.rbKipas:
                        namaBarang = "Kipas";
                        hrgBarang = 70000;
                        biayaAdmin = biayaAdminKipas * jmlBarang;
                        total = (jmlBarang * hrgBarang) + biayaAdmin;
                        break;
                    case R.id.rbSetrika:
                        namaBarang = "Setrika";
                        hrgBarang = 50000;
                        biayaAdmin = biayaAdminSetrika * jmlBarang;
                        total = (jmlBarang * hrgBarang) + biayaAdmin;
                        break;
                    case R.id.rbMagicom:
                        namaBarang = "Magicom";
                        hrgBarang = 100000;
                        biayaAdmin = biayaAdminMagicom * jmlBarang;
                        total = (jmlBarang * hrgBarang) + biayaAdmin;
                        break;
                    default:
                        total = 0;
                        break;
                }

                // Diskon untuk member
                if (radioButton.isChecked()) {
                    double diskon = total * 0.05;
                    total -= diskon;
                }

                tvNamaPembeli.setText("Nama Pembeli : " + namaPelanggan);
                tvNamaBarang.setText("Nama Barang : " + namaBarang);
                tvJmlBarang.setText("Jumlah Barang : " + jmlBarang);
                tvHargaBarang.setText("Harga Barang : " + hrgBarang);
                tvBiayaAdmin.setText("Biaya Admin : " + biayaAdmin);
                tvTotal.setText("Total Harga : " + total);

                kembalian = uangByr - total;
                if (uangByr < total) {
                    tvKeterangan.setText("Keterangan : Uang bayar kurang Rp. " + (-kembalian));
                    tvKembalian.setText("Uang Kembalian : Rp. 0");
                } else {
                    tvKeterangan.setText("Keterangan : Tunggu kembalian");
                    tvKembalian.setText("Uang Kembalian : Rp. " + kembalian);
                }
            }
        });
    }
}
