<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])) {
    if ($db->dbConnect()) {
        $fname = $db->reqrows("register");
            echo $fname;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>