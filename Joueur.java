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

// Classe commune à tous les joueurs : IA ou humain
// L'idée est que, en ayant la même interface, tous les joueurs sont traités de la même
// manière par le moteur de jeu. C'est plus simple et permet toutes les combinaisons.
//
// Tous les joueurs ont donc potentiellement la possibilité de :
// - provoquer une temporisation (utilisé dans une IA)
// - tenir compte d'une temporisation écoulée (utilisé dans une IA)
// - tenir compte d'un coup joué à la souris(utilisé par un joueur humain)
abstract class Joueur {
    Plateau plateau;
    int num;
    
    Joueur(int n, Plateau p) {
        num = n;
        plateau = p;
    }
    protected void placerCoup(int i, int j) {
        
    }
    // Méthode appelée lorsque le tour d'un joueur vient
    // renvoie un temps à attendre en millisecondes ou 0 si aucune temporisation requise
    int passageDeMain() {
        return 0;
    }
    // Méthode appelée une fois le temps écoulé
    boolean tempsEcoule() {
        return false;
    }
    // Méthode appelée lors d'un clic sur le plateau
    boolean jeu(int i, int j) {
        return false;
    }
}