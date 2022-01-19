package co.com.sofka.score.domain.course;

public class Student {
    private final String name;
    private final  String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String id(){
        return email;
    }

    public String email() {
        return email;
    }

    public String name() {
        return name;
    }

    public void eval(Score score) {

    }
}
