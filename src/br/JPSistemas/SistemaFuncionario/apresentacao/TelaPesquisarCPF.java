/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.apresentacao;

import br.JPSistemas.SistemaFuncionario.entidade.Diretor;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import br.JPSistemas.SistemaFuncionario.negocio.GerenteBO;
import br.JPSistemas.SistemaFuncionario.negocio.UsuarioInvalidoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSIMAR
 */
public class TelaPesquisarCPF extends javax.swing.JFrame {

    TelaInicial inicial;
    TelaAlterarGerente telaAlterarGerente;

    /**
     * Creates new form TelaPesquisarCPF
     */
    public TelaPesquisarCPF(TelaInicial telaInicial) {
        initComponents();
        inicial = telaInicial;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCPF = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        btnProcurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Gerente");

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("Digite o CPF do Gerente");

        btnProcurar.setText("Pesquisar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnProcurar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnProcurar)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        try {
            Gerente gerente = new Gerente();
            gerente.setCpf(txtCPF.getText());
            GerenteBO gerenteBO = new GerenteBO();
            gerenteBO.buscarPorCPF(gerente);
            telaAlterarGerente = new TelaAlterarGerente(gerenteBO.buscarPorCPF(gerente), inicial);
            inicial.adicionarTab("Alterar Dados de Gerente",telaAlterarGerente);
            this.dispose();
        } catch (UsuarioInvalidoException ex) {
            String titulo = "Erro ao Procurar Gerente";
            String mensagem = "Gerente Não Encontrado!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaCadastroDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            String titulo = "Erro ao Procurar Gerente";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaCadastroDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnProcurarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField txtCPF;
    // End of variables declaration//GEN-END:variables
}
