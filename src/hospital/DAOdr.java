package hospital;
import java.sql.*;
class Dr 
{
    int id;
    String name, specialist, shift;
}
public class DAOdr {
    Connection con = null;
    void connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbhopital";
            con = DriverManager.getConnection(url, "root", "");
        } catch (Exception ex) {
            System.out.println("Student_Connection Error try again");
            System.out.println(ex);
        }
    }
    int insert(Dr h) {
        int ret = 0;
        try{
            connection();
            String qry = "insert into tblDr values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, h.id);
            pst.setString(2, h.name);
            pst.setString(3, h.specialist);
            pst.setString(4, h.shift);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO insert");
            System.out.println(ex);
        }
        return ret;
    }
    int update(Dr h) {
        int ret = 0;
        try{
            connection();
            String qry = "update tblDr set name=?, speciality=?, shift=? where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setString(1, h.name);
            pst.setString(2, h.specialist);
            pst.setString(3, h.shift);
            pst.setInt(4, h.id);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO update");
            System.out.println(ex);
        }
        return ret;
    }
    int delete(Dr h) {
        int ret = 0;
        try {
            connection();
            String qry = "delete from tblDr where id=?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, h.id);
            ret = pst.executeUpdate();
           
        } 
        catch (Exception ex) {
            System.out.println("Error in deletion try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectById(Dr h) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblDr where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, h.id);
            ret = pst.executeQuery();  
        } 
        catch (Exception ex) {
            System.out.println("Error in selected by ID, try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectAll(Dr h) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblDr";
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