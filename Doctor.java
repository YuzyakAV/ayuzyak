package ru.job4j.Inheritance;

/**
 * Doctor.
 * Class Doctor.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */
public class Doctor extends Profession {
    /**
     * The class field.
     * Class inheritance Profession and the creation of an original metodov and fields.
     * Сreating internship.
     * Сreating residency.
     */
    private int internship;
    private int residency;
    public Doctor(String name, int age, String university, String recommendations, int aScore) {
        super(name, age, university, recommendations, aScore);
        this.internship = internship;
        this.residency = residency;
    }
}