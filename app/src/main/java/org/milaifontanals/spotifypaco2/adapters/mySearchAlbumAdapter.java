package org.milaifontanals.spotifypaco2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.models.searchAlbum.AlbumJSON;

import java.util.List;

public class mySearchAlbumAdapter extends RecyclerView.Adapter<mySearchAlbumAdapter.ViewHolder>{
    private List<AlbumJSON> mAlbums;
    private Context mContext;
    private int idxElementSeleccionat = -1;

    public mySearchAlbumAdapter(List<AlbumJSON> a, Context c) {
        this.mAlbums = a;
        this.mContext = c;
    }

    @NonNull
    @Override
    public mySearchAlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View alb = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_seach_album, parent, false);

        return new ViewHolder(alb);
    }

    @Override
    public void onBindViewHolder(@NonNull mySearchAlbumAdapter.ViewHolder holder, int position) {
        AlbumJSON album = mAlbums.get(position);



        holder.txvNom.setText(album.getName().toString());
        holder.txvAutor.setText(album.getArtist().toString());
        String imgUrl = album.getImage().get(0).getText().toString();
        if(!(imgUrl.isEmpty() || imgUrl.equals(""))) {
            ImageLoader.getInstance().displayImage(imgUrl, holder.imvFoto);
        }

    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvFoto;
        TextView txvNom;
        TextView txvAutor;
        public ViewHolder(@NonNull View album) {
            super(album);
            imvFoto = album.findViewById(R.id.imvFoto);
            txvNom = album.findViewById(R.id.txvNom);
            txvAutor = album.findViewById(R.id.txvAutor);
        }

    }
}
