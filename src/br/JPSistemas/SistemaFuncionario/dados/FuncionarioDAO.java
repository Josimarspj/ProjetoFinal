/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.dados;

import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Funcionario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSIMAR
 */
public class FuncionarioDAO {

    private static final String SQL_INSERT = "INSERT INTO FUNCIONARIO(NOME,CPF,RG,SALARIO,DATA_NASC,ID_CARGO) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_TODOS = "SELECT NOME,CPF,ID_CARGO, SALARIO, DATA_NASC,ID, RG FROM FUNCIONARIO";
    private static final String SQL_BUSCAR_POR_ID = "SELECT F.NOME,C.NOME FROM FUNCIONARIOS F JOIN CARGO C ON F.ID_CARGO=C_ID WHERE F_ID=?";
    private static final String SQL_BUSCAR_POR_CPF = "SELECT * FROM FUNCIONARIO WHERE CPF=?";
    private static final String SQL_BUSCAR_POR_CARGO = "SELECT ID,NOME,CPF FROM FUNCIONARIO WHERE ID_CARGO=?";
    private static final String SQL_EXCLUIR_POR_ID = "DELETE FROM FUNCIONARIO WHERE ID= ?";
    private static final String SQL_ALTERAR_SALARIO = "UPDATE FUNCIONARIO SET SALARIO= ? WHERE ID_CARGO=?";
    private static final String SQL_ALTERAR = "UPDATE FUNCIONARIO SET NOME=?,CPF=?,RG=?,SALARIO=?,DATA_NASC=?,ID_CARGO=? WHERE ID=?";
    
    public void inserir(Funcionario funcionario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_INSERT);
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getCpf());
            comando.setString(3, funcionario.getRg());
            comando.setDouble(4, funcionario.getSalario());
            Date date = new Date(funcionario.getDataNascimento().getTime());
            comando.setDate(5, date);
            comando.setInt(6, funcionario.getCargo().getId());
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
        }

    }

    public Funcionario buscarPorID(Funcionario funcionario) throws SQLException {
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Connection conexao = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_ID);
            comando.setInt(1, funcionario.getId());
            resultado = comando.executeQuery();
            resultado.next();
            funcionario.setNome(resultado.getString(1));
            Cargo cargo = new Cargo();
            cargo.setNome(resultado.getString(2));
            funcionario.setCargo(cargo);
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
        return funcionario;
    }

    public Funcionario procurarPorCpf(Funcionario funcionario) throws SQLException {
        Funcionario funcionario1 = null;
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CPF);
            comando.setString(1, funcionario.getCpf());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                funcionario1 = new Funcionario();
                funcionario1.setId(resultado.getInt(1));
                funcionario1.setNome(resultado.getString(2));
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
        return funcionario1;
    }

    public List<Funcionario> buscarTodos() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection conexao = null;
        ResultSet resultado = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_SELECT_TODOS);
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(resultado.getString(1));
                funcionario.setCpf(resultado.getString(2));
                CargoDAO cargoDAO = new CargoDAO();
                funcionario.setCargo(cargoDAO.buscarPorID(resultado.getInt(3)));
                funcionario.setSalario(resultado.getDouble(4));
                funcionario.setDataNascimento(resultado.getDate(5));
                funcionario.setId(resultado.getInt(6));
                funcionario.setRg(resultado.getString(7));
                funcionarios.add(funcionario);
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
        return funcionarios;
    }

    public List<Funcionario> buscarTodos(Cargo cargo) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Connection conexao = null;
        ResultSet resultado = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_BUSCAR_POR_CARGO);
            comando.setInt(1, cargo.getId());
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getInt(1));
                funcionario.setNome(resultado.getString(2));
                funcionario.setCpf(resultado.getString(3));
                funcionarios.add(funcionario);
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
        return funcionarios;
    }

    public void excluir(Funcionario funcionario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_EXCLUIR_POR_ID);
            comando.setInt(1, funcionario.getId());
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

    public void alterarSalario(Connection conexao, Cargo cargo) throws SQLException {
        PreparedStatement comando = null;
        try {
            comando = conexao.prepareStatement(SQL_ALTERAR_SALARIO);
            comando.setDouble(1, cargo.getSalario());
            comando.setInt(2, cargo.getId());
            comando.execute();
        } catch (Exception e) {
            if (conexao != null && !conexao.isClosed()) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    

    public void alterar(Funcionario funcionario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ALTERAR);
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getCpf());
            comando.setString(3, funcionario.getRg());
            comando.setDouble(4, funcionario.getSalario());
            Date date = new Date(funcionario.getDataNascimento().getTime());
            comando.setDate(5, date);
            comando.setInt(6, funcionario.getCargo().getId());
            comando.setInt(7,funcionario.getId());
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
    }
    
}
}
