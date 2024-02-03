package org.milaifontanals.spotifypaco2.view;

import static org.milaifontanals.spotifypaco2.view.MySearchFragment.TAG;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.spotifypaco2.API.APIManager;
import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.adapters.albumsAdapter;
import org.milaifontanals.spotifypaco2.adapters.songsAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentSongListBinding;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.models.Song;
import org.milaifontanals.spotifypaco2.models.searchSongs.Example;
import org.milaifontanals.spotifypaco2.models.searchSongs.Track;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;
import org.milaifontanals.spotifypaco2.viewmodel.SongViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListFragment extends Fragment {
    private FragmentManager fm;
    private int idAlbum;
    private SongViewModel sViewModel;
    private AlbumViewModel aViewModel;
    private FragmentSongListBinding mBinding;
    private songsAdapter sAdapter;
    public SongListFragment() {}


    public static SongListFragment newInstance(int idAlbum) {
        SongListFragment frag = new SongListFragment();

        Bundle args = new Bundle();
        args.putInt("idAlbum", idAlbum);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        aViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);


        this.fm = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSongListBinding.inflate(getLayoutInflater());


        Bundle args = getArguments();
        if (args != null) {
            idAlbum = args.getInt("idAlbum");

            /*      Assignar els elements del album
            mBinding.imvFotoAlbum.setImageResource(album.getDrawable());
            mBinding.txvNomAlbum.setText(album.getName());
            mBinding.txvNomGrup.setText(album.getAuthor());
            */

            if (getContext() != null) {
                consultarSongs(idAlbum);
            }

            LiveData<Album> albumLiveData = cercaAlbum(idAlbum);
            albumLiveData.observe(getViewLifecycleOwner(), albumTrobat -> {
                mBinding.txvNomAlbum.setText(albumTrobat.getName().toString());
                mBinding.txvNomGrup.setText(albumTrobat.getAuthor().toString());
                String url = albumTrobat.getDrawable().toString();
                ImageLoader.getInstance().displayImage(url, mBinding.imvFotoAlbum);
            });
        }

        mBinding.imbTornar.setOnClickListener(view -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        mBinding.imbAfegir.setOnClickListener(v -> {
            navegarACreacioSong();
        });



        return mBinding.getRoot();
    }

    private void consultarSongs(int idCercada) {
        LiveData<List<Song>> songs =  sViewModel.getSongsById(idCercada);

        songs.observe(this.getViewLifecycleOwner(), tSongs -> {
            // Si la llista de cançons del album és buida, llavors l'omplim...
            if(tSongs.isEmpty()) {
                cercarTracks(idCercada);
                consultarSongs(idCercada);
            }

            sAdapter = new songsAdapter(tSongs, SongListFragment.this.getContext());
            mBinding.rcvSongs.setLayoutManager(new LinearLayoutManager(SongListFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
            mBinding.rcvSongs.setAdapter(sAdapter);
        });
    }

    /*
     *  Mètode per a omplir o cercar les cançons dins d'un album mitjançant l'id del album.
     *  Primer cerca l'album  la base de dades segons l'id i amb el nom i l'autor del album cercat
     *  fem la petició al json, que finalment ens retornarà una llista de cançons
     */
    private void cercarTracks(int idCercada) {
        LiveData<Album> album = cercaAlbum(idCercada);
        album.observe(this.getViewLifecycleOwner(), albumC -> {
            // Faig la consulta al json
            String nomAlbum = albumC.getName().toString();
            String nomArtist = albumC.getAuthor().toString();
            APIManager.getInstance().getTracks(nomAlbum, nomArtist, new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    List<Track> lSongs = response.body().getAlbum().getTracks().getTrack();
                    for (int i = 0; i < lSongs.size(); i++) {
                        // Deso les noves cançons a la base de dades:
                        sViewModel.insertSong(idCercada, lSongs.get(i).getName().toString(), false, lSongs.get(i).getDuration().toString());
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Log.d(TAG, "La recuperació de cançons ha fallat...");
                }
            });


        });
    }

    private void navegarACreacioSong() {
        Fragment fr = CreacioSongFragments.newInstance(idAlbum);

        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fr)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private LiveData<Album> cercaAlbum(int idAlbum) {
        LiveData<Album> aCercat = aViewModel.getAlbumById(idAlbum);

        return aCercat;
    }


}