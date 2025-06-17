<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])) {
    if ($db->dbConnect()) {
        if ($db->reqacc("register", $_POST['renum'])) {
            echo "accepted";
        } else echo "ID Not Found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
