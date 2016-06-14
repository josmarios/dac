package br.ufal.ic.dac.lattes;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3.xslt.XSLTransform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LattesXSL {

	private static LattesXSL instance;
	private final String DATA_DIR = "XML-Data/";
	private final String XSL_DIR = "templates/";
	private final String OUTPUT_DIR = "output-data/";
	private final String TEMP_DIR = "TEMP/";

	private LattesXSL() {
	}

	public static LattesXSL getInstance() {
		if (instance == null) {
			instance = new LattesXSL();
		}
		return instance;
	}

	public void processXml(String type) {

		new File(OUTPUT_DIR).mkdirs();

		File source = new File(DATA_DIR);
		source.mkdirs();

		// creates temp dir
		File temp = new File(TEMP_DIR);
		temp.mkdirs();

		// Lists all curriculums
		List<File> curriculums = Arrays.asList(source.listFiles());

		System.out.println("Extracting data...");
		for (File curriculum : curriculums) {
			try {
				XSLTransform.transform(curriculum.getAbsolutePath(), XSL_DIR + type + ".xsl",
						TEMP_DIR + type + "-" + curriculum.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// merge all files
		System.out.println("Merging files...");
		mergeFiles(type, Arrays.asList(temp.listFiles()));

		System.out.println("Done!");
	}

	private void mergeFiles(String type, List<File> files) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document result = dBuilder.newDocument();

			Element root = result.createElement(type);

			// Gets all root elements and add them to a unique Document
			for (File file : files) {
				Document current = dBuilder.parse(file);

				Node rootElement = result.importNode(current.getDocumentElement(), true);

				// Gets the root node
				root.appendChild(rootElement);
			}

			result.appendChild(root);

			// Creates and saves output file
			File out = new File(OUTPUT_DIR + type + ".xml");

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(result), new StreamResult(out));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
