package co.com.sofka.score.infra.handle;

import co.com.sofka.score.domain.course.command.AddLesson;
import co.com.sofka.score.domain.course.command.CreateCourse;
import co.com.sofka.score.infra.generic.UseCaseHandle;
import co.com.sofka.score.usecase.CreateCourseUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCourseHandle extends UseCaseHandle {

    private final CreateCourseUseCase useCase;

    public CreateCourseHandle(CreateCourseUseCase useCase){
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.campus.score.CourseCreate")
    void consumeBlocking(CreateCourse command) {
        var events = useCase.apply(command);
        save(command.getCourseId(), events);
    }

}
