package br.com.caelum.carangobom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.caelum.carangobom.exception.MarcaAssociadaException;
import br.com.caelum.carangobom.exception.UsuarioExistenteException;
import br.com.caelum.carangobom.validacao.ListaDeErrosOutputDto;

@RestControllerAdvice
public class ErrosDeValidacaoControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ListaDeErrosOutputDto capturaErrosDeValidacao(MethodArgumentNotValidException excecao) {
        ListaDeErrosOutputDto listaDeErros = new ListaDeErrosOutputDto();

        excecao.getBindingResult()
                .getFieldErrors()
                .forEach(error -> listaDeErros.adicionaErroEmParametro(error.getField(), error.getDefaultMessage()));

        return listaDeErros;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MarcaAssociadaException.class)
    public ListaDeErrosOutputDto capturaMarcaAssociadaAveiculo(MarcaAssociadaException excecao) {
        ListaDeErrosOutputDto listaDeErros = new ListaDeErrosOutputDto();
        listaDeErros.adicionaErroEmParametro("Marca não removida", excecao.getMessage());

        return listaDeErros;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioExistenteException.class)
    public ListaDeErrosOutputDto capturaUsuarioCadastrado(UsuarioExistenteException excecao) {
        ListaDeErrosOutputDto listaDeErros = new ListaDeErrosOutputDto();
        listaDeErros.adicionaErroEmParametro("email", excecao.getMessage());

        return listaDeErros;
    }

}