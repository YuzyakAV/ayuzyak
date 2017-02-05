package ru.job4j.Inheritance;

/**
 * Teacher.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */
public class Teacher extends Profession {
    /**
     * The class field.
     * Class inheritance Profession and the creation of an original method and fields.
     * Сreating refresher training.
     */
    private int refTrain;
    public Teacher(String name, int age, String university, String recommendations, int aScore) {
        super(name, age, university, recommendations, aScore);
        this.refTrain = refTrain;
    }
}