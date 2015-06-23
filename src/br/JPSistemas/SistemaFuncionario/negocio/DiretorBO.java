/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.negocio;

import br.JPSistemas.SistemaFuncionario.dados.DiretorDAO;
import br.JPSistemas.SistemaFuncionario.entidade.Diretor;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOSIMAR
 */
public class DiretorBO {

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

    public Diretor autenticarUsuario(Diretor usuario) throws Exception {
        String senha = senhaMD5(usuario.getSenha());
        usuario.setSenha(senha);
        DiretorDAO diretorDAO = new DiretorDAO();
        Diretor diretor = new Diretor();
        diretor = diretorDAO.buscarPorSenha(usuario);
        if (diretor != null) {
            return diretor;
        } else {
            throw new UsuarioInvalidoException();
        }

    }
}
