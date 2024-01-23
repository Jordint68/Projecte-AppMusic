package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.databinding.FragmentMySearchBinding;

public class MySearchFragment extends Fragment {
    private FragmentMySearchBinding mBinding;

    public MySearchFragment() {}

    public static MySearchFragment newInstance(String param1, String param2) {
        MySearchFragment fragment = new MySearchFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMySearchBinding.inflate(getLayoutInflater());

        // Spinner
        String llistaTipus[] = {"album", "autor"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, llistaTipus);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spnTipus.setAdapter(spnAdapter);


        return mBinding.getRoot();
    }
}