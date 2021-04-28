package tentandoateumashoras;

/* ArvoreAvl.java
 * Arvore AVL 
*@ALUNO: KLEYSON DINIZ MOUREIRA
 * INSTITUICAO: Faculdade Internacional da Paraiba (FPB)
 * CURSO: CCO - 4 Período
 * OBJETIVO: Realização de AVL - JAVA (Atividade N1)
 * DESENVOLVEDOR : Kleyson Diniz Moureira
 * LOCAL: Centro/FPB - João Pessoa/PB - Brasil
 * DATA: 25/04/2021 (Criação)
 * ULTIMA MODIFICACAO: 27/04/2021 às 01h24min AM (24h)
@CONTATO:	Email: kleysondiniz@gmail.com */

public class AvlNode {
	protected int altura, conteudochave;
	protected AvlNode esquerda, direita;
	public static int tamanho;

	public AvlNode(int elemento) {
		this(elemento, null, null);
	}

	public AvlNode(int elemento, AvlNode Esq, AvlNode Dir) {
		conteudochave = elemento;
		esquerda = Esq;
		direita = Dir;
		altura = 0;
	}
}