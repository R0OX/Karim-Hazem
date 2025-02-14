<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])) {
    if ($db->dbConnect()) {
        if ($db->reqdeny("register", $_POST['renum'])) {
            echo "denied";
        } else echo "ID Not Found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
