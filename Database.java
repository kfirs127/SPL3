package bgu.spl.net;

import sun.security.util.Password;

import java.util.Dictionary;
import java.util.List;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
    private Dictionary<String , String> Admins;
    private Dictionary<String , String> Students;
    private Dictionary<Integer ,String> Courses;
    private Dictionary<Integer, List<String> > StudentsInCourses;
    private Dictionary<String , List<Integer> > CoursesOfStudent;
    private Dictionary<Integer , Integer> availableSeatsInCourse;
    private Dictionary<Integer , Integer> maxSeatsInCourse;
    private Dictionary<Integer, List<Integer>> KdamCourses;
    private Dictionary<String , List<Integer>> studentKdamCourse;

    //to prevent user from creating new Database
    private Database() {
        // TODO: implement
    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return singleton;
    }

    /**
     * loades the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath) {
        // TODO: implement
        return false;
    }

    public boolean admin_Username_Password(String userName , String password) {
        return Admins.get(userName).equals(password);
    }

    public boolean students_Username_Password(String userName , String password) {
        return Students.get(userName).equals(password);
    }

    public boolean isAdminExsist(String userName){
        while (Admins.keys().hasMoreElements()){
            if(Admins.keys().nextElement().equals(userName)) return true;
        }
        return false;
    }

    public boolean isStudentExsist(String userName){
        return Students.get(userName) != null;
    }

    public void registerAdmin(String userName , String password){ Admins.put(userName , password); }

    public void registerStudent(String userName , String password){ Students.put(userName , password); }

    public boolean exsistingCours(int numCourse){ return Courses.get(numCourse) != null; }

    public boolean availableSeats(int numCourse) { return availableSeatsInCourse.get(numCourse) > 0; }

    public boolean allKdamCourse(String username , int courseNum){ return studentKdamCourse.get(username).containsAll(KdamCourses.get(courseNum)); }

    public void registerCourse(int courseNum , String student){
        List<String> students = StudentsInCourses.get(courseNum);
        students.add(student);
        StudentsInCourses.put(courseNum , students);
        List<Integer> courses = CoursesOfStudent.get(student);
        courses.add(courseNum);
        CoursesOfStudent.put(student , courses);
        availableSeatsInCourse.put(courseNum , availableSeatsInCourse.get(courseNum) + 1);
        studentKdamCourse.put(student , courses);
    }

    public List<Integer> getKDAMCourse(int courseNum) { return KdamCourses.get(courseNum); }

    public String getCourseName(int courseNum) { return Courses.get(courseNum); }

    public int getAvailableSeats(int courseNum){ return availableSeatsInCourse.get(courseNum); }

    public int getMaxSeats(int courseNum) { return maxSeatsInCourse.get(courseNum); }

    public List<String> getStuentsInCourse(int courseNum){
        java.util.Collections.sort(StudentsInCourses.get(courseNum));
        return StudentsInCourses.get(courseNum);
    }

    public List<Integer> getCoursesOfStudent(String student){
        return CoursesOfStudent.get(student);
    }

    public boolean isStudentRegisterToCourse(String student , int courseNum) { return StudentsInCourses.get(courseNum).contains(student); }

    public void unregisterStudentFromCourse(String student , int courseNum){
        List<String> students = StudentsInCourses.get(courseNum);
        students.remove(student);
        List<Integer> courses = CoursesOfStudent.get(student);
        courses.remove(courseNum);
        StudentsInCourses.put(courseNum , students);
        CoursesOfStudent.put(student , courses);
        availableSeatsInCourse.put(courseNum , availableSeatsInCourse.get(courseNum) - 1);
        studentKdamCourse.put(student , courses);
    }
}