package sample;



public class Plateau {
    int[][] plateau;
    int hauteur;
    int largeur;

    Plateau(int h, int l){
        this.hauteur=h;
        this.largeur=l;
        this.plateau = new int[h][l];
        this.plateau[0][0]=0;
        for (int i=1; i< h; i++) {
            for (int j = 1; j < l; j++) {
                this.plateau[i][j] = 1;
            }
        }
    }









}
