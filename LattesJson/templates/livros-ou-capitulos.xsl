<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<LIVROS-PUBLICADOS-OU-ORGANIZADOS>
		<xsl:for-each select="CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/LIVROS-E-CAPITULOS/LIVROS-PUBLICADOS-OU-ORGANIZADOS/LIVRO-PUBLICADO-OU-ORGANIZADO">
			<LIVRO>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DO-LIVRO/@TITULO-DO-LIVRO"/></TITULO>
				<ANO><xsl:value-of select="DADOS-BASICOS-DO-LIVRO/@DOI"/></ANO>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DO-LIVRO/@IDIOMA"/></IDIOMA>
				<PERIODICO><xsl:value-of select="DETALHAMENTO-DO-LIVRO/@ISBN"/></PERIODICO>
				<AUTORES>
					<xsl:for-each select="AUTORES">
						<NOME-AUTOR><xsl:value-of select="@NOME-COMPLETO-DO-AUTOR"/></NOME-AUTOR>
						<ORDEM-DE-AUTORIA><xsl:value-of select="@ORDEM-DE-AUTORIA"/></ORDEM-DE-AUTORIA>
					</xsl:for-each>
				</AUTORES>
    			</LIVRO>
    	</xsl:for-each>
	</LIVROS-PUBLICADOS-OU-ORGANIZADOS>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
