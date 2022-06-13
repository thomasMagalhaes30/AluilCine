package fr.iut.aluilcine.repositories;


import fr.iut.aluilcine.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface MovieRepositoryCustom{

    void updateMovieByIdAfterAddReview(String movieId, int mark);

    //Page<Movie> findByReleaseDateBeforeOrderByReleaseDateDesc(Date to, Pageable pageable);
}
