<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['hall'])) {
    if ($db->dbConnect()) {
        if ($db->errorup("errors")) {
            echo "denied";
        } else echo "ID Not Found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
