package co.com.sofka.score.domain.course.command;

import co.com.sofka.score.domain.generic.Command;

public class AddLesson extends Command {

    private String courseId;
    private String lessonId;
    private String name;

//    public AddLesson(String courseId, String lessonId, String name){
//
//        this.courseId = courseId;
//        this.lessonId = lessonId;
//        this.name = name;
//    }

    public AddLesson() {
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getName() {
        return name;
    }
}
