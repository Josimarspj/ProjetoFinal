/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.entidade;

/**
 *
 * @author JOSIMAR
 */
public class GraficoCargoFuncionario {
    public String nomeCargo;
    public int quantidadeFuncionarios;

    public GraficoCargoFuncionario() {
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public int getQuantidadeFuncionarios() {
        return quantidadeFuncionarios;
    }

    public void setQuantidadefuncionarios(int quantidadefuncionarios) {
        this.quantidadeFuncionarios = quantidadefuncionarios;
    }
    
    
}
