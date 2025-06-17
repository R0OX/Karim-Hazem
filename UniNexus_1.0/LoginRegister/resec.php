<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email'])) {
    if ($db->dbConnect()) {
        $sec = $db->datasec("team", $_POST['email']);
            echo $sec;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>