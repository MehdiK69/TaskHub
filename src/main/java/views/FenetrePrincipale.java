package views;

import javax.swing.*;

public class FenetrePrincipale extends JFrame {

    public FenetrePrincipale() {
        super();
        construireFenetrePrincipale();
    }

    public void construireFenetrePrincipale() {
        setTitle("TaskHub - Gestion de tâches");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setContentPane(construirePanelPrincipale());
        setVisible(true);
    }

    public JPanel construirePanelPrincipale() {
        JPanel panelPrincipale = new JPanel();
        JButton boutonAjouterProjet = new JButton("Ajouter un projet");
        panelPrincipale.add(boutonAjouterProjet);
        JButton boutonAjouterTache = new JButton("Ajouter une tâche");
        panelPrincipale.add(boutonAjouterTache);
        JButton boutonSupprimerProjet = new JButton("Supprimer un projet");
        panelPrincipale.add(boutonSupprimerProjet);
        JButton boutonSupprimerTache = new JButton("Supprimer une tâche");
        panelPrincipale.add(boutonSupprimerTache);

        boutonAjouterProjet.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Bouton Ajouter un projet cliqué !"
            );
        });
        boutonAjouterTache.addActionListener(e -> {

        });
        return panelPrincipale;

    }
}
