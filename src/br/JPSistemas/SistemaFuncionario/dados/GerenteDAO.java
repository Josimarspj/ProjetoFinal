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
public class GerenteDAO {

    private static final String SQL_INSERT_USUARIO = "INSERT INTO GERENTE(NOME,CPF,RG,SALARIO,DATA_NASC, USUARIO, SENHA, ID_DEPARTAMENTO) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_BUSCAR_POR_SENHA = "SELECT NOME FROM GERENTE WHERE USUARIO=? AND SENHA=?";
    private static final String SQL_BUSCAR_POR_CODIGO = "SELECT * FROM GERENTE WHERE ID=?";
    private static final String SQL_BUSCAR_POR_USUARIO = "SELECT * FROM GERENTE WHERE USUARIO=?";
    private static final String SQL_BUSCAR_POR_CPF = "SELECT * FROM GERENTE WHERE CPF=?";

    public void inserir(Gerente gerente) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT_USUARIO);
            comando.setString(1, gerente.getNome());
            comando.setString(2, gerente.getCpf());
            comando.setString(3, gerente.getRg());
            comando.setDouble(4, gerente.getSalario());
            Date date = new Date(gerente.getDataNascimento().getTime());
            comando.setDate(5, date);
            comando.setString(6, gerente.getUsuario());
            comando.setString(7, gerente.getSenha());
            comando.setInt(8, gerente.getDepartamento().getId());
            comando.execute();
            conexao.commit();
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

    public Gerente buscarPorSenha(Gerente gerente) throws SQLException {
        Gerente gerente1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_SENHA);
            comando.setString(1, gerente.getUsuario());
            comando.setString(2, gerente.getSenha());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                gerente.setNome(resultado.getString(1));
                gerente1 = gerente;
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
        return gerente1;
    }

    public Gerente procurarPorCodigo(int codigo) throws SQLException {
        Gerente gerente1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CODIGO);
            comando.setInt(1, codigo);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                gerente1 = new Gerente();
                gerente1.setId(resultado.getInt(codigo));
                gerente1.setNome(resultado.getString(2));
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
        return gerente1;
    }

    public Gerente procurarPorUsuario(Gerente gerente) throws SQLException {
        Gerente gerente1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_USUARIO);
            comando.setString(1, gerente.getUsuario());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                gerente1 = new Gerente();
                gerente1.setId(resultado.getInt(1));
                gerente1.setNome(resultado.getString(2));
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
        return gerente1;
    }

    public Gerente procurarPorCpf(Gerente gerente) throws SQLException {
        Gerente gerente1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CPF);
            comando.setString(1, gerente.getCpf());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                gerente1 = new Gerente();
                gerente1.setId(resultado.getInt(1));
                gerente1.setNome(resultado.getString(2));
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
        return gerente1;
    }
}
