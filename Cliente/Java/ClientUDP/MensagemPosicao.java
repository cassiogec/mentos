/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientUDP;

/**
 *
 * @author Rubens Rangel
 */
public class MensagemPosicao {
    
        String tipomensagem;
        String operacao;
        String placa;
        String datahora;
        String latitude;
        String longitude;

    public MensagemPosicao(String tipomensagem, String operacao, String placa, String datahora, String latitude, String longitude) {
        this.tipomensagem = tipomensagem;
        this.operacao = operacao;
        this.placa = placa;
        this.datahora = datahora;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
        
        

   
    
    
}
