import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Html {
	private String caminho;

	public void criarTabelaHtml(List<PdfFiles> pdfs, String path) throws IOException {
		//https://gist.github.com/2sbsbsb/2951464
		//https://www.w3schools.com/html/html_styles.asp


		FileWriter out = new FileWriter(new File(path, "Covid Repository.html"));

		out.write("<html>\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"table, th, td {\r\n" + 
				"  border: 1px solid black;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"  background-color: #eee;\r\n" + 
				"}\r\n" + 
				"th, td {\r\n" + 
				"  padding: 15px;\r\n" + 
				"  text-align: center;\r\n" + 
				"}\r\n" + 
				"table#t01 {\r\n" + 
				"  width: 100%;    \r\n" + 
				"  background-color: #f1f1c1;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<h2> <em>Covid Scientific Discoveries Repository</em></h2>\r\n" + 
				"\r\n" + 
				"<table style=\"width:100%\">\r\n" + 
				"  <tr>\r\n" + 
				"    <th><b>Article Title</b></th>\r\n" + 
				"    <th><b>Jornal Name</b></th> \r\n" + 
				"    <th><b>Publication Year</b></th>\r\n" + 
				"     <th><b>Authors</b></th>\r\n" + 
				"  </tr></thead><tbody>");
		for(PdfFiles p: pdfs) {
			out.write("<tr>");
			out.write("<td>");
			out.write("<a href=");
			out.write(p.getPath());
//			out.write(p.getPath().substring(0, p.getPath().length()/2));
//			out.write(p.getPath().substring((p.getPath().length()/2)+1, p.getPath().length()));
			//System.out.println(p.getPath());
			out.write(">");
			out.write(p.getTitle()+"</a>");
			//out.write("</a>");
			out.write("</td><td>");
			out.write(p.getJornalName());
			out.write("</td><td>");
			out.write(p.getYearPub());
			out.write("</td><td>");
			for(String a: p.getAuthors()) { 
				int i= p.getAuthors().size();
				out.write(a);
				if(p.getAuthors().get(i-1).equals(a))
					out.write(" .");
				else out.write(", ");

			}
			out.write("</td>");
			out.write("</tr>");

		}
		out.write("</tbody></table><small><i>Complemento ao Projeto - Engenharia de Software II . Mário da Costa Nº82030</i></small>");
		out.write("<h2><p> </p></h2>"+"<img src="+"logoiscte.png"+ "alt="+"Iscte Logo" +"width="+"500" +
				"height="+"333"+">"+"</body></html>");
		out.close();
	}



}




