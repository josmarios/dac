<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO>
		<xsl:for-each select="CURRICULO-VITAE/OUTRA-PRODUCAO/ORIENTACOES-CONCLUIDAS/ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO">
			<ORIENTACAO-CONCLUIDA-PARA-POS-DOUTORADO>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@TITULO"/></TITULO>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@IDIOMA"/></IDIOMA>
				<ANO><xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@ANO"/></ANO>
				<NOME-DO-ORIENTANDO><xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DO-ORIENTANDO"/></NOME-DO-ORIENTANDO>
				<NOME-INSTITUICAO><xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DA-INSTITUICAO"/></NOME-INSTITUICAO>
				<NOME-CURSO><xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DO-CURSO"/></NOME-CURSO>
			</ORIENTACAO--PARA-POS-DOUTORADO>
    	</xsl:for-each>
	</ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
