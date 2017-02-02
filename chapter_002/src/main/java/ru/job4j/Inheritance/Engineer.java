package ru.job4j.Inheritance;

/**
 * Engineer.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */
public class Engineer extends Profession {
    /**
     * The class field.
     * Class inheritance Profession and the creation of an original metodov and fields.
     * Сreating experience.
     */
    private int experience;
    public Engineer(String name, int age, String university, String recommendations, int aScore) {
        super(name, age, university, recommendations, aScore);
        this.experience = experience;
    }
}