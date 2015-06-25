/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.JPSistemas.SistemaFuncionario.apresentacao;

import br.JPSistemas.SistemaFuncionario.entidade.Cargo;
import br.JPSistemas.SistemaFuncionario.entidade.Departamento;
import br.JPSistemas.SistemaFuncionario.entidade.Gerente;
import br.JPSistemas.SistemaFuncionario.negocio.CPFJaCadastradoException;
import br.JPSistemas.SistemaFuncionario.negocio.DepartamentoBO;
import br.JPSistemas.SistemaFuncionario.negocio.DepartamentoJaContemGerenteException;
import br.JPSistemas.SistemaFuncionario.negocio.GerenteBO;
import br.JPSistemas.SistemaFuncionario.negocio.ListaDepartamentoVaziaException;
import br.JPSistemas.SistemaFuncionario.negocio.UsuarioJaCadatradoException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JOSIMAR
 */
public class TelaAlterarGerente extends javax.swing.JPanel implements AbaSelecionada {

    Gerente gerente;
    Departamento departamento;
    List<Cargo> cargos;
    List<Departamento> departamentos;
    TelaInicial inicial;

    /**
     * Creates new form TelaCadastroFuncionario
     */
    public TelaAlterarGerente(Gerente gerente, TelaInicial telaInicial) {
        initComponents();
        departamento = new Departamento();
        cargos = new ArrayList<Cargo>();
        departamentos = new ArrayList<Departamento>();
        this.gerente = gerente;
        inicial= telaInicial;
    }

    public void carregarListaDepartamento() {
        try {
            DepartamentoBO departamentoBO = new DepartamentoBO();
            departamentos = departamentoBO.buscarTodos();
            for (int i = 0; i < departamentos.size(); i++) {
                Departamento departamento = departamentos.get(i);
                cbxDepartamento.addItem(departamento.getArea());
            }
        } catch (ListaDepartamentoVaziaException ex) {
            String titulo = "Lista de Departamento";
            String mensagem = "Lista de Departamento Vazia, Cadastre um Departemnto e seus cargos para Realizar o Cadastro do Funcionario";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtRg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        cbxDepartamento = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JFormattedTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setText("Nome*");

        jLabel2.setText("CPF *");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setEnabled(false);
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        jLabel3.setText("RG *");

        try {
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Data de Nascimento*");

        jLabel5.setText("Cargo*");

        btnSalvar.setText("Alterar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel6.setText("Login *");

        txtLogin.setEnabled(false);

        jLabel7.setText("Senha *");

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        cbxDepartamento.setEnabled(false);
        cbxDepartamento.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cbxDepartamentoMouseMoved(evt);
            }
        });
        cbxDepartamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbxDepartamentoFocusLost(evt);
            }
        });
        cbxDepartamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxDepartamentoMouseClicked(evt);
            }
        });

        jLabel8.setText("Departamento *");

        txtCargo.setEditable(false);
        txtCargo.setText("Gerente");
        txtCargo.setEnabled(false);
        txtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargoActionPerformed(evt);
            }
        });

        jLabel9.setText("Salario");

        txtSalario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtSalario.setText("3000");
        txtSalario.setEnabled(false);

        txtCodigo.setEnabled(false);

        jLabel10.setText("Código");

        jLabel11.setText("* Campos Obrigatorios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCargo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(txtRg, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(cbxDepartamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(2, 2, 2)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            Gerente gerenteAntigo = gerente;
            lerDadosTela();
            camposObrigatorios();
            GerenteBO gerenteBO = new GerenteBO();
            gerenteBO.alterar(gerente);
            String mensagem = "Dados Alterados com Sucesso!";
            String titulo = "Alteração de dados de Gerente";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
        }catch(CamposObrigatoriosException ex){
            String titulo = "Erro Alterar ao Alterar de Gerente";
            String mensagem = "Campo(s) Obrigatório(s) Não Preenchido(s)!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaCadastroDepartamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DepartamentoJaContemGerenteException ex) {
            String titulo = "Erro Alterar ao Alterar de Gerente";
            String mensagem = "Departamento já contem Gerente Desgninado!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CPFJaCadastradoException ex) {
            String titulo = "Erro Alterar ao Alterar de Gerente";
            String mensagem = "CPF de Gerente Ja Cadastrado em um Departamento!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioJaCadatradoException ex) {
            String titulo = "Erro Alterar ao Alterar de Gerente";
            String mensagem = "Nome de Usuário Ja Cadastrado!";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            String titulo = "Erro Alterar ao Alterar de Gerente";
            String mensagem = "Ocorreu um erro inesperado, Favor entra em contato com o administrador";
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TelaAlterarGerente.class.getName()).log(Level.SEVERE, null, e);
        }

// TODO add your handling code here:
// TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void cbxDepartamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxDepartamentoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentoMouseClicked

    private void cbxDepartamentoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxDepartamentoMouseMoved
        cbxDepartamento.removeAllItems();
        carregarListaDepartamento();

// TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentoMouseMoved

    private void cbxDepartamentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxDepartamentoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDepartamentoFocusLost

    private void txtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargoActionPerformed

    public void lerDadosTela() throws ParseException {
        gerente.setNome(txtNome.getText());
        gerente.setCpf(txtCpf.getText());
        int linha = cbxDepartamento.getSelectedIndex();
        this.departamento = departamentos.get(linha);
        gerente.setDepartamento(departamento);
        gerente.setRg(txtRg.getText());
        String formato = "dd/MM/yyyy";
        java.util.Date date = new SimpleDateFormat(formato).parse(txtDataNascimento.getText());
        gerente.setDataNascimento(date);
        String salario = txtSalario.getText();
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        gerente.setSalario(formatador.parse(salario).doubleValue());
        gerente.setUsuario(txtLogin.getText());
        gerente.setSenha(txtSenha.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbxDepartamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    private javax.swing.JFormattedTextField txtSalario;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables

    @Override
    public void OnAbaSelecionada() {
        cbxDepartamento.removeAllItems();
        carregarListaDepartamento();
        txtNome.setText(gerente.getNome());
        txtCpf.setText(gerente.getCpf());
        txtRg.setText(gerente.getRg());
        String formato = "dd/MM/yyyy";
        String date = new SimpleDateFormat(formato).format(gerente.getDataNascimento());
        txtDataNascimento.setText(date);
        txtLogin.setText(gerente.getUsuario());
        cbxDepartamento.setSelectedItem(gerente.getDepartamento().getArea());
        txtCodigo.setText(String.valueOf(gerente.getId()));
    }

    private void camposObrigatorios() {
        if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()
                || txtRg.getText().isEmpty() || txtDataNascimento.getText().isEmpty()
                || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()|| cbxDepartamento.getModel().getSize()==0) {
            throw new CamposObrigatoriosException();
        }
    }
}