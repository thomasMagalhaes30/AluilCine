package fr.iut.aluilcine.repositories;


public interface MovieRepositoryCustom{

    void updateMovieByIdAfterAddReview(String movieId, int mark);
}
