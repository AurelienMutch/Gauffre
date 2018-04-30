package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Case {
	public Button button;
	public int i,j;
	
	public Case(int x, int y, String s){
		this.i = x;
		this.j = y;
		this.button = new Button(s);
		// TODO Auto-generated constructor stub
	}
	
	public Button get_b(){
		return this.button;
	}
	public int get_x(){
		return this.i;
	}
	public int get_y(){
		return this.j;
	}
}
