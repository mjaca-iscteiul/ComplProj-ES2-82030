import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.metadata.model.DateType;
import pl.edu.icm.cermine.metadata.model.DocumentAuthor;
import pl.edu.icm.cermine.metadata.model.DocumentDate;
public class Janela {



	private JFrame frame; 
	private JTextField diretoria = new JTextField(30);
	private JButton procurar; 
	private JButton computador;
	private JLabel label;   
	private JFileChooser chooser;
	private String path;
	private String pathd;
	private Extract extract=new Extract();
	private List<PdfFiles> pdfs= new ArrayList<>();

	public Janela() {
		frame= new JFrame( "Covid Repositories");
		open(); //adiciono o conteúdo À frame que é definir o layout como BorderLayout
		frame.setSize(600, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public void open() { 
		frame.setVisible(true);
		centreWindow(frame);

		frame.setLayout(new BorderLayout());

		JPanel pesquisa = new JPanel();

		pesquisa.setLayout(new FlowLayout());
		label = new JLabel("Diretoria:");
		pesquisa.add(label);
		pesquisa.add (diretoria,null ); 
		computador= new JButton("Procurar Pasta");
		procurar = new JButton("Analisar");
		pesquisa.add(computador, FlowLayout.RIGHT);
		pesquisa.add(procurar,null) ;
		ImageIcon image = new ImageIcon(getClass().getResource("logoiscte.png"));
		JLabel imageLabel = new JLabel(image); 
		imageLabel.setSize(10, 10);

		frame.add(imageLabel, BorderLayout.PAGE_END);
		frame.add(pesquisa);







		procurar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {

				path=diretoria.getText();

				int n= JOptionPane.showConfirmDialog(frame,  "O processo de Análise dos PDF's poderá demorar alguns minutos."+"\n"
						+"O caminho dos seus ficheiros é: "+"\n"+path+"\n", "Carregue Yes para Continuar!",JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					extract.setDiretoria(path);
					System.exit(0);
				}
				else if  (n == JOptionPane.NO_OPTION){
					System.exit(0);
				}

				System.exit(0);
				frame.pack();

			}
		});

		computador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);



				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					path=f.getAbsolutePath();
					int n= JOptionPane.showConfirmDialog(frame,  "O processo de Análise dos PDF's poderá demorar alguns minutos."+"\n"
							+"O caminho dos seus ficheiros é: "+"\n"+path+"\n", "Carregue Yes para Continuar!",JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						extract.setDiretoria(path);
						System.exit(0);
					}
					else if  (n == JOptionPane.NO_OPTION){
						System.exit(0);
					}
				} else {
					System.exit(0);

				}

				System.exit(0);
				frame.pack();


			}
		});
	}




	public void janelaEspera(boolean visivel) {
		JDialog dialog = new JDialog();
		dialog.setTitle("A extrair informação dos pdf's...");
		dialog.setLocationRelativeTo(frame);
		centreWindow(dialog);
		dialog.pack();
		dialog.setVisible(visivel);
		System.exit(0);

	}



	public static void main(String[] args) {
		Janela g = new Janela();
		//g.open();

	}

}
