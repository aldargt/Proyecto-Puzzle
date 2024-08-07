package proyecto_final_puzzle;

import java.sql.*;
//import com.mysql.jdbc.Connection;
//import java.beans.Statement;
//import com.mysql.jdbc.Statement;
//import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
   public static Connection conectar=null;
   static Statement sentencia;
    public static Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/puzzle","Aldar","aldargts5233");
            sentencia=conectar.createStatement();
            //JOptionPane.showMessageDialog(null, "Conexion Establecida","Mensaje", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Conexion Establecida");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse","Mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
        return conectar;
    }
    public static void Guardar(Jugadores CV){
         String q="INSERT INTO ganadores Values('"+CV.getNicks()+"','"+CV.getMovidas()+"','"+CV.getHoras()+"','"+CV.getMinutos()+"','"+CV.getSegundos()+"');";
         try {
            sentencia.executeUpdate(q);
             System.out.println("Correcto");
        } catch (Exception e) {
             System.out.println("Error");
        }
    }
    public static ResultSet getTabla(String consulta){
        Connection cn=conexion();
        Statement st;
        ResultSet datos=null;
        try {
            st=cn.createStatement();
            datos=st.executeQuery(consulta);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return datos;
    }
    
    
   
}