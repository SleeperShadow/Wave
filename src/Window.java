import java.awt.*;
import javax.swing.*;
public class Window extends Canvas{
	private static final long serialVersionUID=-240840600533728354L;
	
	public Window(int width,int height, String title, Base game) {
		JFrame frame= new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		game.requestFocus();
		
	}
}
