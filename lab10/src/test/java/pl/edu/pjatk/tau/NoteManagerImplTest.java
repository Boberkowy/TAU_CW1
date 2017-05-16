package pl.edu.pjatk.tau;

import org.junit.After;
import org.junit.Test;
import pl.edu.pjatk.tau.Domain.Note;
import pl.edu.pjatk.tau.Service.NoteManagerImpl;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.*;


public class NoteManagerImplTest {

    NoteManagerImpl noteManager = new NoteManagerImpl();

    public NoteManagerImplTest() throws SQLException {}

    @Test
    public void connectionCheck() {
        assertNotNull(noteManager.getConnection());
    }

    @Test
    public void newNoteShouldBeAdded() throws SQLException {
        Note note = new Note(1236,"Title made by JUnit","2017-12-12","Content made by JUnit");
        assertEquals(1,noteManager.addNote(note));
        assertNotNull(noteManager.selectNote(note.getId()));
    }

    @Test
    public void deleteNoteShouldBeSuccessful() throws SQLException {
        Note note = new Note(1008,"Delete title Test","2017-11-13","Delete content Test");
        noteManager.addNote(note);
        int beforeDelete = noteManager.countRow();
        assertEquals(1,noteManager.deleteNote(note.getId()));
        int afterDelete = noteManager.countRow();
        assertNotSame(beforeDelete,afterDelete);
        assertNotSame(note.getId(),noteManager.selectNote(note.getId()).getId());
    }

    @Test
    public void editNoteShouldBeSuccessful() throws SQLException {
        Note noteToEdit = new Note(1337,"To edit title by Junit","2017-12-12","To edit content byJUnit");
        Note noteEdited = new Note(1337,"Edited title by Junit","2017-12-13","Edited content przez JUnit");
        noteManager.addNote(noteToEdit);
        assertEquals(1,noteManager.editNote(noteEdited));
        assertNotSame(noteToEdit.getContent(),noteManager.selectNote(noteEdited.getId()).getContent());
    }

    @Test
    public void selectNote() throws SQLException {
        Note note = new Note(1338,"Select Title","2017-12-12","Select content");
        noteManager.addNote(note);
        assertEquals(note.getId(),noteManager.selectNote(note.getId()).getId());
    }

    @After
    public void clearup() throws SQLException{
        noteManager.clear();
    }

}