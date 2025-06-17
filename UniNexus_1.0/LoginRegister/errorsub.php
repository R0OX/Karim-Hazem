<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['hall']) && isset($_POST['error']) && isset($_POST['build']) && isset($_POST['desc'])) {
    if ($db->dbConnect()) {
        if ($db->errorsub("errors", $_POST['hall'],$_POST['error'], $_POST['build'],$_POST['desc'])) {
            echo "Submit Successfull";
        } else echo "Submit Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required!!";
?>
