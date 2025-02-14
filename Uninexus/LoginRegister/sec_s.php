<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['table'],$_POST['ID'])) {
    if ($db->dbConnect()) {
        if ($db->sec_start($_POST['table'],$_POST['ID'])) {
            echo "Sign up Success";
        } else echo "ID Not Found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>