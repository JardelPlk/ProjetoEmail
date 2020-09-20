package steam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import steam.bd.EmailEmpresaDAO;
import steam.bd.EmailPessoaDAO;
import steam.bd.EmpresaDAO;
import steam.bd.PessoaDAO;
import steam.bd.UtilBD;
import steam.entidades.Cadastro;
import steam.entidades.Cliente;
import steam.entidades.Data;
import steam.entidades.Destinatario;
import steam.entidades.Email;
import steam.entidades.Empresa;
import steam.entidades.Pessoa;

public class Main {
	
	public static void main(String args[]) {
		UtilBD.initBD();
		//Variaveis de entrada
		Scanner entradaNumero = new Scanner(System.in);
		Scanner entradaString = new Scanner(System.in);
		//Para pausar o programa em alguns momentos
		String pausa;
		//listas que serao utilizadas
		ArrayList<Pessoa> cadastroPessoa = new ArrayList<Pessoa>();
		ArrayList<Empresa> cadastroEmpresa = new ArrayList<Empresa>();
		ArrayList<Email> listaEmail = new ArrayList<Email>();
		//Outras variaveis utilizadas no código
		String nomeUsuario;
		String nome;
		int dia;
		int mes;
		int ano;
		String sexo;
		String senha;
		String dataNascimento;
		//Utilizaremos o polimorfismo com as variais do tipo Cadastro
		Cadastro contato;
		Cadastro autor;
		Cadastro destinatario;
		Pessoa pessoa;
		Empresa empresa;
		Pessoa cliente;
		int indice1;
		int indice2;
		boolean controle;
		int resposta1;
		int resposta2;
		String ramo;
		boolean controle1;
		int opcao1;
		int opcao2;
		int opcao3;
		String assunto;
		String mensagem;
		String dataEmail;
		Email email;
		int size;
		int codigoAutoIncremento = 0;
		int codigo;
		int indice3;
		Integer idEmail;
		Integer id;
		//Variaveis DAO
		EmpresaDAO empresaDAO = new EmpresaDAO();
		List<Empresa> empresaLista = new ArrayList<Empresa>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> pessoaLista = new ArrayList<Pessoa>();
		EmailEmpresaDAO emailEmpresaDAO = new EmailEmpresaDAO();
		EmailPessoaDAO emailPessoaDAO = new EmailPessoaDAO();
		List<Email> emailLista = new ArrayList<Email>();
		
		while(true) {
			
			System.out.println("Sistema de Email\n\nDigite uma das opções:\n\n1 - Acessar opções de criação, atualização, listagem e remoção de usuários\n2 - Acessar opções de criação, atualização, listagem e remoção de emails\n3 - Sair");
			opcao1 = entradaNumero.nextInt();
			
			switch(opcao1) {
				case 1:
					while(true) {
						System.out.println("Digite uma das opções: \n\n1 - Opções de cadastro de usuários\n2 - Opções de clientes do usuário\n3 - Voltar\n");
						opcao2 = entradaNumero.nextInt();
						
						switch(opcao2) {
							case 1://Opções de cadastros
								while(true) {
									System.out.println("Digite uma das opções:\n\n1 - Cadastrar uma nova conta\n2 - Listar todos os cadastros\n3 - Atualizar cadastro\n4 - Remover cadastro\n5 - Voltar\n");
									opcao3 = entradaNumero.nextInt();
									
									switch(opcao3) {
										case 1://Cadastrar usuarios
											//Usuário informará o tipo de conta que ele deseja cadastrar
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											//O programa pesquisará no Array que o usuário informar, usamos a variavel controle para informar se
											//existem ou nao usuarios com o mesmo nome de usuario
											System.out.println("\nDigite o nome de usuario: ");
											nomeUsuario = entradaString.nextLine();
											controle = true;
											if(resposta1 == 1) {
												pessoaLista = pessoaDAO.todos();
												for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) {
													if(pessoaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuario existente!\n");
														controle = false;
														break;
													}
												}
											}else {
												empresaLista = empresaDAO.todos();
												for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
													if(empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuário existente!\n");
														controle = false;
														break;
													}
												}
											}
												
											if(controle == false)
												break;
											//O usuário informará outras informações
											System.out.println("\nDigite o nome:");
											nome = entradaString.nextLine();
											System.out.println("\nDigite a data de nascimento:");
											dataNascimento = entradaString.nextLine();
											System.out.println("\nDigite a senha:");
											senha = entradaString.nextLine();
											
											//Usamos novamente a variavel resposta para podermos armazenar o tipo de usuario no Array correto
											if(resposta1 == 1) {
												System.out.println("\nDigite o sexo:");
												sexo = entradaString.nextLine();
												pessoa = new Pessoa(null, nome, nomeUsuario, senha, dataNascimento, sexo);
												pessoaDAO.adicionar(pessoa);
											}else {
												System.out.println("\nDigite o ramo da empresa:");
												ramo = entradaString.nextLine();
												empresa = new Empresa(null, nome, nomeUsuario, senha, dataNascimento, ramo);
												empresaDAO.adicionar(empresa);
											}
											
											System.out.println("\nCadastro realizado com sucesso!");
											pausa = entradaString.nextLine();
											break;
										case 2://Listar os dados cadastros, menos as senhas, pois elas são usadas em outras funções para autentificar o usuário 
											//Listara o tipo empresarial ou o tipo pessoal
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											if(resposta1 == 1) {
												pessoaLista = pessoaDAO.todos();
												for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) 
													System.out.printf("\nDados do usuário:\nId: %d\nNome de usuario: %s\nNome: %s\nData de Criação: %s\nSexo: %s\n",pessoaLista.get(indice1).getId(),pessoaLista.get(indice1).getNomeUsuario(),pessoaLista.get(indice1).getNome(),pessoaLista.get(indice1).getDataNascimento(),pessoaLista.get(indice1).getSexo());
											}else {
												empresaLista = empresaDAO.todos();
												for(indice1 = 0; indice1 < empresaLista.size(); indice1++) 
													System.out.printf("\nDados do usuário:\nId: %d\nNome de usuario: %s\nNome: %s\nData de Nascimento: %s\nRamo: %s\n",empresaLista.get(indice1).getId(),empresaLista.get(indice1).getNomeUsuario(),empresaLista.get(indice1).getNome(),empresaLista.get(indice1).getDataNascimento(),empresaLista.get(indice1).getRamo());
											}
											pausa = entradaString.nextLine();
											break;
										case 3://Atualizar cadastro
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuario: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											//Função para ver se o nome usuario e a senha existem na lista
											controle = false;
											pessoaLista = pessoaDAO.todos();
											empresaLista = empresaDAO.todos();
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) {
													if((pessoaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (pessoaLista.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário existente!");
														controle = true;
														break;
													}	
												}
											}else {
												for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
													if((empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (empresaLista.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário existente!");
														controle = true;
														break;
													}	
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não encontrado");
												break;
											}
										
												System.out.println(empresaLista.get(indice1).getNomeUsuario());
											
											//Ver se o novo nome ja existe na lista
											controle = true;
											System.out.println("\nDigite o novo nome de usuario: ");
											nomeUsuario = entradaString.nextLine();
											if(resposta1 == 1) {
												for(int indice = 0; indice < pessoaLista.size(); indice++) {
													if(pessoaLista.get(indice).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuario existente");
														controle = false;
														break;
													}
												}
											}else {
												for(int indice = 0; indice < empresaLista.size(); indice++) {
													if(empresaLista.get(indice).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuario existente");
														controle = false;
														break;
													}
												}
											}
											if(controle == false)
												break;

											//Recebendo todos os paramentros
											System.out.println("\nDigite o novo nome: ");
											nome = entradaString.nextLine();
											System.out.println("\nDigite a nova data de nascimento: ");
											dataNascimento = entradaString.nextLine();
											System.out.println("\nDigite a nova senha: ");
											senha = entradaString.nextLine();
											
											//Atualizando o cadastro com sets
											if(resposta1 == 1) {
												System.out.println("\nDigite o novo sexo: ");
												sexo = entradaString.nextLine();
												pessoa = pessoaLista.get(indice1);
												pessoa.setNomeUsuario(nomeUsuario);
												pessoa.setNome(nome);
												pessoa.setDataNascimento(dataNascimento);
												pessoa.setSexo(sexo);
												pessoa.setSenha(senha);
												pessoaDAO.atualizar(pessoa);
											}else {
												System.out.println("\nDigite o novo ramo da empresa: ");
												ramo = entradaString.nextLine();
												empresaLista.get(indice1).setNomeUsuario(nomeUsuario);
												empresaLista.get(indice1).setNome(nome);
												empresaLista.get(indice1).setDataNascimento(dataNascimento);
												empresaLista.get(indice1).setRamo(ramo);
												empresaLista.get(indice1).setSenha(senha);
												empresa = empresaLista.get(indice1);
												empresaDAO.atualizar(empresa);
											}
											
											System.out.println("\nCadastro atualizado com sucesso!");
											pausa = entradaString.nextLine();
											break;
										case 4://Remover cadastro
											//Basicamente faz a mesma função para verificar se este cadastro existe, então ele remove o mesmo
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												pessoaLista = pessoaDAO.todos();
												for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) {
													if((pessoaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (pessoaLista.get(indice1).getSenha().contentEquals(senha))) {
														pessoa = pessoaLista.get(indice1);
														pessoaDAO.remover(pessoa);
														System.out.println("\nCadastro removido com sucesso!");
														controle = true;
													}	
												}
											}else {
												empresaLista = empresaDAO.todos();
												for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
													if((empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (empresaLista.get(indice1).getSenha().contentEquals(senha))) {
														empresa = empresaLista.get(indice1);
														empresaDAO.remover(empresa);
														System.out.println("\nCadastro removido com sucesso!");
														controle = true;
													}	
												}
											}
											
											if(controle == false)
												System.out.println("\nCadastro não encontrado!");
											break;
										case 5://Voltar
											break;
										default:
											System.out.println("\nOpção não existente!");
											break;
									}
									if(opcao3 == 5)
										break;
								}
								break;
							case 2://Opções de clientes
								while(true) {
									System.out.println("\nDigite uma das opções:\n\n1 - Adicionar cliente\n2 - Remover cliente\n3 - Listar clientes\n4 - Voltar");
									opcao3 = entradaNumero.nextInt();
									
									switch(opcao3) {
										case 1://Adicionar cliente
											//Não faremos aquela pergunta de inicio perguntando o tipo porque as contas empresarias terão apenas clientes do tipo pessoa
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											empresaLista = empresaDAO.todos();
											for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
												if((empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (empresaLista.get(indice1).getSenha().contentEquals(senha))) {
													System.out.println("\nUsuário encontrado!");
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											System.out.println("\nDigite o nome de usuário do cliente: ");
											nomeUsuario = entradaString.nextLine();
											controle = false;
											pessoaLista = pessoaDAO.todos();
											for(indice2 = 0; indice2 < pessoaLista.size(); indice2++) {
												if(pessoaLista.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
													System.out.println("\nUsuário encontrado!");
													id = pessoaLista.get(indice2).getId();
													nome = pessoaLista.get(indice2).getNome();
													cliente = new Pessoa(id, nome, nomeUsuario);
													empresa = empresaLista.get(indice1);
													empresa.adicionarCliente(cliente);
													empresaDAO.adicionarCliente(empresa);
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não encontrado!");
												break;
											}
											System.out.println("\nCliente adicionado com sucesso!");
											break;
										case 2://Remover cliente
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											empresaLista = empresaDAO.todos();
											for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
												if((empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (empresaLista.get(indice1).getSenha().contentEquals(senha))) {
													System.out.println("\nUsuário encontrado!");
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											empresa = empresaLista.get(indice1);
											empresaDAO.removerCliente(empresa);
											break;
										case 3://Listar clientes
											List<Cliente> clienteLista = new ArrayList<Cliente>();
											
											clienteLista = empresaDAO.todosCliente();
											
											for(indice1=0;indice1<clienteLista.size();indice1++) {
												System.out.printf("\n\nId da Empresa: %d\nNome da Empresa: %s\nNome de usuário da empresa: %s\nId do cliente: %d\nNome do cliente: %s\nNome de usuário do cliente: %s",clienteLista.get(indice1).getEmpresa().getId(),clienteLista.get(indice1).getEmpresa().getNome(),clienteLista.get(indice1).getEmpresa().getNomeUsuario(),clienteLista.get(indice1).getCliente().getId(),clienteLista.get(indice1).getCliente().getNome(),clienteLista.get(indice1).getCliente().getNomeUsuario());
											}
											
											break;
										case 4://Voltar
											break;
										default:
											System.out.println("\nOpção não existente!");
											break;
									}
									if(opcao3 == 4)
										break;
								}
								break;
							case 3://Voltar
								break;
							default:
								System.out.println("\nOpção não existente!");
								pausa = entradaString.nextLine();
								break;
						}
						if(opcao2 == 3)
							break;
					}
					break;
				case 2:
					while(true) {
						System.out.println("\nDigite uma das opções:\n\n1 - Escrever email\n2 - Atualizar email\n3 - Remover email\n4 - Listar emails\n5 - Voltar");
						opcao2 = entradaNumero.nextInt();
						
						switch(opcao2) {
							case 1://Escrever email
								//O programa fará aquela mesma validação para ver se o usuário existe
								System.out.println("\nDigite o seu tipo de usuário:\n1 - Pessoal\n2 - Empresarial");
								resposta1 = entradaNumero.nextInt();
								System.out.println("\nDigite o seu nome de usuário: ");
								nomeUsuario = entradaString.nextLine();
								System.out.println("\nDigite a sua senha: ");
								senha = entradaString.nextLine();
								controle = false;
								
								pessoaLista = pessoaDAO.todos();
								empresaLista = empresaDAO.todos();
								if(resposta1 == 1) {
									for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) {
										if((pessoaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (pessoaLista.get(indice1).getSenha().contentEquals(senha))) {
											System.out.println("\nUsuário encontrado");
											controle = true;
											break;
										}
									}
								}else {
									for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
										if((empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (empresaLista.get(indice1).getSenha().contentEquals(senha))) {
											System.out.println("\nUsuário encontrado");
											controle = true;
											break;
										}
									}
								}
								if(controle == false) {
									System.out.println("\nUsuário não encontrado!");
									break;
								}
								//autor é o autor da mensagem
								if(resposta1 == 1) {
									idEmail = pessoaLista.get(indice1).getId();
									emailLista = emailPessoaDAO.todos();
								}else {
									idEmail = empresaLista.get(indice1).getId();
									emailLista = emailEmpresaDAO.todos();
								}
								
								System.out.println("\nDigite a mensagem:");
								mensagem = entradaString.nextLine();
								System.out.println("\nDigite o data da mensagem:");
								dataEmail = entradaString.nextLine();
								
								System.out.println("\nDeseja informar o assunto da mensagem?[1-Sim/2-Não]");
								resposta2 = entradaNumero.nextInt();
								if(resposta2 == 1) {
									System.out.println("\nDigite o assunto da mensagem:");
									//Sobrecarga do método construtor do assunto
									assunto = entradaString.nextLine();
									email = new Email(null, idEmail, assunto, mensagem, dataEmail);
								}else
									email = new Email(null, idEmail, mensagem, dataEmail);
								
								//Enquanto não tiver pelo menos um desnatario adicionado o loop externo continuará, pois o email não poderá ser enviado
								while(email.getDestinatarios().size() == 0) {
									resposta2 = 1;
									while(resposta2 == 1) {
										System.out.println("\nDigite o tipo do destinatário que deseja adicionar:\n1 - Pessoal\n2 - Empresarial");
										int resposta3 = entradaNumero.nextInt();
										
										System.out.println("\nDigite o nome de usuário do destinatário:");
										nomeUsuario = entradaString.nextLine();
										controle = false;
										
										if(resposta3 == 1) {
											for(indice1 = 0; indice1 < pessoaLista.size(); indice1++) {
												if(pessoaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
													id = pessoaLista.get(indice1).getId();
													nome = pessoaLista.get(indice1).getNome();
													//Mais exemplos da utilização do poliformismo
													destinatario = new Pessoa(id, nome, nomeUsuario);
													email.adicionarDestinatarios(destinatario);
													controle = true;
													break;
												}
											}
										}else {
											for(indice1 = 0; indice1 < empresaLista.size(); indice1++) {
												if(empresaLista.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
													id = empresaLista.get(indice1).getId();
													nome = empresaLista.get(indice1).getNome();
													//Mais exemplos da utilização do poliformismo
													destinatario = new Empresa(id, nome, nomeUsuario);
													email.adicionarDestinatarios(destinatario);
													controle = true;
													break;
												}
											}
										}
									
										if(controle == false) {
											System.out.println("\nUsuário não existente!");
											break;
										}
										
										System.out.println("\nUsuário inserido com sucesso!");
										System.out.println("\nDeseja inserir mais destinatários?[1-Sim/2-Não]");
										resposta2 = entradaNumero.nextInt();
										
										if(resposta2 == 2) {
											if(resposta1 == 1) 
												emailPessoaDAO.adicionar(email);
											else
												emailEmpresaDAO.adicionar(email);
										}
									}
								}
								break;
							case 2://Atualizar email
								System.out.println("\nDigite o tipo de usuário que você é:\n1 - Pessoal\n2 - Empresarial\n");
								resposta1 = entradaNumero.nextInt();
								System.out.println("\nDigite o id do email:");
								idEmail = entradaNumero.nextInt();
								System.out.println("\nDigite seu id de usuário:");
								id = entradaNumero.nextInt();
								
								if(resposta1 == 1) {
									emailLista = emailPessoaDAO.todos();
								}else {
									emailLista = emailEmpresaDAO.todos();
								}
								
								controle = false;
								for(indice1 = 0; indice1 < emailLista.size(); indice1++) {
									if((emailLista.get(indice1).getIdEmail() == idEmail) && (emailLista.get(indice1).getIdAutor() == id)) {
										System.out.println("\nEmail encontrado!");
										controle = true;
										break;
									}
								}
								
								if(controle == false) {
									System.out.println("\nEmail não encontrado!");
									break;
								}
								
								//O autor e o código da mensagem não se alteram so o restante das informações
								System.out.println("\nDigite a mensagem:");
								mensagem = entradaString.nextLine();
								System.out.println("\nDigite o data da mensagem:");
								dataEmail = entradaString.nextLine();
								//Aqui ocorrerá a sobrecarga do método construtor da classe email, pois o usuário poderá informar se o email terá o assunto ou não
								System.out.println("\nDeseja informar o assunto da mensagem?[1-Sim/2-Não]");
								resposta2 = entradaNumero.nextInt();
								
								if(resposta2 == 1) {
									System.out.println("\nDigite o assunto da mensagem:");
									assunto = entradaString.nextLine();
									emailLista.get(indice1).setAssunto(assunto);
									emailLista.get(indice1).setMensagem(mensagem);
									emailLista.get(indice1).setDataMensagem(dataEmail);
								}else {
									emailLista.get(indice1).setMensagem(mensagem);
									emailLista.get(indice1).setDataMensagem(dataEmail);
								}
								
								if(resposta1 == 1) {
									email = emailLista.get(indice1);
									emailPessoaDAO.atualizar(email);
								}else {
									email = emailLista.get(indice1);
									emailEmpresaDAO.atualizar(email);
								}
								
								System.out.println(email.getMensagem());
								
								System.out.println("\nAtualização concluída com sucesso!");
								pausa = entradaString.nextLine();
								break;
							case 3://Remover email
								System.out.println("\nDigite o tipo de usuário que você é:\n1 - Pessoal\n2 - Empresarial\n");
								resposta1 = entradaNumero.nextInt();
								System.out.println("\nDigite o id do email:");
								idEmail = entradaNumero.nextInt();
								System.out.println("\nDigite seu id de usuário:");
								id = entradaNumero.nextInt();
								
								if(resposta1 == 1) {
									emailLista = emailPessoaDAO.todos();
								}else {
									emailLista = emailEmpresaDAO.todos();
								}
								
								controle = false;
								for(indice1 = 0; indice1 < emailLista.size(); indice1++) {
									if((emailLista.get(indice1).getIdEmail() == idEmail) && (emailLista.get(indice1).getIdAutor() == id)) {
										System.out.println("\nEmail encontrado!");
										controle = true;
										break;
									}
								}
								
								if(controle == false) {
									System.out.println("\nEmail não encontrado!");
									break;
								}
								
								email = emailLista.get(indice1);
								if(resposta1 == 1){
									emailPessoaDAO.remover(email);
									System.out.println("\nEmail removido com sucesso!");
								}else {
									emailEmpresaDAO.remover(email);
									System.out.println("\nEmail removido com sucesso!");
								}
								pausa = entradaString.nextLine();
								break;
							case 4://Listar emails
								System.out.println("\nDigite os tipos de email que você deseja visualizar:\n1 - Pessoal\n2 - Empresarial\n");
								resposta1 = entradaNumero.nextInt();
								List<Destinatario> destiPessoa = new ArrayList<Destinatario>();
								List<Destinatario> destiPessoaEmpresa = new ArrayList<Destinatario>();
								List<Destinatario> destiEmpresa = new ArrayList<Destinatario>();
								List<Destinatario> destiEmpresaPessoa = new ArrayList<Destinatario>();
								if (resposta1 == 1) {
									emailLista = emailPessoaDAO.todos();
									destiPessoa = emailEmpresaDAO.todosDestinatario(3);
									destiPessoaEmpresa = emailEmpresaDAO.todosDestinatario(4);
								}else {
									emailLista = emailEmpresaDAO.todos();
									destiEmpresa = emailEmpresaDAO.todosDestinatario(1);
									destiEmpresaPessoa = emailEmpresaDAO.todosDestinatario(2);
								}
								for(indice1=0;indice1<emailLista.size();indice1++) {
									System.out.printf("\n\nIdEmail: %d\nIdUsuario: %d\nAssunto: %s\nMensagem: %s\nData do Email: %s",emailLista.get(indice1).getIdEmail(),emailLista.get(indice1).getIdAutor(),emailLista.get(indice1).getAssunto(),emailLista.get(indice1).getMensagem(),emailLista.get(indice1).getDataMensagem());
									if(resposta1 == 1) {
										for(indice2=0;indice2<destiPessoa.size();indice2++) {
											if(emailLista.get(indice1).getIdEmail() == destiPessoa.get(indice2).getIdEmail()) {
												pessoa = pessoaDAO.get(destiPessoa.get(indice2).getIdDestinatario());
												System.out.printf("\nId do destinatario Pessoa: %d\nNome do destinatario pessoa: %s",destiPessoa.get(indice2).getIdDestinatario(),pessoa.getNomeUsuario());
											}
										}
										for(indice2=0;indice2<destiPessoaEmpresa.size();indice2++) {
											if(emailLista.get(indice1).getIdEmail() == destiPessoaEmpresa.get(indice2).getIdEmail()) {
												empresa = empresaDAO.get(destiPessoaEmpresa.get(indice2).getIdDestinatario());
												System.out.printf("\nId do destinatario Empresa: %d\nNome do destinatario empresa: %s",destiPessoaEmpresa.get(indice2).getIdDestinatario(),empresa.getNomeUsuario());
											}
										}
									}else {
										for(indice2=0;indice2<destiEmpresa.size();indice2++) {
											if(emailLista.get(indice1).getIdEmail() == destiEmpresa.get(indice2).getIdEmail()) {
												empresa = empresaDAO.get(destiEmpresa.get(indice2).getIdDestinatario());
												System.out.printf("\nId do destinatario Empresa: %d\nNome do destinatario empresa: %s",destiEmpresa.get(indice2).getIdDestinatario(),empresa.getNomeUsuario());
											}
										}
										for(indice2=0;indice2<destiEmpresaPessoa.size();indice2++) {
											if(emailLista.get(indice1).getIdEmail() == destiEmpresaPessoa.get(indice2).getIdEmail()) {
												pessoa = pessoaDAO.get(destiEmpresaPessoa.get(indice2).getIdDestinatario());
												System.out.printf("\nId do destinatario Pessoa: %d\nNome do destinatario pessoa: %s",destiEmpresaPessoa.get(indice2).getIdDestinatario(),pessoa.getNomeUsuario());
											}
										}
									}
								}
								
								break;
							case 5://Voltar
								break;
							default:
								System.out.println("\nOpção não existente!");
								break;
						}
						if(opcao2 == 5)
							break;
					}
					break;
				case 3:
					System.out.println("\nObrigado pela visita!");
					break;
				default:
					System.out.println("\nOpção não existente");
					break;
			}
			if(opcao1 == 3)
				break;
		}
		UtilBD.fecharConexao();
	}
}
	
