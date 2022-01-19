package co.com.sofka.score.domain.course;

import co.com.sofka.score.domain.generic.AggregateRoot;
import co.com.sofka.score.domain.generic.DomainEvent;
import co.com.sofka.score.domain.generic.EventChange;
import co.com.sofka.score.domain.course.events.CourseCreated;
import co.com.sofka.score.domain.course.events.LessonAdded;

import java.util.List;
import java.util.Map;

public class Course extends AggregateRoot implements EventChange {
    protected Map<String, Lesson> lessons;
    protected String name;

    public Course(String id, String name) {
        super(id);
        subscribe(new CourseEventChange(this));
        appendChange(new CourseCreated(id, name)).apply();
    }

    private Course(String id){
        super(id);
        subscribe(new CourseEventChange(this));
    }

    public static Course from(String id, List<DomainEvent> events){
        var course = new Course(id);
        events.forEach(course::applyEvent);
        return course;
    }

    public void addLesson(String id, String name){
        appendChange(new LessonAdded(id, name)).apply();
    }

    public void addStudentToLessonOf(String lessonId, String name, String email){
        this.lessons.get(lessonId).addStudent(name, email);
    }

    public void evaluationStudent(String lessonId, String email, Score score){
        this.lessons.get(lessonId).evaluationStudents(email, score);
    }

    public Lesson findLessonById(String id){
        return lessons.get(id);
    }
}
