/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorUDP;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Usuário
 */
public class NewJFrame extends javax.swing.JFrame {


    JPanel painelFundo;
    JTable tabela;
    JScrollPane barraRolagem;
       
    Object [][] dados = {
        {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
        {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
        {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
    };
    
    String [] colunas = {"Nome", "Sequencia", "Data/Hora"};
    
    public NewJFrame() {
        super ("Contatos");
    }
    
    
    public void criaJanela(){
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        tabela = new JTable(dados, colunas);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem); 
        
         
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 500);
        setVisible(true);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        NewJFrame njf = new NewJFrame();
        njf.criaJanela();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}