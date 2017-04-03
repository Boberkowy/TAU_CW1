<!DOCTYPE html>
<head>
    <title>Blog Technologie Internetu</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link href='http://fonts.googleapis.com/css?family=Amatic+SC&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'/>
    <script type="text/javascript" src ="js/jquery-3.1.1.js"></script>
    <script type="application/javascript" src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
    <script type="application/javascript" src="js/popup.js"></script>
    <script type="application/javascript" src="js/confirmDelete.js"></script>
    <script type="application/javascript" src="js/ajax.js"></script>
    <script type="application/javascript" src="js/countdivs.js"></script>

</head>
<?php
include("notes.php");
if(!isset($_SESSION['login'])) {
    header("location: index.php");
}
if($_SESSION['role'] == 'admin')
    header("location: Admin/admin.php");

?>
<body>
<div id = "page">
    <div id="name"><a id="namelink" href="index.php">Blogasek</a>
        <div id="nav_horizontal">
            <span class="btn btn-default"><a href="addNote.php">DODAJ WPIS</a></span>
            <span class="btn btn-default"><a href="logout.php">WYLOGUJ</a></span>
        </div>
    </div>

    <div id = "content">
        <div id="notes">
        <?php
        echo "<span id='session'>";
        echo $_SESSION['role'];
        "</span>";
        echo "oczekuje sie tutaj notatek";
        selectNotes();

?>


        </div>
        <input type="hidden" id="howManyResults" value="5">
        <input type="button" id="load" value="Załaduj więcej">
        </div>

    <footer>
        <p> Strona stworzona przez Mateusza Owerczuk na projekt zaliczeniowy z przedmiotu TIN.</p>
    </footer>

</div>

</body>
</html>