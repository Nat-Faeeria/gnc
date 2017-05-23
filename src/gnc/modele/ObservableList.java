package gnc.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by nathanael on 23/05/17.
 */
public abstract class ObservableList<T>  extends Observable{

    private List<T> liste;

    public ObservableList() {
        this.liste = new ArrayList<>();
    }

    public List<T> getList() {
        return liste;
    }

    public void add(T object) {
        liste.add(object);
        this.notifyObservers(this.liste);
    }

    public void addList(List<T> list) {
        this.liste.addAll(list);
    }

    public boolean isEmpty() {
        return liste.size() == 0;
    }

}
