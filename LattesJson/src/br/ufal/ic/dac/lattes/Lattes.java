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

	   			 
	   			 try {
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
	   				 trabEventos.remove("SEQUENCIA-PRODUCAO");
	   				 JSONObject dados_basicos_trab = trabEventos.getJSONObject("DADOS-BASICOS-DO-TRABALHO");
	   				 dados_basicos_trab.remove("NATUREZA");
	   				 dados_basicos_trab.remove("MEIO-DE-DIVULGACAO");
	   				 dados_basicos_trab.remove("HOME-PAGE-DO-TRABALHO");
	   				 dados_basicos_trab.remove("FLAG-RELEVANCIA");
	   				 dados_basicos_trab.remove("FLAG-DIVULGACAO-CIENTIFICA");
	   				 
	   			 } catch (Exception e) {
	   				 System.out.println(e);
	   				 // TODO: handle exception
	   			 }
	   			 
	   		 /*try{

	   			 JSONObject Areas = trabEventos.getJSONObject("AREAS-DO-CONHECIMENTO");
	   			 int tam = Areas.length();
	   			 System.out.println(tam);
	   			 for (int k = 1; k <= tam; k++){
	   				 
	   				 
	   				 String string = "AREA-DO-CONHECIMENTO-"+k;
	   				 JSONObject areadoConhecimento = (JSONObject) Areas.get(string);
	   				 
	   			 }
	   			 
	   			 
	   		 }
	   			 catch (Exception e ){};*/
	   		 
	   			 
	   			 
	   			  JSONArray autores = trabEventos.getJSONArray("AUTORES");
	   			 
	   			  for (int j = 0; j < autores.length(); j++) {
	   				  try {
	   					  JSONObject autor = (JSONObject) autores.get(j);
	   					 
	   					  autor.remove("NRO-ID-CNPQ");
	   				 } catch (Exception e) {
	   					 // TODO: handle exception
	   				 }
	   			 
	   			 
	   			  }
	   		 }
	   		 try {
	   			 JSONObject textJorn = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").getJSONObject("TEXTOS-EM-JORNAIS-OU-REVISTAS").getJSONObject("TEXTO-EM-JORNAL-OU-REVISTA");
	   			 JSONArray autores = textJorn.getJSONArray("AUTORES");
	   			 
	   			  for (int j = 0; j < autores.length(); j++) {
	   				  try {
	   					  JSONObject autor = (JSONObject) autores.get(j);
	   					 
	   					  autor.remove("NRO-ID-CNPQ");
	   				 } catch (Exception e) {
	   					 // TODO: handle exception
	   				 }
	   			 
	   			 
	   			  }
	   			  textJorn.getJSONObject("DETALHAMENTO-DO-TEXTO").remove("PAGINA-FINAL");
	   			  textJorn.getJSONObject("DETALHAMENTO-DO-TEXTO").remove("PAGINA-INICIAL");
	   			  textJorn.getJSONObject("DETALHAMENTO-DO-TEXTO").remove("DATA-DE-PUBLICACAO");
	   			  textJorn.getJSONObject("DADOS-BASICOS-DO-TEXTO").remove("NATUREZA");
	   			  textJorn.getJSONObject("DADOS-BASICOS-DO-TEXTO").remove("MEIO-DE-DIVULGACAO");
	   			  textJorn.getJSONObject("DADOS-BASICOS-DO-TEXTO").remove("HOME-PAGE-DO-TRABALHO");
	   			  textJorn.getJSONObject("DADOS-BASICOS-DO-TEXTO").remove("FLAG-RELEVANCIA");
	   			  textJorn.getJSONObject("DADOS-BASICOS-DO-TEXTO").remove("FLAG-DIVULGACAO-CIENTIFICA");
	   			  textJorn.remove("SEQUENCIA-PRODUCAO");
	   		 } catch (Exception e) {
	   			 System.out.println(e);
	   		 }
	   		 try {
	   			 JSONArray artigos =  json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").getJSONObject("ARTIGOS-PUBLICADOS").getJSONArray("ARTIGO-PUBLICADO");
	   			 for(int l = 0; l< artigos.length();l++){
	   				 JSONObject artigo = (JSONObject) artigos.get(l);
	   				 JSONObject detalhes = artigo.getJSONObject("DETALHAMENTO-DO-ARTIGO");
	   				 detalhes.remove("PAGINA-FINAL");
	   				 detalhes.remove("PAGINA-INICIAL");
	   				 detalhes.remove("SERIE");
	   				 detalhes.remove("FASCICULO");
	   				 JSONObject basicData = artigo.getJSONObject("DADOS-BASICOS-DO-ARTIGO");
	   				 basicData.remove("NATUREZA");
	   				 basicData.remove("MEIO-DE-DIVULGACAO");
	   				 basicData.remove("HOME-PAGE-DO-TRABALHO");
	   				 basicData.remove("FLAG-RELEVANCIA");
	   				 basicData.remove("FLAG-DIVULGACAO-CIENTIFICA");
	   				 
	   				 JSONArray autores = artigo.getJSONArray("AUTORES");
	   				 
	   				  for (int j = 0; j < autores.length(); j++) {
	   					  try {
	   						  JSONObject autor = (JSONObject) autores.get(j);
	   						 
	   						  autor.remove("NRO-ID-CNPQ");
	   					 } catch (Exception e) {
	   						 // TODO: handle exception
	   					 }
	   				 
	   			 }
	   				  artigo.remove("SEQUENCIA-PRODUCAO");
	   				  artigo.remove("ORDEM-IMPORTANCIA");
	   				  }
	   		 }
	   		  catch (Exception e) {
	   			 System.out.println(e);
	   			 // TODO: handle exception
	   		 }
	   		 
	   		 //Livros ou Capitulos
	   		 try {
	   		 JSONArray books =  json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").getJSONObject("LIVROS-E-CAPITULOS").getJSONObject("LIVROS-PUBLICADOS-OU-ORGANIZADOS").getJSONArray("LIVRO-PUBLICADO-OU-ORGANIZADO");
	   		 for(int l = 0; l< books.length();l++){
	   			 JSONObject book = (JSONObject) books.get(l);
	   			 JSONObject detalhes = book.getJSONObject("DETALHAMENTO-DO-LIVRO");
	   			 detalhes.remove("NUMERO-DA-SERIE");
	   			 detalhes.remove("NUMERO-DE-VOLUMES");
	   			 detalhes.remove("NUMERO-DE-PAGINAS");
	   			 detalhes.remove("NOME-DA-EDITORA");
	   			 detalhes.remove("NUMERO-DA-EDICAO-REVISAO");
	   			 detalhes.remove("CIDADE-DA-EDITORA");
	   			 JSONObject basicData = book.getJSONObject("DADOS-BASICOS-DO-LIVRO");
	   			 basicData.remove("NATUREZA");
	   			 basicData.remove("MEIO-DE-DIVULGACAO");
	   			 basicData.remove("HOME-PAGE-DO-TRABALHO");
	   			 basicData.remove("FLAG-RELEVANCIA");
	   			 basicData.remove("FLAG-DIVULGACAO-CIENTIFICA");
	   			 basicData.remove("TIPO");
	   			 basicData.remove("PAIS-DE-PUBLICACAO");
	   			 basicData.remove("ANO");
	   			 
	   			 JSONArray autores = book.getJSONArray("AUTORES");
	   			 
	   			  for (int j = 0; j < autores.length(); j++) {
	   				  try {
	   					  JSONObject autor = (JSONObject) autores.get(j);
	   					 
	   					  autor.remove("NRO-ID-CNPQ");
	   				 } catch (Exception e) {
	   					 // TODO: handle exception
	   				 }
	   			 
	   		 }
	   			  book.remove("SEQUENCIA-PRODUCAO");
	   			  book.remove("ORDEM-IMPORTANCIA");
	   			  }
	   	 }
	   	  catch (Exception e) {
	   		 System.out.println(e);
	   		 // TODO: handle exception
	   	 }
	   		 
	   	 //Artigo-aceito-publicação
	   		 try {
	   			 JSONObject artPublic = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-BIBLIOGRAFICA").getJSONObject("ARTIGOS-ACEITOS-PARA-PUBLICACAO").getJSONObject("ARTIGO-ACEITO-PARA-PUBLICACAO");
	   			 JSONArray autores = artPublic.getJSONArray("AUTORES");
	   			 
	   			  for (int j = 0; j < autores.length(); j++) {
	   				  try {
	   					  JSONObject autor = (JSONObject) autores.get(j);
	   					 
	   					  autor.remove("NRO-ID-CNPQ");
	   				 } catch (Exception e) {
	   					 // TODO: handle exception
	   				 }   		 
	   			 
	   			  }
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("PAGINA-FINAL");
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("PAGINA-INICIAL");
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("DATA-DE-PUBLICACAO");
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("FASCICULO");
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("VOLUME");
	   			  artPublic.getJSONObject("DETALHAMENTO-DO-ARTIGO").remove("SERIE");
	   			  artPublic.getJSONObject("DADOS-BASICOS-DO-ARTIGO").remove("NATUREZA");
	   			  artPublic.getJSONObject("DADOS-BASICOS-DO-ARTIGO").remove("MEIO-DE-DIVULGACAO");
	   			  artPublic.getJSONObject("DADOS-BASICOS-DO-ARTIGO").remove("HOME-PAGE-DO-TRABALHO");
	   			  artPublic.getJSONObject("DADOS-BASICOS-DO-ARTIGO").remove("FLAG-RELEVANCIA");
	   			  artPublic.getJSONObject("DADOS-BASICOS-DO-ARTIGO").remove("FLAG-DIVULGACAO-CIENTIFICA");
	   			  artPublic.remove("SEQUENCIA-PRODUCAO");
	   		 } catch (Exception e) {
	   			 System.out.println(e);
	   		 }
	   		 // Producao tecnica
	   		 try {
	   			 json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-TECNICA").remove("DEMAIS-TIPOS-DE-PRODUCAO-TECNICA");
	   			 json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-TECNICA").remove("TRABALHO-TECNICO");
	   			 // Marca
	   			 JSONObject marca = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-TECNICA").getJSONObject("MARCA");
	   			 JSONArray autores = marca.getJSONArray("AUTORES");
	   			 for (int j = 0; j < autores.length(); j++) {
	   				  try {
	   					  JSONObject autor = (JSONObject) autores.get(j);
	   					 
	   					  autor.remove("NRO-ID-CNPQ");
	   				 } catch (Exception e) {
	   					 // TODO: handle exception
	   				 }   		 
	   			 
	   			  }
	   			 
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").remove("NATUREZA");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("NUMERO-DEPOSITO-PCT");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("DATA-DEPOSITO-PCT");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("DATA-DE-PEDIDO-DE-EXAME");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("DATA-DE-CONCESSAO");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("DATA-PEDIDO-DE-DEPOSITO");
	   			  marca.getJSONObject("DETALHAMENTO-DA-MARCA").getJSONObject("REGISTRO-OU-PATENTE").remove("FORMATO-DATA-DEPOSITO-PCT");
	   			  marca.getJSONObject("DADOS-BASICOS-DA-MARCA").remove("FLAG-POTENCIAL-INOVACAO");
	   			  marca.getJSONObject("DADOS-BASICOS-DA-MARCA").remove("FLAG-RELEVANCIA");
	   			  marca.remove("SEQUENCIA-PRODUCAO");
	   			  JSONArray softwares = json.getJSONObject("CURRICULO-VITAE").getJSONObject("PRODUCAO-TECNICA").getJSONArray("SOFTWARE");
	   			  for (int f = 0; f < softwares.length(); f++) {
	   				  JSONObject software = (JSONObject) softwares.get(f);
	   				  autores = software.getJSONArray("AUTORES");
	   					 for (int j = 0; j < autores.length(); j++) {
	   						  try {
	   							  JSONObject autor = (JSONObject) autores.get(j);
	   							 
	   							  autor.remove("NRO-ID-CNPQ");
	   						 } catch (Exception e) {
	   							 // TODO: handle exception
	   						 }   		 
	   					 
	   					  }
	   					 
	   					 
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("FLAG-POTENCIAL-INOVACAO");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("FLAG-RELEVANCIA");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("MEIO-DE-DIVULGACAO");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("HOME-PAGE-DO-TRABALHO");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("PAIS");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("NATUREZA");
	   					 software.getJSONObject("DADOS-BASICOS-DO-SOFTWARE").remove("ANO");
	   					 software.getJSONObject("DETALHAMENTO-DO-SOFTWARE").remove("AMBIENTE");
	   					 software.getJSONObject("DETALHAMENTO-DO-SOFTWARE").remove("DISPONIBILIDADE");
	   					 software.getJSONObject("DETALHAMENTO-DO-SOFTWARE").remove("PLATAFORMA");
	   					 software.getJSONObject("DETALHAMENTO-DO-SOFTWARE").remove("INSTITUICAO-FINANCIADORA");
	   					 software.remove("SEQUENCIA-PRODUCAO");
	   			  }
	   				 
	   		 } catch (Exception e) {
	   			 // TODO: handle exception
	   		 }
	   		 
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("INFORMACOES-ADICIONAIS-CURSOS");
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("PARTICIPACAO-EM-BANCA-JULGADORA");
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("PARTICIPACAO-EM-BANCA-TRABALHOS-CONCLUSAO");
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("PARTICIPACAO-EM-EVENTOS-CONGRESSOS");
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("INFORMACOES-ADICIONAIS-INSTITUICOES");
	   		 json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").remove("FORMACAO-COMPLEMENTAR");
	   		 //ORIENTACOES EM ANDAMENTO
	   		 try {
	   			 
	   			 JSONObject ORIENTACOES = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-COMPLEMENTARES").getJSONObject("ORIENTACOES-EM-ANDAMENTO");
	   			 JSONArray ORIENTMESTRE = ORIENTACOES.getJSONArray("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO");
	   			 JSONArray ORIENTPOS = ORIENTACOES.getJSONArray("ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO");
	   			 JSONArray ORIENTIC = ORIENTACOES.getJSONArray("ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA");
	   			 JSONArray ORIENTOUTRAS = ORIENTACOES.getJSONArray("OUTRAS-ORIENTACOES-EM-ANDAMENTO");
	   			 JSONArray ORIENTDOC = ORIENTACOES.getJSONArray("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO");
	   		 //MESTRE
	   			 
	   			 for (int o = 0; o < ORIENTMESTRE.length(); o++) {
	   				 JSONObject mestre = (JSONObject) ORIENTMESTRE.get(o);
	   				 
	   				 mestre.remove("SEQUENCIA-PRODUCAO");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("FLAG-BOLSA");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("CODIGO-INSTITUICAO");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("CODIGO-AGENCIA-FINANCIADORA");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("CODIGO-ORGAO");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("NOME-DA-AGENCIA");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("NOME-CURSO-INGLES");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("NOME-ORGAO");
	   				 mestre.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("NUMERO-ID-ORIENTANDO");
	   				 mestre.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("HOME-PAGE");
	   				 mestre.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("NATUREZA");
	   				 mestre.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO").remove("TITULO-DO-TRABALHO-INGLES");
	   				 ;
	   			 }
	   		 //IC
	   			 for (int o = 0; o < ORIENTIC.length(); o++) {
	   				 JSONObject IC = (JSONObject) ORIENTIC.get(o);
	   				 
	   				 IC.remove("SEQUENCIA-PRODUCAO");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("FLAG-BOLSA");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("CODIGO-INSTITUICAO");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("CODIGO-AGENCIA-FINANCIADORA");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("CODIGO-ORGAO");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("NOME-DA-AGENCIA");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("NOME-CURSO-INGLES");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("NOME-ORGAO");
	   				 IC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("NUMERO-ID-ORIENTANDO");
	   				 IC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("HOME-PAGE");
	   				 IC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("NATUREZA");
	   				 IC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA").remove("TITULO-DO-TRABALHO-INGLES");
	   				 ;
	   			 }
	   			 //POS DOC
	   			 for (int o = 0; o < ORIENTPOS.length(); o++) {
	   				 JSONObject outras = (JSONObject) ORIENTPOS.get(o);
	   				 
	   				 outras.remove("SEQUENCIA-PRODUCAO");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("FLAG-BOLSA");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("CODIGO-INSTITUICAO");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("CODIGO-AGENCIA-FINANCIADORA");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("CODIGO-ORGAO");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("NOME-DA-AGENCIA");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("NOME-CURSO-INGLES");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("NOME-ORGAO");
	   				 outras.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("NUMERO-ID-ORIENTANDO");
	   				 outras.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("HOME-PAGE");
	   				 outras.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("NATUREZA");
	   				 outras.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-POS-DOUTORADO").remove("TITULO-DO-TRABALHO-INGLES");
	   				 ;
	   			 }
	   			 //OUTRAS
	   			 
	   			 for (int o = 0; o < ORIENTOUTRAS.length(); o++) {
	   				 JSONObject OUTRAS = (JSONObject) ORIENTOUTRAS.get(o);
	   				 
	   				 OUTRAS.remove("SEQUENCIA-PRODUCAO");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("FLAG-BOLSA");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("CODIGO-INSTITUICAO");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("CODIGO-AGENCIA-FINANCIADORA");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("CODIGO-ORGAO");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("NOME-DA-AGENCIA");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("NOME-CURSO-INGLES");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("NOME-ORGAO");
	   				 OUTRAS.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("NUMERO-ID-ORIENTANDO");
	   				 OUTRAS.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("HOME-PAGE");
	   				 OUTRAS.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("NATUREZA");
	   				 OUTRAS.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-EM-ANDAMENTO").remove("TITULO-DO-TRABALHO-INGLES");
	   				 ;
	   			 }
	   			 //DOC
	   			 
	   			 for (int o = 0; o < ORIENTDOC.length(); o++) {
	   				 JSONObject DOC = (JSONObject) ORIENTDOC.get(o);
	   				 
	   				 DOC.remove("SEQUENCIA-PRODUCAO");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("FLAG-BOLSA");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("CODIGO-INSTITUICAO");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("CODIGO-AGENCIA-FINANCIADORA");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("CODIGO-ORGAO");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("NOME-DA-AGENCIA");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("NOME-CURSO-INGLES");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("NOME-ORGAO");
	   				 DOC.getJSONObject("DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("NUMERO-ID-ORIENTANDO");
	   				 DOC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("HOME-PAGE");
	   				 DOC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("NATUREZA");
	   				 DOC.getJSONObject("DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO").remove("TITULO-DO-TRABALHO-INGLES");
	   				 ;
	   			 }
	   		 } catch (Exception e) {
	   			 // TODO: handle exception
	   			 System.out.println(e);
	   		 }

			JSONObject graducao = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("GRADUACAO");
			graducao.remove("TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO");
			graducao.remove("CODIGO-AREA-CURSO");
			graducao.remove("CODIGO-INSTITUICAO-GRAD");
			graducao.remove("CODIGO-INSTITUICAO-OUTRA-GRAD");
			graducao.remove("NIVEL");
			graducao.remove("NOME-INSTITUICAO-OUTRA-GRAD");
			graducao.remove("CODIGO-INSTITUICAO");
			graducao.remove("CODIGO-AGENCIA-FINANCIADORA");
			graducao.remove("CODIGO-ORGAO");
			graducao.remove("FORMACAO-ACADEMICA-TITULACAO");
			graducao.remove("NOME-ORIENTADOR-GRAD");
			graducao.remove("NOME-AGENCIA");
			graducao.remove("TITULO-DO-TRABALHO-DE-CONCLUSAO-DE-CURSO-INGLES");
			graducao.remove("NOME-ORGAO");
			graducao.remove("CODIGO-CURSO");
			graducao.remove("SEQUENCIA-FORMACAO");
			graducao.remove("FLAG-BOLSA");
			graducao.remove("NOME-CURSO-INGLES");
			graducao.remove("TIPO-GRADUACAO");
			graducao.remove("CODIGO-CURSO-CAPES");
			
			JSONObject ensMedio = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("ENSINO-MEDIO-SEGUNDO-GRAU");
			ensMedio.remove("SEQUENCIA-FORMACAO");
			ensMedio.remove("CODIGO-INSTITUICAO");
			graducao.remove("NIVEL");

			JSONObject mestr = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("MESTRADO");
			mestr.remove("NOME-ORIENTADOR-DOUT");
			mestr.remove("CODIGO-AREA-CURSO");
			mestr.remove("NOME-INSTITUICAO-DOUT");
			mestr.remove("TITULO-DA-DISSERTACAO-TESE-INGLES");
			mestr.remove("CODIGO-INSTITUICAO-DOUT");
			mestr.remove("NIVEL");
			mestr.remove("CODIGO-INSTITUICAO");
			mestr.remove("CODIGO-AGENCIA-FINANCIADORA");
			mestr.remove("CODIGO-ORGAO");
			mestr.remove("NUMERO-ID-ORIENTADOR");
			mestr.remove("NOME-AGENCIA");
			mestr.remove("NOME-ORGAO");
			mestr.remove("NOME-INSTITUICAO-OUTRA-DOUT");
			mestr.remove("CODIGO-CURSO");
			mestr.remove("SEQUENCIA-FORMACAO");
			mestr.remove("FLAG-BOLSA");
			mestr.remove("NOME-CURSO-INGLES");
			mestr.remove("CODIGO-INSTITUICAO-OUTRA-DOUT");
			mestr.remove("CODIGO-CURSO-CAPES");
			mestr.remove("SETORES-DE-ATIVIDADE");
			
			JSONObject pos = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("POS-DOUTORADO");
			pos.remove("TITULO-DO-TRABALHO-INGLES");
			pos.remove("NUMERO-ID-ORIENTADOR");
			pos.remove("NOME-AGENCIA");
			pos.remove("NIVEL");
			pos.remove("SEQUENCIA-FORMACAO");
			pos.remove("FLAG-BOLSA");
			pos.remove("CODIGO-INSTITUICAO");
			pos.remove("NOME-CURSO-INGLES");
			pos.remove("CODIGO-AGENCIA-FINANCIADORA");
			pos.remove("NOME-ORGAO");
			pos.remove("NOME-INSTITUICAO-OUTRA-DOUT");
			pos.remove("CODIGO-CURSO");
			pos.remove("CODIGO-CURSO-CAPES");
			
			JSONObject espec = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("ESPECIALIZACAO");
			espec.remove("NIVEL");
			espec.remove("CODIGO-INSTITUICAO");
			espec.remove("CODIGO-AGENCIA-FINANCIADORA");
			espec.remove("CODIGO-ORGAO");
			espec.remove("NOME-ORGAO");
			espec.remove("CODIGO-CURSO");
			espec.remove("SEQUENCIA-FORMACAO");
			espec.remove("FLAG-BOLSA");
			espec.remove("NOME-CURSO-INGLES");
			espec.remove("TITULO-DA-MONOGRAFIA-INGLES");
			
			JSONObject ensFundamental = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("ENSINO-FUNDAMENTAL-PRIMEIRO-GRAU");
			ensFundamental.remove("SEQUENCIA-FORMACAO");
			ensFundamental.remove("CODIGO-INSTITUICAO");
			ensFundamental.remove("NIVEL");
			
			JSONObject dout = json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("FORMACAO-ACADEMICA-TITULACAO").getJSONObject("DOUTORADO");
			dout.remove("NOME-ORIENTADOR-DOUT");
			dout.remove("CODIGO-AREA-CURSO");
			dout.remove("NOME-INSTITUICAO-DOUT");
			dout.remove("TITULO-DA-DISSERTACAO-TESE-INGLES");
			dout.remove("CODIGO-INSTITUICAO-DOUT");
			dout.remove("NIVEL");
			dout.remove("CODIGO-INSTITUICAO");
			dout.remove("CODIGO-AGENCIA-FINANCIADORA");
			dout.remove("CODIGO-ORGAO");
			dout.remove("NUMERO-ID-ORIENTADOR");
			dout.remove("NOME-AGENCIA");
			dout.remove("NOME-ORGAO");
			dout.remove("NOME-INSTITUICAO-OUTRA-DOUT");
			dout.remove("CODIGO-CURSO");
			dout.remove("SEQUENCIA-FORMACAO");
			dout.remove("FLAG-BOLSA");
			dout.remove("NOME-CURSO-INGLES");
			dout.remove("CODIGO-INSTITUICAO-OUTRA-DOUT");
			dout.remove("CODIGO-CURSO-CAPES");
			dout.remove("SETORES-DE-ATIVIDADE");
						
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("IDIOMAS");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("RESUMO-CV");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("PREMIOS-TITULOS");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("OUTRAS-INFORMACOES-RELEVANTES");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("PERMISSAO-DE-DIVULGACAO");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("SIGLA-PAIS-NACIONALIDADE");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("CIDADE-NASCIMENTO");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("DATA-FALECIMENTO");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").remove("NACIONALIDADE");
			json.getJSONObject("CURRICULO-VITAE").getJSONObject("DADOS-GERAIS").getJSONObject("ENDERECO").remove("FLAG-DE-PREFERENCIA");

			json.getJSONObject("CURRICULO-VITAE").getJSONObject("OUTRA-PRODUCAO").remove("DEMAIS-TRABALHOS");
			
			
			JSONArray mestrados = json.getJSONObject("CURRICULO-VITAE").getJSONObject("OUTRA-PRODUCAO").getJSONObject("ORIENTACOES-CONCLUIDAS").getJSONArray("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO");
			for (int i = 0; i < mestrados.length(); i++) {
				JSONObject mestrado = (JSONObject)mestrados.get(i);
				mestrado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("HOME-PAGE");
				mestrado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("TITULO-INGLES");
				mestrado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("FLAG-RELEVANCIA");
				mestrado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("DOI");
				mestrado.remove("SEQUENCIA-PRODUCAO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("TIPO-DE-ORIENTACAO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("NUMERO-ID-ORIENTADO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("NOME-ORGAO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("CODIGO-CURSO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("FLAG-BOLSA");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("CODIGO-INSTITUICAO");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("NUMERO-DE-PAGINAS");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("CODIGO-AGENCIA-FINANCIADORA");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("NOME-DO-CURSO-INGLES");
				mestrado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO").remove("CODIGO-ORGAO");
			}
			
			JSONObject doutorado = json.getJSONObject("CURRICULO-VITAE").getJSONObject("OUTRA-PRODUCAO").getJSONObject("ORIENTACOES-CONCLUIDAS").getJSONObject("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO");
			doutorado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("HOME-PAGE");
			doutorado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("TITULO-INGLES");
			doutorado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("FLAG-RELEVANCIA");
			doutorado.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("DOI");
			doutorado.remove("SEQUENCIA-PRODUCAO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("TIPO-DE-ORIENTACAO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("NUMERO-ID-ORIENTADO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("NOME-ORGAO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("CODIGO-CURSO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("FLAG-BOLSA");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("CODIGO-INSTITUICAO");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("NUMERO-DE-PAGINAS");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("CODIGO-AGENCIA-FINANCIADORA");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("NOME-DO-CURSO-INGLES");
			doutorado.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO").remove("CODIGO-ORGAO");
			
			JSONArray posdocs = json.getJSONObject("CURRICULO-VITAE").getJSONObject("OUTRA-PRODUCAO").getJSONObject("ORIENTACOES-CONCLUIDAS").getJSONArray("ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO");
			
			for (int i = 0; i < posdocs.length(); i++) {
				JSONObject posdoc = (JSONObject)posdocs.get(i);
				posdoc.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("HOME-PAGE");
				posdoc.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("TITULO-INGLES");
				posdoc.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("FLAG-RELEVANCIA");
				posdoc.getJSONObject("DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("DOI");
				posdoc.remove("SEQUENCIA-PRODUCAO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("TIPO-DE-ORIENTACAO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("NUMERO-ID-ORIENTADO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("NOME-ORGAO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("CODIGO-CURSO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("FLAG-BOLSA");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("CODIGO-INSTITUICAO");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("NUMERO-DE-PAGINAS");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("CODIGO-AGENCIA-FINANCIADORA");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("NOME-DO-CURSO-INGLES");
				posdoc.getJSONObject("DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO").remove("CODIGO-ORGAO");
			}
			
			JSONArray outras = json.getJSONObject("CURRICULO-VITAE").getJSONObject("OUTRA-PRODUCAO").getJSONObject("ORIENTACOES-CONCLUIDAS").getJSONArray("OUTRAS-ORIENTACOES-CONCLUIDAS");
			
			for (int i = 0; i < outras.length(); i++) {
				JSONObject outra = (JSONObject)outras.get(i);
				outra.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("HOME-PAGE");
				outra.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("TITULO-INGLES");
				outra.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("FLAG-RELEVANCIA");
				outra.getJSONObject("DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("DOI");
				outra.remove("SEQUENCIA-PRODUCAO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("TIPO-DE-ORIENTACAO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("NUMERO-ID-ORIENTADO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("NOME-ORGAO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("CODIGO-CURSO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("FLAG-BOLSA");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("CODIGO-INSTITUICAO");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("NUMERO-DE-PAGINAS");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("CODIGO-AGENCIA-FINANCIADORA");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("NOME-DO-CURSO-INGLES");
				outra.getJSONObject("DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS").remove("CODIGO-ORGAO");
			}
			

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
