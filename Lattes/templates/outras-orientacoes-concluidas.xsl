<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<CURRICULO>
	<ID><xsl:value-of select="CURRICULO-VITAE/@NUMERO-IDENTIFICADOR"/></ID>
	<NOME><xsl:value-of select="CURRICULO-VITAE/DADOS-GERAIS/@NOME-COMPLETO"/></NOME>
	<ORIENTACOES-CONCLUIDAS>
		
		<xsl:for-each select="CURRICULO-VITAE/OUTRA-PRODUCAO/ORIENTACOES-CONCLUIDAS/ORIENTACOES-CONCLUIDAS-PARA-MESTRADO">
			<ORIENTACAO>
				<TIPO>
					MESTRADO
				</TIPO>

				<TITULO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@TITULO"/>
				</TITULO>

				<ANO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@ANO"/>
				</ANO>

				<NATUREZA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NATUREZA"/>
				</NATUREZA>

				<IDIOMA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@IDIOMA"/>
				</IDIOMA>

				<PAIS>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@PAIS"/>
				</PAIS>

				<ORIENTADO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-ORIENTADO"/>
				</ORIENTADO>

				<CURSO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DO-CURSO"/>
				</CURSO>

				<INSTITUICAO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DA-INSTITUICAO"/>
				</INSTITUICAO>

				<AREAS-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
				</AREAS-DO-CONHECIMENTO>
				<PALAVRAS-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-1"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-2"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-3"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-4"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-5"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-6"/></NOME></PALAVRA-CHAVE>
				</PALAVRAS-CHAVE>

				<AGENCIA>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-MESTRADO/@NOME-DA-AGENCIA"/>
				</AGENCIA>

			</ORIENTACAO>
		</xsl:for-each>


		<xsl:for-each select="CURRICULO-VITAE/OUTRA-PRODUCAO/ORIENTACOES-CONCLUIDAS/ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO">
			<ORIENTACAO>
				<TIPO>
					DOUTORADO
				</TIPO>

				<TITULO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@TITULO"/>
				</TITULO>

				<ANO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@ANO"/>
				</ANO>

				<NATUREZA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NATUREZA"/>
				</NATUREZA>

				<IDIOMA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@IDIOMA"/>
				</IDIOMA>

				<PAIS>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@PAIS"/>
				</PAIS>

				<ORIENTADO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-ORIENTADO"/>
				</ORIENTADO>

				<CURSO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DO-CURSO"/>
				</CURSO>

				<INSTITUICAO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DA-INSTITUICAO"/>
				</INSTITUICAO>
				<AREAS-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
				</AREAS-DO-CONHECIMENTO>
				<PALAVRAS-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-1"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-2"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-3"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-4"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-5"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-6"/></NOME></PALAVRA-CHAVE>
				</PALAVRAS-CHAVE>

				<AGENCIA>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO/@NOME-DA-AGENCIA"/>
				</AGENCIA>

			</ORIENTACAO>
		</xsl:for-each>

		<xsl:for-each select="CURRICULO-VITAE/OUTRA-PRODUCAO/ORIENTACOES-CONCLUIDAS/ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO">
			<ORIENTACAO>
				<TIPO>
					POS-DOUTORADO
				</TIPO>

				<TITULO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@TITULO"/>
				</TITULO>

				<ANO>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@ANO"/>
				</ANO>

				<NATUREZA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NATUREZA"/>
				</NATUREZA>

				<IDIOMA>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@IDIOMA"/>
				</IDIOMA>

				<PAIS>
					<xsl:value-of select="DADOS-BASICOS-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@PAIS"/>
				</PAIS>

				<ORIENTADO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DO-ORIENTADO"/>
				</ORIENTADO>

				<CURSO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DO-CURSO"/>
				</CURSO>

				<INSTITUICAO>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DA-INSTITUICAO"/>
				</INSTITUICAO>
				<AREAS-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
				</AREAS-DO-CONHECIMENTO>
				<PALAVRAS-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-1"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-2"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-3"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-4"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-5"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-6"/></NOME></PALAVRA-CHAVE>
				</PALAVRAS-CHAVE>

				<AGENCIA>
					<xsl:value-of select="DETALHAMENTO-DE-ORIENTACOES-CONCLUIDAS-PARA-POS-DOUTORADO/@NOME-DA-AGENCIA"/>
				</AGENCIA>
			</ORIENTACAO>
		</xsl:for-each>

		<xsl:for-each select="CURRICULO-VITAE/OUTRA-PRODUCAO/ORIENTACOES-CONCLUIDAS/OUTRAS-ORIENTACOES-CONCLUIDAS">
			<ORIENTACAO>
				<TIPO>
					OUTRAS
				</TIPO>

				<TITULO>
					<xsl:value-of select="DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@TITULO"/>
				</TITULO>

				<ANO>
					<xsl:value-of select="DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@ANO"/>
				</ANO>

				<NATUREZA>
					<xsl:value-of select="DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NATUREZA"/>
				</NATUREZA>

				<IDIOMA>
					<xsl:value-of select="DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@IDIOMA"/>
				</IDIOMA>

				<PAIS>
					<xsl:value-of select="DADOS-BASICOS-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@PAIS"/>
				</PAIS>

				<ORIENTADO>
					<xsl:value-of select="DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DO-ORIENTADO"/>
				</ORIENTADO>

				<CURSO>
					<xsl:value-of select="DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DO-CURSO"/>
				</CURSO>

				<INSTITUICAO>
					<xsl:value-of select="DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DA-INSTITUICAO"/>
				</INSTITUICAO>
				<AREAS-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-1/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-2/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-3/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-4/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-5/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-6/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
					<AREA-DO-CONHECIMENTO>
					<NOME><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-AREA-DO-CONHECIMENTO"/></NOME>
					<NOME-ESPECIALIDADE><xsl:value-of select="AREAS-DO-CONHECIMENTO/AREA-DO-CONHECIMENTO-7/@NOME-DA-ESPECIALIDADE"/></NOME-ESPECIALIDADE>
					</AREA-DO-CONHECIMENTO>
				</AREAS-DO-CONHECIMENTO>
				<PALAVRAS-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-1"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-2"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-3"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-4"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-5"/></NOME></PALAVRA-CHAVE>
					<PALAVRA-CHAVE><NOME><xsl:value-of select="PALAVRAS-CHAVE/@PALAVRA-CHAVE-6"/></NOME></PALAVRA-CHAVE>
				</PALAVRAS-CHAVE>
				<AGENCIA>
					<xsl:value-of select="DETALHAMENTO-DE-OUTRAS-ORIENTACOES-CONCLUIDAS/@NOME-DA-AGENCIA"/>
				</AGENCIA>

			</ORIENTACAO>
		</xsl:for-each>
    	
	</ORIENTACOES-CONCLUIDAS>
</CURRICULO>
  
</xsl:template>
</xsl:stylesheet>
