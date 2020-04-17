package com.azhar.jakartahealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.azhar.jakartahealth.R;
import com.azhar.jakartahealth.holder.PuskesmasHolder;
import com.azhar.jakartahealth.model.ModelPuskesmas;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class PuskesmasAdapter extends RecyclerView.Adapter<PuskesmasHolder> {

    public List<ModelPuskesmas> rumahSakitList;
    private PuskesmasAdapter.onSelectData onSelectData;
    private Context mContext;

    public interface onSelectData {
        void onSelected(ModelPuskesmas modelRumahSakit);
    }

    public PuskesmasAdapter(Context context, List<ModelPuskesmas> modelRumahSakitList,
                            PuskesmasAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.rumahSakitList = modelRumahSakitList;
        this.onSelectData = xSelectData;
    }

    @Override
    public PuskesmasHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data_puskesmas, viewGroup, false);
        return new PuskesmasHolder(view);
    }

    @Override
    public void onBindViewHolder(PuskesmasHolder viewHolder, int position) {

        final ModelPuskesmas data = rumahSakitList.get(position);
        viewHolder.tv_nama_puskesmas.setText(data.getNamaPuskesmas());
        viewHolder.tv_location.setText(data.getLocation());
        viewHolder.cvPuskesmas.setOnClickListener(new View.OnClickListener() {
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
