<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['hall'])) {
    if ($db->dbConnect()) {
        if ($db->errordone("errors", $_POST['hall'])) {
            echo "Done";
        } else echo "Hall not found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>