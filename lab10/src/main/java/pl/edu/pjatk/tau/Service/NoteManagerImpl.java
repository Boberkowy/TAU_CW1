package pl.edu.pjatk.tau.Service;


import org.springframework.stereotype.Component;
import pl.edu.pjatk.tau.Domain.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boberkowy on 04.05.2017.
 */
@Component
public class NoteManagerImpl implements NoteManager {

    private Connection connection;

    private String url = "jdbc:hsqldb:hsql://localhost/workdb";
    private String username = "SA";
    private String password = "";

    private String createTableNotes = "CREATE TABLE Note(id bigint GENERATED BY DEFAULT AS IDENTITY, title VARCHAR(350), date date, content LONGVARCHAR)";
    /*private String createTableNotes = "CREATE TABLE " +
            "Note( `id` INT NOT NULL AUTO_INCREMENT , `title` VARCHAR(350) NOT NULL , " +
            "`date` DATETIME NOT NULL , `content` LONGTEXT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
*/
    private PreparedStatement addNoteStmt;
    private PreparedStatement deleteNoteStmt;
    private PreparedStatement editNoteStmt;
    private PreparedStatement getAllNotesStmt;
    private PreparedStatement selectNoteStmt;
    private Statement statement;

    public NoteManagerImpl() throws SQLException{
        connection = DriverManager.getConnection(url,username,password);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, null,
                null);

        boolean tableExist = false;

        while (rs.next()){
            if("Notes".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
                tableExist = true;
                break;
            }
        }

        if(!tableExist)
            statement.executeUpdate(createTableNotes);

        addNoteStmt = connection.prepareStatement("INSERT INTO  \"PUBLIC\".\"NOTE\" (id,title,date,content) VALUES (?,?,?,?)");
        editNoteStmt = connection.prepareStatement("UPDATE \"PUBLIC\".\"NOTE\" SET title = ?, date = ?, content =? WHERE  id = ?");
        deleteNoteStmt = connection.prepareStatement("DELETE FROM \"PUBLIC\".\"NOTE\" WHERE id = ?");
        getAllNotesStmt = connection.prepareStatement("SELECT * FROM \"PUBLIC\".\"NOTE\"");
        selectNoteStmt = connection.prepareStatement("SELECT * FROM \"PUBLIC\".\"NOTE\" WHERE \"ID\" = ?");
    }

        @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public int addNote(Note note) throws SQLException {
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

    @Override
    public int deleteNote(int id) throws SQLException {
        int count = 0;
        try{
            deleteNoteStmt.setInt(1,id);
            count =  deleteNoteStmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int editNote(Note note) throws SQLException {
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

    @Override
    public Note selectNote(int id) throws SQLException{
        int count = 0;
        Note n = new Note();
        try{
            selectNoteStmt.setInt(1,id);
            ResultSet rs = selectNoteStmt.executeQuery();
            while(rs.next()){
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setDate(rs.getString("date"));
                n.setContent(rs.getString("content"));
                count = 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Note> getAllNotes() throws SQLException{
        List<Note> notes = new ArrayList<Note>();

        try{
            ResultSet rs = getAllNotesStmt.executeQuery();
            while (rs.next()){
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setDate(rs.getString("date"));
                note.setContent(rs.getString("content"));

                notes.add(note);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public int countRow() throws SQLException {
        int count = 0;
        try{
            PreparedStatement stmt = connection.prepareStatement("select * from PUBLIC.NOTE");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                count = rs.getRow();
                //count = rs.getInt("count(*)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Number of note : " + count);
        return count;
    }

    public void clear() throws SQLException{
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM PUBLIC.NOTES");
           if(stmt.executeUpdate() == 1){
               System.out.println("Deleted successful");
           }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}