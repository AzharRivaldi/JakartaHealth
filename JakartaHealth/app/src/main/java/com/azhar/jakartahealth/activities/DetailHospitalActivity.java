package com.azhar.jakartahealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.azhar.jakartahealth.R;
import com.azhar.jakartahealth.model.ModelRumahSakit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailHospitalActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMaps;
    private double latitude, longitude;
    TextView txtNameRS, txtFax, txtJenisRs, txtKodePos, txtNoTlp, txtEmail, txtWebsite;
    String NameRS, JenisRs, KodePos, NoTlp, Email, Website, Alamat, Fax;
    ModelRumahSakit modelRumahSakitList;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        Toolbar tbDetailRS = findViewById(R.id.tbDetailRS);
        setSupportActionBar(tbDetailRS);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelRumahSakitList = (ModelRumahSakit) getIntent().getSerializableExtra("rsDetail");
        if (modelRumahSakitList != null) {
            NameRS = modelRumahSakitList.getNamaRs();
            JenisRs = modelRumahSakitList.getJenisRs();
            KodePos = modelRumahSakitList.getKodePos();
            NoTlp = modelRumahSakitList.getTelepon();
            Email = modelRumahSakitList.getEmail();
            Fax = modelRumahSakitList.getFaximile();
            Website = modelRumahSakitList.getWebsite();
            latitude = modelRumahSakitList.getLatitude();
            longitude = modelRumahSakitList.getLongitude();

            txtNameRS = findViewById(R.id.txtNameRS);
            txtJenisRs = findViewById(R.id.txtJenisRs);
            txtKodePos = findViewById(R.id.txtKodePos);
            txtNoTlp = findViewById(R.id.txtNoTlp);
            txtEmail = findViewById(R.id.txtEmail);
            txtFax = findViewById(R.id.txtFax);
            txtWebsite = findViewById(R.id.txtWebsite);
            btnCall = findViewById(R.id.btnCall);

            txtNameRS.setText("RS" + " " + NameRS);
            txtJenisRs.setText(JenisRs);
            txtKodePos.setText(KodePos);
            txtNoTlp.setText("021 " + NoTlp);
            txtFax.setText("021 " + Fax);
            txtEmail.setText(Email);
            if (Website == null) {
                txtWebsite.setText("-");
            } else {
                SpannableString spannableString = new SpannableString("http://" + Website);
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                txtWebsite.setText(spannableString);
                txtWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + Website));
                        startActivity(intent);
                    }
                });
            }

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
