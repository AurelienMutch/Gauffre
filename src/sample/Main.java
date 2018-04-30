package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

public class Main extends Application {

	private static int nb_case_x = 20;
	private static int nb_case_y = 20;
	private static int taille_case_long = 50;
	private static int taille_case_larg = 30;
	
	public int type_joueur2;
	public int scene_type = 1;
	
	
	private static int taille_fenetre_x= nb_case_x*taille_case_long;
	private static int taille_fenetre_y = nb_case_y*taille_case_larg;
	
	
	//private static int taille_case_x = (int)taille_fenetre_x/nb_case_x;
	//private static int taille_case_y = (int)taille_fenetre_y/nb_case_y;
	
	
	private static Gauffre gauffrette= new Gauffre(nb_case_x, nb_case_y);
	private static Case[][] grid= new Case[nb_case_x][nb_case_y];
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        remplirGridTable();
		launch(args);
	}
	
	public static void remplirGridTable(){
		for (int i=0; i<nb_case_x;i++){
			for(int j=0; j<nb_case_y;j++){
				if(gauffrette.get_val(i, j) == 0){
					grid[i][j]= new Case(i,j,"poisson");
				}
				else if(gauffrette.get_val(i, j) == 1){
					grid[i][j]= new Case(i,j,"Sain");
				}
				else{
					grid[i][j]= new Case(i,j,"pas bien");
				}
			}
		}
	}
	
	public void remplir_gridpane(GridPane gridpane){
		for(int i =0; i<nb_case_x;i++){
    		for(int j=0; j<nb_case_y;j++){
				
				int x=i;
				int y= j;
    			gridpane.add(grid[i][j].get_b(),i,j,1,1);
    			grid[i][j].get_b().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
    				@Override
    	            public void handle(MouseEvent e) {
    					gauffrette.transforme(grid[x][y].get_x(),grid[x][y].get_y());
    					System.out.println("super");
    					gridpane.getChildren().clear();
    					remplirGridTable();
    					remplir_gridpane(gridpane);
    				
    			}});
    		}
    	}
	}
	
   public void start(Stage primaryStage) throws Exception{
       	//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("La Gauffre !!");
        Scene scenee = null;
        if(scene_type ==0){
        	GridPane menu = new GridPane();
        	GridPane menu1 = new GridPane();
        	GridPane menu2 = new GridPane();
        	Button play = new Button("Jouer");
        	Button sortir = new Button("quit");
        	menu1.setAlignment(Pos.TOP_CENTER);
        	menu1.setAlignment(Pos.BOTTOM_CENTER);
        	menu.setAlignment(Pos.CENTER);
        	menu1.add(play, 4, 6);
        	menu2.add(sortir, 4, 8);
        	menu.add(menu1, 0, 1);
        	menu.add(menu2, 1, 1);
        	scenee = new Scene(menu, 300, 200);
        	primaryStage.setScene(scenee);
            primaryStage.show();
        	
        }
        else if (scene_type == 1){
        	GridPane gridpane = new GridPane();
        	remplir_gridpane(gridpane);

        	
        	scenee = new Scene(gridpane, taille_fenetre_x, taille_fenetre_y);
        }
        primaryStage.setScene(scenee);
        primaryStage.show();
    }
}
