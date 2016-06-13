<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<ARTIGOS-PUBLICADOS>
		<xsl:for-each select="CURRICULO-VITAE/PRODUCAO-BIBLIOGRAFICA/ARTIGOS-PUBLICADOS/ARTIGO-PUBLICADO">
			<ARTIGO>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DO-ARTIGO/@TITULO-DO-ARTIGO"/></TITULO>
				<ANO><xsl:value-of select="DADOS-BASICOS-DO-ARTIGO/@ANO-DO-ARTIGO"/></ANO>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DO-ARTIGO/@IDIOMA"/></IDIOMA>
				<PERIODICO><xsl:value-of select="DETALHAMENTO-DO-ARTIGO/@TITULO-DO-PERIODICO-OU-REVISTA"/></PERIODICO>
				<LOCAL><xsl:value-of select="DETALHAMENTO-DO-ARTIGO/@LOCAL-DE-PUBLICACAO"/></LOCAL>
				<AUTORES>
					<xsl:for-each select="AUTORES">
						<AUTOR><xsl:value-of select="@NOME-COMPLETO-DO-AUTOR"/></AUTOR>
					</xsl:for-each>
				</AUTORES>
    			</ARTIGO>
    	</xsl:for-each>
	</ARTIGOS-PUBLICADOS>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
