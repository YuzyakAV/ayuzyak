package ru.job4j.Inheritance;

/**
 * Engineer.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */
public class Engineer extends Profession {
    /**
     * number of projects.
     */
    private int numberOfProjects;

    /**
     * number of subordinate workers.
     */
    private int numberOfSubordinateWorkers;

    /**
     * method for set number of projects created by Engineer.
     * @param numberOfProjects created by Engineer.
     */
    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    /**
     * method for get number of projects created by Engineer.
     * @return numberOfProjects created by Engineer.
     */
    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    /**
     * method for set number of subordinate Engineer workers.
     * @param numberOfSubordinateWorkers Engineer workers.
     */
    public void setNumberOfSubordinateWorkers(int numberOfSubordinateWorkers) {
        this.numberOfSubordinateWorkers = numberOfSubordinateWorkers;
    }

    /**
     * method for get number of subordinate Engineer workers.
     * @return numberOfSubordinateWorkers.
     */
    public int getNumberOfSubordinateWorkers() {
        return numberOfSubordinateWorkers;
    }
}