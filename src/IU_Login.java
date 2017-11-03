/**
 *
 * @author Havoc
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

public class IU_Login extends javax.swing.JFrame {
private Statement stmt; 
private ResultSet rs;
private List<String> salida = new ArrayList<>(); 

public IU_Login() {
    
initComponents(); 
/***************    Establecer conexión con base de datos local      *****************/
//String connectString="jdbc:postgresql://localhost:5432/Belfiori";
//try{
//    Class.forName("org.postgresql.Driver");
//    Connection conexion=DriverManager.getConnection(connectString, "postgres", "perro");
//    stmt = conexion.createStatement();
//    rs = stmt.executeQuery("select * from usuarios");       //Solicita los usuario registrados en la BD
//    while (rs.next())
//        { 
//        salida.add(rs.getString("nombre")); 
//        salida.add(rs.getString("pass"));   
//        } 
//    }
//catch(Exception e)
//    {
//    JOptionPane.showMessageDialog(null, e);
//    } 
///***************    Establecer conexión con base de datos en azure     *****************/
String url = String.format("jdbc:postgresql://%s/%s", "pgserverbelfiori.postgres.database.azure.com", "postgres");
Properties properties = new Properties();
properties.setProperty("user", "BelfioriAdmin@pgserverbelfiori");
properties.setProperty("password", "1Gato*2Perros");
properties.setProperty("ssl", "true");
try{
    Class.forName("org.postgresql.Driver");
    Connection conexion = DriverManager.getConnection(url, properties);
    stmt = conexion.createStatement();
    rs = stmt.executeQuery("select * from usuarios");   //Solicita los usuario registrados en la BD
    while (rs.next())
        { 
        salida.add(rs.getString("nombre")); 
        salida.add(rs.getString("pass"));   
        } 
    }
catch(Exception e)
    {
    JOptionPane.showMessageDialog(null, e);
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

        Nombre = new javax.swing.JTextField();
        Ingresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Ingresar.setText("Ingresar");
        Ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IngresarMouseClicked(evt);
            }
        });

        jLabel1.setText("Nombre de usuario");

        jLabel2.setText("Contraseña");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(Ingresar)
                    .addComponent(Nombre)
                    .addComponent(Contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Ingresar)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IngresarMouseClicked
    String name=null, pass=null;
    
    name=Nombre.getText();      //Validación de usuario
    pass=Contraseña.getText();
    if(name.equals(salida.get(0))&&pass.equals(salida.get(1)))
        {
        new IU_inventario(name,pass).setVisible(true); 
        dispose();
        }
    else{
        JOptionPane.showMessageDialog(null, "Nombre o contraseña\nincorrecta");
        Nombre.setText("");
        Contraseña.setText("");
        }
    }//GEN-LAST:event_IngresarMouseClicked

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
            java.util.logging.Logger.getLogger(IU_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IU_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IU_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IU_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IU_Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JButton Ingresar;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}