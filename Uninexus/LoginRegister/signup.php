<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['FName']) && isset($_POST['ID']) && isset($_POST['Email']) && isset($_POST['Occ'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("register", $_POST['FName'],$_POST['ID'], $_POST['Email'],$_POST['Occ'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required!!";
?>
