package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, LocalDate dataPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.avaliacoes = new ArrayList<Avaliacao>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void adicionarAvaliacao(String descricao, Double qtdEstrelas) {
        if (descricao == null || descricao.isEmpty() || descricao.isBlank()) {
            throw new ArgumentoInvalidoException();
        }
           if(qtdEstrelas == null || qtdEstrelas < 0 ||qtdEstrelas > 5) {
                throw new ArgumentoInvalidoException(); }
            else {
            Avaliacao avaliacao = new Avaliacao(descricao, qtdEstrelas);
            avaliacoes.add(avaliacao);
           }
        }
    public Double calcularMediaAvaliacoes() {
        if(avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }
        else {
            Double soma = 0.0;
            for(Avaliacao avaliacao : avaliacoes) {
                soma += avaliacao.getQtdEstrelas();
            }
            return soma / avaliacoes.size();
        }

    }
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", mediaAvaliacoes=" + calcularMediaAvaliacoes() +
                '}';
    }
    }

