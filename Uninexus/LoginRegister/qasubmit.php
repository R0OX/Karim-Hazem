<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Email']) && isset($_POST['phone']) && isset($_POST['name']) && isset($_POST['title'])&& isset($_POST['desc'])&& isset($_POST['subj'])&& isset($_POST['qnum'])) {
    if ($db->dbConnect()) {
        if ($db->qasubmit($_POST['Email'],$_POST['phone'], $_POST['name'],$_POST['title'],$_POST['desc'],$_POST['subj'],$_POST['qnum'])) {
            echo "Q&A Sent";
        } else echo "Q&A Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required!!";
?>
