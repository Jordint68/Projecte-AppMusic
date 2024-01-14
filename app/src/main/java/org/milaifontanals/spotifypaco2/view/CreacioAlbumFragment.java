package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.databinding.FragmentCreacioAlbumBinding;

public class CreacioAlbumFragment extends Fragment {
    private FragmentCreacioAlbumBinding mBinding;
    public CreacioAlbumFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding.numpDia.setMinValue(1);
        mBinding.numpDia.setMaxValue(31);
        mBinding.numpMes.setMinValue(1);
        mBinding.numpMes.setMaxValue(12);
        mBinding.numpAny.setMinValue(1950);
        mBinding.numpAny.setMaxValue(2040);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCreacioAlbumBinding.inflate(getLayoutInflater());


        



        return mBinding.getRoot();
    }
}