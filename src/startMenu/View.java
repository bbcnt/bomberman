package startMenu;
import java.awt.*;// pour la gestion de la fenetre
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

import role.*;
import startMenu.Model.ScreenObservers;
import startMenu.Model.ServerIpObservers;

public class View extends JFrame implements Observer {
	 
	
	private Container container = getContentPane();
	private ComboPanel comboPanel = new ComboPanel();
	private JComboBox comboPanel2 = new JComboBox();
	private JButton bouton = new JButton("Rejoindre");
	private JLabel serverStatusMessages = new JLabel("servermessages");
	private JButton bouton1 = new JButton("Créer");
	private JButton bouton2 = new JButton("Lancer");
	private JLabel titre = new JLabel("BOMBERMAN");
	private JLabel ipAdress = new JLabel("IP: xxx.xxx.xxx");
	private JLabel ipserver = new JLabel("IP Serveur:");
	private JLabel portserver = new JLabel("Port:");
	private Font titreFont = new Font ("Serif", Font.BOLD | Font.ITALIC,64);
	private Font ipFont = new Font ("Serif", Font.BOLD | Font.ITALIC,40);
   private String filename = new String("Bomberman2.jpg");
   private JTextField iptext = new JTextField(10);
   private JTextField porttext = new JTextField(10);
   private JPanel[] panel = new JPanel[5];
   
   private int pokeCounter = 0;
   private Model model;


	public View(final Model model) {
	   this.model = model;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimEcran = tk.getScreenSize(); 
		//permet de definir la taille et la position de la fenetre
		setBounds((dimEcran.width -500)/2, (dimEcran.height - 600)/2, 500, 600);
		setVisible(true);//permet de rendre visible la fenetre
		setResizable(false);
		
		setTitle("BomberMan");// definir le titre
		titre.setHorizontalAlignment(JLabel.RIGHT);
		titre.setFont(titreFont);
	    titre.setForeground(Color.magenta);
		ipAdress.setFont(ipFont);
		ipAdress.setForeground(Color.magenta);
		ipserver.setForeground(Color.magenta);
		portserver.setForeground(Color.magenta);
			try {
				titre.setIcon(new ImageIcon(ImageIO.read( new File("logo.gif") ) ));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	

		bouton.setPreferredSize(new Dimension(100, 40));
		bouton.setEnabled(true);
		bouton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (model.getRole().getConnected()) {
               model.getRole().send("POKE" + pokeCounter++);
            } else {
               ((ClientRole)model.getRole()).connectServer(iptext.getText(), Integer.valueOf(porttext.getText()));
            }
         }
		});
		bouton1.setPreferredSize(new Dimension(100, 40));
		bouton1.setEnabled(true);
		bouton1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            if (model.getRole().getConnected()) {
               model.getRole().send("POKE" + pokeCounter++);
            } else {
               ((ServerRole)model.getRole()).startServer(Integer.valueOf(porttext.getText()));
            }
         }
		});
		bouton2.setPreferredSize(new Dimension(100, 40));
		bouton2.setEnabled(false);
		
		ImagePanel backPanel = new ImagePanel(filename);
		backPanel.setLayout(new GridLayout(6, 1));//position du bouton
	   
		comboPanel2.addItem("--");
		comboPanel2.setEditable(true);
		comboPanel2.setMaximumRowCount(4);
		
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		panel[2] = new JPanel();
		panel[3] = new JPanel();
		panel[4] = new JPanel();
        
		panel[0].setOpaque(false);
		panel[1].setOpaque(false);
		panel[2].setOpaque(false);
		panel[3].setOpaque(false);
		panel[4].setOpaque(false);
		
		panel[3].setVisible(false);
		panel[4].setVisible(false);
		
		panel[0].add(titre);
		panel[1].add(ipAdress);
		panel[2].add(comboPanel);

		backPanel.add(panel[0]);
		backPanel.add(panel[1]);
		backPanel.add(panel[2]);
		backPanel.add(panel[3]);
		backPanel.add(panel[4]);
       
		this.setContentPane(backPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		this.repaint();
		
	}

	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }

		}
	 

	public class ComboPanel extends JPanel {
		private String choices[] = { "--","Client", "Serveur"};
		private JComboBox combo = new JComboBox();
		public ComboPanel(){
			
			for(int i = 0; i < choices.length; i++)
				combo.addItem(choices[i]);
			combo.setEditable(false);
			add(combo);
			combo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(combo.getSelectedItem().toString() == choices[1]){
						panel[3].setVisible(true);
						panel[4].setVisible(true);
						
						panel[3].remove(bouton1);
						panel[3].remove(bouton2);
						panel[4].remove(portserver);
						panel[4].remove(porttext);
						panel[4].remove(comboPanel2);
						
						panel[3].add(bouton);
						panel[4].add(ipserver);
						panel[4].add(iptext);
						panel[4].add(portserver);
						panel[4].add(porttext);
						panel[4].validate();
						panel[4].repaint();
						panel[3].validate();
						
						model.setRoleToClient();
						
						panel[3].repaint();
					}
					
					if(combo.getSelectedItem().toString() == choices[2]){
						panel[3].setVisible(true);
						panel[4].setVisible(true);
						
						panel[3].remove(bouton);
						panel[4].remove(ipserver);
					    panel[4].remove(iptext);
						
					    panel[3].add(bouton1);
						panel[3].add(bouton2);
						panel[4].add(portserver);
						panel[4].add(porttext);
						panel[4].add(comboPanel2);
						panel[4].validate();
						panel[4].repaint();
						panel[3].validate();
						
						model.setRoleToServer();
						
						panel[3].repaint();
						
					}
					if(combo.getSelectedItem().toString() == choices[0]){
						panel[3].setVisible(false);
						panel[4].setVisible(false);
						panel[3].remove(bouton);
						panel[4].remove(ipserver);
						panel[4].remove(iptext);
						panel[3].remove(bouton1);
						panel[3].remove(bouton2);
						panel[4].remove(portserver);
						panel[4].remove(porttext);
						panel[4].remove(comboPanel2);
						panel[4].validate();
						panel[4].repaint();
						panel[3].validate();
						panel[3].repaint();
					}
				}
			});
		}
		
		
	}

   @Override
   public void update(Observable obs, Object arg) {
      
      if (obs instanceof ServerIpObservers){
         ipAdress.setText(model.getRole().getIpAddress());
         repaint();
      } else if(obs instanceof ScreenObservers) {
         bouton.setText((String)arg);
         bouton1.setText((String)arg);
         repaint();
      }

      
   }

}

