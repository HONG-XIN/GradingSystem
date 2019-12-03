package main.backend;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteBuilder;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;

public class DB {
    public static void initialize(){
        Nitrite db = Nitrite.builder().filePath("C:/Users/ZHUZIYU/IdeaProjects/GradingSystem/GradingSystem.db").openOrCreate();
        // Create an Object Repository
        ObjectRepository<Score> ScoreRepository = db.getRepository(Score.class);
        ObjectRepository<Student> StudentRepository = db.getRepository(Student.class);
        Student stu = new Student("123", "a", "b");
        StudentRepository.insert(stu);

    }

    public static void main(String[] args){
        DB.initialize();
    }
}

