package sample;

abstract class Joueur {

    //renvoi le coup jouer par le joueur courrant
     coup_jouer(Plateau p){}



    //renvoie le temp a attendre par la machine pour simuler la reflexion de l'homme
    //pour un joueur humain le temps est 0
    //pour un joueur machine le temps est a definir dans la fonction de la classe heritante
    int temporisation(){
        return 0;
    }





}
