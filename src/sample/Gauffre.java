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
	
	public void restart(){
		for (int i=0; i< this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                this.table[i][j] = 1;
            }
        }
        this.table[0][0]=0;
	}
	
	
	public int get_l(){
		return this.largeur;
	}
	public int get_h(){
		return this.hauteur;
	}
	
	public int get_val(int i, int j){
		return this.table[i][j];
	}
	
	public void transforme(int x, int y){
		for (int i=x; i<hauteur;i++){
			for (int j=y; j<largeur;j++){
				this.table[i][j] = 2;
			}
		}
	}
	
	
	public void initialiseFromVect(Vector Vect){
		int h = this.hauteur;
		int compt = h;
		int l= this.largeur;
		int l2=0;
		for(int i=0;i<l+h;i++){
			if (Vect.get_val(i)){
				for(int j=0;j<h;j++){
					this.table[l2][j]=1;
				}
				for(int y=h;y<hauteur;y++){
					this.table[l2][y]=2;
				}
			}else{
				h=h-1;
			}
		}
	}
	
	
	
}
