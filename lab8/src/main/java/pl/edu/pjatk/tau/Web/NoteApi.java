package pl.edu.pjatk.tau.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pjatk.tau.Domain.Note;
import pl.edu.pjatk.tau.Service.NoteManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Boberkowy on 04.05.2017.
 */
@RestController
public class NoteApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    NoteManager noteManager;

    @RequestMapping("/")
    public String index() {
        return "Sprawdzam czy działa";
    }

    @RequestMapping(
            value = "/note",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Note> getAllNotes(@RequestParam(value = "name", defaultValue = "note") String name)throws SQLException {
        List<Note> notesList = new ArrayList();
        notesList = noteManager.getAllNotes();
        return notesList;
    }


    @RequestMapping(
            value = "/note/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Note> getOneNote(@PathVariable("id") int id) throws SQLException {
        Note note = noteManager.selectNote(id);
        if (note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Note>(note, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/note/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Note> deleteNote(@PathVariable("id") int id) throws SQLException{
        Note note = noteManager.selectNote(id);
        if(note == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        noteManager.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @RequestMapping(
            value = "note/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Note> editNote(@PathVariable("id") int id, @RequestBody Note note) throws SQLException{
        Note toEditNote = noteManager.selectNote(id);
        if(toEditNote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toEditNote.setTitle(note.getTitle());
        toEditNote.setDate(note.getDate());
        toEditNote.setContent(note.getContent());
        noteManager.editNote(toEditNote);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(
            value = "note/",
            method = RequestMethod.POST

    )
    @ResponseBody
    public ResponseEntity<Void> addNote(@RequestBody Note note,UriComponentsBuilder ucBuilder) throws SQLException{
        noteManager.addNote(note);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(note.getId()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
