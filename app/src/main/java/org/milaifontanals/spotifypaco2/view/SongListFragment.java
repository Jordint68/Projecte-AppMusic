package org.milaifontanals.spotifypaco2.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.adapters.songsAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentSongListBinding;
import org.milaifontanals.spotifypaco2.models.Album;

public class SongListFragment extends Fragment {
    private FragmentSongListBinding mBinding;
    public SongListFragment() {}

    public static SongListFragment newInstance(Album album) {
        SongListFragment frag = new SongListFragment();

        Bundle args = new Bundle();
        args.putParcelable("album", album);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSongListBinding.inflate(getLayoutInflater());


        Bundle args = getArguments();
        if (args != null) {
            Album album = args.getParcelable("album");

            mBinding.imvFotoAlbum.setImageResource(album.getDrawable());
            mBinding.txvNomAlbum.setText(album.getName());
            mBinding.txvNomGrup.setText(album.getAuthor());

            if (getContext() != null) {
                songsAdapter adapter = new songsAdapter(album.getSongs(), getContext());
                mBinding.rcvSongs.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                mBinding.rcvSongs.setAdapter(adapter);
            }
        }

        mBinding.imbTornar.setOnClickListener(view -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });



        return mBinding.getRoot();
    }


}