<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<LIVROS-PUBLICADOS-OU-ORGANIZADOS>
		<xsl:for-each select="CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/LIVROS-E-CAPITULOS/CAPITULOS-DE-LIVROS-PUBLICADOS/CAPITULO-DE-LIVRO-PUBLICADO">
			<LIVRO>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DO-CAPITULO/@TITULO-DO-CAPITULO-DO-LIVRO"/></TITULO>
				<DOI><xsl:value-of select="DADOS-BASICOS-DO-CAPITULO/@DOI"/></DOI>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DO-CAPITULO/@IDIOMA"/></IDIOMA>
				<TITULO-DO-LIVRO><xsl:value-of select="DETALHAMENTO-DO-CAPITULO/@TITULO-DO-LIVRO"/></TITULO-DO-LIVRO>
				<ISBN><xsl:value-of select="DETALHAMENTO-DO-CAPITULO/@ISBN"/></ISBN>
				<AUTORES>
					<xsl:for-each select="AUTORES">
						<NOME-AUTOR><xsl:value-of select="@NOME-COMPLETO-DO-AUTOR"/></NOME-AUTOR>
						<ORDEM-DE-AUTORIA><xsl:value-of select="@ORDEM-DE-AUTORIA"/></ORDEM-DE-AUTORIA>
						<NRO-ID-CNPQ><xsl:value-of select="@NRO-ID-CNPQ"/></NRO-ID-CNPQ>
					</xsl:for-each>
				</AUTORES>
    			</LIVRO>
    	</xsl:for-each>
	</LIVROS-PUBLICADOS-OU-ORGANIZADOS>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
