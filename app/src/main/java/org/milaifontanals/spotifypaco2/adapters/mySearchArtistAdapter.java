package org.milaifontanals.spotifypaco2.adapters;

import static org.milaifontanals.spotifypaco2.view.MySearchFragment.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.models.searchAlbum.Image;
import org.milaifontanals.spotifypaco2.models.searchArtist.Artist;

import java.util.List;

public class mySearchArtistAdapter extends RecyclerView.Adapter<mySearchArtistAdapter.ViewHolder> {
    private List<Artist> mArtist;
    private Context mContext;
    private NavController mNc;

    public mySearchArtistAdapter(List<Artist> a, Context c) {
        this.mArtist = a;
        this.mContext = c;

        //mNc = Navigation.findNavController(this, R.id.nav_mymusic);

    }

    @NonNull
    @Override
    public mySearchArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View art = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_search_artist, parent, false);

        return new ViewHolder(art);
    }

    @Override
    public void onBindViewHolder(@NonNull mySearchArtistAdapter.ViewHolder holder, int position) {
        Artist artist = mArtist.get(position);

        holder.txvNom.setText(artist.getName().toString());

        String imgUrl = artist.getImage().get(0).getText().toString();
        Log.d(TAG, imgUrl);
        if(holder.imvFoto != null && imgUrl != null && !imgUrl.isEmpty()) {
            ImageLoader.getInstance().displayImage(imgUrl, holder.imvFoto);
        } else {
            // Si no es troba cap imatge, càrreguem una preestablerta...
            ImageLoader.getInstance().displayImage("https://static.vecteezy.com/system/resources/previews/010/174/290/non_2x/no-photo-crossed-out-sign-line-icon-illustration-vector.jpg", holder.imvFoto);
        }

    }


    @Override
    public int getItemCount() {
        return mArtist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvFoto;
        TextView txvNom;
        public ViewHolder(@NonNull View row) {
            super(row);
            imvFoto = row.findViewById(R.id.imvFotoArtist);
            txvNom = row.findViewById(R.id.txvNom);
        }
    }


}
