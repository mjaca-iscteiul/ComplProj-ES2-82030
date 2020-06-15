import java.util.ArrayList;
import java.util.List;
public class PdfFiles {

	private String title;
	private String jornalName;
	private List<String> authors=new ArrayList<>();
	private String year;
	private String path;

	public  PdfFiles (String title, String jornalName, String year, List<String> authors, String path) {
		this.title=title;
		this.jornalName=jornalName;
		this.year=year;
		this.authors=authors;
		this.path=path;
	}

	public String getTitle() {
		return title;

	}

	public String getJornalName() {
		return jornalName;
	}

	public List<String> getAuthors() {
		
		return authors;
	}

	public String getYearPub() {
		return year;

	}

	public String getPath() {
		return path;
	}







}
