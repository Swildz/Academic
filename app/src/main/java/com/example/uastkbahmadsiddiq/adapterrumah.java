package com.example.uastkbahmadsiddiq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapterrumah  extends RecyclerView.Adapter<adapterrumah.ViewHolderRumah> {

    Context context;
    List<rumah> rumahList;

    public adapterrumah(Context context, List<rumah>  rumahList) {
        this.context = context;
        this.rumahList = rumahList;
    }


    @NonNull
    @Override
    public ViewHolderRumah onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, null);
        return new ViewHolderRumah(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRumah holder, int position) {
        rumah rumah = rumahList.get(position);
        Glide.with(context).load(rumahList.get(position).getGambar())
                .into(holder.gambar);
        holder.nama.setText(String.valueOf(rumah.getNama()));
        holder.keterangan.setText(String.valueOf(rumah.getKeterangan()));
        holder.harga.setText(String.valueOf(rumah.getHarga()));
        holder.alamat.setText(String.valueOf(rumah.getAlamat()));
        holder.latitude.setText(String.valueOf(rumah.getLatitude()));
        holder.longitude.setText(String.valueOf(rumah.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return rumahList.size();
    }

    public class ViewHolderRumah extends RecyclerView.ViewHolder {

        private ImageView gambar;
        private TextView nama, keterangan, harga, alamat, latitude, longitude;

        public ViewHolderRumah(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txtNama);
            keterangan = itemView.findViewById(R.id.txtKeterangan);
            gambar = itemView.findViewById(R.id.imageRumah);
            harga = itemView.findViewById(R.id.txtNama);
            alamat = itemView.findViewById(R.id.txtAlamat);
            latitude = itemView.findViewById(R.id.txtLat);
            longitude = itemView.findViewById(R.id.txtLong);

        }
    }
}