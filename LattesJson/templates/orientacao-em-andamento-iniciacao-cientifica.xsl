<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<ORIENTACOES-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA>
		<xsl:for-each select="CURRICULO-VITAE/DADOS-COMPLEMENTARES/ORIENTACOES-EM-ANDAMENTO/ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA">
			<ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA>
		  		<TITULO><xsl:value-of select="DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@TITULO-DO-TRABALHO"/></TITULO>
				<DOI><xsl:value-of select="DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@DOI"/></DOI>
				<IDIOMA><xsl:value-of select="DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@IDIOMA"/></IDIOMA>
				<ANO><xsl:value-of select="DADOS-BASICOS-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@ANO"/></ANO>
				<NOME-DO-ORIENTANDO><xsl:value-of select="DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@NOME-DO-ORIENTANDO"/></NOME-DO-ORIENTANDO>
				<NOME-INSTITUICAO><xsl:value-of select="DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@NOME-INSTITUICAO"/></NOME-INSTITUICAO>
				<NOME-CURSO><xsl:value-of select="DETALHAMENTO-DA-ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA/@NOME-CURSO"/></NOME-CURSO>
			</ORIENTACAO-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA>
    	</xsl:for-each>
	</ORIENTACOES-EM-ANDAMENTO-DE-INICIACAO-CIENTIFICA>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
