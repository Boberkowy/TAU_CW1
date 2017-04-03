<?php
session_start();

function selectNotes()
{
    include 'db.php';

    $id = mysqli_real_escape_string($db, $_SESSION['login']);

    $noteQuery = "SELECT notes.*, photos.photo FROM `notes` LEFT JOIN `photos` ON notes.NoteID = photos.NoteID WHERE notes.UserID = '$id' ORDER BY notes.NoteID DESC LIMIT 0,5 ";
    $noteResult = mysqli_query($db, $noteQuery);
    $count = mysqli_num_rows($noteResult);
    if ($count > 0) {
        while ($row = mysqli_fetch_assoc($noteResult)) {
            echo "<div id='notesList'>";
            echo "<h1>" . $row['title'] . "</h1>";
            echo "<p>" . $row['date'] . "</p>";
            echo "<p>" . $row['content'] . "</p>";
            if ($row['photo'] != null) {
                echo '<img src="data:image/jpeg;base64,' . base64_encode($row['photo']) . '" />';
            }
            if($_SESSION['role'] == "user"){ ?>
                <a href="deleteNote.php?id=<?php echo $row['NoteID'];?>" onclick="UserNoteDeleteConfirm(' <?php  echo $row['NoteID']; ?> ',' <?php echo $row['title']?>'); " >Usuń</a>
                <a href="editNote.php?id=<?php echo $row['NoteID'];?>">Edytuj</a>

                    </div>

                <?php }
                else {
                ?>  <a href="../deleteNote.php?id=><?php echo $row['NoteID']; ?>" onclick="NoteDeleteConfirm('<?php echo $row['NoteID']; ?> ',' <?php echo $row['title'] ?>')">Usuń</a>
                <a href="../editNote.php?id=<?php echo $row['NoteID']; ?>">Edytuj</a>
                </div>
                <?php
            }}?>

    <?php }}

