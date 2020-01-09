import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	private int xCoor, yCoor, width, height;
	
	public Snake(int xCoor, int yCoor, int tileSize) { // kordinates ir gyvates ilgis 
		this.xCoor =xCoor;
		this.yCoor =yCoor;
		width = tileSize;
		height = tileSize;
	}
	
	public void draw(Graphics g) { //piesiam gyvate
		g.setColor(Color.blue);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		
	}
	
	
	//getters and setters
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
