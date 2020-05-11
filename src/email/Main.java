package email;

import java.util.ArrayList;
import java.util.Scanner;

import email.entidades.Cadastro;
import email.entidades.Data;
import email.entidades.Email;
import email.entidades.Empresa;
import email.entidades.Pessoa;

public class Main {
	
	public static void main(String args[]) {
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
		Data dataNascimento;
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
		Data dataEmail;
		Email email;
		int size;
		int codigoAutoIncremento = 0;
		int codigo;
		int indice3;
		
		while(true) {
			
			System.out.println("Sistema de Email\n\nDigite uma das opções:\n\n1 - Acessar opções de criação, atualização, listagem e remoção de usuários\n2 - Acessar opções de criação, atualização, listagem e remoção de emails\n3 - Sair");
			opcao1 = entradaNumero.nextInt();
			
			switch(opcao1) {
				case 1:
					while(true) {
						System.out.println("Digite uma das opções: \n\n1 - Opções de cadastro de usuários\n2 - Opções de contatos do usuário\n3 - Opções de clientes do usuário\n4 - Voltar\n");
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
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if(cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuario existente!\n");
														controle = false;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if(cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
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
											System.out.print("\nDia: ");
											dia = entradaNumero.nextInt();
											System.out.print("Mes: ");
											mes = entradaNumero.nextInt();
											System.out.print("Ano: ");
											ano = entradaNumero.nextInt();
											System.out.println("\nDigite a senha:");
											senha = entradaString.nextLine();
											
											dataNascimento = new Data(dia, mes, ano);
											//Usamos novamente a variavel resposta para podermos armazenar o tipo de usuario no Array correto
											if(resposta1 == 1) {
												System.out.println("\nDigite o sexo:");
												sexo = entradaString.nextLine();
												pessoa = new Pessoa(nome, nomeUsuario, senha, dataNascimento, sexo);
												cadastroPessoa.add(pessoa);
											}else {
												System.out.println("\nDigite o ramo da empresa:");
												ramo = entradaString.nextLine();
												empresa = new Empresa(nome, nomeUsuario, senha, dataNascimento, ramo);
												cadastroEmpresa.add(empresa);
											}
											
											System.out.println("\nCadastro realizado com sucesso!");
											pausa = entradaString.nextLine();
											break;
										case 2://Listar os dados cadastros, menos as senhas, pois elas são usadas em outras funções para autentificar o usuário 
											//Listara o tipo empresarial ou o tipo pessoal
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) 
													System.out.printf("\nDados do usuário:\nNome de usuario: %s\nNome: %s\nData de Nascimento: %d/%d/%d\nSexo: %s\n",cadastroPessoa.get(indice1).getNomeUsuario(),cadastroPessoa.get(indice1).getNome(),cadastroPessoa.get(indice1).getDataNascimento().getDia(),cadastroPessoa.get(indice1).getDataNascimento().getMes(),cadastroPessoa.get(indice1).getDataNascimento().getAno(),cadastroPessoa.get(indice1).getSexo());
											}else
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) 
													System.out.printf("\nDados do usuário:\nNome de usuario: %s\nNome: %s\nData de Nascimento: %d/%d/%d\nRamo: %s\n",cadastroEmpresa.get(indice1).getNomeUsuario(),cadastroEmpresa.get(indice1).getNome(),cadastroEmpresa.get(indice1).getDataNascimento().getDia(),cadastroEmpresa.get(indice1).getDataNascimento().getMes(),cadastroEmpresa.get(indice1).getDataNascimento().getAno(),cadastroEmpresa.get(indice1).getRamo());
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
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não encontrado");
												break;
											}
											//Ver se o novo nome ja existe na lista
											controle = true;
											System.out.println("\nDigite o novo nome de usuario: ");
											nomeUsuario = entradaString.nextLine();
											if(resposta1 == 1) {
												for(int indice = 0; indice < cadastroPessoa.size(); indice++) {
													if(cadastroPessoa.get(indice).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nUsuario existente");
														controle = false;
														break;
													}
												}
											}else {
												for(int indice = 0; indice < cadastroEmpresa.size(); indice++) {
													if(cadastroEmpresa.get(indice).getNomeUsuario().contentEquals(nomeUsuario)) {
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
											System.out.print("\nNovo dia: ");
											dia = entradaNumero.nextInt();
											System.out.print("Novo mes: ");
											mes = entradaNumero.nextInt();
											System.out.print("Novo ano: ");
											ano = entradaNumero.nextInt();
											System.out.println("\nDigite a nova senha: ");
											senha = entradaString.nextLine();
											
											dataNascimento = new Data(dia, mes, ano);
											//Atualizando o cadastro com sets
											if(resposta1 == 1) {
												System.out.println("\nDigite o novo sexo: ");
												sexo = entradaString.nextLine();
												cadastroPessoa.get(indice1).setNomeUsuario(nomeUsuario);
												cadastroPessoa.get(indice1).setNome(nome);
												cadastroPessoa.get(indice1).setDataNascimento(dataNascimento);
												cadastroPessoa.get(indice1).setSexo(sexo);
												cadastroPessoa.get(indice1).setSenha(senha);
											}else {
												System.out.println("\nDigite o novo ramo da empresa: ");
												ramo = entradaString.nextLine();
												cadastroEmpresa.get(indice1).setNomeUsuario(nomeUsuario);
												cadastroEmpresa.get(indice1).setNome(nome);
												cadastroEmpresa.get(indice1).setDataNascimento(dataNascimento);
												cadastroEmpresa.get(indice1).setRamo(ramo);
												cadastroEmpresa.get(indice1).setSenha(senha);
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
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														cadastroPessoa.remove(indice1);
														System.out.println("\nCadastro removido com sucesso!");
														controle = true;
													}	
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														cadastroEmpresa.remove(indice1);
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
							case 2://Opções de contatos
								while(true) {
									System.out.println("\nDigite uma das opções:\n\n1 - Adicionar contato\n2 - Remover contato\n3 - Listar contatos por usuário\n4 - Atualizar contato\n5 - Voltar");
									opcao3 = entradaNumero.nextInt();
									
									switch(opcao3) {
										case 1://Pode-se adicionar qualquer tipo de contato mas ele deve constar no sistema, para adicionar o contato ao usuário
											//utiliza-mos o polimorfismo pois criamos no inicio um objeto contato do tipo Cadastro utilizando uma sobrecarga do método
											//construtor da classe cadastro e este objeto poderá receber tanto um objeto do tipo Pessoa como um do tipo Empresa
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuario: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}
											
											if(controle == false) {
												System.out.println("\nUsuário não encontrado");
												break;
											}
											//Inserir o contato no usuario cadastrado, o usuario pode inserir quantos contatos desejar
											resposta2 = 1;
											while(resposta2 == 1) {
												controle = false;
												System.out.println("\nDigite o tipo de contato:\n1 - Pessoal\n2 - Empresarial");
												resposta1 = entradaNumero.nextInt();
												System.out.println("\nDigite o nome de usuario do contato: ");
												nomeUsuario = entradaString.nextLine();
												//Vamos verificar se este contato existe no Array que o usuário especificou
												if(resposta1 == 1) {
													for(indice2 = 0; indice2 < cadastroPessoa.size(); indice2++) {
														if(cadastroPessoa.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
															nome = cadastroPessoa.get(indice2).getNome();
															//Nesta hora ocorre o poliformismo 
															contato = new Pessoa(nome, nomeUsuario);
															//Chamando a função de inscerção
															cadastroPessoa.get(indice1).adicionarContato(contato);
															controle = true;
															break;
														}
													}
												}else {
													for(indice2 = 0; indice2 < cadastroEmpresa.size(); indice2++) {
														if(cadastroEmpresa.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)){
															nome = cadastroEmpresa.get(indice2).getNome();
															//Nesta hora ocorre o poliformismo 
															contato = new Empresa(nome, nomeUsuario);
															//Chamando a função de inscerção
															cadastroPessoa.get(indice1).adicionarContato(contato);
															controle = true;
															break;
														}
													}
												}
												
												if(controle == false) 
													System.out.println("\nContato não encontrado\n");
												else
													System.out.println("\nContato inserido com sucesso!\n");
												
												System.out.println("\nDeseja inserir mais contatos?[1-Sim/2-Não]");
												resposta2 = entradaNumero.nextInt();
											}
											break;
										case 2://Remover contato
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}
											
											if(controle == false) {
												System.out.println("\nUsuário não encontrado");
												break;
											}
											resposta2 = 1;
											while(resposta2 == 1) {
												System.out.println("\nDigite o nome do usuário do contato: ");
												nomeUsuario = entradaString.nextLine();
												//Chamando a função de remoção
												if(resposta1 == 1)
													resposta2 = cadastroPessoa.get(indice1).removerContato(nomeUsuario);
												else
													resposta2 = cadastroEmpresa.get(indice1).removerContato(nomeUsuario);
												if(resposta2 == 0)
													System.out.println("\nContato removido com sucesso!");
												else
													System.out.println("\nContato não encontrado");
												System.out.println("\nDeseja remover mais contatos?[1-Sim/2-Não]");
												resposta2 = entradaNumero.nextInt();
											}
											break;
										case 3://Listar contatos por usuário
											//Ocorrerá a função de validação do usuário, então ele poderá ver todos os seus contatos 
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}
											
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											if(resposta1 == 1)
												cadastroPessoa.get(indice1).listarContato();
											else
												cadastroEmpresa.get(indice1).listarContato();
											pausa = entradaString.nextLine();
											break;
										case 4://Atualização de contato
											System.out.println("\nDigite o tipo de conta:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome do usuario: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
													if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}else {
												for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
													if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
														System.out.println("\nUsuário encontrado!");
														controle = true;
														break;
													}
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não encontrado");
												break;
											}
											//O usuário irá digitar o nome de usuário do contato e o tipo, e se ele for encontrado então ele vai inserir
											//O novo nome de usuário do contato, e o programa procurar se este novo nome existe realmente
											System.out.println("\nDigite o tipo do contato:\n1 - Pessoal\n2 - Empresarial");
											resposta2 = entradaNumero.nextInt();
											System.out.println("\nDigite o nome de usuário do contato:");
											nomeUsuario = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice2 = 0; indice2 < cadastroPessoa.get(indice1).getContatos().size(); indice2++) {
													if(cadastroPessoa.get(indice1).getContatos().get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nDigite o novo nome do usuário:");
														nomeUsuario = entradaString.nextLine();
														if(resposta2 == 1) {
															for(indice3 = 0; indice3 < cadastroPessoa.size(); indice3++) {
																if(cadastroPessoa.get(indice3).getNomeUsuario().contentEquals(nomeUsuario)) {
																	controle = true;
																	nome = cadastroPessoa.get(indice3).getNome();
																	cadastroPessoa.get(indice1).getContatos().get(indice2).setNomeUsuario(nomeUsuario);
																	cadastroPessoa.get(indice1).getContatos().get(indice2).setNome(nome);
																}
															}
														}else {
															for(indice3 = 0; indice3 < cadastroEmpresa.size(); indice3++) {
																if(cadastroEmpresa.get(indice3).getNomeUsuario().contentEquals(nomeUsuario)) {
																	controle = true;
																	nome = cadastroEmpresa.get(indice3).getNome();
																	cadastroPessoa.get(indice1).getContatos().get(indice2).setNomeUsuario(nomeUsuario);
																	cadastroPessoa.get(indice1).getContatos().get(indice2).setNome(nome);
																}
															}
														}
													}
												}
											}else {
												for(indice2 = 0; indice2 < cadastroEmpresa.get(indice1).getContatos().size(); indice2++) {
													if(cadastroEmpresa.get(indice1).getContatos().get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
														System.out.println("\nDigite o novo nome do usuário:");
														nomeUsuario = entradaString.nextLine();
														
														if(resposta2 == 1) {
															for(indice3 = 0; indice3 < cadastroPessoa.size(); indice3++) {
																if(cadastroPessoa.get(indice3).getNomeUsuario().contentEquals(nomeUsuario)) {
																	controle = true;
																	nome = cadastroPessoa.get(indice3).getNome();
																	cadastroEmpresa.get(indice1).getContatos().get(indice2).setNomeUsuario(nomeUsuario);
																	cadastroEmpresa.get(indice1).getContatos().get(indice2).setNome(nome);
																}
															}
														}else {
															for(indice3 = 0; indice3 < cadastroEmpresa.size(); indice3++) {
																if(cadastroEmpresa.get(indice3).getNomeUsuario().contentEquals(nomeUsuario)) {
																	controle = true;
																	nome = cadastroEmpresa.get(indice3).getNome();
																	cadastroEmpresa.get(indice1).getContatos().get(indice2).setNomeUsuario(nomeUsuario);
																	cadastroEmpresa.get(indice1).getContatos().get(indice2).setNome(nome);
																}
															}
														}
														
													}
												}
											}
											if(controle == false)
												System.out.println("\nNão foi possível atualizar o contato!");
											else
												System.out.println("\nContato atualizado com sucesso!");
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
							case 3://Opções de clientes
								while(true) {
									System.out.println("\nDigite uma das opções:\n\n1 - Adicionar cliente\n2 - Remover cliente\n3 - Listar clientes\n4 - Atualizar cliente\n5 - Voltar");
									opcao3 = entradaNumero.nextInt();
									
									switch(opcao3) {
										case 1://Adicionar cliente
											//Não faremos aquela pergunta de inicio perguntando o tipo porque as contas empresarias terão apenas clientes do tipo pessoa
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
												if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
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
											for(indice2 = 0; indice2 < cadastroPessoa.size(); indice2++) {
												if(cadastroPessoa.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
													System.out.println("\nUsuário encontrado!");
													nome = cadastroPessoa.get(indice2).getNome();
													cliente = new Pessoa(nome, nomeUsuario);
													cadastroEmpresa.get(indice1).adicionarCliente(cliente);
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
											for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
												if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
													System.out.println("\nUsuário encontrado!");
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											controle1 = true;
											//O usuário poderá remover quantos clientes quiser
											while(controle1 == true) {
												System.out.println("\nDigite o nome de usuário do cliente: ");
												nomeUsuario = entradaString.nextLine();
												cadastroEmpresa.get(indice1).removerCliente(nomeUsuario);
												System.out.println("\nDeseja remover mais contatos?[true/false]");
												controle1 = entradaNumero.nextBoolean();
											}
											break;
										case 3://Listar clientes
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
												if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
													System.out.println("\nUsuário encontrado!");
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											//Função para listar clientes
											cadastroEmpresa.get(indice1).listarCLiente();
											break;
										case 4://Atualizar clientes
											System.out.println("\nDigite o nome do usuário: ");
											nomeUsuario = entradaString.nextLine();
											System.out.println("\nDigite a senha: ");
											senha = entradaString.nextLine();
											controle = false;
											for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
												if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
													System.out.println("\nUsuário encontrado!");
													controle = true;
													break;
												}
											}
											if(controle == false) {
												System.out.println("\nUsuário não existente!");
												break;
											}
											System.out.println("\nDigite o nome de usuário do cliente:");
											nomeUsuario = entradaString.nextLine();
											controle = false;
											for(indice2 = 0; indice2 < cadastroEmpresa.get(indice1).getClientes().size(); indice2++) {
												if(cadastroEmpresa.get(indice1).getClientes().get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
													System.out.println("\nDigite o novo nome de usuário:");
													nomeUsuario = entradaString.nextLine();
													
													for(indice3 = 0; indice3 < cadastroPessoa.size(); indice3++) {
														if(cadastroPessoa.get(indice3).getNomeUsuario().contentEquals(nomeUsuario)) {
															controle = true;
															nome = cadastroPessoa.get(indice3).getNome();
															cadastroEmpresa.get(indice1).getClientes().get(indice2).setNomeUsuario(nomeUsuario);
															cadastroEmpresa.get(indice1).getClientes().get(indice2).setNome(nome);
														}
													}
												}
											}
											if(controle == false)
												System.out.println("\nNão foi possível atualizar o cliente!");
											else
												System.out.println("\nCliente atualizado com sucesso!");
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
							case 4://Voltar
								break;
							default:
								System.out.println("\nOpção não existente!");
								pausa = entradaString.nextLine();
								break;
						}
						if(opcao2 == 4)
							break;
					}
					break;
				case 2:
					while(true) {
						System.out.println("\nDigite uma das opções:\n\n1 - Escrever email\n2 - Atualizar email\n3 - Remover email\n4 - Listar emails enviados\n5 - Listar emails recebidos\n6 - Voltar");
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
								if(resposta1 == 1) {
									for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
										if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
											System.out.println("\nUsuário encontrado");
											controle = true;
											break;
										}
									}
								}else {
									for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
										if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
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
								if(resposta1 == 1)
									autor = cadastroPessoa.get(indice1);
								else
									autor = cadastroEmpresa.get(indice1);
								
								//O codigo é incrementado automaticamente
								codigoAutoIncremento++;
								System.out.println("\nDigite a mensagem:");
								mensagem = entradaString.nextLine();
								System.out.println("\nDigite o dia da mensagem:");
								//Sobrecarga do método contrutor da data
								dia = entradaNumero.nextInt();
								
								dataEmail = new Data(dia);
								
								System.out.println("\nDeseja informar o assunto da mensagem?[1-Sim/2-Não]");
								resposta2 = entradaNumero.nextInt();
								if(resposta2 == 1) {
									System.out.println("\nDigite o assunto da mensagem:");
									//Sobrecarga do método construtor do assunto
									assunto = entradaString.nextLine();
									email = new Email(codigoAutoIncremento, autor, assunto, mensagem, dataEmail);
								}else
									email = new Email(codigoAutoIncremento, autor, mensagem, dataEmail);
								
								listaEmail.add(email);
								//Usamos a variavel size para sabermos o indice em que adicionaremos os destinatarios
								size = listaEmail.size()-1;
								//Enquanto não tiver pelo menos um desnatario adicionado o loop externo continuará, pois o email não poderá ser enviado
								while(listaEmail.get(size).getDestinatarios().size() == 0) {
									resposta2 = 1;
									while(resposta2 == 1) {
										System.out.println("\nDigite o tipo do destinatário que deseja adicionar:\n1 - Pessoal\n2 - Empresarial");
										resposta1 = entradaNumero.nextInt();
										
										System.out.println("\nDigite o nome de usuário do destinatário:");
										nomeUsuario = entradaString.nextLine();
										controle = false;
										if(resposta1 == 1) {
											for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
												if(cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
													nome = cadastroPessoa.get(indice1).getNome();
													//Mais exemplos da utilização do poliformismo
													destinatario = new Pessoa(nome, nomeUsuario);
													listaEmail.get(size).adicionarDestinatarios(destinatario);
													controle = true;
													break;
												}
											}
										}else {
											for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
												if(cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) {
													nome = cadastroEmpresa.get(indice1).getNome();
													//Mais exemplos da utilização do poliformismo
													destinatario = new Empresa(nome, nomeUsuario);
													listaEmail.get(size).adicionarDestinatarios(destinatario);
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
									}
								}
								break;
							case 2://Atualizar email
								System.out.println("\nDigite seu nome de usuário:");
								nomeUsuario = entradaString.nextLine();
								System.out.println("\nDigite sua senha:");
								senha = entradaString.nextLine();
								System.out.println("\nDigite o código do seu email:");
								codigo = entradaNumero.nextInt();
								controle = false;
								for(indice1 = 0; indice1 < listaEmail.size(); indice1++) {
									if((listaEmail.get(indice1).getAutor().getNomeUsuario().contentEquals(nomeUsuario)) && (listaEmail.get(indice1).getAutor().getSenha().contentEquals(senha)) && (listaEmail.get(indice1).getCodigo() == codigo)) {
										System.out.println("\nUsuário e email encontrado!");
										controle = true;
										break;
									}
								}
								if(controle == false) {
									System.out.println("\nUsuário ou email não encontrado!");
									break;
								}
								
								//O autor e o código da mensagem não se alteram so o restante das informações
								System.out.println("\nDigite a mensagem:");
								mensagem = entradaString.nextLine();
								System.out.println("\nDigite o dia da mensagem:");
								dia = entradaNumero.nextInt();
								//Aqui ocorrerá a sobrecarga do método construtor da classe data, onde o usuário passará apenas o dia
								dataEmail = new Data(dia);
								//Aqui ocorrerá a sobrecarga do método construtor da classe email, pois o usuário poderá informar se o email terá o assunto ou não
								System.out.println("\nDeseja informar o assunto da mensagem?[1-Sim/2-Não]");
								resposta2 = entradaNumero.nextInt();
								if(resposta2 == 1) {
									System.out.println("\nDigite o assunto da mensagem:");
									assunto = entradaString.nextLine();
									listaEmail.get(indice1).setMensagem(mensagem);
									listaEmail.get(indice1).setAssunto(assunto);
									listaEmail.get(indice1).setDataMensagem(dataEmail);
								}else {
									listaEmail.get(indice1).setMensagem(mensagem);
									listaEmail.get(indice1).setDataMensagem(dataEmail);
								}
								
								System.out.println("\nDeseja alterar os destinatarios?[1-Sim/2-Não]");
								resposta1 = entradaNumero.nextInt();
								if(resposta1 == 1) {
									for(indice2=0; indice2 < listaEmail.get(indice1).getDestinatarios().size(); indice2++) {
										destinatario = listaEmail.get(indice1).getDestinatarios().get(indice2);
										listaEmail.get(indice1).removerDestinatarios(destinatario);
									}
									
									while(listaEmail.get(indice1).getDestinatarios().size() == 0) {
										resposta2 = 1;
										while(resposta2 == 1) {
											System.out.println("\nDigite o tipo do destinatário que deseja adicionar:\n1 - Pessoal\n2 - Empresarial");
											resposta1 = entradaNumero.nextInt();
											
											System.out.println("\nDigite o nome de usuário do destinatário:");
											nomeUsuario = entradaString.nextLine();
											controle = false;
											if(resposta1 == 1) {
												for(indice2 = 0; indice2 < cadastroPessoa.size(); indice2++) {
													if(cadastroPessoa.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
														nome = cadastroPessoa.get(indice2).getNome();
														//Mais exemplos da utilização do poliformismo
														destinatario = new Pessoa(nome, nomeUsuario);
														listaEmail.get(indice1).adicionarDestinatarios(destinatario);
														controle = true;
														break;
													}
												}
											}else {
												for(indice2 = 0; indice2 < cadastroEmpresa.size(); indice2++) {
													if(cadastroEmpresa.get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
														nome = cadastroEmpresa.get(indice2).getNome();
														//Mais exemplos da utilização do poliformismo
														destinatario = new Empresa(nome, nomeUsuario);
														listaEmail.get(indice1).adicionarDestinatarios(destinatario);
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
										}
									}
								}
								System.out.println("\nAtualização concluída com sucesso!");
								pausa = entradaString.nextLine();
								break;
							case 3://Remover email
								System.out.println("\nDigite seu nome de usuário:");
								nomeUsuario = entradaString.nextLine();
								System.out.println("\nDigite sua senha:");
								senha = entradaString.nextLine();
								System.out.println("\nDigite o código do seu email:");
								codigo = entradaNumero.nextInt();
								controle = false;
								for(indice1 = 0; indice1 < listaEmail.size(); indice1++) {
									if((listaEmail.get(indice1).getAutor().getNomeUsuario().contentEquals(nomeUsuario)) && (listaEmail.get(indice1).getAutor().getSenha().contentEquals(senha)) && (listaEmail.get(indice1).getCodigo() == codigo)) {
										listaEmail.remove(indice1);
										System.out.println("\nEmail removido com sucesso!");
										controle = true;
										break;
									}
								}
								if(controle == false) {
									System.out.println("\nUsuário ou email não encontrado!");
									break;
								}
								pausa = entradaString.nextLine();
								break;
							case 4://Listar emails enviados
								System.out.println("\nDigite o nome de usuário:");
								nomeUsuario = entradaString.nextLine();
								System.out.println("\nDigite a senha:");
								senha = entradaString.nextLine();
								controle = false;
								for(indice1 = 0; indice1 < listaEmail.size(); indice1++) {
									if((listaEmail.get(indice1).getAutor().getNomeUsuario().contentEquals(nomeUsuario)) && (listaEmail.get(indice1).getAutor().getSenha().contentEquals(senha))) {
										System.out.println("\nUsuário encontrado!");
										controle = true;
										break;
									}
								}
								if(controle == false) {
									System.out.println("\nUsuário não encontrado!");
									break;
								}
								for(indice2 = 0; indice2 < listaEmail.size(); indice2++) {
									if(listaEmail.get(indice2).getAutor().getNomeUsuario().contentEquals(nomeUsuario)) {
										System.out.printf("\nCódigo: %d\nAutor: %s\nAssunto: %s\nMensagem: %s\nData de envio: %d/%d/%d\n",listaEmail.get(indice2).getCodigo(),listaEmail.get(indice2).getAutor().getNomeUsuario(),listaEmail.get(indice2).getAssunto(),listaEmail.get(indice2).getMensagem(),listaEmail.get(indice2).getDataMensagem().getDia(),listaEmail.get(indice2).getDataMensagem().getMes(),listaEmail.get(indice2).getDataMensagem().getAno());
										System.out.println("Destinatários:");
										//Aqui chama-se a função para listar os destinatários
										listaEmail.get(indice2).listarDestinatarios();
									}
								}
								pausa = entradaString.nextLine();
								break;
							case 5://Listar emails recebidos
								System.out.println("\nDigite o nome de usuário:");
								nomeUsuario = entradaString.nextLine();
								System.out.println("\nDigite a senha:");
								senha = entradaString.nextLine();
								controle = false;
								for(indice1 = 0; indice1 < listaEmail.size(); indice1++) {
									for(indice2 = 0; indice2 < listaEmail.get(indice1).getDestinatarios().size(); indice2++) {
										if(listaEmail.get(indice1).getDestinatarios().get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
											controle = true;
											break;
										}
									}
									if(controle == true)
										break;
								}
								if(controle == false) {
									System.out.println("\nUsuário não encontrado!");
									break;
								}else {
									controle = false;
									for(indice1 = 0; indice1 < cadastroPessoa.size(); indice1++) {
										if((cadastroPessoa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroPessoa.get(indice1).getSenha().contentEquals(senha))) {
											System.out.println("\nUsuário encontrado!");
											controle = true;
											break;
										}
									}
									for(indice1 = 0; indice1 < cadastroEmpresa.size(); indice1++) {
										if((cadastroEmpresa.get(indice1).getNomeUsuario().contentEquals(nomeUsuario)) && (cadastroEmpresa.get(indice1).getSenha().contentEquals(senha))) {
											System.out.println("\nUsuário encontrado!");
											controle = true;
											break;
										}
									}
								}
								if(controle == false) {
									System.out.println("\nSenha incorreta!");
									break;
								}
								for(indice1 = 0; indice1 < listaEmail.size(); indice1++) {
									for(indice2 = 0; indice2 < listaEmail.get(indice1).getDestinatarios().size(); indice2++) {
										if(listaEmail.get(indice1).getDestinatarios().get(indice2).getNomeUsuario().contentEquals(nomeUsuario)) {
											System.out.printf("\nCódigo: %d\nAutor:\nNome de usuário: %s\nNome: %s\nAssunto: %s\nMensagem: %s\nData de envio: %d/%d/%d\nDestinatário:\nNome de usuário: %s\nNome: %s\n",listaEmail.get(indice1).getCodigo(),listaEmail.get(indice1).getAutor().getNomeUsuario(),listaEmail.get(indice1).getAutor().getNome(),listaEmail.get(indice1).getAssunto(),listaEmail.get(indice1).getMensagem(),listaEmail.get(indice1).getDataMensagem().getDia(),listaEmail.get(indice1).getDataMensagem().getMes(),listaEmail.get(indice1).getDataMensagem().getAno(),listaEmail.get(indice1).getDestinatarios().get(indice2).getNomeUsuario(),listaEmail.get(indice1).getDestinatarios().get(indice2).getNome());
										}
									}
								}
								pausa = entradaString.nextLine();
								break;
							case 6://Voltar
								break;
							default:
								System.out.println("\nOpção não existente!");
								break;
						}
						if(opcao2 == 6)
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
	}
}