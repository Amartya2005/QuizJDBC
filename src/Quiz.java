package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Quiz {

    public static void startQuiz(String rollNo) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM question";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Scanner sc = new Scanner(System.in);

            while (rs.next()) {

                int qId = rs.getInt("q_id");
                String question = rs.getString("question_text");

                List<String> options = new ArrayList<>();
                options.add(rs.getString("option_a"));
                options.add(rs.getString("option_b"));
                options.add(rs.getString("option_c"));
                options.add(rs.getString("option_d"));

                String correct = rs.getString("correct_option");

                Collections.shuffle(options);

                System.out.println("\n" + question);

                char label = 'A';
                Map<Character, String> optionMap = new HashMap<>();

                for (String opt : options) {
                    optionMap.put(label, opt);
                    System.out.println(label + ". " + opt);
                    label++;
                }

                System.out.print("Your answer (A/B/C/D): ");
                char answer = sc.next().toUpperCase().charAt(0);

                boolean isCorrect =
                        optionMap.get(answer).equals(
                                rs.getString("option_" + correct.toLowerCase())
                        );

                saveAnswer(con, rollNo, qId, answer, isCorrect);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void saveAnswer(
            Connection con,
            String rollNo,
            int qId,
            char selected,
            boolean isCorrect) {

        try {
            String insert =
                    "INSERT INTO result (roll_no, q_id, selected_option, is_correct) " +
                            "VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, rollNo);
            ps.setInt(2, qId);
            ps.setString(3, String.valueOf(selected));
            ps.setBoolean(4, isCorrect);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
