/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientUDP;

/**
 *
 * @Rubens Rangel
 */
public class MensagemVeiculo {
    
        String tipomensagem;
        String operacao;
        String placa;
        String tipo;
        String capacidade;
        String uncapacidade;

    public MensagemVeiculo(String tipomensagem, String operacao, String placa, String tipo, String capacidade, String uncapacidade) {
        this.tipomensagem = tipomensagem;
        this.operacao = operacao;
        this.placa = placa;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.uncapacidade = uncapacidade;
    }

    public String getTipomensagem() {
        return tipomensagem;
    }

    public void setTipomensagem(String tipomensagem) {
        this.tipomensagem = tipomensagem;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getUncapacidade() {
        return uncapacidade;
    }

    public void setUncapacidade(String uncapacidade) {
        this.uncapacidade = uncapacidade;
    }


     
}
