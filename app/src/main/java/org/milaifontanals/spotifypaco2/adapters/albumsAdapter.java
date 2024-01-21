package org.milaifontanals.spotifypaco2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.view.SongListFragment;

import java.util.List;

public class albumsAdapter extends RecyclerView.Adapter<albumsAdapter.ViewHolder> {
    private List<Album> mAlbums;
    private Context mContext;
    private Context mListener;
    private int idxElementSeleccionat = -1;
    private FragmentManager fm;

    public albumsAdapter(List<Album> albums, Context c, FragmentManager fragmentManager) {
        this.mContext = c;
        mListener = c;
        mAlbums = albums;
        this.fm = fragmentManager;

    }

    @NonNull
    @Override
    public albumsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View alb = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_album, parent, false);

        return new ViewHolder(alb);
    }

    @Override
    public void onBindViewHolder(@NonNull albumsAdapter.ViewHolder holder, int position) {
        Album albumActual = mAlbums.get(position);
        holder.imvFoto.setImageResource(albumActual.getDrawable());
        holder.txvNom.setText(albumActual.getAuthor());
        holder.txvTitle.setText(albumActual.getName());
        holder.txvAny.setText(String.valueOf(albumActual.getAny()));

        holder.itemView.setOnClickListener(view -> {
            int posicioAnterior = this.idxElementSeleccionat;
            this.idxElementSeleccionat= holder.getAdapterPosition();
            this.notifyItemChanged(idxElementSeleccionat);
            this.notifyItemChanged(posicioAnterior);


            navegarAFragSongs(albumActual.getId());
        });
    }

    private void navegarAFragSongs(int idAlbum) {
        Fragment frSongs = SongListFragment.newInstance(idAlbum);

        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, frSongs)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvFoto;
        TextView txvNom;
        TextView txvTitle;
        TextView txvAny;
        public ViewHolder(@NonNull View artist) {
            super(artist);
            imvFoto = artist.findViewById(R.id.imvFoto);
            txvNom = artist.findViewById(R.id.txvNom);
            txvTitle = artist.findViewById(R.id.txvTitle);
            txvAny = artist.findViewById(R.id.txvAny);

        }
    }
}
