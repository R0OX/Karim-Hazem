<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['ID'])) {
    if ($db->dbConnect()) {
        if ($db->entry("entry", $_POST['ID'])) {
            echo "Access Granted";
        } else echo "Access denied";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
