package gnc.modele;

/**
 * Created by nathanael on 23/05/17.
 */
public class Fonctions extends ObservableList<Fonction> {

    public Fonction find(String code) {
        for (Fonction f : this.getList()) {
            if (f.getCode().equals(code)) {
                return f;
            }
        }
        return null;
    }

}
