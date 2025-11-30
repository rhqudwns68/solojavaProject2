package dto;

public class BoardDTO {

    private int no;
    private String title;
    private String content;
    private String writer;
    private String regdate;

    public BoardDTO() {
    }

    public BoardDTO(int no, String title, String content, String writer, String regdate) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regdate = regdate;
    }

    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
}
