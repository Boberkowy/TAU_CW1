package pl.edu.pjatk.tau.service;

import pl.edu.pjatk.tau.domain.Note;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boberkowy on 19.04.2017.
 */
public class NoteManager {

    private Connection connection;

    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private String createTableNotes = "CREATE TABLE " +
            "Note( `id` INT NOT NULL AUTO_INCREMENT , `title` VARCHAR(350) NOT NULL , " +
            "`date` DATETIME NOT NULL , `content` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";

    private PreparedStatement addNoteStmt;
    private PreparedStatement deleteNoteStmt;
    private PreparedStatement editNoteStmt;
    private PreparedStatement getAllNotesStmt;
    private PreparedStatement selectNoteStmt;
    private Statement statement;

    public NoteManager() throws SQLException{
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, "%",
                null);

        boolean tableExist = false;

        while (rs.next()){
            if("notesiczek".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
                tableExist = true;
                break;
            }
        }

        if(!tableExist)
            statement.executeUpdate(createTableNotes);

        addNoteStmt = connection.prepareStatement("INSERT INTO notesiczek (id,title,date,content) VALUES (?,?,?,?)");
        editNoteStmt = connection.prepareStatement("UPDATE `notesiczek` SET `title`= ?,`date`= ?,`content`=? WHERE `id` = ?");
        deleteNoteStmt = connection.prepareStatement("DELETE FROM `notesiczek` WHERE `id` = ?");
        getAllNotesStmt = connection.prepareStatement("SELECT * FROM notesiczek");
        selectNoteStmt = connection.prepareStatement("SELECT * FROM `notesiczek` WHERE `id` = ?");
    }

    public Connection getConnection(){return connection;}

    public int deleteNote(Note note) throws SQLException{
        int count = 0;

        try{
            System.out.println("ID JEST TAKE : " + note.getId());
            deleteNoteStmt.setInt(1,note.getId());
            count =  deleteNoteStmt.executeUpdate();
            System.out.println("USUNĄŁEM POPRAWNIE JAKBY CO " + count);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;


    }

    public void clearNote() throws SQLException{
        connection.prepareStatement("DELETE FROM notesiczek").executeUpdate();
    }

    public int addNote(Note note){
        int count = 0;
        try{
            addNoteStmt.setInt(1,note.getId());
            addNoteStmt.setString(2,note.getTitle());
            addNoteStmt.setString(3,note.getDate());
            addNoteStmt.setString(4,note.getContent());

            count = addNoteStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

   public int editNote(Note note) throws SQLException{
       int count = 0;

       try{
           editNoteStmt.setString(1,note.getTitle());
           editNoteStmt.setString(2,note.getDate());
           editNoteStmt.setString(3,note.getContent());
           editNoteStmt.setInt(4,note.getId());
           count = editNoteStmt.executeUpdate();
       }catch (SQLException e){
           e.printStackTrace();
       }

       return count;
   }


   public int selectNote(Note note) throws SQLException{
        int count = 0;

        try{
            selectNoteStmt.setInt(1,note.getId());
            count = selectNoteStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return count;
   }
    public List<Note> getAllNotes(){
        List<Note> notes = new ArrayList<Note>();

        try{
            ResultSet rs = getAllNotesStmt.executeQuery();
            while (rs.next()){
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("name"));
                note.setDate(rs.getString("date"));
                note.setContent(rs.getString("content"));

                notes.add(note);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes;
    }
}
