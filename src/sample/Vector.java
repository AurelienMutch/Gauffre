package sample;

public class Vector {
	boolean[] Vect;
	int size;
	
	
	
	
	public Vector(Gauffre g) {
		int l = g.get_l();
		int h = g.get_h();
		int taille = h + l;
		this.size= taille;
		int currentlargeur = 0;
		this.Vect = new boolean[taille];
		for(int i = 0; i<taille;i++){
			if(l != 0){
				if(h !=0){
			if (g.get_val(h, currentlargeur)!=2){
				this.Vect[i]=false;
				h=h-1;
			}
			else{
				this.Vect[i]= true;
				currentlargeur =+ 1;
				l = l-1;
			}}
				else{
					this.Vect[i]= true;
				}
		}else{
			this.Vect[i]= false;
		}
			}	
				// TODO Auto-generated constructor stub
	}
	
	
	public int get_size(){
		return this.size;
	}
	public boolean get_val(int i){
		return this.Vect[i];
	}
	

}
