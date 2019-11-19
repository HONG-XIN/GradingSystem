package main.database;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArvoreDatabase {

    private ObjectRepository<Arvore> arvoreStore;

    public ArvoreDatabase(){
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath("test.db")
                .openOrCreate();

        arvoreStore = db.getRepository(Arvore.class);
    }

    public void create(Arvore a){
        arvoreStore.insert(a);
    }

    public List<Arvore> all() {

        ArrayList<Arvore> resultado = new ArrayList<Arvore>();

        Iterator<Arvore> it = arvoreStore.find().iterator();

        while (it.hasNext())
            resultado.add(it.next());

        return resultado;
    }

    public Arvore buscaPorId(int id){
        Cursor<Arvore> cursor = arvoreStore.find(ObjectFilters.eq("id", id));

        Iterator<Arvore> iterator = cursor.iterator();

        if(iterator.hasNext())
            return iterator.next();
        else
            return null;
    }

    public void delete(Arvore a){
        arvoreStore.remove(a);
    }

    public void delete(int id){
        arvoreStore.remove(ObjectFilters.eq("id", id));
    }

    public void update(Arvore a){
        arvoreStore.update(ObjectFilters.eq("id", a.getId()), a );
    }
}
