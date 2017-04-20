package pl.edu.pjatk.tau.domain;

/**
 * Created by Boberkowy on 19.04.2017.
 */
public class Note {
    private int id;
    private String title;
    private String date;
    private String content;

    public Note(){
    }

    public Note(int id, String title, String date, String content){
        super();
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
