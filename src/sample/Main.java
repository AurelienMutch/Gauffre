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

    private static Stage MainStage;
	private static int nb_case_x = 20;
	private static int nb_case_y = 20;
	private static int taille_case_long = 32;
	private static int taille_case_larg = 29;

	public int type_joueur2;
	public int scene_type = 0;
	
	
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
					grid[i][j]= new Case(i,j,"P");
                    grid[i][j].get_b().getStyleClass().add("b-style1");
				}
				else if(gauffrette.get_val(i, j) == 1){
					grid[i][j]= new Case(i,j,"S");
                    grid[i][j].get_b().getStyleClass().add("b-style2");
				}
				else{
					grid[i][j]= new Case(i,j,"M");
                    grid[i][j].get_b().getStyleClass().add("b-style3");
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

   public Scene welcomePage()
   {
       Scene scenee = null;
       GridPane menu = new GridPane();
       GridPane menu1 = new GridPane();
       GridPane menu2 = new GridPane();
       Button play = new Button("Jouer");
       Button sortir = new Button("quit");

       play.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
       @Override
       public void handle(MouseEvent e) {
           scene_type = 2;

           afficheStage();

       }});

       menu1.setAlignment(Pos.TOP_CENTER);
       menu1.setAlignment(Pos.BOTTOM_CENTER);
       menu.setAlignment(Pos.CENTER);
       menu1.add(play, 4, 6);
       menu2.add(sortir, 4, 8);
       menu.add(menu1, 0, 1);
       menu.add(menu2, 1, 1);
       scenee = new Scene(menu, 300, 200);
       return scenee;
   }

   public Scene jouerPage()
   {
       Scene scenee = null;
       GridPane gridpane = new GridPane();
       gridpane.setHgap(2);
       gridpane.setVgap(2);
       remplir_gridpane(gridpane);
       scenee = new Scene(gridpane, taille_fenetre_x, taille_fenetre_y);
       return scenee;
   }

   public Scene SelectModePage()
   {
       Scene scenee = null;

       String[] Modes = {"Humain contre IA","Humain contre Humain"};

       GridPane Menus = new GridPane();

       for(int i = 0;i< Modes.length; i++) {
           int IndexGrid = i;
           GridPane SubMenu = new GridPane();

           Button SubButton = new Button(Modes[i]);

           SubButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
               @Override
               public void handle(MouseEvent e) {

                   if (IndexGrid == 0) {
                       scene_type = 3;
                   } else {
                       scene_type = 1;
                   }
                   afficheStage();
               }});

           SubMenu.add(SubButton,4,6);
           Menus.add(SubMenu,0,i,2,1);
       }

       Menus.setVgap(10);
       Menus.setAlignment(Pos.CENTER);
       scenee = new Scene(Menus, 300, 200);
       return scenee;
   }

   public Scene SelectLevelPage()
   {
       Scene scenee = null;

       String[] Levels = {"Débutant","Autonome","Avancé"};

       GridPane Menus = new GridPane();

       for(int i=0;i < Levels.length; i++) {

           int level = i;
           GridPane SubMenu = new GridPane();

           Button SubButton = new Button(Levels[i]);

           SubButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
               @Override
               public void handle(MouseEvent e) {

                   type_joueur2 = level;

                   scene_type =1;

                   afficheStage();

               }});

           SubMenu.add(SubButton,4,6);
           Menus.add(SubMenu,0,i,2,1);
       }

       Menus.setVgap(10);
       Menus.setAlignment(Pos.CENTER);
       scenee = new Scene(Menus, 300, 200);
       return scenee;
   }

   public void afficheStage()
   {
       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       MainStage.setTitle("La Gauffre !!");
       Scene scenee = null;

       switch(scene_type) {
           case 0:
               scenee =  welcomePage();
               break;
           case 1:
               scenee =  jouerPage();
               break;
           case 2:
               scenee =  SelectModePage();
               break;
           case 3:
               scenee =  SelectLevelPage();
               break;
       }

       scenee.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
       MainStage.setScene(scenee);
       MainStage.show();
   }
	
   public void start(Stage primaryStage) throws Exception{
        MainStage = primaryStage;

       afficheStage();
    }
}
