<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Email']) && isset($_POST['ID'])) {
    if ($db->dbConnect()) {
        if ($db->forget("forgetpass", $_POST['Email'],$_POST['ID'],)) {
            echo "Request Success";
        } else echo "Request Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required!!";
?>
