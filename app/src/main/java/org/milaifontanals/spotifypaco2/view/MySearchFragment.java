package org.milaifontanals.spotifypaco2.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.milaifontanals.spotifypaco2.API.APIManager;
import org.milaifontanals.spotifypaco2.R;
import org.milaifontanals.spotifypaco2.adapters.mySearchAlbumAdapter;
import org.milaifontanals.spotifypaco2.adapters.mySearchArtistAdapter;
import org.milaifontanals.spotifypaco2.databinding.FragmentMySearchBinding;
import org.milaifontanals.spotifypaco2.models.searchAlbum.AlbumJSON;
import org.milaifontanals.spotifypaco2.models.searchAlbum.Resultats;
import org.milaifontanals.spotifypaco2.models.searchAlbum.Results;
import org.milaifontanals.spotifypaco2.models.searchArtist.Artist;
import org.milaifontanals.spotifypaco2.models.searchArtist.ResultatsArtist;
import org.milaifontanals.spotifypaco2.models.searchArtist.ResultsArtist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySearchFragment extends Fragment {
    public static final String TAG = "MYMUSIC_TAG";
    private FragmentMySearchBinding mBinding;
    private mySearchAlbumAdapter msAlbumAdapter;
    private mySearchArtistAdapter msArtistAdapter;

    private NavController navController;

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
        String llistaTipus[] = {"album", "artista"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, llistaTipus);
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spnTipus.setAdapter(spnAdapter);

        // Button:
        mBinding.btnCerca.setOnClickListener(view -> {
            String tipus_seleccionat = mBinding.spnTipus.getSelectedItem().toString();

            // Acció de quan el botó de cerca es presionat:
            String param = mBinding.edtBusqueda.getText().toString();
            if(!param.equals("")) {
                if(tipus_seleccionat.equals("album")) {
                    // Realitzar la cerca d'albums:
                    cercaAlbums(param);
                } else if(tipus_seleccionat.equals("artista")) {
                    // Realitzar la cerca d'artistes:
                    cercaArtist(param);
                }
            }
        });


        return mBinding.getRoot();
    }

    private void cercaAlbums(String param) {
        APIManager.getInstance().getAlbums(param, new Callback<Resultats>() {
            @Override
            public void onResponse(Call<Resultats> call, Response<Resultats> response) {
                Results res = response.body().getResults();

                omplirRecyclerAlbums(res.getAlbummatches().getAlbum());
            }

            @Override
            public void onFailure(Call<Resultats> call, Throwable t) {

            }
        });
    }

    private void cercaArtist(String param) {
        APIManager.getInstance().getArtists(param, new Callback<ResultatsArtist>() {
            @Override
            public void onResponse(Call<ResultatsArtist> call, Response<ResultatsArtist> response) {
                ResultsArtist res = response.body().getResults();

                omplirRecyclerArtist(res.getArtistmatches().getArtist());
            }

            @Override
            public void onFailure(Call<ResultatsArtist> call, Throwable t) {

            }
        });
    }

    private void omplirRecyclerAlbums(List<AlbumJSON> albums) {
        mBinding.rcyResultats.setLayoutManager(
                new LinearLayoutManager(
                        mBinding.btnCerca.getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );
        msAlbumAdapter = new mySearchAlbumAdapter(albums, mBinding.btnCerca.getContext());
        mBinding.rcyResultats.setAdapter(msAlbumAdapter);
    }

    private void omplirRecyclerArtist(List<Artist> artists) {
        mBinding.rcyResultats.setLayoutManager((
                new LinearLayoutManager(
                        mBinding.btnCerca.getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        ));
        msArtistAdapter = new mySearchArtistAdapter(artists, mBinding.btnCerca.getContext());
        mBinding.rcyResultats.setAdapter(msArtistAdapter);

    }



}