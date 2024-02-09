package org.milaifontanals.spotifypaco2.view;

import static org.milaifontanals.spotifypaco2.view.MySearchFragment.TAG;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import org.milaifontanals.spotifypaco2.models.searchAlbum.AlbumJSON;
import org.milaifontanals.spotifypaco2.models.searchSongs.AlbumJSONSongs;
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
    private SongViewModel sViewModel;
    private AlbumViewModel aViewModel;
    private FragmentSongListBinding mBinding;
    private songsAdapter sAdapter;
    private int idAlbum;
    private String nomAlbum;
    private String nomArtist;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            idAlbum = args.getInt("idAlbum");


            LiveData<Album> albumLiveData = aViewModel.getAlbumById(idAlbum);
            albumLiveData.observe(getViewLifecycleOwner(), albumTrobat -> {
                nomAlbum = albumTrobat.getName().toString();
                nomArtist = albumTrobat.getAuthor().toString();
                mBinding.txvNomAlbum.setText(albumTrobat.getName().toString());
                mBinding.txvNomGrup.setText(albumTrobat.getAuthor().toString());
                String url = albumTrobat.getDrawable().toString();
                ImageLoader.getInstance().displayImage(url, mBinding.imvFotoAlbum);

                consultarSongs(idAlbum);
            });
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSongListBinding.inflate(getLayoutInflater());

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null; // Esto ayuda a prevenir fugas de memoria
    }

    private void consultarSongs(int idCercada) {
        if(getView() != null) {
            LiveData<List<Song>> songs =  sViewModel.getSongsById(idCercada);
            songs.observe(this.getViewLifecycleOwner(), tSongs -> {
                if(getView() != null) {
                    // Si la llista de cançons del album és buida, llavors l'omplim....
                    if(tSongs.isEmpty()) {
                        cercarTracks(idCercada);
                    } else {
                        sAdapter = new songsAdapter(tSongs, SongListFragment.this.getContext());
                        mBinding.rcvSongs.setLayoutManager(new LinearLayoutManager(SongListFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
                        mBinding.rcvSongs.setAdapter(sAdapter);
                    }
                }
            });
        }
    }

    /*
     *  Mètode per a omplir o cercar les cançons dins d'un album mitjançant l'id del album.
     *  Primer cerca l'album  la base de dades segons l'id i amb el nom i l'autor del album cercat
     *  fem la petició al json, que finalment ens retornarà una llista de cançons
     */
    private void cercarTracks(int idCercada) {
            // Faig la consulta al json
            APIManager.getInstance().getTracks(nomArtist, nomAlbum, new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    List<Track> lSongs = null;
                    Example ex = response.body();
                    AlbumJSONSongs ajs = response.body().getAlbum();
                    if(ajs != null) {
                        if(ajs.getTracks() != null){
                            lSongs = ajs.getTracks().getTrack();

                            for (int i = 0; i < lSongs.size(); i++) {
                                // Deso les noves cançons a la base de dades:
                                Log.d(TAG, lSongs.get(i).getName().toString());
                                sViewModel.insertSong(idCercada, lSongs.get(i).getName().toString(), false, lSongs.get(i).getDuration().toString());
                                consultarSongs(idCercada);
                            }
                        } else {
                            Log.d(TAG, "La lista de canciones és nul·la o està buida...");
                        }

                    } else {
                        Log.d(TAG, "El json és nul...");
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Log.d(TAG, "La recuperació de cançons ha fallat...");
                }
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
}