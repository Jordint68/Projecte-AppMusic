package org.milaifontanals.spotifypaco2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.models.Song;

import java.util.List;

public class songsAdapter  extends RecyclerView.Adapter<songsAdapter.ViewHolder> {
    private List<Song> mSongs;
    private Context mContext;
    private Context mListener;
    private int idxSeleccionat = -1;

    public songsAdapter(List<Song> songs, Context c) {
        this.mContext = c;
        mListener = c;
        mSongs = songs;
    }

    @NonNull
    @Override
    public songsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View song = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_song, parent, false);

        return new ViewHolder(song);
    }

    @Override
    public void onBindViewHolder(@NonNull songsAdapter.ViewHolder holder, int position) {
        Song songActual = mSongs.get(position);
        holder.txvId.setText(songActual.getId()+"");
        holder.txvTitle.setText(songActual.getName());
        holder.txvTime.setText(songActual.getTime());
        if(songActual.getMg()) {
            holder.imbMg.setImageResource(R.drawable.full_heart);
        } else {
            holder.imbMg.setImageResource(R.drawable.empty_heart);
        }

        holder.imbMg.setOnClickListener(view -> {
            if(songActual.getMg()) {
                songActual.setMg(false);
                holder.imbMg.setImageResource(R.drawable.empty_heart);
            } else {
                songActual.setMg(true);
                holder.imbMg.setImageResource(R.drawable.full_heart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvId;
        ImageButton imbMg;
        TextView txvTitle;
        TextView txvTime;
        public ViewHolder(@NonNull View song) {
            super(song);

            txvId = song.findViewById(R.id.txvId);
            imbMg = song.findViewById(R.id.imbMg);
            txvTitle = song.findViewById(R.id.txvTitle);
            txvTime = song.findViewById(R.id.txvTime);

            song.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int posicio = getAdapterPosition();

                    if (posicio != -1) {
                        notifyItemChanged(idxSeleccionat);
                        idxSeleccionat = posicio;
                        notifyItemChanged(idxSeleccionat);
                    }
                }
            });
        }
    }

    public int getIdxSeleccionat() {
        return idxSeleccionat;
    }
}
