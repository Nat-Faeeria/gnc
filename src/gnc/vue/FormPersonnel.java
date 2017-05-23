package gnc.vue;

import gnc.controllers.FonctionCtrl;
import gnc.controllers.PersonnelCtrl;
import gnc.modele.Fonction;
import gnc.modele.Personnel;
import gnc.utils.Constants;
import gnc.utils.SpringUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nathanael on 23/05/17.
 */
public class FormPersonnel extends JFrame {

    private PersonnelCtrl persController;
    private FonctionCtrl fctController;

    private JLabel lbl_Matricule;
    private JLabel lbl_Id;
    private JLabel lbl_Fonction;

    private JTextField txt_Matricule;
    private JTextField txt_Id;
    private JComboBox<Fonction> chx_Fonction;

    private JButton validateButton;

    public FormPersonnel() {
        super(Constants.CREATE_PERSONNEL);
        validateButton = new JButton(Constants.BUTTON_OK);
        validateButton.addActionListener(new CreatePersonnelListener());
        initLayout();
    }

    public FormPersonnel(Personnel personnel) {
        super(Constants.EDIT_PERSONNEL);
        validateButton = new JButton(Constants.BUTTON_OK);
        validateButton.addActionListener(new EditPersonnelListener());
        initLayout();
        txt_Matricule.setText(personnel.getMatricule());
        txt_Id.setText(personnel.getId());
        chx_Fonction.setSelectedItem(personnel.getFonction());
    }

    private void initLayout() {

        persController = new PersonnelCtrl();
        fctController = new FonctionCtrl();

        JPanel container = new JPanel(new SpringLayout());

        lbl_Matricule = new JLabel(Constants.LABEL_MATRICULE, JLabel.TRAILING);
        txt_Matricule = new JTextField();
        container.add(lbl_Matricule);
        container.add(txt_Matricule);

        lbl_Id = new JLabel(Constants.LABEL_ID, JLabel.TRAILING);
        txt_Id = new JTextField();
        container.add(lbl_Id);
        container.add(txt_Id);

        lbl_Fonction = new JLabel(Constants.LABEL_FONCTION, JLabel.TRAILING);
        chx_Fonction = new JComboBox<>(fctController.getAllFonction().getList().toArray(new Fonction[0]));
        container.add(lbl_Fonction);
        container.add(chx_Fonction);

        container.add(validateButton);

        SpringUtilities.makeCompactGrid(container, 3, 2, 6, 6, 6, 6);

        this.setContentPane(container);
        this.setSize(540,560);
        this.setVisible(true);
    }

    class CreatePersonnelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            persController.createPersonnel(txt_Matricule.getText(), txt_Id.getText(), (Fonction) chx_Fonction.getSelectedItem());
        }
    }

    class EditPersonnelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            persController.updatePersonnel(txt_Matricule.getText(), txt_Id.getText(), (Fonction) chx_Fonction.getSelectedItem());
        }
    }

}
