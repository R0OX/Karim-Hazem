<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])&&isset($_POST['sub'])) {
    if ($db->dbConnect()) {
        if ($db->qaact("qanda",$_POST['renum'],$_POST['sub'])) {
            echo "";
        } else echo "ID Not Found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>