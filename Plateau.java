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

// Représentation logique du plateau de jeu
class Plateau {
    boolean enCours;2
    int [][] plateau;
    int libre;

    Plateau() {
        plateau = new int[3][3];
        libre = 9;
        enCours = true;
        for (int i=0; i<plateau.length; i++)
            for (int j=0; j<plateau[0].length; j++)
                plateau[i][j] = -1;
    }
    
    boolean libre(int i, int j) {
        return plateau[i][j] == -1;
    }
    
    void jouer(int n, int i, int j) {
        plateau[i][j] = n;
        libre--;
        boolean vertical = true, horizontal=true, slash=true, antiSlash=true;
        for (int p=0; p<plateau.length; p++) {
            horizontal = horizontal && (plateau[i][p] == n);
            vertical = vertical && (plateau[p][j] == n);
            slash = slash && (plateau[p][p] == n);
            antiSlash = antiSlash && (plateau[p][plateau.length-p-1] == n);
        }
        enCours = !(horizontal || vertical || slash || antiSlash) && (libre > 0);
    }
    
    int valeur(int i, int j) {
        return plateau[i][j];
    }
    
    boolean enCours() {
        return enCours;
    }
    
    int largeur() {
        return plateau[0].length;
    }
    
    int hauteur() {
        return plateau.length;
    }
}