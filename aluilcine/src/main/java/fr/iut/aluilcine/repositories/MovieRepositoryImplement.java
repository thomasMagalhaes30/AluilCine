package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.*;

@Component
public class MovieRepositoryImplement implements MovieRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Met à jour la note du film par calcul du dividende/diviseur
     * @param movieId identifiant du film
     * @param dividende dividende du calcul de la note
     * @param diviseur diviseur du calcul de la note (nombre total d'avis)
     */
    private void updateMovieMarkAndTotalReview(String movieId, AggregationExpression dividende, AggregationExpression diviseur){
        // on selectionne le film qui possède le movieId
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(movieId));

        final AggregationUpdate aggregationUpdate = Aggregation.newUpdate();
        aggregationUpdate.set("mark").toValue(Divide.valueOf(dividende).divideBy(diviseur));

        // on set totalReview par le diviseur car il correspond au nombre total d'avis
        aggregationUpdate.set("totalReview").toValue(diviseur);

        mongoTemplate.update(Movie.class).matching(query).apply(aggregationUpdate).all();
    }

    /**
     * Met à jour un film après l'ajout d'un avis
     * @param movieId identifiant du film
     * @param mark note à ajouter
     */
    @Override
    public void updateMovieByIdAfterAddReview(String movieId, int mark) {
        updateMovieMarkAndTotalReview(movieId,
                valueOf(Multiply.valueOf("mark").multiplyBy("totalReview")).add(mark),
                valueOf("totalReview").add(1)
        );
    }

    /**
     * Met à jour un film après la modification d'un avis
     * @param movieId identifiant du film
     * @param oldmark ancienne note
     * @param newMark nouvelle note
     */
    @Override
    public void updateMovieByIdAfterUpdateReview(String movieId, int oldmark, int newMark) {
        updateMovieMarkAndTotalReview(movieId,
                valueOf( valueOf(Multiply.valueOf("mark").multiplyBy("totalReview")).subtract(oldmark) ).add(newMark),
                valueOf("totalReview").add(0)
        );
    }

    /**
     * Met à jour un film après la suppression d'un avis
     * @param movieId identifiant du film
     * @param mark note à retirer
     */
    @Override
    public void updateMovieByIdAfterDeleteReview(String movieId, int mark) {
        updateMovieMarkAndTotalReview(movieId,
                valueOf(Multiply.valueOf("mark").multiplyBy("totalReview")).subtract(mark),
                valueOf("totalReview").subtract(1)
        );
    }
}
