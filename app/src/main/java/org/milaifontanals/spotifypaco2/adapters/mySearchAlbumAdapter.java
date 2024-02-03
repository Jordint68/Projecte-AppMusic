package org.milaifontanals.spotifypaco2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.models.searchAlbum.AlbumJSON;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;

import java.util.List;

public class mySearchAlbumAdapter extends RecyclerView.Adapter<mySearchAlbumAdapter.ViewHolder>{
    private List<AlbumJSON> mAlbums;
    private Context mContext;
    private AlbumViewModel viewModel;
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
        String imgUrl = album.getImage().get(3).getText().toString();
        if(!(imgUrl.isEmpty() || imgUrl.equals(""))) {
            ImageLoader.getInstance().displayImage(imgUrl, holder.imvFoto);
        }
        else {
            // Si no es troba cap imatge, càrreguem una preestablerta...
            String imgUrl2 = "https://static.vecteezy.com/system/resources/previews/010/174/290/non_2x/no-photo-crossed-out-sign-line-icon-illustration-vector.jpg";
            ImageLoader.getInstance().displayImage(imgUrl2, holder.imvFoto);
        }

        // Descàrregar l'album cercat i desar-lo en la base de dades:
        holder.itemView.setOnClickListener(view -> {
            int posicioAnterior = this.idxElementSeleccionat;
            this.idxElementSeleccionat = holder.getAdapterPosition();
            this.notifyItemChanged(idxElementSeleccionat);
            this.notifyItemChanged(posicioAnterior);

            String albNom = album.getName().toString();
            String albAutor = album.getArtist().toString();

            viewModel = new ViewModelProvider((ViewModelStoreOwner) mContext).get(AlbumViewModel.class);
            if(!(imgUrl.isEmpty() || imgUrl.equals(""))) {
                viewModel.insertAlbum(albNom, albAutor, 2024, imgUrl);
            } else {
                String imgUrl2 = "https://static.vecteezy.com/system/resources/previews/010/174/290/non_2x/no-photo-crossed-out-sign-line-icon-illustration-vector.jpg";
                viewModel.insertAlbum(albNom, albAutor, 2024, imgUrl2);
            }

        });
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
