package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	
	public static int WIDTH = 120;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//setar as dimenss�es da tela
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-5);
		enemy = new Enemy(100, 0);
		ball = new Ball(100, HEIGHT/2 - 1);
		
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		//entre parenteses vai ser o nome da janela
		JFrame frame = new JFrame("Pong");
		//n�o poder redimensionar a janela
		frame.setResizable(false);
		//realmente fechar quando clicar no x
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		//centralizar a tela
		frame.setLocationRelativeTo(null);
		//dar imagem de tela
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	//colocar as logicas que est�o dentro do tick de cada classe referente
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}

	
	public void run() {
		while(true){
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}


	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
			
		}
		
	}


	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
			
		}
		
		
	}


	
	public void keyTyped(KeyEvent e) {
		
		
	}

}
