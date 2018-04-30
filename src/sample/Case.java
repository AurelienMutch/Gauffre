package sample;

import javafx.scene.control.Button;

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
