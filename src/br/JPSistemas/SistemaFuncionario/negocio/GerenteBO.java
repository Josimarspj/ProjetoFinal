/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.negocio;

import br.JPSistemas.SistemaFuncionario.dados.DepartamentoDAO;
import br.JPSistemas.SistemaFuncionario.dados.GerenteDAO;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JOSIMAR
 */
public class GerenteBO {

    public static String converterHexadecimalParaString(byte[] valorHexadecimal) {
        StringBuilder valorConvertido = new StringBuilder();
        for (byte caracter : valorHexadecimal) {
            valorConvertido.append(String.format("%02X", 0xFF & caracter));
        }
        return valorConvertido.toString();
    }

    public String senhaMD5(String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest algoritmo = MessageDigest.getInstance("MD5");
        byte[] codigoHashHexaDecimal = algoritmo.digest(senha.getBytes("UTF-8"));
        String codigoHashFinal = converterHexadecimalParaString(codigoHashHexaDecimal);
        return codigoHashFinal;
    }

    public Gerente autenticarUsuario(Gerente usuario) throws Exception {
        GerenteDAO gerenteDAO = new GerenteDAO();
        String senha = senhaMD5(usuario.getSenha());
        usuario.setSenha(senha);
        Gerente gerente = gerenteDAO.buscarPorSenha(usuario);
        if (gerente != null) {
            return gerente;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public Gerente buscarPorCodigo(int codigo) throws Exception {
        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente = gerenteDAO.procurarPorCodigo(codigo);
        if (gerente != null) {
            return gerente;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public void inserir(Gerente gerente) throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        if (departamentoDAO.buscarPorGerente(gerente) == null) {
            if (gerenteDAO.procurarPorCpf(gerente)==null) {
                if (gerenteDAO.procurarPorUsuario(gerente) == null) {
                    String senha = senhaMD5(gerente.getSenha());
                    gerente.setSenha(senha);
                    gerenteDAO.inserir(gerente);
                } else {
                    throw new UsuarioJaCadatradoException();
                }
            }else{
                throw new CPFJaCadastradoException();
            }

        } else {
            throw new DepartamentoJaContemGerenteException();
        }
    }
}
