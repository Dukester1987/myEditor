
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ldulka
 */
public class Loading extends javax.swing.JFrame implements Runnable{

    private File file;
    private JEditorPane pane;
    private int lines;
    private boolean loading = true;

    public Loading(File file, JEditorPane pane, int linesTotal) {
        initComponents();
        this.file = file;
        this.pane = pane;
        this.lines = linesTotal;  
        
        jProgressBar1.setValue(0);
        
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loading file");
        setAlwaysOnTop(true);
        setUndecorated(true);

        jProgressBar1.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar1.setStringPainted(true);

        jLabel1.setText("Loading file please wait...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            String s = "";
            Document doc = pane.getDocument();
            BufferedReader br = new BufferedReader(new FileReader(file));  
            int actual = 0;
            pane.setEditable(false);
            while ((s = br.readLine()) != null) {                               
                doc.insertString(doc.getLength(), s+"\n", null);
                updateBar(actual,lines);
                actual += 1;
            }               
            br.close();    
            pane.setEditable(true);
            this.dispose();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException | BadLocationException ex) {
            System.out.println(ex);
        }        
    }

    private void updateBar(int actual, int lines) {
        double percentage = ((double) actual/(double) lines)*100;
        //System.out.printf("%s perc , %s actual, %s total lines\n",percentage,actual,lines);
        jProgressBar1.setValue((int) percentage);
    }
}
