package main.backend;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

import javax.print.Doc;
import java.util.ArrayList;

public class CategoryGroup {
    private IdNumberCategoryGroup idNumber;
    private String name;
    private Weight weight;
    private ArrayList<Category> categories;

    //constructor
    public CategoryGroup() {
        idNumber = new IdNumberCategoryGroup();
        name = "";
        weight = new Weight();
        categories = new ArrayList<>();
    }

    public CategoryGroup(String name, double weight) {
        this();
        setName(name);
        setWeight(weight);
    }
    public CategoryGroup(String id, String name, double weight, ArrayList<Category> categories){
        this();
        setId(id);
        setName(name);
        setWeight(weight);
        setCategories(categories);
    }
    //accessor
    public IdNumberCategoryGroup getIdNumber() {
        return this.idNumber;
    }

    public String getId() {
        return this.idNumber.getId();
    }

    public IdNumberCategoryGroup getIdNumberObject() {return this.idNumber;}

    public String getName() {
        return this.name;
    }

    public double getWeight() {
        return this.weight.getValue();
    }

    public Weight getWeightObject() {return this.weight;}

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double value) {
        this.weight.setValue(value);
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    //functions
    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    //DB function
    //from RAM to DB
    public Document write(){
        Document CategoryGroupDoc = new Document();
        CategoryGroupDoc.put("name", getName());
        if(this.getIdNumberObject() != null){
            CategoryGroupDoc.put("idNumber", getIdNumberObject().write());
        }
        if(this.getWeightObject() != null){
            CategoryGroupDoc.put("weight", getWeightObject().write());
        }
        ArrayList<Document> CategoriesList = new ArrayList<Document>();
        for(int i = 0; i < categories.size(); i++){
            CategoriesList.add(categories.get(i).write());
        }
        CategoryGroupDoc.put("categories", CategoriesList);
        return CategoryGroupDoc;
    }

    //FROM DB TO RAM
    public void read(Document doc){
        if (doc != null){
            setName((String) doc.get("name"));
            Document idNumberDoc = (Document) doc.get("idNumber");
            if(idNumberDoc != null){
                IdNumberCategoryGroup idNumber = new IdNumberCategoryGroup();
                idNumber.read(idNumberDoc);
                this.idNumber = idNumber;
            }
            Document weightDoc = (Document) doc.get("weight");
            if (weightDoc != null){
                Weight weight = new Weight();
                weight.read(weightDoc);
                this.weight = weight;
            }
            ArrayList<Document> categoriesDoc = (ArrayList<Document>) doc.get("categories");
            if (categoriesDoc != null){
                for (Document categoryDoc: categoriesDoc){
                    if (categoryDoc != null){
                        Category category = new Category();
                        category.read(categoryDoc);
                        categories.add(category);
                    }
                }
            }
        }
    }
}
