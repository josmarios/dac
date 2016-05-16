package br.ufal.ic.dac.lattes;

import java.awt.dnd.Autoscroll;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Lattes {

	public static void main(String[] args) {

		String input = "curriculo.xml";
		String dest = "curriculo.json";

		Lattes l = new Lattes();

		String json = l.generateJson(input);

		System.out.println(json);

		l.saveFile(json, dest);

	}

	private String generateJson(String fileName) {

		String content = "";
		String output = "";

		try {
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;

			while ((line = br.readLine()) != null)
				content += line;

			br.close();

			JSONObject json = XML.toJSONObject(content);

			// xmlJSONObj.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").remove("TRABALHOS-EM-EVENTOS");
			// xmlJSONObj.remove("CURRICULO-VITAE");

			// json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").remove("TRABALHOS-EM-EVENTOS");

			json.getJSONObject("CURRICULO-VITAE").remove("SISTEMA-ORIGEM-XML");
			json.getJSONObject("CURRICULO-VITAE").remove("HORA-ATUALIZACAO");

			JSONArray array = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA")
					.getJSONObject("TRABALHOS-EM-EVENTOS").getJSONArray("TRABALHO-EM-EVENTOS");

			for (int i = 0; i < array.length(); i++) {
				JSONObject trabEventos = (JSONObject) array.get(i);

				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("PAGINA-FINAL");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("PAGINA-INICIAL");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("VOLUME");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("CIDADE-DO-EVENTO");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("SERIE");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("CIDADE-DA-EDITORA");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("CLASSIFICACAO-DO-EVENTO");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("FASCICULO");
				trabEventos.getJSONObject("DETALHAMENTO-DO-TRABALHO").remove("NOME-DA-EDITORA");
				trabEventos.remove("SETORES-DE-ATIVIDADE");

				// JSONArray autores = trabEventos.getJSONArray("AUTORES");
				//
				// for (int j = 0; j < autores.length(); j++) {
				// JSONObject autor = (JSONObject) autores.get(i);
				// autor.remove("NRO-ID-CNPQ");
				// }
			}

			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("IDIOMAS");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("RESUMO-CV");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("PREMIOS-TITULOS");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("OUTRAS-INFORMACOES-RELEVANTES");

			output = json.toString(1);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return output;
	}

	private void saveFile(String data, String fileName) {
		File dest = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(new File(fileName));
			fw.write(data);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
