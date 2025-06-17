<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email'])) {
    if ($db->dbConnect()) {
        $photo = $db->datapp("team", $_POST['email']);
            echo $photo;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>