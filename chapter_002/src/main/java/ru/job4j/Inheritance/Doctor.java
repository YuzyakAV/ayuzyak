package ru.job4j.Inheritance;

/**
 * Doctor.
 * Class Doctor.
 * @author ayuzyak
 * @version 1.
 * @since 02.02.2017.
 */

public class Doctor extends Profession {
    /**
     * number of patients.
     */
    private int numberOfPatients;

    /**
     * number of operations.
     */
    private int numberOfOperations;

    /**
     * number of subordinating nurse.
     */
    private int numberOfNurse;

    /**
     * method for set number of patients doctors.
     * @param numberOfPatients doctors.
     */
    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    /**
     * method for get number of patients doctor's.
     * @return numberOfPatients doctor's.
     */
    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    /**
     * method for set number of operations doctor's.
     * @param numberOfOperations doctor's.
     */
    public void setNumberOfOperations(int numberOfOperations) {
        this.numberOfOperations = numberOfOperations;
    }

    /**
     * method for get number of operations doctor's.
     * @return numberOfOperations doctor's.
     */
    public int getNumberOfOperations() {
        return numberOfOperations;
    }

    /**
     * method for set number of subordinate nurses doctor's.
     * @param numberOfNurse doctor's.
     */
    public void setNumberOfNurse(int numberOfNurse) {
        this.numberOfNurse = numberOfNurse;
    }

    /**
     * method for get number of subordinate nurses doctor's.
     * @return numberOfNurse doctor's.
     */
    public int getNumberOfNurse() {
        return numberOfNurse;
    }
}