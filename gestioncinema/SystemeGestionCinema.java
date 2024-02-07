import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class SystemeGestionCinema extends JFrame {

    private List<Film> films = new ArrayList<>();
    private List<SalleCinema> salles = new ArrayList<>();
    private List<Diffusion> projections = new ArrayList<>();
    private List<Billet> billets = new ArrayList<>();

    private DefaultListModel<String> modeleListeFilms = new DefaultListModel<>();
    private DefaultListModel<String> modeleListeSalles = new DefaultListModel<>();
    private DefaultListModel<String> modeleListeProjections = new DefaultListModel<>();
    private DefaultListModel<String> modeleListeBillets = new DefaultListModel<>();

    private JList<String> listeFilms = new JList<>(modeleListeFilms);
    private JList<String> listeSalles = new JList<>(modeleListeSalles);
    private JList<String> listeProjections = new JList<>(modeleListeProjections);
    private JList<String> listeBillets = new JList<>(modeleListeBillets);

    private JMenuBar menuBar;
    private JMenu filmMenu, salleMenu, diffusionMenu, billetMenu;

    public SystemeGestionCinema() {
        setTitle("Systeme de Gestion de Cinema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        JScrollPane filmScrollPane = new JScrollPane(listeFilms);
        JScrollPane salleScrollPane = new JScrollPane(listeSalles);
        JScrollPane projectionScrollPane = new JScrollPane(listeProjections);
        JScrollPane billetScrollPane = new JScrollPane(listeBillets);

        add(creerPanelStyle(filmScrollPane));
        add(creerPanelStyle(salleScrollPane));
        add(creerPanelStyle(projectionScrollPane));
        add(creerPanelStyle(billetScrollPane));

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        filmMenu = new JMenu("Gestion des films");
        menuBar.add(filmMenu);

        JMenuItem ajouterFilmItem = new JMenuItem("Ajouter un film");
        JMenuItem modifierFilmItem = new JMenuItem("Modifier un film");
        JMenuItem supprimerFilmItem = new JMenuItem("Supprimer un film");
        JMenuItem afficherFilmsItem = new JMenuItem("Afficher la liste des films");

        filmMenu.add(ajouterFilmItem);
        filmMenu.add(modifierFilmItem);
        filmMenu.add(supprimerFilmItem);
        filmMenu.addSeparator();
        filmMenu.add(afficherFilmsItem);

        ajouterFilmItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterFilm();
            }
        });

        modifierFilmItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifierFilm();
            }
        });

        supprimerFilmItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supprimerFilm();
            }
        });

        afficherFilmsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherFilms();
            }
        });

        salleMenu = new JMenu("Gestion des salles de diffusion");
        menuBar.add(salleMenu);

        JMenuItem ajouterSalleItem = new JMenuItem("Ajouter une salle");
        JMenuItem modifierSalleItem = new JMenuItem("Modifier une salle");
        JMenuItem supprimerSalleItem = new JMenuItem("Supprimer une salle");
        JMenuItem afficherSallesItem = new JMenuItem("Afficher la liste des salles");

        salleMenu.add(ajouterSalleItem);
        salleMenu.add(modifierSalleItem);
        salleMenu.add(supprimerSalleItem);
        salleMenu.addSeparator();
        salleMenu.add(afficherSallesItem);

        ajouterSalleItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterSalle();
            }
        });

        modifierSalleItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifierSalle();
            }
        });

        supprimerSalleItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supprimerSalle();
            }
        });

        afficherSallesItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherSalles();
            }
        });

        diffusionMenu = new JMenu("Gerer des diffusions");
        menuBar.add(diffusionMenu);

        JMenuItem ajouterDiffusionItem = new JMenuItem("Ajouter une diffusion");
        JMenuItem modifierDiffusionItem = new JMenuItem("Modifier une diffusion");
        JMenuItem supprimerDiffusionItem = new JMenuItem("Supprimer une diffusion");
        JMenuItem afficherDiffusionsItem = new JMenuItem("Afficher les diffusions d'une date par salle");

        diffusionMenu.add(ajouterDiffusionItem);
        diffusionMenu.add(modifierDiffusionItem);
        diffusionMenu.add(supprimerDiffusionItem);
        diffusionMenu.addSeparator();
        diffusionMenu.add(afficherDiffusionsItem);

        ajouterDiffusionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterProjection();
            }
        });

        modifierDiffusionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifierDiffusion();
            }
        });

        supprimerDiffusionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supprimerDiffusion();
            }
        });

        afficherDiffusionsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherDiffusions();
            }
        });

        billetMenu = new JMenu("Gestion des billets");
        menuBar.add(billetMenu);

        JMenuItem reserverBilletItem = new JMenuItem("Reserver un billet");
        JMenuItem annulerBilletItem = new JMenuItem("Annuler un billet");
        JMenuItem afficherBilletsRestantsItem = new JMenuItem("Afficher le nombre de billets restants");

        billetMenu.add(reserverBilletItem);
        billetMenu.add(annulerBilletItem);
        billetMenu.addSeparator();
        billetMenu.add(afficherBilletsRestantsItem);

        reserverBilletItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reserverBillet();
            }
        });

        annulerBilletItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                annulerBillet();
            }
        });

        afficherBilletsRestantsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherBilletsRestants();
            }

            private void afficherBilletsRestants() {
                Diffusion projectionSelectionnee = choisirProjectionDisponible();
                if (projectionSelectionnee != null) {
                    afficherBilletsRestantsPourDiffusion(projectionSelectionnee);
                } else {
                    JOptionPane.showMessageDialog(null, "Operation annulee.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel creerPanelStyle(Component composant) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        panel.add(composant, BorderLayout.CENTER);
        return panel;
    }

    private void ajouterFilm() {
        String nom = JOptionPane.showInputDialog("Nom du film : ");
    
        if (nom != null && !nom.isEmpty()) {
            String dureeInput = JOptionPane.showInputDialog("Durée du film (en minutes) : ");
            if (dureeInput != null && !dureeInput.isEmpty()) {
                try {
                    int duree = Integer.parseInt(dureeInput);
    
                    String genre = JOptionPane.showInputDialog("Genre du film : ");
                    if (genre != null && !genre.isEmpty()) { // Check if genre is specified
                        String version = JOptionPane.showInputDialog("Version du film (Vo/Vf) : ");
    
                        if ("Vo".equalsIgnoreCase(version) || "Vf".equalsIgnoreCase(version)) {
                            Film nouveauFilm = new Film(nom, duree, genre, version);
                            films.add(nouveauFilm);
                            modeleListeFilms.addElement(nouveauFilm.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Opération annulée : La version du film doit être 'Vo' ou 'Vf'.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Opération annulée : Genre du film non spécifié.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Opération annulée : La durée du film doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opération annulée : Durée du film non spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée : Nom du film non spécifié.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    private void modifierFilm() {
        String nomFilm = JOptionPane.showInputDialog("Nom du film à modifier : ");
        Film filmAModifier = trouverFilmParNom(nomFilm);
    
        if (filmAModifier != null) {
            String nouveauNom = JOptionPane.showInputDialog("Nouveau nom du film : ");
    
            if (isNotNullOrEmpty(nouveauNom)) {
                String nouvelleDureeInput = JOptionPane.showInputDialog("Nouvelle durée du film (en minutes) : ");
    
                if (isNotNullOrEmpty(nouvelleDureeInput)) {
                    try {
                        int nouvelleDuree = Integer.parseInt(nouvelleDureeInput);
    
                        String nouveauGenre = JOptionPane.showInputDialog("Nouveau genre du film : ");
                        String nouvelleVersion = JOptionPane.showInputDialog("Nouvelle version du film (Vo/Vf) : ");
    
                        if (isNotNullOrEmpty(nouveauGenre) && isNotNullOrEmpty(nouvelleVersion)) {
                            if ("Vo".equalsIgnoreCase(nouvelleVersion) || "Vf".equalsIgnoreCase(nouvelleVersion)) {
                                // Validation successful, now modify the film
                                filmAModifier.setNom(nouveauNom);
                                filmAModifier.setDuree(nouvelleDuree);
                                filmAModifier.setGenre(nouveauGenre);
                                filmAModifier.setVersion(nouvelleVersion);
    
                                // Update the list model
                                modeleListeFilms.clear();
                                for (Film film : films) {
                                    modeleListeFilms.addElement(film.toString());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La version du film doit être 'Vo' ou 'Vf'.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Le genre et la version du film ne peuvent pas être vides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La durée du film doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La durée du film ne peut pas être vide.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Le nouveau nom du film ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Film non trouvé.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean isNotNullOrEmpty(String value) {
        return value != null && !value.isEmpty();
    }
    
    
    private void supprimerFilm() {
        String nomFilm = JOptionPane.showInputDialog("Nom du film a supprimer : ");
        Film filmASupprimer = trouverFilmParNom(nomFilm);
    
        if (filmASupprimer != null) {
            films.remove(filmASupprimer);
    
            modeleListeFilms.removeElement(filmASupprimer.toString());
        } else if (nomFilm != null) {
            JOptionPane.showMessageDialog(null, "Film non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    private Film trouverFilmParNom(String nom) {
        if (nom == null) {
            return null;  
        }
    
        for (Film film : films) {
            if (film != null && film.getNom() != null && film.getNom().equalsIgnoreCase(nom)) {
                return film;
            }
        }
    
        return null;
    }
    
    private void afficherFilms() {
        StringBuilder filmsList = new StringBuilder("Liste des films :\n");
        for (Film film : films) {
            filmsList.append(film.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, filmsList.toString(), "Liste des films", JOptionPane.INFORMATION_MESSAGE);
    }

private void ajouterSalle() {
    String numeroInput = JOptionPane.showInputDialog("Numero de la salle :");
        
    if (numeroInput != null && !numeroInput.isEmpty()) {
        try {
            int numero = Integer.parseInt(numeroInput);

            // Check if a room with the given number already exists
            if (trouverSalleParNumero(numero) == null) {
                String capaciteInput = JOptionPane.showInputDialog("Capacite d'accueil de la salle : ");
                
                if (capaciteInput != null && !capaciteInput.isEmpty()) {
                    try {
                        int capacite = Integer.parseInt(capaciteInput);

                        SalleCinema nouvelleSalle = new SalleCinema(numero, capacite);
                        salles.add(nouvelleSalle);
                        modeleListeSalles.addElement(nouvelleSalle.toString());
                    } catch (NumberFormatException e) {
                        // Handle the case where the input for capacite is not a valid integer
                        JOptionPane.showMessageDialog(null, "Veuillez entrer une capacité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Une salle avec le même numéro existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input for numero is not a valid integer
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de salle valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

    
    
private void modifierSalle() {
    String numeroInput = JOptionPane.showInputDialog("Numero de la salle à modifier : ");

    if (numeroInput != null && !numeroInput.isEmpty()) {
        try {
            int numeroSalle = Integer.parseInt(numeroInput);
            SalleCinema salleAModifier = trouverSalleParNumero(numeroSalle);

            if (salleAModifier != null) {
                String nouveauNumeroInput = JOptionPane.showInputDialog("Nouveau numero de la salle : ");

                if (nouveauNumeroInput != null && !nouveauNumeroInput.isEmpty()) {
                    try {
                        int nouveauNumero = Integer.parseInt(nouveauNumeroInput);

                        // Check if the new room number is not already assigned to another room
                        if (trouverSalleParNumero(nouveauNumero) == null) {
                            String nouvelleCapaciteInput = JOptionPane.showInputDialog("Nouvelle capacite d'accueil de la salle : ");

                            if (nouvelleCapaciteInput != null && !nouvelleCapaciteInput.isEmpty()) {
                                try {
                                    int nouvelleCapacite = Integer.parseInt(nouvelleCapaciteInput);

                                    salleAModifier.setNumero(nouveauNumero);
                                    salleAModifier.setCapacite(nouvelleCapacite);

                                    modeleListeSalles.clear();
                                    for (SalleCinema salle : salles) {
                                        modeleListeSalles.addElement(salle.toString());
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Modification annulée : Nouvelle capacité non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Modification annulée : Nouvelle capacité non spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Modification annulée : Le nouveau numéro de salle est déjà attribué.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Modification annulée : Nouveau numéro non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Modification annulée : Nouveau numéro non spécifié.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Salle non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Numéro de salle non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Modification annulée : Numéro de salle non spécifié.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

    
    

    private void supprimerSalle() {
        String numeroInput = JOptionPane.showInputDialog("Numero de la salle à supprimer : ");
    
        if (numeroInput != null && !numeroInput.isEmpty()) {
            int numeroSalle = Integer.parseInt(numeroInput);
            SalleCinema salleASupprimer = trouverSalleParNumero(numeroSalle);
    
            if (salleASupprimer != null) {
                salles.remove(salleASupprimer);
    
                modeleListeSalles.removeElement(salleASupprimer.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Salle non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private SalleCinema trouverSalleParNumero(int numeroSalle) {
        for (SalleCinema salle : salles) {
            if (salle.getNumero() == numeroSalle) {
                return salle;
            }
        }
        return null;
    }

    private void afficherSalles() {
        StringBuilder sallesList = new StringBuilder("Liste des salles :\n");
        for (SalleCinema salle : salles) {
            sallesList.append(salle.toString()).append("\n");
        }
    
        JOptionPane.showMessageDialog(null, sallesList.toString(), "Liste des salles", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void ajouterProjection() {
        String nomFilm = JOptionPane.showInputDialog("Nom du film : ");

        if (nomFilm != null && !nomFilm.isEmpty()) {
            Film filmSelectionne = trouverFilmParNom(nomFilm);

            if (filmSelectionne != null) {
                String numeroSalleInput = JOptionPane.showInputDialog("Numero de la salle : ");

                if (isNumeric(numeroSalleInput)) {
                    int numeroSalle = Integer.parseInt(numeroSalleInput);
                    SalleCinema salleSelectionnee = trouverSalleParNumero(numeroSalle);

                    if (salleSelectionnee != null) {
                        String date = JOptionPane.showInputDialog("Date de la projection (JJ-MM-AAAA) : ");

                        if (date != null && !date.isEmpty()) {
                            // Check if heureDebut is provided
                            String heureDebut = JOptionPane.showInputDialog("Heure de debut de la projection : ");
                            if (heureDebut != null && !heureDebut.isEmpty()) {

                                // Check if heureFin is provided
                                String heureFin = JOptionPane.showInputDialog("Heure de fin de la projection : ");
                                if (heureFin != null && !heureFin.isEmpty()) {

                                    // Projection creation
                                    Diffusion nouvelleProjection = new Diffusion(filmSelectionne, salleSelectionnee, date, heureDebut, heureFin);
                                    projections.add(nouvelleProjection);
                                    modeleListeProjections.addElement(nouvelleProjection.toString());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Opération annulée : Heure de fin non spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Opération annulée : Heure de debut non spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opération annulée : Date non spécifiée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Salle non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Le numéro de salle doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Film non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    
    
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    
    private void modifierDiffusion() {
        String nomFilm = JOptionPane.showInputDialog("Nom du film de la diffusion à modifier : ");
    
        if (nomFilm != null && !nomFilm.isEmpty()) {
            Film filmSelectionne = trouverFilmParNom(nomFilm);
    
            if (filmSelectionne != null) {
                String numeroSalleInput = JOptionPane.showInputDialog("Numero de la salle de la diffusion à modifier : ");
    
                if (isNumeric(numeroSalleInput)) {
                    int numeroSalle = Integer.parseInt(numeroSalleInput);
                    SalleCinema salleSelectionnee = trouverSalleParNumero(numeroSalle);
    
                    if (salleSelectionnee != null) {
                        String date = JOptionPane.showInputDialog("Date de la projection (JJ-MM-AAAA) à modifier : ");
                        Diffusion diffusionAModifier = trouverDiffusion(filmSelectionne, salleSelectionnee, date);
    
                        if (diffusionAModifier != null) {
                            String nouvelleHeureDebut = JOptionPane.showInputDialog("Nouvelle heure de début de la projection : ");
                            String nouvelleHeureFin = JOptionPane.showInputDialog("Nouvelle heure de fin de la projection : ");
    
                            if (nouvelleHeureDebut != null && !nouvelleHeureDebut.isEmpty() && nouvelleHeureFin != null && !nouvelleHeureFin.isEmpty()) {
                                // Set new values for heureDebut and heureFin
                                diffusionAModifier.setHeureDebut(nouvelleHeureDebut);
                                diffusionAModifier.setHeureFin(nouvelleHeureFin);
    
                                // Update the list of projections
                                modeleListeProjections.clear();
                                for (Diffusion diffusion : projections) {
                                    modeleListeProjections.addElement(diffusion.toString());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Opération annulée : Les heures de début et de fin ne peuvent pas être vides.", "Information", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Diffusion non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Salle non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Le numéro de salle doit être un nombre entier.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Film non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    private void supprimerDiffusion() {
        String nomFilm = JOptionPane.showInputDialog("Nom du film de la diffusion à supprimer : ");
    
        if (nomFilm != null && !nomFilm.isEmpty()) {
            Film filmSelectionne = trouverFilmParNom(nomFilm);
    
            if (filmSelectionne != null) {
                String numeroSalleInput = JOptionPane.showInputDialog("Numero de la salle de la diffusion à supprimer : ");
    
                if (numeroSalleInput != null && !numeroSalleInput.isEmpty()) {
                    int numeroSalle = Integer.parseInt(numeroSalleInput);
                    SalleCinema salleSelectionnee = trouverSalleParNumero(numeroSalle);
    
                    if (salleSelectionnee != null) {
                        String date = JOptionPane.showInputDialog("Date de la projection (JJ-MM-AAAA) à supprimer : ");
    
                        if (date != null && !date.isEmpty()) {
                            Diffusion diffusionASupprimer = trouverDiffusion(filmSelectionne, salleSelectionnee, date);
    
                            if (diffusionASupprimer != null) {
                                projections.remove(diffusionASupprimer);
                                modeleListeProjections.removeElement(diffusionASupprimer.toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "Diffusion non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Salle non trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Film non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private Diffusion trouverDiffusion(Film film, SalleCinema salleCinema, String date) {
        for (Diffusion diffusion : projections) {
            if (diffusion.getFilm().equals(film) &&
                diffusion.getSalleCinema().equals(salleCinema) &&
                diffusion.getDate().equals(date)) {
                return diffusion;
            }
        }
        return null;
    }
    
    private void afficherDiffusions() {
        StringBuilder diffusionsList = new StringBuilder("Liste des diffusions :\n");
        for (Diffusion diffusion : projections) {
            diffusionsList.append(diffusion.toString()).append("\n");
        }
    
        JOptionPane.showMessageDialog(null, diffusionsList.toString(), "Liste des diffusions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reserverBillet() {
        if (projections.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune projection disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Diffusion projectionSelectionnee = choisirProjectionDisponible();
    
        if (projectionSelectionnee != null) {
            double prix = demanderPrixBillet();
            Billet nouveauBillet = new Billet(billets.size() + 1, prix, projectionSelectionnee);
            billets.add(nouveauBillet);
            modeleListeBillets.addElement(nouveauBillet.toString());
            projectionSelectionnee.decrementerPlacesDisponibles();
            JOptionPane.showMessageDialog(null, "Reservation effectuee avec succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            JOptionPane.showMessageDialog(null, "Aucun billet !.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Diffusion choisirProjectionDisponible() {
        List<Diffusion> projectionsDisponibles = new ArrayList<>();
        for (Diffusion projection : projections) {
            if (projection.getPlacesDisponibles() > 0) {
                projectionsDisponibles.add(projection);
            }
        }
    
        if (projectionsDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune projection disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    
        String[] options = projectionsDisponibles.stream().map(Diffusion::toString).toArray(String[]::new);
    
        String choix = (String) JOptionPane.showInputDialog(
                null,
                "Choisir une projection :",
                "Choix de la projection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
    
        if (choix != null) {
            return projectionsDisponibles.stream()
                    .filter(projection -> projection.toString().equals(choix))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }
    
    private double demanderPrixBillet() {
        String prixStr = JOptionPane.showInputDialog("Prix du billet : ");
        
        if (prixStr != null && !prixStr.trim().isEmpty()) {
            try {
                return Double.parseDouble(prixStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un prix valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return demanderPrixBillet(); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "Opération annulée.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return demanderPrixBillet(); 
        }
    }
    

    private void annulerBillet() {
        if (billets.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun billet reserve.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Billet billetSelectionne = choisirBillet();
    
        if (billetSelectionne != null) {
            Diffusion projectionAssociee = billetSelectionne.getProjection();
    
            billets.remove(billetSelectionne);
            modeleListeBillets.removeElement(billetSelectionne.toString());
    
            projectionAssociee.incrementerPlacesDisponibles();
    
            JOptionPane.showMessageDialog(null, "Annulation de billet effectuee avec succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Aucun billet sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Billet choisirBillet() {
        if (billets.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun billet reserve.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    
        String[] options = billets.stream().map(Billet::toString).toArray(String[]::new);
    
        String choix = (String) JOptionPane.showInputDialog(
                null,
                "Choisir un billet à annuler :",
                "Annulation de billet",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
    
        if (choix != null) {
            return billets.stream()
                    .filter(billet -> billet.toString().equals(choix))
                    .findFirst()
                    .orElse(null);
        } else {
            return null;
        }
    }    

    private void afficherBilletsRestantsPourDiffusion(Diffusion diffusion) {
        if (diffusion != null) {
            int placesDisponibles = diffusion.getPlacesDisponibles();
            JOptionPane.showMessageDialog(
                null,
                "Nombre de billets restants pour la projection :\n" + placesDisponibles,
                "Billets restants",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Diffusion non trouvee.",
                "Erreur",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SystemeGestionCinema();
            }
        });
    }
}
