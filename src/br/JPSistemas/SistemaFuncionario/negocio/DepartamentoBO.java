/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.negocio;

import br.JPSistemas.SistemaFuncionario.dados.DepartamentoDAO;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JOSIMAR
 */
public class DepartamentoBO {

    public void inserir(Departamento departamento) throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.inserir(departamento);
    }

    public Departamento buscarPorCodigo(int codigo) throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Departamento departamento = departamentoDAO.procurarPorCodigo(codigo);
        if (departamento != null) {
            return departamento;
        } else {
            throw new UsuarioInvalidoException();
        }
    }

    public List<Departamento> buscarTodos() throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        if (departamentoDAO.buscarTodos().size() > 0) {
            return departamentoDAO.buscarTodos();
        } else {
            throw new ListaDepartamentoVaziaException();
        }

    }
    
    public void excluir(Departamento departamento) throws Exception {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.excluir(departamento);
    }

    public void alterar(Departamento departamento) throws SQLException {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.alterar(departamento);
    }
}
