package com.alura.literalura.controller;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/todos")
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/autores/todos")
    public List<Autor> listarTodosAutores() {
        return livroService.listarTodosAutores();
    }

    @GetMapping("/autores/vivos")
    public List<Autor> listarAutoresVivosEmAno(@RequestParam int ano) {
        return livroService.listarAutoresVivosEmAno(ano);
    }

    @GetMapping("/idioma")
    public List<Livro> listarLivrosPorIdioma(@RequestParam String idioma) {
        return livroService.listarLivrosPorIdioma(idioma);
    }

    @GetMapping("/idioma/contagem")
    public long contarLivrosPorIdioma(@RequestParam String idioma) {
        return livroService.contarLivrosPorIdioma(idioma);
    }

    @PostMapping("/buscar")
    public void buscarLivros(@RequestParam String titulo) throws Exception {
        livroService.buscarELancarLivros(titulo);
    }
}
