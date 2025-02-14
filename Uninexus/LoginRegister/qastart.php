<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])) {
    if ($db->dbConnect()) {
        $num = $db->qastart("qanda",$_POST['renum']);
        echo $num;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>