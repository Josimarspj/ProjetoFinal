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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            if (gerenteDAO.procurarPorCpf(gerente) == null) {
                if (gerenteDAO.procurarPorUsuario(gerente) == null) {
                    String senha = senhaMD5(gerente.getSenha());
                    gerente.setSenha(senha);
                    gerenteDAO.inserir(gerente);
                } else {
                    throw new UsuarioJaCadatradoException();
                }
            } else {
                throw new CPFJaCadastradoException();
            }

        } else {
            throw new DepartamentoJaContemGerenteException();
        }
    }

    public Gerente buscarPorCPF(Gerente gerente) throws Exception {
        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente1 = gerenteDAO.procurarPorCpf(gerente);
        if (gerente1 != null) {
            return gerente1;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public void alterar(Gerente gerente) throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        gerenteDAO.alterar(gerente);
    }

    public List<Gerente> buscarTodos() throws Exception {
        List<Gerente> gerentes = new ArrayList<Gerente>();
        GerenteDAO gerenteDAO = new GerenteDAO();
        gerentes = gerenteDAO.buscarTodos();
        if (gerentes.size() == 0) {
            throw new ListaGerenteVaziaException();
        }
        return gerentes;
    }

    public void excluir(Gerente gerente) throws Exception {
        GerenteDAO gerenteDAO = new GerenteDAO();
        gerenteDAO.excluir(gerente);
    }
}
