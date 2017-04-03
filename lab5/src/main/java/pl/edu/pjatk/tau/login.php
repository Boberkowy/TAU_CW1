
<?php

include ("db.php");
session_start();

if($_SERVER["REQUEST_METHOD"] == "POST") {

    $username = mysqli_real_escape_string($db, $_POST['login']);
    $password = mysqli_real_escape_string($db, $_POST['password']);

    $query = "SELECT userID , Role FROM users WHERE Login = '$username' AND password = '$password'";
    $result = mysqli_query($db, $query);
    $count = mysqli_num_rows($result);
    if ($count == 0) {
        $_SESSION['error'] = "Nie poprawny login lub hasło";
        header("location: badpass.php");
    }
    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
    if ($count == 1) {
        $_SESSION['login'] = $row['userID'];
        $_SESSION['role'] = $row['Role'];
        echo " przypisałem role i userid";
        if ($_SESSION['role'] == 'user') {
            header("location: profile.php");
        } else if ($_SESSION[role] == 'admin') {
            header("location: Admin/admin.php");
        } else
            header("location: badpass.php");

    }

}
?>
