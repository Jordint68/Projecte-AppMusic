package org.milaifontanals.spotifypaco2.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.adapters.albumsAdapter;
import org.milaifontanals.spotifypaco2.adapters.songsAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentSongListBinding;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.models.Song;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;
import org.milaifontanals.spotifypaco2.viewmodel.SongViewModel;

import java.util.List;

public class SongListFragment extends Fragment {
    private FragmentManager fm;
    private int idAlbum;
    private SongViewModel sViewModel;
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
        this.fm = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSongListBinding.inflate(getLayoutInflater());


        Bundle args = getArguments();
        if (args != null) {
            idAlbum = args.getInt("idAlbum");

            /*      Assinar els elements del album
            mBinding.imvFotoAlbum.setImageResource(album.getDrawable());
            mBinding.txvNomAlbum.setText(album.getName());
            mBinding.txvNomGrup.setText(album.getAuthor());
            */

            if (getContext() != null) {
                consultarSongs(idAlbum);
            }
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
            sAdapter = new songsAdapter(tSongs, SongListFragment.this.getContext());
            mBinding.rcvSongs.setLayoutManager(new LinearLayoutManager(SongListFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
            mBinding.rcvSongs.setAdapter(sAdapter);
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