package JavaDevelomentKitLesson2.Server;

public interface Repository<T> {
    void saveMessage(T text);
    T readText();
}
