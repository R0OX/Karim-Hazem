<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['ID'])) {
    if ($db->dbConnect()) {
        $fname = $db->errorhall("errors",$_POST['ID']);
            echo $fname;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>