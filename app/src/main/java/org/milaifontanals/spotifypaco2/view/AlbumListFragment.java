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
import org.milaifontanals.spotifypaco2.db.DatabaseHelper;
import org.milaifontanals.spotifypaco2.db.DatabaseThread;
import org.milaifontanals.spotifypaco2.models.Album;

import java.util.List;

public class AlbumListFragment extends Fragment {
    private DatabaseHelper dbHelper;
    private FragmentAlbumListBinding mBinding;
    public AlbumListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentAlbumListBinding.inflate(getLayoutInflater());

        albumsAdapter adapter = new albumsAdapter(Album.createListAlbums(), AlbumListFragment.this.getContext(), getParentFragmentManager());
        mBinding.grvArtistes.setLayoutManager(new GridLayoutManager(AlbumListFragment.this.getContext(), 2, LinearLayoutManager.VERTICAL, false));
        mBinding.grvArtistes.setAdapter(adapter);

        return mBinding.getRoot();
    }

    private void getAlbumsDb() {
        dbHelper.getAlbumsAsync(new DatabaseThread.OnQueryCompleteListener() {
            @Override
            public void onQueryComplete(List<Album> albums) {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}