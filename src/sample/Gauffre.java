package sample;

public class Gauffre {

	    int[][] table;
	    int hauteur;
	    int largeur;



	
	public Gauffre(int h, int l) {
		this.hauteur=h;
        this.largeur=l;
        this.table = new int[h][l];
        for (int i=0; i< h; i++) {
            for (int j = 0; j < l; j++) {
                this.table[i][j] = 1;
            }
        }
        this.table[0][0]=0;
	}
	
	public int get_val(int i, int j){
		return this.table[i][j];
	}

}
