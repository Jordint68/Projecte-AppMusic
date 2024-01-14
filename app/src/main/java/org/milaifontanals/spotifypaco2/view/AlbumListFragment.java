package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.adapters.albumsAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentAlbumListBinding;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;

public class AlbumListFragment extends Fragment {
    private AlbumViewModel albViewModel;
    private FragmentAlbumListBinding mBinding;
    private albumsAdapter adapter;
    public AlbumListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAlbumListBinding.inflate(getLayoutInflater());

        // Button add new Album:
        mBinding.imbAfegir.setOnClickListener(v ->  {

        });

        // Button remove new Album:
        mBinding.imbTreure.setOnClickListener(v ->  {
            
        });

        adapter = new albumsAdapter(Album.createListAlbums(), AlbumListFragment.this.getContext(), getParentFragmentManager());
        mBinding.grvArtistes.setLayoutManager(new GridLayoutManager(AlbumListFragment.this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
        mBinding.grvArtistes.setAdapter(adapter);

        return mBinding.getRoot();
    }
}