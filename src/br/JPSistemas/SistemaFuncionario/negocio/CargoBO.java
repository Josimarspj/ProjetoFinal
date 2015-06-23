/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.negocio;

import br.JPSistemas.SistemaFuncionario.dados.CargoDAO;
import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import java.util.List;

/**
 *
 * @author JOSIMAR
 */
public class CargoBO {

    public List<Cargo> buscarTodos(Departamento departamento) throws Exception {
        CargoDAO cargoDAO = new CargoDAO();
        if (cargoDAO.buscarTodosPorDepartamento(departamento).size() > 0) {
            return cargoDAO.buscarTodosPorDepartamento(departamento);
        } else {
            throw new ListaCargosVaziaException();
        }
    }

    public List<Cargo> buscarTodos() throws Exception {
        CargoDAO cargoDAO = new CargoDAO();
        if (cargoDAO.buscarTodos().size() > 0) {
            return cargoDAO.buscarTodos();
        } else {
            throw new ListaCargosVaziaException();
        }
    }

    public void inserir(Cargo cargo) throws Exception {
        try {
            CargoDAO cargoDAO = new CargoDAO();
            cargoDAO.inserir(cargo);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void excluir(Cargo cargo) throws Exception {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.excluir(cargo);
    }

    public void alterar(Cargo cargo) throws Exception {
        CargoDAO cargoDAO = new CargoDAO();
        cargoDAO.alterar(cargo);
    }

}
