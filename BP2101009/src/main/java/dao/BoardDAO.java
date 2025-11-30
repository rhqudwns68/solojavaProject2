package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;

public class BoardDAO {

	private Connection getConnection() throws Exception {
	    String url = "jdbc:mysql://localhost:3306/mini?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
	    String user = "root";
	    String pass = "rhqudwns0011";

	    Class.forName("com.mysql.cj.jdbc.Driver");
	    return DriverManager.getConnection(url, user, pass);
	}


    public int count(String type, String keyword) {

        boolean search = (type != null && keyword != null && !keyword.trim().equals(""));
        String sql = "SELECT COUNT(*) FROM board";

        if (search) {
            if (type.equals("title")) sql += " WHERE title LIKE ?";
            else if (type.equals("content")) sql += " WHERE content LIKE ?";
            else if (type.equals("writer")) sql += " WHERE writer LIKE ?";
            else search = false;
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (search) {
                pstmt.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<BoardDTO> list(int offset, int size, String type, String keyword) {

        List<BoardDTO> list = new ArrayList<>();

        boolean search = (type != null && keyword != null && !keyword.trim().equals(""));
        String sql = "SELECT * FROM board";

        if (search) {
            if (type.equals("title")) sql += " WHERE title LIKE ?";
            else if (type.equals("content")) sql += " WHERE content LIKE ?";
            else if (type.equals("writer")) sql += " WHERE writer LIKE ?";
            else search = false;
        }

        sql += " ORDER BY bno DESC LIMIT ?, ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int i = 1;

            if (search) {
                pstmt.setString(i++, "%" + keyword + "%");
            }

            pstmt.setInt(i++, offset);
            pstmt.setInt(i, size);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDTO dto = new BoardDTO(
                        rs.getInt("bno"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getString("regdate")
                );
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void write(BoardDTO dto) {

        String sql = "INSERT INTO board (title, content, writer) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContent());
            pstmt.setString(3, dto.getWriter());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BoardDTO view(int bno) {

        String sql = "SELECT * FROM board WHERE bno=?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bno);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                BoardDTO dto = new BoardDTO(
                        rs.getInt("bno"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getString("regdate")
                );
                return dto;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
