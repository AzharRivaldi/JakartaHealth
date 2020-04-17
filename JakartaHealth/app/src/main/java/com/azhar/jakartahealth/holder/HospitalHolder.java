package com.azhar.jakartahealth.holder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.jakartahealth.R;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */
public class HospitalHolder extends RecyclerView.ViewHolder {

    public TextView tv_nama_rs, tv_location;
    public CardView cvRs;

    public HospitalHolder(View view) {
        super(view);

        cvRs = view.findViewById(R.id.cvRs);
        tv_nama_rs = view.findViewById(R.id.tv_nama_rs);
        tv_location = view.findViewById(R.id.tv_location);

    }
}
