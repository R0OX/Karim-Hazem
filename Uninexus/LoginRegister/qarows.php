<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['renum'])&&isset($_POST['sub'])) {
    if ($db->dbConnect()) {
        $num = $db->qarows("qanda",$_POST['sub']);
            echo $num;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>