package gnc.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by nathanael on 22/05/17.
 */
public class Personnel extends Observable {

    private String id;
    private String matricule;
    private Fonction fonction;

    public Personnel() {
        super();
    }

    public Personnel(String id, String matricule, Fonction fonction) {
        super();
        this.id = id;
        this.matricule = matricule;
        this.fonction = fonction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
        this.notifyObservers(this.getFonction());
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
        this.notifyObservers(this.getMatricule());
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id='" + id + '\'' +
                ", matricule='" + matricule + '\'' +
                ", fonction=" + fonction +
                '}';
    }
}
