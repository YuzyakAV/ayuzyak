package ru.job4j.Inheritance;

/**
 * Professions.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */

public class Profession {
    /**
     * The class field.
     * Create fields and methods that are common to all inherited classes.
     * Сreating name.
     * Сreating age.
     * Сreating university where the training took place.
     * Сreating recommendations.
     * Сreating average score university.
     */
    private String name;
    private int age;
    private String university;
    private String recommendations;
    private int aScore;
    /**
     * The constructor of the Profession with parameters.
     * @param name - name;
     * @param age - age;
     * @param university - university;
     * @param recommendations - recommendations;
     * @param aScore - aScore;
     */
    public Profession(String name, int age, String university, String recommendations, int aScore) {
        this.name = name;
        this.age = age;
        this.university = university;
        this.recommendations = recommendations;
        this.aScore = aScore;
    }
    /**
     * Getter to specify the name.
     * @return tag.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Getter to specify the age.
     * @return tag.
     */
    public int getAge() {
        return this.age;
    }
    /**
     * Getter to specify the university.
     * @return tag.
     */
    public String getUniversity() {
        return this.university;
    }
    /**
     * Getter to specify the recommendations.
     * @return tag.
     */
    public String getRecommendations() {
        return this.recommendations;
    }
    /**
     * Getter to specify the aScore.
     * @return tag.
     */
    public int getAScore() {
        return this.aScore;
    }
}