/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.apresentacao;

import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import br.JPSistemas.SistemaFuncionario.negocio.CargoBO;
import br.JPSistemas.SistemaFuncionario.negocio.DepartamentoBO;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSIMAR
 */
public class TelaCadastroCargo extends javax.swing.JPanel implements AbaSelecionada {

    List<Departamento> departamentos = null;
    Cargo cargo = null;

    /**
     * Creates new form TelaCadastroCargo
     */
    public TelaCadastroCargo() {
        initComponents();
        departamentos = new ArrayList<Departamento>();
        cargo = new Cargo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSalvarCargo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxDepartamento = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome *");

        txtSalario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });

        jLabel2.setText("Salario*");

        btnSalvarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/JPSistemas/SistemaFuncionario/apresentacao/icones/disk.png"))); // NOI18N
        btnSalvarCargo.setText("Salvar");
        btnSalvarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarCargoActionPerformed(evt);
            }
        });

        jLabel3.setText("Departamento");

        cbxDepartamento.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cbxDepartamentoMouseMoved(evt);
            }
        });

        jLabel4.setText(" * Campos Obrigatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvarCargo)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(11, 11, 11)
                .addComponent(btnSalvarCargo)
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void cbxDepartamentoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxDepartamentoMouseMoved
        cbxDepartamento.removeAllItems();
        carregarListaDepartamento();
// TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentoMouseMoved

    private void btnSalvarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCargoActionPerformed
        try {
            camposObrigatorios();
            CargoBO cargoBO = new CargoBO();
            lerDadosTela();
            cargoBO.inserir(cargo);
            String mensagem = "Cargo Cadastrado com Sucesso!!";
            String titulo = "Cadastro de Cargo";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
            // TODO add your handling code here:
        }catch (CamposObrigatoriosException ex) {
            String titulo = "Erro ao Cadastrar Cargo";
            String mensagem = "Campo Obrigatorio Não Preenchido!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaCadastroDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            String titulo = "Erro ao Cadastrar Cargo";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaCadastroDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarCargoActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained
    public void lerDadosTela() throws ParseException {
        cargo.setNome(txtNome.getText());
        String salario = txtSalario.getText();
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        cargo.setSalario(formatador.parse(salario).doubleValue());
        int linha = cbxDepartamento.getSelectedIndex();
        cargo.setDepartamento(departamentos.get(linha));
    }

    public void carregarListaDepartamento() {
        try {
            DepartamentoBO departamentoBO = new DepartamentoBO();
            departamentos = departamentoBO.buscarTodos();
            for (int i = 0; i < departamentos.size(); i++) {
                Departamento departamento = departamentos.get(i);
                cbxDepartamento.addItem(departamento.getArea());
            }

        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroGerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvarCargo;
    private javax.swing.JComboBox cbxDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtSalario;
    // End of variables declaration//GEN-END:variables

    private void camposObrigatorios(){
        if(txtNome.getText().isEmpty() || txtSalario.getText().isEmpty()||cbxDepartamento.getModel().getSize()==0){
            throw  new CamposObrigatoriosException();
        }
    }
    @Override
    public void OnAbaSelecionada() {
        cbxDepartamento.removeAllItems();
        carregarListaDepartamento();
    }
}
