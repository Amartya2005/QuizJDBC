package Quiz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class ResultService {

    public static int getScore(String rollNo) {

        int score = 0;

        try {
            Connection con = DBConnection.getConnection();

            CallableStatement cs =
                    con.prepareCall("{call calculate_score(?, ?)}");

            cs.setString(1, rollNo);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            score = cs.getInt(2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return score;
    }
}
