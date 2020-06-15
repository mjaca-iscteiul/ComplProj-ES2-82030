import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import pl.edu.icm.cermine.ContentExtractor;
import pl.edu.icm.cermine.*;
import pl.edu.icm.cermine.exception.AnalysisException;
import pl.edu.icm.cermine.metadata.model.DateType;
import pl.edu.icm.cermine.metadata.model.DocumentAuthor;
import pl.edu.icm.cermine.metadata.model.DocumentDate;


import org.jdom.*;


public class Extract {

	public String path;
	private List<PdfFiles> pdfs= new ArrayList<>();
	private Html html= new Html();
	private File n;



	public void setDiretoria(String path)  {
		this.path=path;

		try {
			searchFiles(path);
		} catch (AnalysisException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void searchFiles(String path) throws AnalysisException, IOException{
		this.path=path;
		try {
			File n = new File(path); 
//			if(n.exists()){
//				System.out.println("Diretoria existe!");
//				System.out.println("A diretoriaé : "+path+" e "+n);
			if(caminhoExiste(path)) {
				File[] files =new File(path).listFiles();
				for(File f: files) {

					extrairInfo(f);
				}
		}
			html.criarTabelaHtml(pdfs, path);
			JOptionPane.showMessageDialog(null,  "Ficheiro Covid Repository.html atualizado!");
			System.exit(0);
		} catch(Exception e){
			JOptionPane.showMessageDialog(null,  "Diretoria especificada inválida!");
			System.exit(0);
		}
	}

	public void extrairInfo(File f) throws AnalysisException, IOException {
		//https://stackoverflow.com/questions/58046381/how-to-extract-pdf-content-using-cermine/62307196#62307196
		System.out.println("A obter informação dos ficheiros...");
		List authores = new ArrayList<>();

		ContentExtractor extractor = new ContentExtractor();

		FileInputStream inputStream=new FileInputStream(f);

		extractor.setPDF(inputStream);

		String title =extractor.getMetadata().getTitle();
		String jornal = extractor.getMetadata().getJournal();


		DocumentDate anoPub= extractor.getMetadata().getDate(DateType.PUBLISHED);





		List<DocumentAuthor> authors = extractor.getMetadata().getAuthors();
		for(DocumentAuthor a: authors) {
			String autor = a.getName();
			authores.add(autor);

		}


		String path2= f.getAbsolutePath();


		pdfs.add(new PdfFiles(title, jornal, anoPub.getYear().toString(), authores, path2));
		System.out.println("Título: " + title);

		System.out.println("Autores: " + authores);

		System.out.println("jornal: " + jornal);
		System.out.println("Ano: " + anoPub.getYear());
		System.out.println("Path:"+  f.getAbsolutePath());

	}
	
	public boolean caminhoExiste(String path) {
		File n = new File(path); 
		if(n.exists()){
			System.out.println("Caminho válido");
			File[] files =new File(path).listFiles();
			for(File i: files) {
				if(i.getName().equals("Covid Repository.html")) {
					System.out.println("Ficheiro já existia");
				i.delete();
				}
			
}
			return true;
			
}
		return false;
}
	
	
	public void substituirFile() {
	    JDialog.setDefaultLookAndFeelDecorated(true);
	    int response = JOptionPane.showConfirmDialog(null, "Deseja substituir o ficheiro?", "Ficheiro já existe no destino",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.NO_OPTION) {	
	      System.out.println("No button clicked");
	    } else if (response == JOptionPane.YES_OPTION) {
	      System.out.println("Yes button clicked");
	    } else if (response == JOptionPane.CLOSED_OPTION) {
	      System.out.println("JOptionPane closed");
	    }
	  }
}
