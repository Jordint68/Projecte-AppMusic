package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.databinding.FragmentCreacioAlbumBinding;
import org.milaifontanals.spotifypaco2.models.Album;
import org.milaifontanals.spotifypaco2.viewmodel.AlbumViewModel;

public class CreacioAlbumFragment extends Fragment {
    private AlbumViewModel viewModel;
    private FragmentCreacioAlbumBinding mBinding;
    public CreacioAlbumFragment() {}

    public static CreacioAlbumFragment newInstance() {
        CreacioAlbumFragment frag = new CreacioAlbumFragment();

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreacioAlbumBinding.inflate(getLayoutInflater());

        mBinding.numpAny.setMinValue(1960);
        mBinding.numpAny.setMaxValue(2024);

        // Save and insert into database
        viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        mBinding.btnSave.setOnClickListener(view -> {
            viewModel.insertAlbum(1, mBinding.edtName.getText().toString(), mBinding.edtAutor.getText().toString(), mBinding.numpAny.getValue(), R.drawable.loading);
            tancarFragment();
        });

        // Cancel and return to albums page
        mBinding.btnCancel.setOnClickListener(view -> {
            tancarFragment();
        });




        return mBinding.getRoot();
    }

    private void tancarFragment() {
        if (getFragmentManager() != null) {
            getFragmentManager().popBackStack();
        }
    }


}