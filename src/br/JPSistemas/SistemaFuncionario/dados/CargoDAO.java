/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.dados;

import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import br.JPSistemas.SistemaFuncionario.entidade.Funcionario;
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
public class CargoDAO {

    private static final String SQL_INSERT = "INSERT INTO CARGO(NOME,SALARIO,ID_DEPARTAMENTO) VALUES(?,?,?)";
    private static final String SQL_BUSCAR_TODOS_DEPARTAMENTO = "SELECT ID , NOME , SALARIO, ID_DEPARTAMENTO FROM CARGO WHERE ID_DEPARTAMENTO=?";
    private static final String SQL_BUSCAR_POR_ID = "SELECT ID,NOME, ID_DEPARTAMENTO FROM CARGO WHERE ID = ?";
    private static final String SQL_BUSCAR_TODOS = "SELECT ID, NOME,ID_DEPARTAMENTO, SALARIO FROM CARGO";
    private static final String SQL_EXCLUIR_POR_ID = "DELETE FROM CARGO WHERE ID= ?";
    private static final String SQL_ALTERAR = "UPDATE CARGO SET NOME = ?, SALARIO=?, ID_DEPARTAMENTO=? WHERE ID=?";

    public void inserir(Cargo cargo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, cargo.getNome());
            comando.setDouble(2, cargo.getSalario());
            comando.setInt(3, cargo.getDepartamento().getId());
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
                conexao.close();
            }
        }
    }

    public List<Cargo> buscarTodosPorDepartamento(Departamento departamento) throws SQLException {
        List<Cargo> cargos = new ArrayList<Cargo>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS_DEPARTAMENTO);
            comando.setInt(1, departamento.getId());
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultado.getInt(1));
                cargo.setNome(resultado.getString(2));
                cargo.setSalario(resultado.getDouble(3));
                cargo.setDepartamento(departamento);
                cargos.add(cargo);
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
        return cargos;
    }

    public Cargo buscarPorID(int id) throws SQLException {
        Cargo cargo = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_ID);
            comando.setInt(1, id);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                cargo = new Cargo();
                cargo.setId(resultado.getInt(1));
                cargo.setNome(resultado.getString(2));
                Departamento departamento = new Departamento();
                departamento.setId(resultado.getInt(3));
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                departamento = departamentoDAO.buscarPorID(departamento.getId());
                cargo.setDepartamento(departamento);
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
        return cargo;
    }

    public List<Cargo> buscarTodos() throws SQLException {
        List<Cargo> cargos = new ArrayList<Cargo>();
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_TODOS);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(resultado.getInt(1));
                cargo.setNome(resultado.getString(2));
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                List<Funcionario> funcionarios = new ArrayList<Funcionario>();
                funcionarios = funcionarioDAO.buscarTodos(cargo);
                DepartamentoDAO departamentoDAO = new DepartamentoDAO();
                cargo.setDepartamento(departamentoDAO.buscarPorID(resultado.getInt(3)));
                cargo.setFuncionarios(funcionarios);
                cargo.setSalario(resultado.getDouble(4));
                cargos.add(cargo);
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
        return cargos;
    }

    public void excluir(Cargo cargo) throws SQLException {
        Connection connection = null;
        PreparedStatement comando = null;
        try {
            connection = BancoDadosUtil.getConnection();
            comando = connection.prepareStatement(SQL_EXCLUIR_POR_ID);
            comando.setInt(1, cargo.getId());
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
            if (comando != null && !connection.isClosed()) {
                comando.close();
            }
        }
    }

    public void alterar(Cargo cargo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ALTERAR);
            comando.setString(1, cargo.getNome());
            comando.setDouble(2, cargo.getSalario());
            comando.setInt(3, cargo.getDepartamento().getId());
            comando.setInt(4, cargo.getId());
            comando.execute();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.alterarSalario(conexao,cargo);
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
        }
    }
}
