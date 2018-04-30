package sample;

public class IA extends Joueur{
	
	public IA(String s, int t){
		super(s,t);
	}
	
    void coup_jouer() {

    }

    @Override
    int temporisation() { return 1500; }


}
