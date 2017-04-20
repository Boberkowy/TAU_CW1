package pl.edu.pjatk.tau;

import org.junit.After;
import org.junit.Test;
import pl.edu.pjatk.tau.domain.Note;
import pl.edu.pjatk.tau.service.NoteManager;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Boberkowy on 20.04.2017.
 */
public class NoteManagerTest {

    NoteManager noteManager = new NoteManager();

    public NoteManagerTest() throws SQLException{

    }

    @After
    public void cleanup() throws SQLException{
        noteManager.clearNote();
    }

    @Test
    public void connectionCheck (){ assertNotNull(noteManager.getConnection());}

    @Test
    public void newNoteShouldBeAdded() throws SQLException{
        Note note = new Note(1004,"Title Test","1992-12-13","Content Test");
        noteManager.clearNote();
        assertEquals(1,noteManager.addNote(note));
    }

    @Test
    public void deleteNoteShouldBeSuccessful() throws SQLException{
        Note note = new Note(1005,"Delete title Test","2017-11-13","Delete content Test");
        noteManager.addNote(note);
        assertEquals(1,noteManager.deleteNote(note));
    }

    @Test
    public void editNoteShouldBeSuccessful() throws SQLException{
        Note note = new Note(1234,"Edit title Test","2017-11-11","Edit content Test");
        noteManager.addNote(note);
        assertEquals(1,noteManager.editNote(note));
    }



}
