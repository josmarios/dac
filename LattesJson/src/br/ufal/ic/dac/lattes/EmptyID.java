package br.ufal.ic.dac.lattes;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class EmptyID {
	public static EmptyID instance;

	private EmptyID() {

	}

	public static EmptyID getInstance() {
		if (instance == null) {
			instance = new EmptyID();
		}
		return instance;
	}

	public void listFiles(List<File> curriculums) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {

			dBuilder = dbFactory.newDocumentBuilder();
			for (File f : curriculums) {
				Document current = dBuilder.parse(f);

				if (current.getDocumentElement().getAttribute("NUMERO-IDENTIFICADOR") == "") {

					System.out.println("FILE: " + f.getName());

				}

			}
		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {

		File directory = new File("XML-Data/");
		directory.mkdirs();

		EmptyID.getInstance().listFiles(Arrays.asList(directory.listFiles()));

	}

}
