import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.lang.StringBuilder;
import java.io.IOException;
public class start {
	public static boolean next = true;
	public static String csvPath;
	public static String xsltPath;
	public static String exportPath;
	public static ArrayList<String> csvAtts = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(next) {
			Scanner in = new Scanner(System.in);
			System.out.println("<XML TO CSV CONVERTER>");
			System.out.println("Enter the path to your CSV file");
			csvPath = in.nextLine();
			System.out.println("Enter the path to your XSLT file");
			xsltPath = in.nextLine();
			System.out.println("Enter export location");
			exportPath = in.nextLine();
			System.out.println("Pulling CSV File from: "+csvPath);
			System.out.println("Pulling XSLT File from: "+xsltPath);
			System.out.println("Exporting files to: "+exportPath);
			try {
				readWrite();
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
				System.out.println("CSV file reading failed due to IOException");
			}
			
			System.out.println("Convert another file? (Y)es | (N)o");
			char yn = in.next().charAt(0);
			if(yn == 'N' || yn == 'n') {
				next = false;
			}
		}
	}
	
	static void readWrite() throws IOException{
		String xmlString;
		Path csvFilePath = Paths.get(csvPath);
		Path exportXmlPath = Paths.get(exportPath);
		ArrayList<String> lines;
		ArrayList<String> atts;
		ArrayList<ArrayList<String>> values;
		lines = new ArrayList<String>(Files.readAllLines(csvFilePath, StandardCharsets.UTF_8));
		System.out.println("CSV file contents: ");
		for(String s : lines ) {
			System.out.println(s);
		}
		System.out.println("----------------------------------");
		atts = getCsvAtts(lines);
		values = getValues(lines);
		xmlString = createXML(atts,values,(lines.size() - 1));
		try {
			Files.write(exportXmlPath, xmlString.getBytes());
		}
		catch (SecurityException e) {
			System.out.println("You do not have rights to write to "+exportXmlPath.toString());
		}
		catch (IOException e) {
			System.out.println("File writing failed due to an IOException");
		}
	}
	
	static ArrayList<String> getCsvAtts(ArrayList<String> l) throws IOException{
		String firstLine;
		firstLine = l.get(0);
		String[] strlist = firstLine.split(",");
		System.out.println("Attributes: ");
		System.out.println(new ArrayList<String>(Arrays.asList(strlist)).toString());
		System.out.println("----------------------------------");
		return new ArrayList<String>(Arrays.asList(strlist));
	}
	
	static ArrayList<ArrayList<String>> getValues(ArrayList<String> l) {
		ArrayList<ArrayList<String>> vals = new ArrayList<ArrayList<String>>();
		for(int i = 1; i < l.size(); i++) {
			String line = l.get(i);
			String[] strarr = line.split(",");
			vals.add(new ArrayList<String>(Arrays.asList(strarr)));
		}
		return vals;
	}
	
	static String createXML(ArrayList<String> a, ArrayList<ArrayList<String>> v, int max) {
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<?xml-stylesheet type=\"text/xsl\" href=\""+xsltPath+"\"?>\n");
		xml.append("<root>\n");
		for(int i = 0; i < max; i++) {
			xml.append("<element>\n");
			for(int j = 0; j < a.size(); j++) {
				xml.append('<');
				xml.append(a.get(j));
				xml.append('>');
				xml.append(v.get(i).get(j));
				xml.append("</");
				xml.append(a.get(j));
				xml.append(">\n");
			}
			xml.append("</element>\n");
		}
		xml.append("</root>");
		System.out.println("XML: ");
		System.out.println(xml.toString());
		System.out.println("----------------------------------");
		return xml.toString();
	}
}
