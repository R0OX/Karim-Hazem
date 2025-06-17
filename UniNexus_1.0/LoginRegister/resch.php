<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['table'],$_POST['sec'],$_POST['time'])) {
    if ($db->dbConnect()) {
        $sch = $db->resch($_POST['table'],$_POST['sec'],$_POST['time']);
            echo $sch;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>