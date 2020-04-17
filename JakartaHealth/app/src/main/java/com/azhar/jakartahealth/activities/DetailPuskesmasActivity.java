package com.azhar.jakartahealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.azhar.jakartahealth.R;
import com.azhar.jakartahealth.model.ModelPuskesmas;
import com.azhar.jakartahealth.model.ModelRumahSakit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailPuskesmasActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMaps;
    private double latitude, longitude;
    TextView txtNamaPuskesmas, txtNamaKepala, txtNoTlp, txtEmail, txtFax;
    String NamaPuskesmas, NamaKepala, NoTlp, Email, Alamat, Fax;
    ModelPuskesmas modelPuskesmas;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_puskesmas);

        Toolbar tbDetailPuskesmas = findViewById(R.id.tbDetailPuskesmas);
        setSupportActionBar(tbDetailPuskesmas);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelPuskesmas = (ModelPuskesmas) getIntent().getSerializableExtra("puskesmasDetail");
        if (modelPuskesmas != null) {
            NamaPuskesmas = modelPuskesmas.getNamaPuskesmas();
            NamaKepala = modelPuskesmas.getKepalaPuskesmas();
            NoTlp = modelPuskesmas.getTelepon();
            Email = modelPuskesmas.getEmail();
            Fax = modelPuskesmas.getFaximile();
            latitude = modelPuskesmas.getLatitude();
            longitude = modelPuskesmas.getLongitude();

            txtNamaPuskesmas = findViewById(R.id.txtNamaPuskesmas);
            txtNamaKepala = findViewById(R.id.txtNamaKepala);
            txtNoTlp = findViewById(R.id.txtNoTlp);
            txtEmail = findViewById(R.id.txtEmail);
            txtFax = findViewById(R.id.txtFax);
            btnCall = findViewById(R.id.btnCall);

            txtNamaPuskesmas.setText(NamaPuskesmas);
            txtNamaKepala.setText(NamaKepala);
            txtNoTlp.setText("021 " + NoTlp);
            txtFax.setText("021 " + Fax);
            txtEmail.setText(Email);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "021 " + NoTlp));
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(Alamat));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
