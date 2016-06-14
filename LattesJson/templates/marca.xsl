<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<MARCAS>
		<xsl:for-each select="CURRICULO-VITAE/PRODUCAO-TECNICA/MARCA">
			<MARCA>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DA-MARCA/@TITULO"/></TITULO>
				<PAIS><xsl:value-of select="DADOS-BASICOS-DA-MARCA/@PAIS"/></PAIS>
				<ANO><xsl:value-of select="DADOS-BASICOS-DA-MARCA/@ANO-DESENVOLVIMENTO"/></ANO>
				<FINALIDADE><xsl:value-of select="DETALHAMENTO-DA-MARCA/@FINALIDADE"/></FINALIDADE>
				<AUTORES>
					<xsl:for-each select="AUTORES">
						<NOME-AUTOR><xsl:value-of select="@NOME-COMPLETO-DO-AUTOR"/></NOME-AUTOR>
						<ORDEM-DE-AUTORIA><xsl:value-of select="@ORDEM-DE-AUTORIA"/></ORDEM-DE-AUTORIA>
					</xsl:for-each>
				</AUTORES>
    			</LIVRO>
    	</xsl:for-each>
	</MARCAS>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
