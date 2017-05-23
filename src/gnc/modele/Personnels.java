package gnc.modele;

/**
 * Created by nathanael on 23/05/17.
 */
public class Personnels extends ObservableList<Personnel> {

    public Personnel find(String matricule) {
        for (Personnel p : this.getList()) {
            if (p.getMatricule().equals(matricule)) {
                return p;
            }
        }
        return null;
    }

}
