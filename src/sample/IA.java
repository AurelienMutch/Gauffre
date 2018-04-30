package sample;
import java.util.ArrayList;
import java.util.Random;

public class IA extends Joueur{
	int difficulte;	// 0 pour facile; 1 pour moyen; 2 pour difficile
	public IA(String s, int t, int d){
		super(s,t);
		this.difficulte=d;
	}
	
    public Case coup_jouer(Gauffre P) {
    	switch(this.difficulte){
	    	case 0: return coup_random(P);	// IA facile
	    	case 1: if(coup_gagnant(P)!=null){	//IA moyen
	    			return coup_gagnant(P);
	    		}else{
	    			ArrayList<Case> caseUtilisable = this.case_utilisable(P);
	    			ArrayList<Case> coupPerdant = this.coup_perdant(P);
	    			ArrayList<Case> coupNonPerdant = new ArrayList<Case>();
	    			boolean estPerdant;
	    			for(Case u : caseUtilisable){	// On calcul quels cases sont libre et ne sont pas des coups perdant
	    				estPerdant = false;
	        			for(Case p : coupPerdant){
	        				if(u.i==p.i && u.j == p.j){
	        					estPerdant = true;
	        				}
	        			}
	        			if(!estPerdant){
	        				coupNonPerdant.add(u);
	        			}
	    			}
	    			if(coupNonPerdant.isEmpty()){	//Tous les coups sont perdants
	    				return caseUtilisable.get(caseUtilisable.size());	
	    			}else{	// On renvoie un coup qui ne perds pas
	    		    	Random gen = new Random();
	    		    	int c = gen.nextInt(coupNonPerdant.size());
	    		    	return coupNonPerdant.get(c);
	    			}
	    		}
	    	default:
	    		return null;
    	}    	
    }
    
    public Case coup_random(Gauffre P){		// Renvoie une case libre choisie aléatoirement, ou la case posion si c'est la seule libre
    	ArrayList<Case>caseUtilisable = this.case_utilisable(P);
    	if(caseUtilisable.isEmpty()){
    		return new Case(0,0,null);
    	}
    	Random gen = new Random();
    	int c = gen.nextInt(caseUtilisable.size());
    	return caseUtilisable.get(c);
    }

    public Case coup_gagnant(Gauffre P){
    	
    	// Cas evident: victoire en 1 trour
    	if(P.table[0][1]==2 && P.table[1][0]!=2){	
    		return new Case(1,0,null);
    	}else if(P.table[1][0]==2 && P.table[0][1]!=2){
    		return new Case(0,1,null);
    		
        // Cas assez evident: victoire en 2 tour	    		
    	}else if((P.table[0][2]==2 && P.table[0][1]!=2) && (P.table[3][0]==2 && P.table[2][0]!=2)){    	
    		return new Case(2,0,null);
    	}else if((P.table[2][0]==2 && P.table[1][0]!=2) && (P.table[0][3]==2 && P.table[0][3]!=2)){    		
    		return new Case(0,2,null);
    		
        // Pas de coups gagnat evident    		
    	}else{ 			
        	return null;
    	}
    }
    
    public ArrayList<Case> coup_perdant(Gauffre P){
    	ArrayList<Case>  coupPerdant = new ArrayList<Case>();
    	coupPerdant.add(new Case(0,0,null));	// Le posion est toujours un coup perdant
    	if(P.table[0][1]!=2 && P.table[1][0]!=2){	//  coup perdant evidant
    		coupPerdant.add(new Case(0,1,null));
    		coupPerdant.add(new Case(1,0,null));
    	}else if((P.table[0][1]!=2 && P.table[0][2]==2) && P.table[3][0]!=2){	// Coup perdant en deux tour
    		coupPerdant.add(new Case(3,0,null));
    	}else if((P.table[1][0]!=2 && P.table[2][0]==2) && P.table[0][3]!=2){
    		coupPerdant.add(new Case(0,3,null));
    	}
    	return coupPerdant;
    }
    
    public ArrayList<Case> case_utilisable(Gauffre P){	// La case est cliquable
    	ArrayList<Case> caseUtilisable = new ArrayList<Case>();
    	for(int i=0;i<P.largeur;i++){
    		for(int j=0;j<P.hauteur;j++){
    			if(P.table[i][j]!=2){
    				caseUtilisable.add(new Case(i,j,null));
    			}
    		}
    	}
    	return caseUtilisable;
    }
    
    @Override
    int temporisation() { return 1500; }


}
