package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

    public static boolean authenticate(String rollNo, String password) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM student WHERE roll_no=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rollNo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}

