package com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alura.literalura.service.LivroService;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LivroService livroService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Menu:");
			System.out.println("1. Buscar livros por título");
			System.out.println("2. Listar todos os livros");
			System.out.println("3. Listar todos os autores");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Listar livros por idioma");
			System.out.println("6. Contar livros por idioma");
			System.out.println("7. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					livroService.buscarELancarLivros(titulo);
					break;
				case 2:
					System.out.println(livroService.listarTodos());
					break;
				case 3:
					System.out.println(livroService.listarTodosAutores());
					break;
				case 4:
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					System.out.println(livroService.listarAutoresVivosEmAno(ano));
					break;
				case 5:
					System.out.print("Digite o idioma: ");
					String idioma = scanner.nextLine();
					System.out.println(livroService.listarLivrosPorIdioma(idioma));
					break;
				case 6:
					System.out.print("Digite o idioma: ");
					idioma = scanner.nextLine();
					System.out.println("Contagem: " + livroService.contarLivrosPorIdioma(idioma));
					break;
				case 7:
					running = false;
					break;
				default:
					System.out.println("Opção inválida.");
			}
		}

		scanner.close();
	}
}
