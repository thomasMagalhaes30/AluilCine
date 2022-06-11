package fr.iut.aluilcine.repositories;

import com.mongodb.BasicDBObject;
import fr.iut.aluilcine.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.logging.Logger;

@Component
public class MovieRepositoryImplement implements MovieRepositoryCustom{

    @Autowired
    MongoTemplate mongoTemplate;


    /*
        Requete

        db.movie.findAndModify({
            query: {_id : ObjectId("62a3afc341961a57fb485cfb")},
            update: [
                { $set:
                    {
                        mark: { $divide: [ { $add: [ 5, { $multiply: ["$mark", "$totalReview"] } ] }, { $add: [ "$totalReview", 1 ] } ] },
                        totalReview: { $add: [ "$totalReview", 1 ] }
                    }
                }
            ]
        })
     */
    @Override
    public void updateMovieByIdAfterAddReview(String movieId, int mark) {
        // on selectionne le film qui possède le movieId
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(movieId));

        // on met à jour la note et on increment le nombre de commentaires
        Update update = new Update();
        //mark: { $divide: [ { $add: [ 5, { $multiply: ["$mark", "$totalReview"] } ] }, { $add: [ "$totalReview", 1 ] } ] },
        //update.set("mark", 2f);
        //update.set("mark", "$totalReview");
        //update.multiply("mark", query. .getInteger("totalReview") );

        //update.set("mark", update.inc("$totalReview", 1) );
        //update.set("mark", 2f);

        /*
        System.out.println("test");
        System.out.println(update.getUpdateObject());
        System.out.println(query.getQueryObject());*/

        update.inc("totalReview");

        Movie movie = mongoTemplate.findAndModify(query, update, Movie.class);
        if (movie == null ) return;

        update = new Update();
        update.set("mark", (movie.getMark() * movie.getTotalReview() + mark) / (movie.getTotalReview() + 1) );
        mongoTemplate.updateMulti(query, update, Movie.class);
    }
}
