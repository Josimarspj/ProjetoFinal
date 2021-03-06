/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.apresentacao;

import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import br.JPSistemas.SistemaFuncionario.negocio.DepartamentoBO;
import br.JPSistemas.SistemaFuncionario.negocio.ListaDepartamentoVaziaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author JOSIMAR
 */
public class TelaConsultaDepartamento extends javax.swing.JPanel implements AbaSelecionada {

    List<Departamento> departamentos;
    TelaInicial inicial;
    TelaAlterarDepartamento telaAlterarDepartamento;

    /**
     * Creates new form TelaRelatorioFuncionarioCargo
     */
    public TelaConsultaDepartamento(TelaInicial telaInicial) {
        initComponents();
        departamentos = new ArrayList<Departamento>();
        inicial = telaInicial;
    }

    @Override
    public void OnAbaSelecionada() {
        exibirDadosTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Area"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDepartamentos);

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/JPSistemas/SistemaFuncionario/apresentacao/icones/user_edit.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/JPSistemas/SistemaFuncionario/apresentacao/icones/user_delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAlterar)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            int linhaSelecionada = this.tblDepartamentos.getSelectedRow();
            Departamento departamento = this.departamentos.get(linhaSelecionada);
            String mensagem = "Deseja Excluir Departamento " + departamento.getArea() + "?";
            String titulo = "Excluir Departamento";
            int resultado = JOptionPane.showConfirmDialog(this, mensagem, titulo, JOptionPane.YES_NO_OPTION);
            if (resultado == JOptionPane.YES_OPTION) {
                DepartamentoBO departamentoBO = new DepartamentoBO();
                departamentoBO.excluir(departamento);
                titulo = "Excluir Departamento";
                mensagem = "Departamento Com Sucesso!";
                JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
                exibirDadosTabela();
            }
        } catch (Exception ex) {
            String titulo = "Excluir Departamento!";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaConsultaDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int linhaSelecionada = this.tblDepartamentos.getSelectedRow();
        Departamento departamento = this.departamentos.get(linhaSelecionada);
        telaAlterarDepartamento = new TelaAlterarDepartamento(departamento);
        inicial.adicionarTab("Alterar Departamento", telaAlterarDepartamento);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void exibirDadosTabela() {
        try {
            this.carregarListaDepartamento();
            ModeloDadosFuncionarios modeloDadosFuncionarios = new ModeloDadosFuncionarios();
            this.tblDepartamentos.setModel(modeloDadosFuncionarios);
            btnAlterar.setEnabled(true);
            btnExcluir.setEnabled(true);
        } catch (ListaDepartamentoVaziaException ex) {
            btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            String titulo = "Lista de Departamento";
            String mensagem = "Lista de Departamento Vazia!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaConsultaDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            String titulo = "Erro ao Carregar Lista de Departamento!";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaConsultaDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarListaDepartamento() throws Exception {
        DepartamentoBO departamentoBO = new DepartamentoBO();
        departamentos = departamentoBO.buscarTodos();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDepartamentos;
    // End of variables declaration//GEN-END:variables
class ModeloDadosFuncionarios extends AbstractTableModel {

        @Override
        public String getColumnName(int coluna) {
            if (coluna == 0) {
                return "ID";
            } else {
                return "Area";

            }
        }

        @Override
        public int getRowCount() {
            return departamentos.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Departamento departamento = departamentos.get(rowIndex);
            if (columnIndex == 0) {
                return departamento.getId();
            } else {
                return departamento.getArea();
            }
        }

    }

}
