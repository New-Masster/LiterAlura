package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final String baseUrl = "https://gutendex.com/books/";

    @Transactional
    public void buscarELancarLivros(String titulo) {
        System.out.println("Iniciando busca na API...");

        String url = baseUrl + "?search=" + titulo;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        System.out.println("API response received.");

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode books = root.path("results");

            if (books.isArray() && books.size() > 0) {
                JsonNode book = books.get(0);
                Livro livro = extrairDadosLivro(book);
                salvarLivro(livro);
                System.out.println("Livro encontrado e salvo: " + livro);
            } else {
                System.out.println("Nenhum livro encontrado com o título fornecido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar e lançar livro: " + e.getMessage());
        }
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    @Transactional
    public List<Autor> listarAutoresVivosEmAno(int ano) {
        return autorRepository.findByAnoNascimentoLessThanEqual(ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public int contarLivrosPorIdioma(String idioma) {
        return livroRepository.countByIdioma(idioma);
    }

    private Livro extrairDadosLivro(JsonNode book) {
        Livro livro = new Livro();
        livro.setTitulo(book.path("title").asText());
        livro.setIdioma(book.path("languages").get(0).asText());
        livro.setNumeroDownloads(book.path("downloads").asInt());
        Autor autor = new Autor();
        autor.setNome("Nome do Autor");
        autor.setNacionalidade("Nacionalidade do Autor");
        autor.setAnoNascimento(1990);
        livro.setAutor(autor);
        return livro;
    }

    @Transactional
    protected void salvarLivro(Livro livro) {
        livroRepository.save(livro);
    }
}
