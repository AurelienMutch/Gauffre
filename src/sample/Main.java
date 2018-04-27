package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	private static int taille_fenetre_x= 400;
	private static int taille_fenetre_y = 400;
	private static int nb_case_x = 20;
	private static int nb_case_y = 20;
	
	//private static int taille_case_x = (int)taille_fenetre_x/nb_case_x;
	//private static int taille_case_y = (int)taille_fenetre_y/nb_case_y;
	
	
	private static Gauffre gauffrette= new Gauffre(nb_case_x, nb_case_y);
	private static Button[][] grid= new Button[nb_case_x][nb_case_y];
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        remplirGridTable();
		launch(args);
	}
	
	public static void remplirGridTable(){
		for (int i=0; i<nb_case_x;i++){
			for(int j=0; j<nb_case_y;j++){
				if(gauffrette.get_val(i, j) == 0){
					grid[i][j]= new Button("poisson");
				}
				else{
					grid[i][j]= new Button("Sain"); 
				}
			}
		}
	}

   public void start(Stage primaryStage) throws Exception{
       	//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("La Gauffre !!");
        
        GridPane gridpane = new GridPane();

        for(int i =0; i<nb_case_x;i++){
        	for(int j=0; j<nb_case_y;j++){
        		gridpane.add(grid[i][j],i,j,1,1);
        	}
        }
        Scene scenee = new Scene(gridpane, taille_fenetre_x, taille_fenetre_y);
        primaryStage.setScene(scenee);
        primaryStage.show();
    }
}
