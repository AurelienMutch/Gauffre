package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public interface Affichage {

	 public void afficheStage()
	   {
	       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
	       Main.MainStage.setTitle("La Gauffre !!");
	       Scene scenee = null;

	       switch(scene_type) {
	           case 0:
	               scenee =  welcomePage();
	               break;
	           case 1:
	               scenee =  jouerPage();
	               break;
	       }
	
	
	       public Scene jouerPage()
	       {
	           Scene scenee = null;
	           VBox vb= new VBox();
	           HBox hb2 = new HBox();
	           hb2.setSpacing((Main.taille_fenetre_x/4));
	           HBox hb = new HBox();
	           hb.setSpacing((taille_fenetre_x/5));
	           GridPane gridpane = new GridPane();
	           gridpane.setHgap(2);
	           gridpane.setVgap(2);
	           remplir_gridpane(gridpane);
	           Button button_precedent = new Button("Précédent");
	           Label lb1 = new Label(nom_joueur1);
	           Label lb2 = new Label(nom_joueur2);
	           Button button_suivant = new Button("Suivant");
	           hb.getChildren().add(button_precedent);
	           hb.getChildren().add(lb1);
	           hb.getChildren().add(lb2);
	           hb.getChildren().add(button_suivant);
	           Button button_Menu = new Button("Menu");
	           button_Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
	               @Override
	               public void handle(MouseEvent e) {
	                   scene_type = 0;
	                   gauffrette.restart();
	                   remplirGridTable();
	                   afficheStage();

	               }});
	           Button button_Sauvegarde = new Button("Sauvegarder");
	           Button button_quitter = new Button("Quitter");
	           button_quitter.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
	               @Override
	               public void handle(MouseEvent e) {
	                   System.exit(0);
	               }});
	           hb2.getChildren().add(button_Menu);
	           hb2.getChildren().add(button_Sauvegarde);
	           hb2.getChildren().add(button_quitter);
	           vb.getChildren().add(hb);
	           vb.getChildren().add(gridpane);
	           vb.getChildren().add(hb2);
	           scenee = new Scene(vb, taille_fenetre_x, taille_fenetre_y);
	           return scenee;
	       }
	
	
	       
	
	
}
