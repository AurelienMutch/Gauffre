/*
 * Morpion pédagogique
 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import java.security.InvalidParameterException;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class Morpion extends Application {
    Plateau plateau;
    Canvas c;
    Joueur [] joueurs;
    int joueurCourant;
    long echeance;

    void dessin() {
        double width = c.getWidth();
        double height = c.getHeight();
        int lignes = plateau.hauteur();
        int colonnes = plateau.largeur();
        GraphicsContext g = c.getGraphicsContext2D();
        
        g.clearRect(0, 0, c.getWidth(), c.getHeight());
        if (!plateau.enCours())
            g.strokeText("Fin", 20, c.getHeight()/2);
        for (int i=1; i<lignes;i++) {
            g.strokeLine(0, i*height/lignes, width, i*height/lignes);
            g.strokeLine(i*width/lignes, 0, i*width/lignes, height);
        }
        for (int i=0; i<lignes; i++)
            for (int j=0; j<colonnes; j++)
                switch (plateau.valeur(i, j)) {
                    case 0:
                        g.strokeOval(j*width/lignes, i*height/lignes, width/lignes, height/lignes);
                        break;
                    case 1:
                        g.strokeLine(j*width/lignes, i*height/lignes, (j+1)*width/lignes, (i+1)*height/lignes);
                        g.strokeLine(j*width/lignes, (i+1)*height/lignes, (j+1)*width/lignes, i*height/lignes);
                        break;
                }
    }

    // Ce programme, par soucis de concision, n'est pas découpé en paquetages,
    // mais les 3 fonctions suivantes font partie du moteur du jeu : aucun lien
    // avec la partie graphique, aucun lien avec le détail de mesure du temps
    int traiteAction(int i, int j) {
        if (plateau.enCours() && joueurs[joueurCourant].jeu(i, j))
            return changeJoueur();
        else
            return 0;
    }
    
    int traiteTemporisation() {
        if (plateau.enCours() && joueurs[joueurCourant].tempsEcoule())
            return changeJoueur();
        else
            return 0;
    }
    
    int changeJoueur() {
        joueurCourant = 1 - joueurCourant;
        return joueurs[joueurCourant].passageDeMain();
    }
    // Fin de la partie plutôt "moteur"
        
    void fixeEcheance(int attente) {
        if (attente > 0)
            echeance = attente*1000000 + System.nanoTime();
        else
            echeance = 0;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        plateau = new Plateau();
        joueurs = new Joueur[2];
        joueurCourant = 0;
        echeance = System.nanoTime();
        
        Iterator<String> it = getParameters().getRaw().iterator();
        for (int i=0; i<joueurs.length; i++) {
            String nature;
            if (it.hasNext())
                nature = it.next();
            else
                nature = "IA";
            switch (nature) {
                case "humain":
                    joueurs[i] = new JoueurHumain(i, plateau);
                    break;
                case "IA":
                    joueurs[i] = new JoueurIA(i, plateau);
                    break;
                default:
                    throw new InvalidParameterException(nature);
            }
        }

        primaryStage.setTitle("Morpion");
        c = new Canvas(400,400);
        
        // Composant de regroupement qui occupe toute la place disponible
        // Le noeud donné en paramètre est placé au centre du BorderPane
        BorderPane b = new BorderPane(c);
        Scene s;
        s = new Scene(b);
        primaryStage.setScene(s);
        primaryStage.show();
        dessin();
        
        c.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (plateau.enCours()) {
                    int i = (int) (e.getY()*plateau.hauteur()/c.getHeight());
                    int j = (int) (e.getX()*plateau.largeur()/c.getWidth());
                    int attente = traiteAction(i, j);
                    fixeEcheance(attente);
                    dessin();
                }
            }
        });
        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long temps) {
                if ((echeance > 0) && (temps > echeance)) {
                    int attente = traiteTemporisation();
                    fixeEcheance(attente);
                    dessin();
                }
            }
        };
        anim.start();
    }

    public static void main(String [] args) {
        launch(args);
    }
}
