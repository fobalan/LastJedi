package br.com.test.lastjedi.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.adapter.FilmsAdapter;
import br.com.test.lastjedi.adapter.PeopleListAdapter;
import br.com.test.lastjedi.dao.FilmsDAO;
import br.com.test.lastjedi.helper.FilmsHelper;
import br.com.test.lastjedi.listener.RecyclerViewListener;
import br.com.test.lastjedi.model.Films;
import br.com.test.lastjedi.model.Movie;
import br.com.test.lastjedi.model.People;
import br.com.test.lastjedi.retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Samurai on 10/10/2017.
 */

public class FilmsFragment extends Fragment implements RecyclerViewListener {
    private FilmsHelper filmsHelper;
    private List<Films> list = new ArrayList<>();
    private FilmsDAO filmsDAO;
    private FilmsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_films, container, false);
        People people = (People) getArguments().getSerializable("people");
        filmsHelper = new FilmsHelper(rootView);
        filmsDAO = new FilmsDAO(getActivity());
        onConfigureRyclerView();
        onLoadList(people);
        return rootView;
    }

    private void onLoadList(People people) {
        final ProgressDialog progress = ProgressDialog.show(getActivity(),"Aguarde","Buscando Filmes..",true,true);
        List<String> filmsUrl = people.getFilms();
        for (String filmUrl: filmsUrl) {
            if(filmsDAO.checkIfExistsByUrl(filmUrl)){
                list.add(filmsDAO.getFilmsByUrl(filmUrl));
                adapter.notifyItemInserted(list.size() - 1);
            } else {
                getFilmsFromService(filmUrl);
            }
        }
        progress.dismiss();
    }

    private void getFilmsFromService(String characterUrl) {
        Call<Films> call = new RetrofitInitializer(characterUrl)
                .getFilmsService()
                .getFilms();
        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                Films newFilms = response.body();
                if(newFilms != null) {
                    getImageUrlFromService(newFilms);
                }
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                Toast.makeText(getActivity(), "Não foi possivel trazer todos os filmes",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getImageUrlFromService(final Films newFilms) {
        Call<Movie> call = new RetrofitInitializer("https://api.themoviedb.org/")
                .getMovieService()
                .getMovie("331a18bda3c37db737b76e221c67bda1",newFilms.getTitle());
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                newFilms.setImageUrl("https://image.tmdb.org/t/p/w500/" + movie.getMovieResult().get(0).getPosterPath());
                filmsDAO.insert(newFilms);
                list.add(newFilms);
                adapter.notifyItemInserted(list.size() - 1);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                Toast.makeText(getActivity(), "Não foi possivel a imagem do filme",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onConfigureRyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        filmsHelper.getRecyclerView().setLayoutManager(layoutManager);
        filmsHelper.getRecyclerView().setHasFixedSize(true);

        adapter = new FilmsAdapter(getActivity(), list, R.layout.fragment_films_item);
        adapter.setOnItemClickListener(this);

        filmsHelper.getRecyclerView().setAdapter(adapter);
    }

    @Override
    public void onViewClick(View v, int position) {

    }
}
