package gnc.modele;

import java.util.List;

/**
 * Created by nathanael on 22/05/17.
 */
public class Personnel {

    private String id;
    private String matricule;
    private Fonction fonction;

    private List<NonConformite> ncTrouvees;
    private List<NonConformite> ncImputees;

    public Personnel() {}

    public Personnel(String id, String matricule, Fonction fonction, List<NonConformite> ncTrouvees, List<NonConformite> ncImputees) {
        this.id = id;
        this.matricule = matricule;
        this.fonction = fonction;
        this.ncTrouvees = ncTrouvees;
        this.ncImputees = ncImputees;
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
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public List<NonConformite> getNcTrouvees() {
        return ncTrouvees;
    }

    public List<NonConformite> getNcImputees() {
        return ncImputees;
    }
}
