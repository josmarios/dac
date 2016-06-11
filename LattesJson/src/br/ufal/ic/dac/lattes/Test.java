package br.ufal.ic.dac.lattes;

import org.w3.xslt.XSLTransform;

public class Test {

	public static void main(String[] args) {
		
		String input = "/home/josmario/xml-data/curriculo.xml";
		String model = "/home/josmario/xml-data/template.xsl";
		String output = "/home/josmario/xml-data/output";
		
		try {
			XSLTransform.transform(input, model, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}