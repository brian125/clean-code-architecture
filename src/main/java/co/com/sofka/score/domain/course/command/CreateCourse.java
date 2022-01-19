package co.com.sofka.score.domain.course.command;

import co.com.sofka.score.domain.generic.Command;

public class CreateCourse extends Command {

    private String courseId;
    private String name;

//    public CreateCourse(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public CreateCourse() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
