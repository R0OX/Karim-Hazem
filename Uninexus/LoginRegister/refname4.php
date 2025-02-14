<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email'])) {
    if ($db->dbConnect()) {
        $name = $db->datafname("it_employees", $_POST['email']);
            echo $name;
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>