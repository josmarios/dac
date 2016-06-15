package br.ufal.ic.dac.lattes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.XML;

public class Lattes {

	private JSONObject xmlToJson(File input) {

		JSONObject json = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(input), "ISO-8859-1"));
			String line, content = "";

			while ((line = br.readLine()) != null) {
				content += line;
			}
			br.close();
			json = XML.toJSONObject(content);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return json;
	}

	private JSONObject getJsonData(String dataType, JSONObject rootNode) {
		JSONObject json = new JSONObject();

		switch (dataType) {

		case "JORNAIS-OU-REVISTAS":
			json = generateJsonArrayJornaisOuRevistas(rootNode);
			break;

		case "DADOS-GERAIS":
			json = generateJsonBasicData(rootNode);
			break;

		case "TRABALHOS-EM-EVENTOS":

		default:

			break;
		}
		return json;
	}

	private void processData(String type) throws Exception {

		File dir = new File("XML-Data/");
		File[] files = dir.listFiles();

		JSONArray jsonArray = new JSONArray();

		for (File f : files) {

			JSONObject json = xmlToJson(f);

			if (type.equals("TRABALHOS-EM-EVENTOS")) {
				jsonArray = concatArray(generateJsonArrayTrabalhosEventos(json), jsonArray);
			}

			JSONObject jsonObject = getJsonData(type, json);

			if (jsonObject.length() != 0)
				jsonArray.put(jsonObject);
		}

		JSONObject rootNode = new JSONObject();
		rootNode.put(type, jsonArray);

		saveFile(rootNode.toString(), type + ".json");

	}

	public static void main(String[] args) throws IOException {

		try {
			new Lattes().processData("DADOS-GERAIS");
			new Lattes().processData("JORNAIS-OU-REVISTAS");
			new Lattes().processData("TRABALHOS-EM-EVENTOS");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JSONArray concatArray(JSONArray arr1, JSONArray arr2) throws JSONException {
		JSONArray result = new JSONArray();
		for (int i = 0; i < arr1.length(); i++) {
			result.put(arr1.get(i));
		}
		for (int i = 0; i < arr2.length(); i++) {
			result.put(arr2.get(i));
		}
		return result;
	}

	private JSONObject generateJsonBasicData(JSONObject json) {
		JSONObject jsonOutput = new JSONObject();
		try {
			jsonOutput.put("NOME-EM-CITACOES-BIBLIOGRAFICAS", json.getJSONObject("CURRICULO-VITAE")
					.getJSONObject("DADOS-GERAIS").get("NOME-EM-CITACOES-BIBLIOGRAFICAS"));
			jsonOutput.put("URI", "HTTP://WWW.IC.UFAL.BR/DAC/AUTHOR/LATTES/"
					+ json.getJSONObject("CURRICULO-VITAE").get("NUMERO-IDENTIFICADOR"));
			jsonOutput.put("NOME-COMPLETO",
					json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").get("NOME-COMPLETO"));
			jsonOutput.put("PAIS-DE-NASCIMENTO",
					json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").get("PAIS-DE-NASCIMENTO"));
			try {
				jsonOutput.put("ENDERECO", json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS")
						.getJSONObject("ENDERECO").get("ENDERECO-PROFISSIONAL"));
				
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			};																										
			jsonOutput.put("DATA-ATUALIZACAO", json.getJSONObject("CURRICULO-VITAE").get("DATA-ATUALIZACAO"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonOutput;
	}

	private JSONArray generateJsonArrayTrabalhosEventos(JSONObject json) {
		JSONArray jsonOutput = new JSONArray();

		try {
			JSONArray jsonTrabalhos = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA")
					.getJSONObject("TRABALHOS-EM-EVENTOS").getJSONArray("TRABALHO-EM-EVENTOS");

			for (int i = 0; i < jsonTrabalhos.length(); i++) {
				JSONObject aux = (JSONObject) jsonTrabalhos.get(i);
				aux.put("URI", "http://www.ic.ufal.br/dac/author/lattes/"
						+ json.getJSONObject("CURRICULO-VITAE").get("NUMERO-IDENTIFICADOR"));
				jsonTrabalhos.remove(i);
				jsonTrabalhos.put(i, aux);
			}
			jsonOutput = jsonTrabalhos;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonOutput;
	}

	private JSONObject generateJsonArrayJornaisOuRevistas(JSONObject json) {

		JSONObject jsonTrabalhos = null;

		try {
			jsonTrabalhos = new JSONObject();

			jsonTrabalhos.put("Author-URI", "http://www.ic.ufal.br/dac/author/lattes/"
					+ json.getJSONObject("CURRICULO-VITAE").get("NUMERO-IDENTIFICADOR"));
			try {
				jsonTrabalhos.put("Object", json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA")
						.getJSONObject("TEXTOS-EM-JORNAIS-OU-REVISTAS").getJSONObject("TEXTO-EM-JORNAL-OU-REVISTA"));
			} catch (Exception e) {
				e.getMessage();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonTrabalhos;
	}

	private void saveFile(String data, String fileName) {

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
