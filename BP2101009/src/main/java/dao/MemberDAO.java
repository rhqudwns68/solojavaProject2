package dao;

import java.sql.*;
import dto.MemberDTO;

public class MemberDAO {

	private Connection getConnection() throws Exception {
	    String url = "jdbc:mysql://localhost:3306/mini?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
	    String user = "root";
	    String pass = "rhqudwns0011";

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(url, user, pass);
	}

    public boolean exist(String id) {

        String sql = "SELECT id FROM member WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean join(MemberDTO dto) {

        if (exist(dto.getId())) {
            return false;
        }

        String sql = "INSERT INTO member (id, pw, name, email, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPw());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getEmail());
            pstmt.setString(5, dto.getPhone());

            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public MemberDTO login(String id, String pw) {

        String sql = "SELECT * FROM member WHERE id=? AND pw=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                MemberDTO dto = new MemberDTO(
                        rs.getString("id"),
                        rs.getString("pw"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
