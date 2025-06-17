<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])&&isset($_POST['sub'])) {
    if ($db->dbConnect()) {
        $title = $db->qatitle("qanda",$_POST['renum'],$_POST['sub']);
            echo $title;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>