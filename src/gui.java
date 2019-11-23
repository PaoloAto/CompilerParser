//import grammar.sample1Parser.program_return;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.util.Arrays;

import static org.antlr.v4.runtime.CharStreams.fromString;





public class gui extends javax.swing.JFrame {


    public gui() {
        initComponents();
        ScanPane.setDividerLocation(0.33);
        ParseRunPane.setDividerLocation(0.50);
        Output.setEditable(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        ButtonTextSplit = new javax.swing.JSplitPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        Input = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        ScanPane = new javax.swing.JSplitPane();
        ParseRunPane = new javax.swing.JSplitPane();
        ParseBtn = new javax.swing.JButton();
        RunBtn = new javax.swing.JButton();
        ScanBtn = new javax.swing.JButton();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonTextSplit.setDividerLocation(100);
        ButtonTextSplit.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        Input.setColumns(20);
        Input.setRows(5);
        jScrollPane1.setViewportView(Input);

        jSplitPane4.setTopComponent(jScrollPane1);

        Output.setColumns(20);
        Output.setRows(5);
        jScrollPane2.setViewportView(Output);

        jSplitPane4.setRightComponent(jScrollPane2);

        ButtonTextSplit.setRightComponent(jSplitPane4);

        ParseBtn.setText("Parse");
        ParseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParseBtnActionPerformed(evt);
            }
        });
        ParseRunPane.setLeftComponent(ParseBtn);

        RunBtn.setText("Run");
        RunBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunBtnActionPerformed(evt);
            }
        });
        ParseRunPane.setRightComponent(RunBtn);

        ScanPane.setRightComponent(ParseRunPane);

        ScanBtn.setText("Scan");
        ScanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScanBtnActionPerformed(evt);
            }
        });
        ScanPane.setLeftComponent(ScanBtn);

        ButtonTextSplit.setLeftComponent(ScanPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ButtonTextSplit, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ButtonTextSplit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void ScanBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
            org.antlr.v4.runtime.CharStream stream = fromString(Input.getText());
            Java8Lexer lexer  = new Java8Lexer(stream);
            org.antlr.v4.runtime.TokenStream tokenStream = new CommonTokenStream(lexer);
            Java8Parser parser = new Java8Parser(tokenStream);

            SyntaxErrorListener listener = new SyntaxErrorListener();
            parser.addErrorListener(listener);
            ParseTree tree = parser.compilationUnit();

            Output.setText("");
            for(int i=0;i<listener.getSyntaxErrors().size();i++){
                String msg = listener.getSyntaxErrors().get(i).getMessage();
                String error = msg.split("'")[1];
                System.out.println(error);
                if (error.contains("missing")) {
                    Output.append("(Syntax error at line:" + listener.getSyntaxErrors().get(i).getLine() + ") " + "missing " + error + "\n");
                } else {
                    Output.append("(Syntax error at line:" + listener.getSyntaxErrors().get(i).getLine() + ") " + listener.getSyntaxErrors().get(i).getMessage() + "\n");
                }
            }


            JFrame frame = new JFrame("Antlr Tree");
            frame.setLocation(600,0);
            JPanel panel = new JPanel();
            TreeViewer viewr = new TreeViewer(Arrays.asList(
                    parser.getRuleNames()),tree);
            //SIZE CHANGE HERE
            viewr.setScale(1);//scale a little
            //
            panel.add(viewr);
            JScrollPane scrollPane = new JScrollPane(panel);
            frame.add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600,400);
            frame.setVisible(true);

    }

    private void ParseBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void RunBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JSplitPane ButtonTextSplit;
    private javax.swing.JTextArea Input;
    private javax.swing.JTextArea Output;
    private javax.swing.JButton ParseBtn;
    private javax.swing.JSplitPane ParseRunPane;
    private javax.swing.JButton RunBtn;
    private javax.swing.JButton ScanBtn;
    private javax.swing.JSplitPane ScanPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration
}