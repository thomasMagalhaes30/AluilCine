package fr.iut.aluilcine.repositories;

import fr.iut.aluilcine.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.*;

@Component
public class MovieRepositoryImplement implements MovieRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;


    /*
        Resultat MongoDB
        Calling update using
            query: { "_id" : { "$oid" : "62a3afc341961a57fb485cfb"}}
            and
            update: [
                { "$set" : { "mark" : { "$divide" : [{ "$add" : [5, { "$multiply" : ["$mark", "$totalReview"]}]}, { "$add" : ["$totalReview", 1]}]}}},
                { "$set" : { "totalReview" : { "$add" : ["$totalReview", 1]}}}
            ]
        in collection: movie
     */
    @Override
    public void updateMovieByIdAfterAddReview(String movieId, int mark) {
        // on selectionne le film qui possède le movieId
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(movieId));

        // on met à jour la note et on increment le nombre de commentaires
        final AggregationUpdate aggregationUpdate = Aggregation.newUpdate();
        aggregationUpdate.set("mark").toValue(
                Divide.valueOf(Add.valueOf(mark).add(Multiply.valueOf("mark").multiplyBy("totalReview")))
                .divideBy( valueOf("totalReview").add(1) ));
        aggregationUpdate.inc("totalReview");

        mongoTemplate.update(Movie.class).matching(query).apply(aggregationUpdate).all();
    }
}
