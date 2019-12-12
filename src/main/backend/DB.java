package main.backend;

import org.dizitart.no2.*;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

public class DB {
    public static void initialize(){
        Nitrite db = Nitrite.builder().filePath("C:/Users/ZHUZIYU/IdeaProjects/GradingSystem/GradingSystem.db").openOrCreate();
        // Create an Object Repository
        ObjectRepository<Score> ScoreRepository = db.getRepository(Score.class);
        ObjectRepository<Student> StudentRepository = db.getRepository(Student.class);
        Student stu = new Student("aaa", "bbbb", "U12345678", "abc", StudentType.GRAD);
        StudentRepository.insert(stu);
        Cursor<Student> cursor = StudentRepository.find();
        /*List<Student> subEmployeeList
                = StudentRepository.find().project(Student.class).toList();
        for(Student student : subEmployeeList) {
            System.out.println(student.getStudentID());
        }*/
    }

    public static void main(String[] args){
        DB.initialize();
    }
}

