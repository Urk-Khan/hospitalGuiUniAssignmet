package hospital;
import java.sql.*;
class staff 
{
    int id;
    String name, department, shift;
}
public class DAOstaff {
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
    int insert(staff s) {
        int ret = 0;
        try{
            connection();
            String qry = "insert into tblStaff values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, s.id);
            pst.setString(2, s.name);
            pst.setString(3, s.department);
            pst.setString(4, s.shift);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO insert");
            System.out.println(ex);
        }
        return ret;
    }
    int update(staff s) {
        int ret = 0;
        try{
            connection();
            String qry = "update tblStaff set name=?, department=?, shift=? where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setString(1, s.name);
            pst.setString(2, s.department);
            pst.setString(3, s.shift);
            pst.setInt(4, s.id);
            ret=pst.executeUpdate();
        }catch(Exception ex){
            System.out.println("error in DAO update");
            System.out.println(ex);
        }
        return ret;
    }
    int delete(staff s) {
        int ret = 0;
        try {
            connection();
            String qry = "delete from tblStaff where id=?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, s.id);
            ret = pst.executeUpdate();
        } 
        catch (Exception ex) {
            System.out.println("Error in deletion try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectById(staff s) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblStaff where id =?";
            PreparedStatement pst = con.prepareStatement(qry);
            pst.setInt(1, s.id);
            ret = pst.executeQuery();
        } 
        catch (Exception ex) {
            System.out.println("Error in selected by ID, try again");
            System.out.println(ex);
        }
        return ret;
    }
    ResultSet SelectAll(staff s) {
        ResultSet ret = null;
        try{
            connection();
            String qry = "select * from tblStaff";
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
