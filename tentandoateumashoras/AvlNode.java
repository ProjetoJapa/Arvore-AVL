package tentandoateumashoras;

/* ArvoreAvl.java
 * Arvore AVL 
*@ALUNO: KLEYSON DINIZ MOUREIRA
 * INSTITUICAO: Faculdade Internacional da Paraiba (FPB)
 * CURSO: CCO - 4 Per�odo
 * OBJETIVO: Realiza��o de AVL - JAVA (Atividade N1)
 * DESENVOLVEDOR : Kleyson Diniz Moureira
 * LOCAL: Centro/FPB - Jo�o Pessoa/PB - Brasil
 * DATA: 25/04/2021 (Cria��o)
 * ULTIMA MODIFICACAO: 27/04/2021 �s 01h24min AM (24h)
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