package main.database;

import main.backend.Course;
import main.backend.GradingSystem;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

public class MainDatabase implements GradingSystemDatabase{

    public static void main(String[] args) {
        Nitrite db = Nitrite.builder()
                .filePath("./GradingSystem.db")
                .openOrCreate();
        NitriteCollection GradingSystemCollection = db.getCollection("GradingSystem");
        GradingSystem gradingSystem = new GradingSystem();
        Course course = new Course();
        /*course.setName("ac");
        gradingSystem.getCourses().add(course);
        Document doc = new Document();
        doc = GradingSystemDatabase.write(gradingSystem, db);
        GradingSystemCollection.insert(doc);*/
        GradingSystemDatabase.read(gradingSystem, db);
        System.out.println(gradingSystem.getCourses().get(0).getName().toString());
        //ArvoreDatabase db = new ArvoreDatabase();

//        Arvore a1 = new Arvore(1, "pinheiro", 10);
//        Arvore a2 = new Arvore(2, "palmeira", 5);
//
//        db.create(a1);
//        db.create(a2);

//        Arvore a3 = new Arvore(3, "pinocchio", 15, "red");
//
//        db.create(a3);

        /*System.out.println("Antes:");

        for(Arvore dbObject : db.all())
            System.out.println(dbObject);*/

//        db.update(new Arvore(1, "pinheiro do parama", 10));

//
//        db.delete(new Arvore(1, "pinheiro", 10));
//        db.delete(2);
//
//        System.out.println("Depois");
//
//        for(Arvore dbObject : db.all())
//            System.out.println(dbObject);

//        System.out.println( db.buscaPorId(2) );

//        Arvore a1 = new Arvore(1, "pinheiro", 10);
//        Arvore a2 = new Arvore(2, "palmeira", 5);

//        db.create(a1);
//        db.create(a2);


    }
}
