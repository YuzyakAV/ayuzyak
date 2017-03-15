package ru.job4j.Inheritance;

/**
 * Professions.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */

public class Profession {
    /**
     * experience.
     */
    private int experience = 0;

    /**
     * qualification.
     */
    private int qualification = 0;

    /**
     * jobPlace.
     */
    private String jobPlace = "";

    /**
     * hours Of Work PerMonth.
     */
    private int hoursOfWorkPerMonth = 0;

    /**
     * hourly Rate.
     */
    private double hourlyRate = 0;

    /**
     * salary.
     */
    private double salary = 0;

    /**
     * education.
     */
    private ArrayList<String> education = new ArrayList<>();

    /**
     * set experience.
     * @param experience - experience in profession.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * get experience.
     * @return experience.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * set qualification.
     * @param qualification - qualification in profession.
     */
    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    /**
     * get qualification.
     * @return qualification.
     */
    public int getQualification() {
        return qualification;
    }

    /**
     * set jobPlace.
     * @param jobPlace - place of job.
     */
    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    /**
     * get jobPlace.
     * @return jobPlace.
     */
    public String getJobPlace() {
        return jobPlace;
    }

    /**
     * set hoursOfWorkPerMonth.
     * @param hoursOfWorkPerMonth - number of working hours per month.
     */
    public void setHoursOfWorkPerMonth(int hoursOfWorkPerMonth) {
        this.hoursOfWorkPerMonth = hoursOfWorkPerMonth;
    }

    /**
     * get hoursOfWorkPerMonth.
     * @return hoursOfWorkPerMonth.
     */
    public int getHoursOfWorkPerMonth() {
        return hoursOfWorkPerMonth;
    }

    /**
     * set hourlyRate.
     * @param hourlyRate - payment for 1 hour of work.
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * get hourlyRate.
     * @return hourlyRate.
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * set salary.
     */
    public void setSalary() {
        this.salary = hoursOfWorkPerMonth * hourlyRate;
    }

    /**
     * get salary.
     * @return salary.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * set education.
     * @param education - array of educations.
     */
    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    /**
     * get education.
     * @return education.
     */
    public ArrayList<String> getEducation() {
        return education;
    }

    /**
     * improve skills.
     * @param skill - add skill.
     */
    public void improveSkills(String skill) {
        education.add(skill);
    }

    /**
     * show all skills.
     */
    public void showSkills() {
        for (String s : education) {
            System.out.println(s);
        }
    }
}