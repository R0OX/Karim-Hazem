<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email'])) {
    if ($db->dbConnect()) {
        $ID = $db->dataphone("it_employees", $_POST['email']);
            echo $ID;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>