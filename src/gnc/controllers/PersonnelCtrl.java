package gnc.controllers;

import gnc.dao.FonctionDAO;
import gnc.dao.PersonnelDAO;
import gnc.modele.Fonction;
import gnc.modele.Personnel;
import gnc.modele.Personnels;

import java.util.List;

/**
 * Created by nathanael on 23/05/17.
 */
public class PersonnelCtrl {

    private PersonnelDAO personnelDAO;
    private static Personnels allPersonnel;

    public PersonnelCtrl() {
        super();
        this.personnelDAO = new PersonnelDAO();
        allPersonnel = new Personnels();
    }

    public void createPersonnel(String matricule, String id, Fonction fonction) {
        personnelDAO.create(new Personnel(matricule, id, fonction));
    }

    public Personnels getAllPersonnel() {
        if (!allPersonnel.isEmpty()){
            return allPersonnel;
        }
        allPersonnel.addList(personnelDAO.selectAll());
        return allPersonnel;
    }

    public Personnel getPersonnelByMatricule(String matricule) {
        Personnel personnel = allPersonnel.find(matricule);
        if (personnel != null) {
            return personnel;
        }
        return personnelDAO.selectBy(matricule);
    }

    public void updatePersonnel(String matricule, String id, Fonction fonction) {
        personnelDAO.update(new Personnel(matricule, id, fonction));
    }

    public void deletePersonnel(String matricule) {
        personnelDAO.delete(matricule);
    }

    public void deletePersonnels(List<Personnel> personnels) {
        for (Personnel p : personnels) {
            this.deletePersonnel(p.getMatricule());
        }
    }

}
