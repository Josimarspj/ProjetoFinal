/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.apresentacao;

import br.JPSistemas.SistemaFuncionario.entidade.Diretor;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import br.JPSistemas.SistemaFuncionario.negocio.DiretorBO;
import br.JPSistemas.SistemaFuncionario.negocio.GerenteBO;
import br.JPSistemas.SistemaFuncionario.negocio.UsuarioInvalidoException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSIMAR
 */
public class TelaLogin extends javax.swing.JFrame {

    TelaInicial inicial;

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin(TelaInicial telaInicial) {
        initComponents();
        this.inicial = telaInicial;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        cbxCargo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Funcionários - Login");
        setExtendedState(-1);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuário");

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        jLabel2.setText("Senha");

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/JPSistemas/SistemaFuncionario/apresentacao/icones/door_in.png"))); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        cbxCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diretor", "Gerente" }));

        jLabel3.setText("Cargo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(txtUsuario)
                    .addComponent(btnEntrar))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btnEntrar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            if (cbxCargo.getSelectedItem().toString().equals("Gerente")) {
                Gerente gerente = new Gerente();
                gerente.setUsuario(txtUsuario.getText());
                gerente.setSenha(txtSenha.getText());
                GerenteBO gerenteBO = new GerenteBO();
                inicial.gerente = gerenteBO.autenticarUsuario(gerente);
                habilitarTelaGerente();
                this.dispose();
            } else {
                Diretor diretor = new Diretor();
                diretor.setSenha(txtSenha.getText());
                diretor.setUsuario(txtUsuario.getText());
                DiretorBO diretorBO = new DiretorBO();
                inicial.diretor = diretorBO.autenticarUsuario(diretor);
                habilitarTelaDiretor();
                this.dispose();
            }
        } catch (UsuarioInvalidoException ex) {
            String titulo = "Login de Usuario";
            String mensagem = "Usuario não Cadastrado!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            String titulo = "Erro ao Fazer Login";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated
    public void habilitarTelaDiretor() {
        inicial.setTitle("Sistema de Funcionário [Usuario Logado["+inicial.diretor.getUsuario()+"]]");
        inicial.mnuConsulta.setVisible(true);
        inicial.mnuCadastro.setVisible(true);
        inicial.mnuRelatorios.setVisible(true);
        inicial.plnLogin.setVisible(true);
        inicial.lbNomeLogin.setText(inicial.diretor.getUsuario());
        inicial.mnuSair.setVisible(true);
        inicial.mnuLogin.setVisible(false);
        inicial.mnuGraficos.setVisible(true);
        inicial.telaConsultaFuncionario.btnExcluir.setVisible(true);
    }

    public void habilitarTelaGerente() {
        inicial.setTitle("Sistema de Funcionário [Usuario Logado["+inicial.gerente.getUsuario()+"]");
        inicial.mnuConsulta.setVisible(true);
        inicial.mnuCadastro.setVisible(true);
        inicial.mnuRelatorios.setVisible(true);
        inicial.plnLogin.setVisible(true);
        inicial.lbNomeLogin.setText(inicial.gerente.getUsuario());
        inicial.mnuSair.setVisible(true);
        inicial.mnuCadastroGerente.setVisible(false);
        inicial.mnuCadastroDepartamento.setVisible(false);
        inicial.mnuCadastroCargo.setVisible(false);
        inicial.mnuLogin.setVisible(false);
        inicial.mnuRelatoriosCargos.setVisible(false);
        inicial.mnuAlterarCargo.setVisible(false);
        inicial.mnuAlterarDepartamento.setVisible(false);
        inicial.mnuAlterarGerente.setVisible(false);
        inicial.mnuGraficos.setVisible(true);
        inicial.telaConsultaFuncionario.btnExcluir.setVisible(false);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JComboBox cbxCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
