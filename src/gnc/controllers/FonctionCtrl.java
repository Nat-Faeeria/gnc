package gnc.controllers;

import gnc.dao.FonctionDAO;
import gnc.modele.Fonction;
import gnc.modele.Fonctions;

import java.util.List;

/**
 * Created by nathanael on 23/05/17.
 */
public class FonctionCtrl {

    private FonctionDAO fonctionDAO;
    private static Fonctions allFonction;

    public FonctionCtrl() {
        super();
        this.fonctionDAO = new FonctionDAO();
        allFonction = new Fonctions();
    }

    public void createFonction(String code, String libelle, Boolean autorisationNC,
                                Boolean autorisationAdmin, Boolean autorisationImputation) {
        fonctionDAO.create(new Fonction(code, libelle,
                autorisationNC, autorisationAdmin, autorisationImputation));
    }

    public Fonctions getAllFonction() {
        if (!allFonction.isEmpty()){
            return allFonction;
        }
        allFonction.addList(fonctionDAO.selectAll());
        return allFonction;
    }

    public Fonction getFonctionByCode(String code) {
        Fonction personnel = allFonction.find(code);
        if (personnel != null) {
            return personnel;
        }
        return fonctionDAO.selectBy(code);
    }

    public void updateFonction(Fonction fonction) {
        fonctionDAO.update(fonction);
    }

    public void deleteFonction(String code) {
        fonctionDAO.delete(code);
    }

    public void deleteFonctions(List<Fonction> fonctions) {
        for (Fonction f : fonctions) {
            this.deleteFonction(f.getCode());
        }
    }

}
