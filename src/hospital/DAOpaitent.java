package hospital;
import java.sql.*;
class paitent 
{
    int id, age;
    String name, condition;
}
public class DAOpaitent {
    Connection con = null;
    void connection()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dbhopital";
        con = DriverManager.getConnection(url, "root", "");
        }catch(Exception ex){
            System.out.println("error in ");
        }
    }
    int insert(paitent p) {
        int ret = 0;
        try{
            connection();
            String qry = "insert into tblPaitent values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, p.id);
            pst.setString(2, p.name);
            pst.setInt(3, p.age);
            pst.setString(4, p.condition);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO insert");
            System.out.println(ex);
        }
        return ret;
    }
    int update(paitent p) {
        int ret = 0;
        try{
            connection();
            String qry = "update tblPaitent set name=?, age=?, condition=? where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setString(1, p.name);
            pst.setInt(2, p.age);
            pst.setString(3, p.condition);
            pst.setInt(4, p.id);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO update");
            System.out.println(ex);
        }
        return ret;
    }
    int delete(paitent p) {
        int ret = 0;
        try {
            connection();
            String qry = "delete from tblPaitent where id=?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, p.id);
            ret = pst.executeUpdate();
        } 
        catch (Exception ex) {
            System.out.println("Error in deletion try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectById(paitent p) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblPaitent where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, p.id);
            ret = pst.executeQuery();   
        } 
        catch (Exception ex) {
            System.out.println("Error in selected by ID, try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectAll(paitent p) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblPaitent";
            Statement st = con.createStatement();
            ret = st.executeQuery(qry);   
        } 
        catch (Exception ex) {
            System.out.println("Error in selected by ID, try again");
            System.out.println(ex);
        }
        return ret;
    }
}