/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.dados;

import br.JPSistemas.SistemaFuncionario.entidade.Diretor;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JOSIMAR
 */
public class DiretorDAO {

    private static final String SQL_INSERT_DIRETOR = "INSERT INTO DIRETOR(NOME,CPF,RG,SALARIO,DATA_NASC, USUARIO, SENHA) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_BUSCAR_POR_SENHA = "SELECT SENHA,USUARIO FROM DIRETOR WHERE USUARIO=? AND SENHA=?";

    public void inserir(Diretor diretor) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao= BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT_DIRETOR);
            comando.setString(1, diretor.getNome());
            comando.setString(2, diretor.getCpf());
            comando.setString(3, diretor.getRg());
            comando.setDouble(4, diretor.getSalario());
            Date date = new Date(diretor.getDataNascimento().getTime());
            comando.setDate(5, date);
            comando.setString(6, diretor.getUsuario());
            comando.setString(7, diretor.getSenha());
            comando.execute();
        } catch (Exception e) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();
            }
        }
    }

    public Diretor buscarPorSenha(Diretor diretor) throws SQLException {
        Diretor diretor1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando=conexao.prepareStatement(SQL_BUSCAR_POR_SENHA);
            comando.setString(1, diretor.getUsuario());
            comando.setString(2, diretor.getSenha());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                diretor.setNome(resultado.getString(1));
                diretor1 = diretor;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (resultado != null && !resultado.isClosed()) {
                resultado.close();
            }
        }
        return diretor1;
    }
}
