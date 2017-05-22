package gnc.modele;

/**
 * Created by nathanael on 22/05/17.
 */
public class Fonction {

    private String code;
    private String libelle;
    private Boolean autorisationNC;
    private Boolean autorisationImputation;
    private Boolean autorisationAdmin;

    public Fonction(String code, String libelle, Boolean autorisationNC, Boolean autorisationAdmin, Boolean autorisationImputation) {
        this.code = code;
        this.libelle = libelle;
        this.autorisationNC = autorisationNC;
        this.autorisationAdmin = autorisationAdmin;
        this.autorisationImputation = autorisationImputation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getAutorisationNC() {
        return autorisationNC;
    }

    public void setAutorisationNC(Boolean autorisationNC) {
        this.autorisationNC = autorisationNC;
    }

    public Boolean getAutorisationImputation() {
        return autorisationImputation;
    }

    public void setAutorisationImputation(Boolean autorisationImputation) {
        this.autorisationImputation = autorisationImputation;
    }

    public Boolean getAutorisationAdmin() {
        return autorisationAdmin;
    }

    public void setAutorisationAdmin(Boolean autorisationAdmin) {
        this.autorisationAdmin = autorisationAdmin;
    }

    public boolean isAdmin() {
        return getAutorisationAdmin();
    }
}
