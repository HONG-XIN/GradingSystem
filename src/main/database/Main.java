package main.database;

public class Main {

    public static void main(String[] args) {

        ArvoreDatabase db = new ArvoreDatabase();

//        Arvore a1 = new Arvore(1, "pinheiro", 10);
//        Arvore a2 = new Arvore(2, "palmeira", 5);
//
//        db.create(a1);
//        db.create(a2);

//        Arvore a3 = new Arvore(3, "pinocchio", 15, "red");
//
//        db.create(a3);

        System.out.println("Antes:");

        for(Arvore dbObject : db.all())
            System.out.println(dbObject);

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
