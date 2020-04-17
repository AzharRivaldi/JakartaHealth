package com.azhar.jakartahealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.azhar.jakartahealth.R;
import com.azhar.jakartahealth.holder.HospitalHolder;
import com.azhar.jakartahealth.model.ModelRumahSakit;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */
public class HospitalKhususAdapter extends RecyclerView.Adapter<HospitalHolder> {

    public List<ModelRumahSakit> rumahSakitList;
    private HospitalKhususAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelRumahSakit modelRumahSakit);
    }

    public HospitalKhususAdapter(Context context, List<ModelRumahSakit> modelRumahSakitList,
                                 HospitalKhususAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.rumahSakitList = modelRumahSakitList;
        this.onSelectData = xSelectData;
    }

    @Override
    public HospitalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_rsk, viewGroup, false);
        return new HospitalHolder(view);
    }

    @Override
    public void onBindViewHolder(HospitalHolder viewHolder, int position) {

        final ModelRumahSakit data = rumahSakitList.get(position);
        viewHolder.tv_nama_rs.setText("RS" + " " + data.getNamaRs());
        viewHolder.tv_location.setText(data.getLocation());
        viewHolder.cvRs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rumahSakitList.size();
    }
}
