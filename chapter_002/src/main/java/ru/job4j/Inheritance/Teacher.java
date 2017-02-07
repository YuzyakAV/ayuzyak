package ru.job4j.Inheritance;

import java.util.ArrayList;
/**
 * Teacher.
 * @author ayuzyak.
 * @version 1.
 * @since 02.02.2017.
 */
public class Teacher extends Profession {
    /**
     * average rating of all students.
     */
    private int averageRatingOfAllStudents;

    /**
     * number of students.
     */
    private int numberOfStudens;

    /**
     * method for checking homework.
     * @param homework is student's homework.
     * @param correctWork is correct homework for checking.
     * @return boolean.
     */
    public boolean checkHomeWork(HomeWork homework, HomeWork correctWork) {
        boolean rate = false;
        return homework.equals(correctWork);
    }

    /**
     * method for teaching student.
     * @param student for teaching.
     * @param lesson for teaching.
     */
    public void teach(Student student, Lesson lesson) {
        student.addLesson(lesson);
    }

    /**
     * method for assess of knowledge.
     * @param student for assessment.
     * @param baseKnowledge is knowledge for assessment.
     * @return assessment of knowledge.
     */
    public int assessKnowledge(Student student, ArrayList<Lesson> baseKnowledge) {
        int assessment = 0;
        final int well = 5;
        if (student.getKnowledge().equals(baseKnowledge)) {
            assessment = well;
        }
        return assessment;
    }
}

/**
 * Class HomeWork.
 */
class HomeWork {

}

/**
 * Class Student.
 */
class Student {

    /**
     * list of knowledge.
     */
    private ArrayList<Lesson> knowledge = new ArrayList<Lesson>();

    /**
     * method for add lesson.
     * @param lesson to add.
     */
    public void addLesson(Lesson lesson) {
        this.knowledge.add(lesson);
    }

    /**
     * method for get knowledge.
     * @return list of lessons.
     */
    public ArrayList<Lesson> getKnowledge() {
        return this.knowledge;
    }
}

/**
 * Class Lesson.
 */
class Lesson {

}