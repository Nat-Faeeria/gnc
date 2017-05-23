package gnc.vue;

import gnc.controllers.PersonnelCtrl;
import gnc.modele.Personnel;
import gnc.modele.Personnels;
import gnc.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by nathanael on 23/05/17.
 */
public class TablePersonnel extends JFrame implements Observer {

    private JPanel container;
    private JList<Personnel> myJList;
    private JButton addPersonnelButton;
    private JButton deletePersonnelButton;
    private PersonnelCtrl controller;

    private List<Personnel> selectedPersonnels;

    public TablePersonnel() {
        super(Constants.JFRAME_PERSONNEL_TITLE);

        this.setSize(540,560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = new JPanel();
        this.setContentPane(container);
        this.setLayout(new BorderLayout());


        controller = new PersonnelCtrl();
        myJList = new JList<>(controller.getAllPersonnel().getList().toArray(new Personnel[0]));
        controller.getAllPersonnel().addObserver(this);
        myJList.addMouseListener(new JListSelectionMouseAdapter());
        container.add(myJList, BorderLayout.CENTER);


        addPersonnelButton = new JButton(Constants.BUTTON_ADD);
        addPersonnelButton.addActionListener(new AddButtonActionListener());

        deletePersonnelButton = new JButton(Constants.BUTTON_DELETE);
        deletePersonnelButton.addActionListener(new DeleteButtonActionListener());

        container.add(addPersonnelButton, BorderLayout.SOUTH);
        //container.add(deletePersonnelButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Personnels) {
            this.myJList.removeAll();
            List<Personnel> personnel = (ArrayList) arg;
            this.myJList.setListData(personnel.toArray(new Personnel[0]));
            this.myJList.repaint();
        }
    }

    class JListSelectionMouseAdapter extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                Personnel personnel = myJList.getSelectedValue();
                FormPersonnel formPersonnel = new FormPersonnel(personnel);
            } else {
                selectedPersonnels = myJList.getSelectedValuesList();
                deletePersonnelButton.setVisible(true);
            }
        }
    }

    class AddButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FormPersonnel formPersonnel = new FormPersonnel();
        }
    }

    class DeleteButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.deletePersonnels(selectedPersonnels);
        }
    }
}