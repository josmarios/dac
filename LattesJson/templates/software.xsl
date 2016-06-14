<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<SOFTWARES>
		<xsl:for-each select="CURRICULO-VITAE/PRODUCAO-TECNICA/SOFTWARE">
			<SOFTWARE>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DO-SOFTWARE/@TITULO-DO-SOFTWARE"/></TITULO>
				<DOI><xsl:value-of select="DADOS-BASICOS-DO-SOFTWARE/@DOI"/></DOI>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DO-SOFTWARE/@IDIOMA"/></IDIOMA>
				<FINALIDADE><xsl:value-of select="DETALHAMENTO-DO-SOFTWARE/@FINALIDADE"/></FINALIDADE>
				<AUTORES>
					<xsl:for-each select="AUTORES">
						<NOME-AUTOR><xsl:value-of select="@NOME-COMPLETO-DO-AUTOR"/></NOME-AUTOR>
						<ORDEM-DE-AUTORIA><xsl:value-of select="@ORDEM-DE-AUTORIA"/></ORDEM-DE-AUTORIA>
					</xsl:for-each>
				</AUTORES>
    			</SOFTWARE>
    	</xsl:for-each>
	</SOFTWARES>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
