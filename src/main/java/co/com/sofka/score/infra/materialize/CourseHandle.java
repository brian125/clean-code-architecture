package co.com.sofka.score.infra.materialize;


import co.com.sofka.score.domain.course.events.CourseCreated;
import co.com.sofka.score.domain.course.events.LessonAdded;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CourseHandle {
    private final MongoClient mongoClient;

    public CourseHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @ConsumeEvent(value = "sofka.campus.score.CourseCreated", blocking = true)
    void consumeCourseCreated(CourseCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("course")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofka.campus.score.LessonAdded", blocking = true)
    void consumeLessonAdd(LessonAdded event) {
        BasicDBObject document = new BasicDBObject();
        document.put("lesson."+event.getLessonId()+".name", event.getName());
        document.put("lesson."+event.getLessonId()+".date", Instant.now());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("course")
                .updateOne(Filters.eq("_id", event.getAggregateId()), updateObject);
    }

}