package org.milaifontanals.spotifypaco2.view;

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

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.adapters.albumsAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentAlbumListBinding;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;

import java.util.List;

public class AlbumListFragment extends Fragment {

    private FragmentManager fm;
    private AlbumViewModel viewModel;
    private FragmentAlbumListBinding mBinding;
    private albumsAdapter adapter;



    public AlbumListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.fm = getFragmentManager();
        viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAlbumListBinding.inflate(getLayoutInflater());

        consultarAlbums();

        // Button add new Album:
        mBinding.imbAfegir.setOnClickListener(v ->  {
            navegarACreacioAlbum();
        });


        return mBinding.getRoot();
    }
    private void navegarACreacioAlbum() {
        Fragment fr = CreacioAlbumFragment.newInstance();

        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fr)
                    .addToBackStack(null)
                    .commit();
        }
    }

    // Consultarà a la base de dades els albums actuals i retornarà una llista d'aquests
    private void consultarAlbums() {
        LiveData<List<Album>> albums =  viewModel.getAlbums();

        albums.observe(this.getViewLifecycleOwner(), elsAlbums -> {
            adapter = new albumsAdapter(elsAlbums, AlbumListFragment.this.getContext(), getParentFragmentManager());
            mBinding.grvArtistes.setLayoutManager(new GridLayoutManager(AlbumListFragment.this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
            mBinding.grvArtistes.setAdapter(adapter);
        });


    }
}