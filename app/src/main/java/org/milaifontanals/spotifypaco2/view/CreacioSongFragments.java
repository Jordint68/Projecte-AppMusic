package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.databinding.FragmentCreacioSongFragmentsBinding;
import org.milaifontanals.spotifypaco2.viewmodel.SongViewModel;

public class CreacioSongFragments extends Fragment {
    private int albumId;
    private static final String CLAU_ID_ALBUM = "albumId";
    private FragmentCreacioSongFragmentsBinding mBinding;
    private SongViewModel sViewModel;

    public CreacioSongFragments() {}

    // TODO: Rename and change types and number of parameters
    public static CreacioSongFragments newInstance(int albumId) {
        CreacioSongFragments fragment = new CreacioSongFragments();
        Bundle args = new Bundle();
        args.putInt(CLAU_ID_ALBUM, albumId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumId = getArguments().getInt(CLAU_ID_ALBUM);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreacioSongFragmentsBinding.inflate(getLayoutInflater());
        sViewModel = new ViewModelProvider(this).get(SongViewModel.class);

        mBinding.numpMin.setMinValue(0);
        mBinding.numpMin.setMaxValue(10);

        mBinding.numpS.setMinValue(0);
        mBinding.numpS.setMaxValue(59);

        mBinding.btnCancel.setOnClickListener(view -> {
            tancarFragment();
        });

        mBinding.btnSave.setOnClickListener(view -> {
            String name = mBinding.edtName.getText().toString();
            if(comprovarValors(name)) {
                String time = mBinding.numpMin.getValue() + ":" + mBinding.numpS.getValue();
                sViewModel.insertSong(albumId, name, false, time);
                tancarFragment();
            }
        });

        return mBinding.getRoot();
    }

    // Funció que comprova si s'han introduit els camps:
    private boolean comprovarValors(String name) {
        if(!name.isEmpty()) {
            return true;
        }
        return false;
    }

    private void tancarFragment() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
        }
    }
}