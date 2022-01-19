package co.com.sofka.score.infra.handle;

import co.com.sofka.score.domain.course.command.AddLesson;
import co.com.sofka.score.infra.generic.UseCaseHandle;
import co.com.sofka.score.usecase.AddLessionUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddLessonHandle extends UseCaseHandle {

    private final AddLessionUseCase useCase;

    public AddLessonHandle(AddLessionUseCase useCase){
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.campus.score.LessonAdd")
    void consumeBlocking(AddLesson command) {
        var events = useCase.apply(command);
        save(command.getCourseId(), events);
    }
}
