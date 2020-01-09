import javax.swing.JFrame;

public class Main {

	public Main() {
		JFrame frame = new JFrame();
		Gameplay gameplay = new Gameplay();
		
		frame.add(gameplay);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake by Danielius Miciulis");
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);//per viduri ekrano
		//frame.setResizable(false); !!!!!!!iskraipo langa!!!!!!!!????
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
