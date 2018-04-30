package sample;

abstract class Joueur {
	public String name;
	public int type;
	
	
	public Joueur(String name, int type){
		this.name = name;
		this.type = type;
	}


    //renvoie le temp a attendre par la machine pour simuler la reflexion de l'homme
    //pour un joueur humain le temps est 0
    //pour un joueur machine le temps est a definir dans la fonction de la classe heritante
    int temporisation(){
        return 0;
    }





}
