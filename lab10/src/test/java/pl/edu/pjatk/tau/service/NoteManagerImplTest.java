package pl.edu.pjatk.tau.service;

import org.dbunit.*;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.edu.pjatk.tau.Domain.Note;
import pl.edu.pjatk.tau.Service.NoteManagerImpl;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@RunWith(JUnit4.class)
public class NoteManagerImplTest extends DBTestCase {

    NoteManagerImpl noteManager = new NoteManagerImpl();

    public NoteManagerImplTest() throws SQLException {
        super("PersonManagerImpl test");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return this.getDataSet("dataset-pm-add.xml");
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.TRUNCATE_TABLE;
    }


    protected IDataSet getDataSet(String datasetName) throws Exception {
        URL url = getClass().getClassLoader().getResource(datasetName);
        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
        return ret;
    }


        @Before
        public void setUp() throws Exception {
            System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
            System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:hsql://localhost/workdb" );
            System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
            System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );

            JdbcDatabaseTester databaseTester = new PropertiesBasedJdbcDatabaseTester();

            FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(
                    ServiceTests.class.getClassLoader().
                            getResource("dataset-pm.xml").openStream()
            );

            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
                Connection jdbcConnection = DriverManager.getConnection(
                        "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
                IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        }


    @Test
    public void connectionCheck() {
        assertNotNull(noteManager.getConnection());
    }

    @Test
    public void newNoteShouldBeAdded() throws Exception {
        Note note1 = new Note(1236,"Title DbUnit 4","2016-03-03","Content DbUnit 4");
        Note note2 = new Note(1236,"Title DbUnit 5","2017-03-03","Content DbUnit 5");
        Note note3 = new Note(1236,"Title DbUnit 6","2017-03-03","Content DbUnit 6");
        Note note4 = new Note(1236,"Title DbUnit 7","2017-03-03","Content DbUnit 7");
        Note note5 = new Note(1236,"Title DbUnit 8","2017-03-03","Content DbUnit 8");
        Note note6 = new Note(1236,"Title DbUnit 9","2017-03-03","Content DbUnit 9");
        Note note7 = new Note(1237,"Title DbUnit","2013-03-03","Content made by JUnit");

        assertEquals(1,noteManager.addNote(note1));
        assertEquals(1,noteManager.addNote(note2));
        assertEquals(1,noteManager.addNote(note3));
        assertEquals(1,noteManager.addNote(note4));
        assertEquals(1,noteManager.addNote(note5));
        assertEquals(1,noteManager.addNote(note6));
        assertEquals(1,noteManager.addNote(note7));

        IDataSet dbDataSet = getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("NOTE");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset-pm-check.xml"));
        ITable expectedTable = expectedDataSet.getTable("NOTE");
        Assertion.assertEquals(expectedTable, filteredTable);

    }

    @Test
    public void deleteNoteShouldBeSuccessful() throws Exception {

        Note note1 = new Note(1236,"Title DbUnit 4","2017-03-03","Content DbUnit 4");
        Note note2 = new Note(1237,"Title DbUnit 5","2017-03-03","Content DbUnit 5");
        Note note3 = new Note(1238,"Title DbUnit 6","2017-03-03","Content DbUnit 6");
        Note note4 = new Note(1239,"Title DbUnit 7","2017-03-03","Content DbUnit 7");
        Note note5 = new Note(1240,"Title DbUnit 8","2017-03-03","Content DbUnit 8");
        Note note6 = new Note(1241,"Title DbUnit 9","2017-03-03","Content DbUnit 9");
        Note note7 = new Note(1242,"Title DbUnit","2013-03-03","Content made by JUnit");

        noteManager.addNote(note1);
        noteManager.addNote(note2);
        noteManager.addNote(note3);
        noteManager.addNote(note4);
        noteManager.addNote(note5);
        noteManager.addNote(note6);
        noteManager.addNote(note7);



        noteManager.deleteNote(note4.getId());
        noteManager.deleteNote(note5.getId());
        noteManager.deleteNote(note6.getId());
        noteManager.deleteNote(note7.getId());

        IDataSet dbDataSet = getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("NOTE");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset-pm-delete-check.xml"));
        ITable expectedTable = expectedDataSet.getTable("NOTE");
        Assertion.assertEquals(expectedTable, filteredTable);


        }

    @Test
    public void editNoteShouldBeSuccessful() throws Exception {

        Note noteToEdit1 = new Note(1238,"Title DbUnit 6","2017-03-03","Content DbUnit 6");
        Note noteToEdit2 = new Note(1239,"Title DbUnit 7","2017-03-03","Content DbUnit 7");
        Note noteToEdit3 = new Note(1241,"Title DbUnit 9","2017-03-03","Content DbUnit 9");

        Note noteEdited1 = new Note(1238,"Edited title by DbUnit","2017-12-13","Edited content przez DbUnit");
        Note noteEdited2 = new Note(1239,"Edited title by DbUnit","2017-12-13","Edited content przez DbUnit");
        Note noteEdited3 = new Note(1241,"Edited title by DbUnit","2017-12-13","Edited content przez DbUnit");

        noteManager.addNote(noteToEdit1);
        noteManager.addNote(noteToEdit2);
        noteManager.addNote(noteToEdit3);

        noteManager.editNote(noteEdited1);
        noteManager.editNote(noteEdited2);
        noteManager.editNote(noteEdited3);

        IDataSet dbDataSet = getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("NOTE");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dataset-pm-edit-check.xml"));
        ITable expectedTable = expectedDataSet.getTable("NOTE");
        Assertion.assertEquals(expectedTable, filteredTable);
    }


    @After
    public void clearup() throws SQLException{
        noteManager.clear();
    }

}