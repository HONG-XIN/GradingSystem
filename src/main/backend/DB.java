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
        ObjectRepository<Score> repository = db.getRepository(Score.class);


    }

    public static void main(String[] args){
        DB.initialize();
    }
}

