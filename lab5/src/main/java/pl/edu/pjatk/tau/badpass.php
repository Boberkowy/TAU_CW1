<html lang="pl">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Blog Technologie Internetu</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link href='http://fonts.googleapis.com/css?family=Amatic+SC&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src ="js/jquery-3.1.1.js"></script>
    <script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
    <script type="application/javascript" src="js/popup.js"></script>

</head>
<body>
<noscript>
    <div id = "jsOff">
        Nie masz włączonego JavaScriptu. Bez niego nie jesteś w stanie korzystać z serwisu.
    </div>
</noscript>
<?php
session_start();

if(isset($_SESSION['login'])){
    header("location: profile.php");
}
?>
<div id="loginForm">
    <button class="cancelButton">X</button>
    <form action="login.php" method="POST" id="formularzyk" >
        <h1>LOGOWANIE</h1>
        <input  type="text" name="login" placeholder="Login" required ></br>
        <input  type="password" name="password" placeholder="Hasło" required><br/>
        <input  type="submit" name="loggin"  value="Zaloguj się" required><br/>
    </form>
</div>

<div id="registerForm">
    <button class="cancelButton">X</button>
    <form action ="register.php" method="POST" id="formularzyk" >
        <h1>REJESTRACJA</h1>
        <input type="text" name="login" placeholder="Login" required ></br>
        <input type="password" name="password" placeholder="Hasło" required><br/>
        <input type ="email" name="email" placeholder="Email"  required><br/>
        <input type="submit" value="Rejestruj" ><br/>
    </form>
</div>

<div id = "page">
    <div id="name"><a id="namelink" href="index.php">Blogasek</a>
        <div id="nav_horizontal">
            <span class="btn btn-default" id="loginButton">Zaloguj</span>
            <span class="btn btn-default" id="registerButton">Zarejestruj</span>
        </div>
    </div>

    <div id = "content">
        "<div id='error'><h1> Wprowadzono nieprawidlowe login lub haslo </h1></div>"
    </div>

    <footer>
        <p> Strona stworzona przez Mateusza Owerczuk na projekt zaliczeniowy z przedmiotu TIN.</p>
    </footer>
</div>


</body>
</html>

