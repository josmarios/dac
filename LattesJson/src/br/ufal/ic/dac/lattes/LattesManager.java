package br.ufal.ic.dac.lattes;

public class LattesManager {

	public static void main(String[] args) {

		// String[] input = { "artigos-aceitos", "artigos-publicados",
		// "capitulos-de-livros-publicados",
		// "livros-ou-capitulos", "livros-publicados-ou-organizados", "marca",
		// "orientacoes-em-andamento",
		// "outras-orientacoes-concluidas", "software" };

		String[] input = { "orientacoes-em-andamento", "marca" };

		for (String category : input) {
			LattesXSL.getInstance().processXml(category);
		}
		System.out.println("Finished!");
	}
}