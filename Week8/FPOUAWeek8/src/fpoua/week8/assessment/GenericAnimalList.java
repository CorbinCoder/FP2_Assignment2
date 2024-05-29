package fpoua.week8.assessment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericAnimalList<T extends Animal>  {
    List<T> list = new ArrayList<T>();
    public GenericAnimalList(){

    }
    public void populate(List<T> aList){
        list.addAll(aList);
        
    }
    
    public void addAnAnimal(T anAnimal){
        list.add(anAnimal);
        
    }
    
    public List<T> getList() {
    	return list;
    }
    
}
