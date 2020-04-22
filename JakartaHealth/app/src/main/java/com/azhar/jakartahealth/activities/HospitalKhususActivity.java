package com.azhar.jakartahealth.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.azhar.jakartahealth.R;
import com.azhar.jakartahealth.adapter.HospitalKhususAdapter;
import com.azhar.jakartahealth.model.ModelRumahSakit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HospitalKhususActivity extends AppCompatActivity implements HospitalKhususAdapter.onSelectData{

    RecyclerView recyclerView;
    HospitalKhususAdapter hospitalAdapter;
    List<ModelRumahSakit> modelRumahSakitList = new ArrayList<>();
    ProgressDialog progressDialog;
    Toolbar tbRSU;
    TextView tvNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        tvNotFound = findViewById(R.id.tvNotFound);
        ImageView imgRefresh = findViewById(R.id.imgRefresh);
        tbRSU = findViewById(R.id.tbRS);
        setSupportActionBar(tbRSU);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        recyclerView = findViewById(R.id.rvRS);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadJSON();

        imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    private void loadJSON() {
        progressDialog.show();
        AndroidNetworking.get("http://api.jakarta.go.id/v1/rumahsakitkhusus")
                .addHeaders("Authorization", "5h30dB4K4Uwuhj4KkmHmFuOgeIeo+XxK4jKRm/v5lNNfbGfsYx2wB2D4IKQWaSu7")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray jsonArray1 = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray1.length(); i++) {

                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                ModelRumahSakit dataApi = new ModelRumahSakit();

                                JSONObject jsonObject2 = jsonObject1.getJSONObject("location");
                                String alamat = jsonObject2.getString("alamat");

                                JSONArray jsonArray2 = jsonObject1.getJSONArray("telepon");
                                for (int x = 0; x < jsonArray2.length(); x++) {
                                    String telepon = jsonArray2.get(x).toString();
                                    dataApi.setTelepon((telepon));
                                }

                                JSONArray jsonArray3 = jsonObject1.getJSONArray("faximile");
                                for (int x = 0; x < jsonArray3.length(); x++) {
                                    String faximile = jsonArray3.get(x).toString();
                                    dataApi.setFaximile((faximile));
                                }

                                dataApi.setNamaRs(jsonObject1.getString("nama_rsk"));
                                dataApi.setJenisRs(jsonObject1.getString("jenis_rsk"));
                                dataApi.setKodePos(jsonObject1.getString("kode_pos"));
                                dataApi.setEmail(jsonObject1.getString("email"));
                                dataApi.setWebsite(jsonObject1.getString("website"));
                                dataApi.setLatitude(jsonObject1.getDouble("latitude"));
                                dataApi.setLongitude(jsonObject1.getDouble("longitude"));
                                dataApi.setLocation(alamat);

                                modelRumahSakitList.add(dataApi);
                                showRumahSakit();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(HospitalKhususActivity.this, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                        if (response.length() == 0)
                            tvNotFound.setVisibility(View.VISIBLE);
                        else
                            tvNotFound.setVisibility(View.GONE);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(ANError anError) {
                        tvNotFound.setVisibility(View.VISIBLE);
                        progressDialog.dismiss();
                        Toast.makeText(HospitalKhususActivity.this, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showRumahSakit() {
        hospitalAdapter = new HospitalKhususAdapter(HospitalKhususActivity.this, modelRumahSakitList, this);
        recyclerView.setAdapter(hospitalAdapter);
    }

    @Override
    public void onSelected(ModelRumahSakit modelRumahSakit) {
        Intent intnt = new Intent(HospitalKhususActivity.this, DetailHospitalActivity.class);
        intnt.putExtra("rsDetail", modelRumahSakit);
        startActivity(intnt);
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
