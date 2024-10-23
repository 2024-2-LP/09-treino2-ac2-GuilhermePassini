package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro) {
        if (livro == null ) {
            throw new ArgumentoInvalidoException();
        }
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        if (livro.getAutor() == null || livro.getAutor().isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        if (livro.getDataPublicacao() == null) {
            throw new ArgumentoInvalidoException();
        }
        else {
        livros.add(livro);
        }
    }
    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        boolean livroRemovido = false;

        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(i);
                livroRemovido = true;
                break;
            }
        }

        if (!livroRemovido) {
            throw new LivroNaoEncontradoException("Livro não encontrado.");
        }
    }
    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ArgumentoInvalidoException();
        }
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("Livro não encontrado.");
    }
    public Integer contarLivros() {
        return livros.size();
    }
    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosAteAno = new ArrayList<>();
        if (ano == null) {
            throw new ArgumentoInvalidoException("Ano não pode ser nulo.");
        }

        for (Livro livro : livros) {
            if (livro.getDataPublicacao().getYear() <= ano) {
                livrosAteAno.add(livro);
            }
        }

        return livrosAteAno;
    }
    public List<Livro> retornarTopCincoLivros() {
        List<LivroComMedia> livrosComMedia = new ArrayList<>();
        for (Livro livro : livros) {
            Double media = livro.calcularMediaAvaliacoes();
            livrosComMedia.add(new LivroComMedia(livro, media));
        }

        livrosComMedia.sort((l1, l2) -> Double.compare(l2.media, l1.media));

        List<Livro> topCincoLivros = new ArrayList<>();
        for (int i = 0; i < Math.min(5, livrosComMedia.size()); i++) {
            topCincoLivros.add(livrosComMedia.get(i).livro);
        }

        return topCincoLivros;
    }

    private class LivroComMedia {
        Livro livro;
        Double media;

        LivroComMedia(Livro livro, Double media) {
            this.livro = livro;
            this.media = media;
        }
    }

}

