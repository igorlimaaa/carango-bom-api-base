package br.com.caelum.carangobom.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDeParametroOutputDto {

    private String parametro;
    private String mensagem;

}
