package pl.edu.pjatk.tau.Service;

import pl.edu.pjatk.tau.Domain.Note;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Boberkowy on 04.05.2017.
 */
public interface NoteManager {

    Connection getConnection();
    int addNote(Note note) throws SQLException;
    int deleteNote(int id) throws SQLException;
    int editNote(Note note) throws SQLException;
    Note selectNote(int id) throws SQLException;
    List<Note> getAllNotes() throws  SQLException;
    int countRow() throws SQLException;
}
