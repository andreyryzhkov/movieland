package com.aryzhkov.movieland.service.cache;

import com.aryzhkov.movieland.entity.Movie;
import com.aryzhkov.movieland.service.MovieEnrichmentService;
import com.aryzhkov.movieland.service.MovieService;
import com.aryzhkov.movieland.web.util.Currency;
import com.aryzhkov.movieland.web.util.MovieRequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Primary
@Service
@RequiredArgsConstructor
public class CacheMovieService implements MovieService {

    private final MovieService movieService;

    private final MovieEnrichmentService movieEnrichmentService;

    private ConcurrentHashMap<Integer, SoftReference<Movie>> cacheMovies = new ConcurrentHashMap<>();

    @Override
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @Override
    public List<Movie> getRandom() {
        return movieService.getRandom();
    }

    @Override
    public List<Movie> getByGenre(int id) {
        return movieService.getByGenre(id);
    }

    @Override
    public List<Movie> getAll(MovieRequestParam movieRequestParam) {
        return movieService.getAll(movieRequestParam);
    }

    @Override
    public List<Movie> getByGenre(int id, MovieRequestParam movieRequestParam) {
        return movieService.getByGenre(id, movieRequestParam);
    }

    @Override
    public Movie getById(int id) {
        SoftReference<Movie> movieSoftReference = cacheMovies.get(id);

        if (movieSoftReference != null) {
            return new Movie(movieSoftReference.get());
        }

        Movie cacheMovie = movieService.getById(id);
        cacheMovies.put(id, new SoftReference<>(cacheMovie));

        return new Movie(cacheMovie);
    }

    @Override
    public Movie getById(int id, Currency currency) {
        return movieService.getById(id, currency);
    }

    @Override
    public Movie add(Movie movie, int[] countryIds, int[] genreIds) {
        Movie cacheMovie = movieService.add(movie, countryIds, genreIds);
        cacheMovies.put(movie.getId(), new SoftReference<>(cacheMovie));

        return cacheMovie;
    }

    @Override
    public Movie edit(Movie movie, int[] countryIds, int[] genreIds) {
        Movie cacheMovie = movieService.edit(movie, countryIds, genreIds);
        cacheMovies.put(movie.getId(), new SoftReference<>(cacheMovie));

        return cacheMovie;
    }
}