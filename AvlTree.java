package tentandoateumashoras;
import java.util.*;

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

public class AvlTree {
	public static AvlNode root = null;
	Calendar cal = new GregorianCalendar();
	
	public AvlTree() {
		root = null;
	}

	private boolean isEmpty() {
		return root == null;
	}

	private static int altura(AvlNode t) {
		return t == null ? -1 : t.altura;
	}

	private static int max(int ladoesq, int ladodir) {
		return ladoesq > ladodir ? ladoesq : ladodir;
	}

	private int getfator(AvlNode t) {
		return altura(t.esquerda) - altura(t.direita);
	}

	private boolean inserir(int x) {
		root = inserir(x, root);
		return true;
	}

	private AvlNode inserir(int x, AvlNode t) {
		if (t == null) {
			t = new AvlNode(x, null, null);
		} else if (x == t.conteudochave)
			System.out.println("ConteudoChave já presente!");
		else if (x < t.conteudochave)
			t.esquerda = inserir(x, t.esquerda);
		else if (x > t.conteudochave)
			t.direita = inserir(x, t.direita);
		t = balance(t);

		return t;
	}
	
	private AvlNode balance(AvlNode t) {
		if (getfator(t) == 2) {
			if (getfator(t.esquerda) > 0) {
				System.out.println("\nRotacao para direita.");
				t = RotacaoDir(t);
			} else {
				System.out.println("\nRotacao dupla para direita.");
				t = RotacaoDuplaDir(t);
			}
		} else if (getfator(t) == -2) {
			if (getfator(t.direita) < 0) {
				System.out.println("\nRotacao para esquerda.");
				t = RotacaoEsq(t);
			} else {
				System.out.println("\nRotacao dupla para esquerda.");
				t = RotacaoDuplaEsq(t);
			}
		}
		t.altura = max(altura(t.esquerda), altura(t.direita)) + 1;
		return t;
	}
	
	private static AvlNode RotacaoDir(AvlNode aux2) {
		AvlNode aux1 = aux2.esquerda;
		aux2.esquerda = aux1.direita;
		aux1.direita = aux2;
		aux2.altura = max(altura(aux2.esquerda), altura(aux2.direita)) + 1;
		aux1.altura = max(altura(aux1.esquerda), aux2.altura) + 1;
		return aux1;
	}

	private static AvlNode RotacaoEsq(AvlNode aux1) {
		AvlNode aux2 = aux1.direita;
		aux1.direita = aux2.esquerda;
		aux2.esquerda = aux1;
		aux1.altura = max(altura(aux1.esquerda), altura(aux1.direita)) + 1;
		aux2.altura = max(altura(aux2.direita), aux1.altura) + 1;
		return aux2;
	}

	private static AvlNode RotacaoDuplaDir(AvlNode aux3) {
		aux3.esquerda = RotacaoEsq(aux3.esquerda);
		return RotacaoDir(aux3);
	}

	private static AvlNode RotacaoDuplaEsq(AvlNode aux1) {
		aux1.direita = RotacaoDir(aux1.direita);
		return RotacaoEsq(aux1);
	}

	private AvlNode ProcurarAlturar_de_Um_No(int elementoP) {
		return ProcurarAlturar_de_Um_No(root, elementoP);
	}

	private void remover(int x) {
		root = remover(x, root);
	}

	private AvlNode remover(int x, AvlNode t) {
		if (t == null) {
			System.out.println("ConteudoChave nao encontrada para remocao!");
			return null;
		}
		if (x < t.conteudochave) {
			t.esquerda = remover(x, t.esquerda);
			return balance(t);
		} else if (x > t.conteudochave) {
			t.direita = remover(x, t.direita);
			return balance(t);
		} else {
			if (t.esquerda == null && t.direita == null) {
				return null;
			}
			if (t.esquerda == null) {
				return t.direita;
			}
			if (t.direita == null) {
				return t.esquerda;
			} else if (t.esquerda != null && t.direita != null) {
				t.conteudochave = minDir(t.direita).conteudochave;
				t.direita = remover(t.conteudochave, t.direita);
			} else
				t = (t.esquerda != null) ? t.esquerda : t.direita;
			return balance(t);
		}
	}
	
	private AvlNode minDir(AvlNode t) {
		if (t.esquerda == null) {
			return t;
		} else {
			t = t.esquerda;
			return minDir(t);
		}
	}
	
	private AvlNode procurar(int elementoP) {
		return procurar(root, elementoP);
	}	

	protected AvlNode procurar(AvlNode p, int elementoP) {
		while (p != null) {
			if (elementoP == p.conteudochave) {
				System.out.println("ConteudoChave " + elementoP + " encontrada!");
				return p;
			} else if (elementoP < p.conteudochave)
				p = p.esquerda;
			else
				p = p.direita;
		}
		System.out.println("ConteudoChave " + elementoP + " nao encontrada!");
		return null;
	}
	
	private int soma_dos_NO_da_AVL(AvlNode raiz){
		if (raiz == null){return 0;}
		else{
			AvlNode.tamanho++;
			if (raiz.direita != null){
				AvlNode.tamanho = soma_dos_NO_da_AVL(raiz.direita);
			}
			if (raiz.esquerda != null){
				AvlNode.tamanho = soma_dos_NO_da_AVL(raiz.esquerda);
			}
			
		}
		
		int recebe_tamanho = AvlNode.tamanho;
		AvlNode.tamanho = 0;
		return recebe_tamanho;
	}	
	
	private int soma_dos_valores_da_arvore(AvlNode raiz) {

        if(raiz == null)
            return 0;
        else {
            int soma = 0;
            soma += soma_dos_valores_da_arvore(raiz.esquerda);
            soma += soma_dos_valores_da_arvore(raiz.direita);
            soma += raiz.conteudochave;
            return soma;
        }
    }
	
	private int Media_Value_AVL(int QtdNO, int SomaNO) {
		int resultado;
		if ((QtdNO == 0) && (SomaNO == 0)) resultado = 0;
		else resultado = (SomaNO / QtdNO);
		return resultado;

	}

	
	public void InOrdem() {
		InOrdem(root);
	}

	protected void InOrdem(AvlNode p) {
		if (p != null) {
			InOrdem(p.esquerda);
			System.out.print(p.conteudochave + " - ");
			InOrdem(p.direita);
		}
	}

	public void PreOrdem() {
		PreOrdem(root);
	}

	protected void PreOrdem(AvlNode p) {
		if (p != null) {
			System.out.print(p.conteudochave + " - ");
			PreOrdem(p.esquerda);
			PreOrdem(p.direita);
		}
	}

	public void PosOrdem() {
		PosOrdem(root);
	}

	protected void PosOrdem(AvlNode p) {
		if (p != null) {
			PosOrdem(p.esquerda);
			PosOrdem(p.direita);
			System.out.print(p.conteudochave + " - ");
		}
	}	

	
	protected AvlNode ProcurarAlturar_de_Um_No(AvlNode p, int elementoP) {
		while (p != null) {
			if (elementoP == p.conteudochave) {
				System.out.println("\nElemento: " + elementoP + " encontrado.\n" + "Altura: "
						+ Procura_e_depois_calcula_a_altura(root) + ".");
				return null;
			}

			else if (elementoP < p.conteudochave)
				p = p.esquerda;
			else
				p = p.direita;
		}
		System.out.println("ConteudoChave " + elementoP + " nao encontrada!");

		return null;
	}
	
	public int Procura_e_depois_calcula_a_altura(AvlNode root) {
		if (root == null) {
			return -1;
		}
		if (root.direita == null && root.esquerda == null) {
			return 0;
		} else if (root.esquerda == null) {
			return 1 + Procura_e_depois_calcula_a_altura(root.direita);
		} else if (root.direita == null) {
			return 1 + Procura_e_depois_calcula_a_altura(root.esquerda);
		} else {
			if (Procura_e_depois_calcula_a_altura(root.esquerda) > Procura_e_depois_calcula_a_altura(root.direita)) {
				return 1 + Procura_e_depois_calcula_a_altura(root.esquerda);
			} else {
				return 1 + Procura_e_depois_calcula_a_altura(root.direita);
			}
		}

	}
	
	public int ProfundidadeArvore(AvlNode atual) {
		if (atual == null) {
			return 0;
		}
		if (atual.direita == null && atual.esquerda == null) {
			return 1;
		} else if (atual.esquerda == null) {
			return 1 + (1 + altura(atual.direita));
		} else if (atual.esquerda == null) {
			return 1 + (1 + altura(atual.esquerda));
		} else {
			if (altura(atual.esquerda) > altura(atual.direita)) {
				return 1 + (1 + altura(atual.esquerda));
			} else {
				return 1 + (1 + altura(atual.direita));
			}
		}
	}	

		
	private void imprime(AvlNode f) {
		if (isEmpty()) {
			return ;
		} else {
			System.out.println("NO : " + f.conteudochave + " - Seu fator de balanceamento er: " + getfator(f));
			if (f.direita != null) {
				imprime(f.direita);
			}
			if (f.esquerda != null) {
				imprime(f.esquerda);
			}
		}
	}

	private void exibirArv() {
		if (isEmpty()) {
			System.out.println("Árvore vazia!");
			return;
		}
		String separador = String.valueOf("  |__");
		System.out.println(AvlTree.root.conteudochave + " (RAIZ)");
		exibirSubArv(root.esquerda, separador);
		exibirSubArv(root.direita, separador);

	}

	private void exibirSubArv(AvlNode node, String separador) {
		if (node != null) {
			AvlNode Pai = this.procurarPai(node.conteudochave);
			if (node.equals(Pai.esquerda) == true) {
				System.out.println(separador + node.conteudochave + " (ESQ)");
			} else {
				System.out.println(separador + node.conteudochave + " (DIR)");
			}
			exibirSubArv(node.esquerda, "     " + separador);
			exibirSubArv(node.direita, "     " + separador);
		}
	}
	
	private AvlNode procurarPai(int elementoPP) {
		AvlNode p = root;
		AvlNode prev = null;
		while (p != null && !(p.conteudochave == elementoPP)) {
			prev = p;
			if (p.conteudochave < elementoPP)
				p = p.direita;
			else
				p = p.esquerda;
		}
		if (p != null && p.conteudochave == elementoPP)
			return prev;
		return null;
	}

	private void Saudacao() {
		Date d = new Date();
		int hour = d.getHours();
		if (hour < 5) {
			System.out.print("\nBoa noite, Professor.\n");
		} else if (hour < 8) {
			System.out.print("\nBom dia, Professor.\n");
		} else if (hour < 12) {
			System.out.print("\nBom dia, Professor.\n");
		} else if (hour < 18) {
			System.out.print("\nBoa tarde, Professor.\n");
		} else {
			System.out.print("\nBoa noite, Professor.\n");
		}
	}
	
	private boolean removerTodosElementos(){
		if (isEmpty()) 	System.out.print("\nA Arvore ja esta vazia.\n");
		else {
			while (this.root != null) {
				remover(root.conteudochave);
			} System.out.print("\nA Arvore foi esvaziada.\n");
		}
		return true;
	}
	

	private void Teste_Implementacao_de_Balanceamento() {
	
	System.out.print("\n-----------------------LEIA------------------------\n");
	System.out.print("\nNOTA:\nPOR FAVOR, VERIFICAR O CODIGO.\n"
			+ "CASO HAJA NECESSIDADE,\nFAVOR TESTAR OUTROS VALORES NA INCLUSAO.\n");
	System.out.print("\nImplementacao com metodo de balanceamento:\n");
	System.out.print("\nExemplo 1: Inserindo [1,2,3]\n");
	inserir(1);	inserir(2);	inserir(3);
	exibirArv();
	System.out.print("\n");
	imprime(root);
	remover(1);	remover(2);	remover(3);
	System.out.print("\n_________________________________________\n");
	System.out.print("\nExemplo 2: Inserindo [3,2,1]\n");
	inserir(3); inserir(2);	inserir(1);
	exibirArv();
	System.out.print("\n");
	imprime(root);
	remover(3); remover(2); remover(1);
	System.out.print("\n_________________________________________\n");
	System.out.print("\nExemplo 3: Inserindo [3,5,4]\n");
	inserir(3);	inserir(5);	inserir(4);
	exibirArv();
	System.out.print("\n");
	imprime(root);
	remover(3);	remover(5);	remover(4);
	System.out.print("\n_________________________________________\n");
	System.out.print("\nExemplo 4: Inserindo [3,1,2]\n");
	inserir(3);	inserir(1);	inserir(2);
	exibirArv();
	System.out.print("\n");
	imprime(root);
	remover(3);	remover(1);	remover(2);
	System.out.print("\n_________________________________________\n");
	}
		
	@SuppressWarnings("resource")
	void IniciarMenu() {
		Scanner entrada = new Scanner(System.in);
		String tipodeOperacao = " ", valor;

		while (tipodeOperacao != null) {
			
			System.out.print("\nDesenvolvedor: KLEYSON DINIZ MOUREIRA");

			
			Saudacao();
			
			System.out.print("\nSelecione um das opcoes: ");
			System.out.print("\n1 - Incluir novo elemento");
			System.out.print("\n2 - Apagar um elemento existente");
			System.out.print("\n3 - Buscar um elemento");
			System.out.print("\n4 - Imprimir a media dos valores armazenados na Arvore");
			System.out.print("\n5 - Imprimir em ordem simetrica, pré-ordem e pos-ordem");
			System.out.print("\n6 - Imprimir a altura de um elemento");
			System.out.print("\n7 - Imprimir a profundidade da arvore");
			System.out.print("\n8 - Implementar o metodo de balanceamento");
			System.out.print("\n9 - Limpar Arvore (Bonus)");
			System.out.print("\n0 - Sair\n");

			System.out.print("\nDigite sua opcao aqui: ");
			tipodeOperacao = entrada.next();

			if (tipodeOperacao == null || tipodeOperacao.length() == 0) {
				return;
			}

			boolean testeOperador = false;
			for (int j = 0; j < tipodeOperacao.length(); j++) {
				if (!Character.isDigit(tipodeOperacao.charAt(j))) {
					testeOperador = true;
				}
			}

			if (testeOperador) {
				System.out.print("\nEntrada invalida, por favor escolha um " + "numero de 1 a 5\n");
			} else {

				if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '4') {
					System.out.print("\nA quantidade de NO nesta Avore er de: " + soma_dos_NO_da_AVL(root));
					System.out.print("\nA soma dos valores de todos os NO er: " + soma_dos_valores_da_arvore(root));
					System.out.print("\nA media dos valores er aproximadamente: " + Media_Value_AVL(soma_dos_NO_da_AVL(root), soma_dos_valores_da_arvore(root))+"\n");

				}

				else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '5') {
					System.out.print("\n-------Imprimindo Arvore-------\n");
					System.out.print("\nArvore Percorrida em InOrdem:\n");
					InOrdem();
					System.out.print("\nArvore Percorrida em PreOrdem:\n");
					PreOrdem();
					System.out.print("\nArvore Percorrida em PosOrdem:\n");
					PosOrdem();
					System.out.print("\n\n");
					exibirArv();
					System.out.print("\n");
					imprime(root);

				}

				else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '7') {
					System.out.print("\nProfundidade da Arvore: " + ProfundidadeArvore(root) + "\n");
				}

				else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '8') {
					removerTodosElementos();
					Teste_Implementacao_de_Balanceamento();

				}

				else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '9') {
					removerTodosElementos();
				}
				
				else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '0') {
					tipodeOperacao = null;
					System.out.print("\nFim do programa\n");
				}

				else {
					System.out.print("\nInforme qual valor:\n");
					valor = entrada.next();

					if (valor == null || valor.length() == 0) {
						return;
					}
					boolean testeparametro = false;
					for (int j = 0; j < valor.length(); j++) {
						if (!Character.isDigit(valor.charAt(j))) {
							testeparametro = true;
						}
					}
					if (testeparametro) {
						System.out.print("\nEntrada incorreta (somente inteiros)\n");

						;
					} else {
						int x = Integer.parseInt(valor);

						if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '1') {
							inserir(x);
						} else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '2') {
							remover(x);
						} else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '3') {
							procurar(x);
						} else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '6') {
							ProcurarAlturar_de_Um_No(x);
						}
					}
				}
			}
		}
	}
}