<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])) {
    if ($db->dbConnect()) {
        $fname = $db->reqname("register",$_POST['renum']);
            echo $fname;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>