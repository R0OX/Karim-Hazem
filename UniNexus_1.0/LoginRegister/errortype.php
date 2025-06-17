<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['hall'])) {
    if ($db->dbConnect()) {
        $fname = $db->errortype("errors",$_POST['hall']);
            echo $fname;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>