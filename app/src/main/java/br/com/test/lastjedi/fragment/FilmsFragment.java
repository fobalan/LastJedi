package br.com.test.lastjedi.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.R;
import br.com.test.lastjedi.adapter.FilmsAdapter;
import br.com.test.lastjedi.constants.Constantes;
import br.com.test.lastjedi.dao.FilmsDAO;
import br.com.test.lastjedi.helper.FilmsHelper;
import br.com.test.lastjedi.listener.RecyclerViewListener;
import br.com.test.lastjedi.model.Films;
import br.com.test.lastjedi.model.Movie;
import br.com.test.lastjedi.model.MovieDetails;
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
        List<String> filmsUrl = people.getFilms();
        for (String filmUrl: filmsUrl) {
            if(filmsDAO.checkIfExistsByUrl(filmUrl)){
                list.add(filmsDAO.getFilmsByUrl(filmUrl));
                adapter.notifyItemInserted(list.size() - 1);
            } else {
                getFilmsFromService(filmUrl);
            }
        }
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

    private void getImageUrlFromService(final Films newFilm) {
        Call<Movie> call = new RetrofitInitializer(Constantes.THE_MOVIEDB_API)
                .getMovieService()
                .getMovie(Constantes.THE_MOVIEDB_API_KEY,newFilm.getTitle());
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                newFilm.setImageUrl(Constantes.IMAGE_URL_SUFIX + movie.getMovieResult().get(0).getPosterPath());
                getHomePageById(movie.getMovieResult().get(0).getId(), newFilm);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                Toast.makeText(getActivity(), "Não foi possivel a imagem do filme",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHomePageById(int idMovie, final Films newFilm) {
        Call<MovieDetails> call = new RetrofitInitializer(Constantes.THE_MOVIEDB_API)
                .getHomePageService()
                .getHomePage(idMovie,Constantes.THE_MOVIEDB_API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                newFilm.setHomePage(response.body().getHomePage());
                filmsDAO.insert(newFilm);
                list.add(newFilm);
                adapter.notifyItemInserted(list.size() - 1);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
                Toast.makeText(getActivity(), "Não foi possivel pegar homepage",Toast.LENGTH_SHORT).show();
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
        if(list.get(position).getHomePage() != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getHomePage()));
            startActivity(browserIntent);
        }
    }
}
