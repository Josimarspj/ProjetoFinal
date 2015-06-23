/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.dados;

import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSIMAR
 */
public class DepartamentoDAO {

    private static final String SQL_INSERT = "INSERT INTO DEPARTAMENTO(AREA) VALUES(?)";
    private static final String SQL_BUSCAR_POR_CODIGO = "SELECT * FROM DEPARTAMENTO WHERE ID=?";
    private static final String SQL_BUSCAR_TODOS = "SELECT ID, AREA FROM DEPARTAMENTO";
    private static final String SQL_BUSCAR_POR_GERENTE = "SELECT D.ID FROM DEPARTAMENTO D JOIN GERENTE G ON ?=G.ID_DEPARTAMENTO";
    private static final String SQL_BUSCAR_POR_ID = "SELECT AREA FROM DEPARTAMENTO WHERE ID=?";
    private static final String SQL_EXCLUIR_POR_ID = "DELETE FROM DEPARTAMENTO WHERE ID= ?";
    private static final String SQL_ALTERAR = "UPDATE DEPARTAMENTO SET AREA = ? WHERE ID=?";
    
    public void inserir(Departamento departamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, departamento.getArea());
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

    public Departamento procurarPorCodigo(int codigo) throws SQLException {
        Departamento departamento = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CODIGO);
            comando.setInt(1, codigo);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                departamento = new Departamento();
                departamento.setId(resultado.getInt(codigo));
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
        return departamento;
    }

    public List<Departamento> buscarTodos() throws SQLException {
        List<Departamento> departamentos = new ArrayList<Departamento>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(resultado.getInt(1));
                departamento.setArea(resultado.getString(2));
                CargoDAO cargoDAO = new CargoDAO();
                List<Cargo> cargos = cargoDAO.buscarTodosPorDepartamento(departamento);
                departamento.setCargos(cargos);
                departamentos.add(departamento);
            }
        } catch (SQLException e) {
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
        return departamentos;
    }

    public Departamento buscarPorGerente(Gerente gerente) throws SQLException {
        Departamento departamento = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_GERENTE);
            comando.setInt(1, gerente.getDepartamento().getId());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                departamento = new Departamento();
                departamento.setId(resultado.getInt(1));
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
        return departamento;
    }

    public Departamento buscarPorID(int id) throws SQLException {
        Departamento departamento = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_ID);
            comando.setInt(1, id);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                departamento = new Departamento();
                departamento.setArea(resultado.getString(1));
            }
        } catch (SQLException e) {
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
        return departamento;
    }
    
    public void excluir(Departamento departamento) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_EXCLUIR_POR_ID);
            comando.setInt(1, departamento.getId());
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
            if (comando != null && !conexao.isClosed()) {
                comando.close();
            }
        }
    }

    public void alterar(Departamento departamento) throws SQLException {
        Connection connection = null;
        PreparedStatement comando = null;
        try {
            connection = BancoDadosUtil.getConnection();
            comando = connection.prepareStatement(SQL_ALTERAR);
            comando.setString(1, departamento.getArea());
            comando.setInt(2, departamento.getId());
            comando.execute();
            connection.commit();
        } catch (Exception e) {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
        }
    }
}
