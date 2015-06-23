/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.negocio;

import br.JPSistemas.SistemaFuncionario.dados.FuncionarioDAO;
import br.JPSistemas.SistemaFuncionario.dados.GerenteDAO;
import br.JPSistemas.SistemaFuncionario.entidade.Funcionario;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JOSIMAR
 */
public class FuncionarioBO {

    public void inserir(Funcionario funcionario) throws Exception {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente = new Gerente();
        gerente.setCpf(funcionario.getCpf());
        if (funcionarioDAO.procurarPorCpf(funcionario) == null && gerenteDAO.procurarPorCpf(gerente) == null) {
            funcionarioDAO.inserir(funcionario);
        } else {
            throw new CPFJaCadastradoException();
        }
    }

    public List<Funcionario> buscarTodos() throws Exception {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarios = funcionarioDAO.buscarTodos();
        if (funcionarios.size() == 0) {
            throw new ListaFuncionariosVaziaException();
        }
        return funcionarios;
    }

    public void excluir(Funcionario funcionario) throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.excluir(funcionario);
    }

    public void alterar(Funcionario funcionario, String CPFAntigo) throws Exception {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (!funcionario.getCpf().equals(CPFAntigo)) {
            GerenteDAO gerenteDAO = new GerenteDAO();
            Gerente gerente = new Gerente();
            gerente.setCpf(funcionario.getCpf());
            if (funcionarioDAO.procurarPorCpf(funcionario) == null && gerenteDAO.procurarPorCpf(gerente) == null) {
                funcionarioDAO.alterar(funcionario);
            } else {
                throw new CPFJaCadastradoException();
            }
        } else {
            funcionarioDAO.alterar(funcionario);
        }

    }
}
