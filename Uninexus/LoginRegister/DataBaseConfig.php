<?php

class DataBaseConfig
{
    public $servername;
    public $username;
    public $hostname;
    public $password;
    public $databasename;

    public function __construct()
    {

        $this->servername = 'localhost';
        $this->username = 'root';
        $this->hostname = 'localhost';
        $this->password = '';
        $this->databasename = 'uninexus';

    }
}

?>
