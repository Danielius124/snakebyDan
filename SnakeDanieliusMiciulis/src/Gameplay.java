import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Gameplay extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 500, HEIGHT = 500; // lauko matmenys

	private Thread gija; //gija

	private boolean run;
	private boolean right = true, left = false, up = false, down = false;
	
	private Snake s;
	private ArrayList<Snake> snake;
	
	private Food f; 
	private ArrayList<Food> food;
	
	private int score = 0; //taskams
	
	private Random r; // random food deliojimas

	private int xCoor = 10, yCoor = 10, size = 10;

	private int ticks = 0;

	public Gameplay() {
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);

		snake = new ArrayList<Snake>();
		food = new ArrayList<Food>();
		
		r = new Random();
		
		start();
	}

	public void start() {
		run = true;
		gija = new Thread(this);
		gija.start();
	}

	public void stop() throws InterruptedException {
		run = false;
	}

	public void tick() throws InterruptedException {
		if (snake.size() == 0) {
			s = new Snake(xCoor, yCoor, 10);
			snake.add(s);
		}

		ticks++;
		if(ticks > 600000) { // zaidimo greitis
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down)yCoor++;
			ticks = 0;
			
			s = new Snake(xCoor, yCoor, 10);
			snake.add(s);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
		if(food.size() == 0) { // maisto idejimas kai array listas tuscias
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);
			
			f = new Food(xCoor, yCoor, 10);
			food.add(f);
		}
		
		for(int i = 0; i < food.size(); i++) { // maisto suvalgymas jeigu suvalgo is food listo pasalinam i taji nari,
			if(xCoor == food.get(i).getxCoor() && yCoor == food.get(i).getyCoor()) {
				size++;
				food.remove(i);
				i++;
				score++;
				System.out.println("SCORE: " + score);
			}
		}
		if(xCoor < 0) { // perejimas is vienos puses i kita
			xCoor = 49;
		}
		if(xCoor > 49) {
			xCoor = 0;
		}
		if(yCoor < 0) {
			yCoor = 49;
		}
		if(yCoor > 49) {
			yCoor = 0;
		}
		
		for(int i = 0; i < snake.size(); i++) { //ar neliecia savo kuno
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if(i != snake.size()-1) {
					System.out.println("Lost");
					stop();
				}
			}
		}
		
	}

	public void paint(Graphics g) {

		// background
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);

//		for (int i = 0; i < WIDTH / 10; i++) { // vertikalios linijos zemelapiui vienas laukas 10*10pix
//			g.drawLine(i * 10, 0, i * 10, HEIGHT);
//		}
//		for (int i = 0; i < HEIGHT / 10; i++) { // horizontalios linijos zemelapiui
//			g.drawLine(0, 10 * i, HEIGHT, 10 * i);
//		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		for(int i = 0; i < food.size(); i++) {
			food.get(i).draw(g);
		}
		
	}

	@Override
	public void run() {
		while (run) {
			try {
				tick();
			} catch (InterruptedException e) {
		}
			repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent k) { // pasitikrinu kuris buvo nuspaustas
		int key = k.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			right = false;
			left = false;
			up = true;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			right = false;
			left = false;
			down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
