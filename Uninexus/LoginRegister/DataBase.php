<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $email;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }


    function logIn($table, $email, $password)
    {
        $email = $this->prepareData($email);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row['email'];
            $dbpassword = $row['password'];
            $dbapprove = $row['approve'];
            if ($dbemail == $email && $dbpassword == $password) {
                if($dbapprove==1){
                    $login = true;
                }
                else{
                    $login = false;
                }
            } else $login = false;
        } else $login = false;

        return $login;  
    }

    function dataID($table, $email)
    {
        $email = $this->prepareData($email);
        if($table=="team"){
            $row_name="student_id";
        }
        elseif($table=="dr"){
            $row_name="DR_id";
        }
        elseif($table=="engineers"){
            $row_name="eng_id";
        }
        elseif($table=="it_employees"){
            $row_name="employee_id";
        }
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row[$row_name];
            return $dbID;
        }        
    }

    function datafname($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row["first_name"];
            return $dbfname;
        }        
    }

    function datalname($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dblname = $row["last_name"];
            return $dblname;
        }        
    }

    function datafaculty($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfaculty = $row["faculty"];
            return $dbfaculty;
        }        
    }

    function dataYear($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbYear = $row["Year"];
            return $dbYear;
        }        
    }

    function dataphone($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbphone = $row["phone_number"];
            return $dbphone;
        }        
    }

    function datasub($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbsub = $row["sub"];
            return $dbsub;
        }        
    }

    function datasec($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbsec = $row["Section"];
            return $dbsec;
        }        
    }

    function datapp($table, $email)
    {
        $this->sql = "select * from " . $table . " where email = '" . $email . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbsec = base64_encode($row["student_photo"]);
            return $dbsec;
        }        
    }
    
    function check($table, $ID)
    {
        $ID = $this->prepareData($ID);
        if($table=="team"){
            $row_name="student_id";
        }
        elseif($table=="dr"){
            $row_name="DR_id";
        }
        elseif($table=="engineers"){
            $row_name="eng_id";
        }
        elseif($table=="it_employees"){
            $row_name="employee_id";
        }
        $this->sql = "select * from " . $table . " where ".$row_name." = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row[$row_name];
            if ($dbID == $ID ) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $Fname, $ID , $Email, $Occ)
    {
        $Fname = $this->prepareData($Fname);
        $ID = $this->prepareData($ID);
        $Email = $this->prepareData($Email);
        $Occ = $this->prepareData($Occ);
        $this->sql =
            "INSERT INTO " . $table . " (FName, ID , Email , OCC) VALUES ('" . $Fname . "','" . $ID . "','" . $Email ."','".$Occ."');";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function forget($table, $Email,$ID )
    {
        $Email = $this->prepareData($Email);
        $ID = $this->prepareData($ID);
        $this->sql =
            "INSERT INTO " . $table . " (Email , ID) VALUES ('" . $Email ."','" . $ID ."');";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function punchin($table, $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from " . $table . " where ID = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row['first_name'];
            $dblname = $row['last_name'];
            $this->sql =
            "INSERT INTO attendance (first_name, last_name, ID, punch_in, late_arrival, absence)
            SELECT *,
            CASE WHEN CURRENT_TIME() > '08:00:00' AND CURRENT_TIME() <= '10:30:00' THEN TIMEDIFF(CURRENT_TIME(), '09:00:00')
            ELSE '00:00:00'
            END AS late_arrival,
            CASE WHEN CURRENT_TIME() >= '10:30:00' THEN 1
            ELSE 0
            END AS absence
            FROM (SELECT '{$dbfname}' AS first_name,'{$dblname}' AS last_name,'{$ID}' AS ID,CURRENT_TIME() AS punch_in) AS temp
            WHERE NOT EXISTS (SELECT 1 FROM attendance
            WHERE ID = '{$ID}' AND DATE(punch_in) = CURDATE())
            ";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
        }
    }

    function punchout($table, $ID) {
        $ID = $this->prepareData($ID);
        $this->sql = "UPDATE {$table}
        SET punch_out = CURRENT_TIME(), 
        early_leave = if(CURRENT_TIME() < '15:00:00', TIMEDIFF('15:00:00', CURRENT_TIME),'00:00:00'),
        overtime = IF(CURRENT_TIME() > '15:00:00', TIMEDIFF(CURRENT_TIME, '15:00:00'), '00:00:00')
        WHERE ID = {$ID} AND Date = CURRENT_DATE;";
        if (mysqli_query($this->connect, $this->sql)) {
          $this->sql = "SELECT * FROM {$table} WHERE ID = {$ID} AND Date = CURRENT_DATE;";
          $result = mysqli_query($this->connect, $this->sql);
          $row = mysqli_fetch_assoc($result);
          if (mysqli_num_rows($result) != 0) {
            $dbearly = strtotime($row['early_leave']);
            $dblate = strtotime($row['late_arrival']);
            $dbover = strtotime($row['overtime']);
            $dbabs = $row['absence'];
            if($dbover<($dblate+$dbearly)&&$dbabs==0){
                $latency = ($dbearly+$dblate)-$dbover;}
            else{
                $latency =strtotime('00:00:00');
            }
            $formatted_latency = date("H:i:s", $latency);
            $this->sql = "UPDATE {$table} SET
            latency = '{$formatted_latency}'
            WHERE ID = {$ID} AND Date = CURRENT_DATE;";
            if (mysqli_query($this->connect, $this->sql)) {
                $this->sql = "SELECT * FROM {$table} WHERE ID = {$ID} AND Date = CURRENT_DATE-1";
                $result = mysqli_query($this->connect, $this->sql);
                $row = mysqli_fetch_assoc($result);
                if (mysqli_num_rows($result) != 0) {
                    $dbmon = strtotime($row['monthly_latency']);
                    $mon = $latency+$dbmon;
                    $formatted_mon = date("G:i:s", $mon);
                    $this->sql = "UPDATE {$table} SET
                    monthly_latency = '{$formatted_mon}'
                    WHERE ID = {$ID} AND Date = CURRENT_DATE;";
                    if (mysqli_query($this->connect, $this->sql)) {
                        return true;
                    } else return false;
                }
                else{
                    $mon = $latency;
                    $formatted_mon = date("G:i:s", $mon);
                    $this->sql = "UPDATE {$table} SET
                    monthly_latency = '{$formatted_mon}'
                    WHERE ID = {$ID} AND Date = CURRENT_DATE;";
                    if (mysqli_query($this->connect, $this->sql)) {
                        return true;
                    } else return false;
                }
            } 
          } else {
            return false;
          }
        }
      }
      
    function entry($table , $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from team where student_id = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row['first_name'];
            $dblname = $row['last_name'];
            $dbfaculty = $row['faculty'];
            $dbentry = $row['entry'];
            if($dbentry==1){
            $this->sql =
            "INSERT INTO {$table} (first_name, last_name, ID, faculty) 
        SELECT * FROM (SELECT '{$dbfname}' AS first_name, '{$dblname}' AS last_name, '{$ID}' AS ID, '{$dbfaculty}' AS faculty) AS temp 
        WHERE NOT EXISTS (SELECT * FROM {$table} WHERE ID = '{$ID}' AND Date = CURRENT_DATE)";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
        }
        else return false;
    }
    }
    function reqname($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row["FName"];
            return $dbfname;
        }        
    }
    function reqID($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row["ID"];
            return $dbID;
        }        
    }
    function reqemail($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row["Email"];
            return $dbemail;
        }        
    }
    function reqocc($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbocc = $row["OCC"];
            return $dbocc;
        }        
    }
    function reqrows($table)
    {
        $this->sql = "select * from " . $table . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_num_rows($result);
            return $row;
                
    }
    function reqacc($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row["ID"];
            $dbocc = $row["OCC"];
            if($dbocc=="Student"){
                $row_name="student_id";
                $dbtable = "team";
            }
            elseif($dbocc=="Doctor"){
                $row_name="DR_id";
                $dbtable = "dr";
            }
            elseif($dbocc=="TA"){
                $row_name="eng_id";
                $dbtable = "engineers";
            }
            elseif($dbocc=="Staff"){
                $row_name="employee_id";
                $dbtable = "it_employees";
            }
            $this->sql = "UPDATE {$dbtable} SET approve = 1 WHERE $row_name  =  {$dbID} ;";
            if (mysqli_query($this->connect, $this->sql)) {
                $this->sql = "DELETE FROM {$table} WHERE request_number  =  {$num} ;";
                if (mysqli_query($this->connect, $this->sql)) {
                    return true;
                }else return false;
            }else return false;
        }  else return false;      
    }

    function reqdeny($table,$num)
    {
        $this->sql = "select * from " . $table . " where request_number = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row["ID"];
            $dbocc = $row["OCC"];
            if($dbocc=="Student"){
                $row_name="student_id";
                $dbtable = "team";
            }
            elseif($dbocc=="Doctor"){
                $row_name="DR_id";
                $dbtable = "dr";
            }
            elseif($dbocc=="TA"){
                $row_name="eng_id";
                $dbtable = "engineers";
            }
            elseif($dbocc=="Staff"){
                $row_name="employee_id";
                $dbtable = "it_employees";
            }
            $this->sql = "UPDATE {$dbtable} SET approve = 0 WHERE $row_name  =  {$dbID} ;";
            if (mysqli_query($this->connect, $this->sql)) {
                $this->sql = "DELETE FROM {$table} WHERE request_number  =  {$num} ;";
                if (mysqli_query($this->connect, $this->sql)) {
                    return true;
                }else return false;
            }else return false;
        }  else return false;         
    }
    function requp($table, $columnName = 'request_number') {
        $this->sql = "SELECT $columnName FROM $table";
        $result = mysqli_query($this->connect, $this->sql);
      
        if (!$result) {
          return false;
        }
      
        $i = 1;
        while ($row = mysqli_fetch_assoc($result)) {
          $this->sql = "UPDATE $table SET $columnName = $i WHERE $columnName = " . $row[$columnName];
          if (!mysqli_query($this->connect, $this->sql)) {
          }
          $i++;
        }
      
        mysqli_free_result($result); 
      
        return true;
      }

    function hallbuild($table,$hall)
    {
        $this->sql = "select * from " . $table . " where Hall_code = '" . $hall . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0){
            $dbbuild = $row["Building_no"];
            return $dbbuild;
        }        
    }

    function hallav($table,$hall)
    {
        $this->sql = "select * from " . $table . " where Hall_code = '" . $hall . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            if(strtotime('09:50:00')>strtotime(date('H:i:s'))){
                $dbav = $row["9:00_9:50"];
                return $dbav;
            } 
            if(strtotime('10:40:00')>strtotime(date('H:i:s'))){
                $dbav = $row["9:50_10:40"];
                return $dbav;
            }
            if(strtotime('11:40:00')>strtotime(date('H:i:s'))){
                $dbav = $row["10:50_11:40"];
                return $dbav;
            }
            if(strtotime('12:30:00')>strtotime(date('H:i:s'))){
                $dbav = $row["11:40_12:30"];
                return $dbav;
            }
            if(strtotime('13:50:00')>strtotime(date('H:i:s'))){
                $dbav = $row["1:00_1:50"];
                return $dbav;
            }if(strtotime('14:40:00')>strtotime(date('H:i:s'))){
                $dbav = $row["1:50_2:40"];
                return $dbav;
            }  
            if(strtotime('15:40:00')>strtotime(date('H:i:s'))){
                $dbav = $row["2:50_3:40"];
                return $dbav;
            }
            if(strtotime('16:30:00')>strtotime(date('H:i:s'))){
                $dbav = $row["3:40_4:30"];
                return $dbav;
            } 
            if(strtotime('17:20:00')>strtotime(date('H:i:s'))){
                $dbav = $row["4:30_5:20"];
                return $dbav;
            } 
        }       
    }

    function errorsub($table, $hall, $error , $build, $desc)
    {
        $hall = $this->prepareData($hall);
        $error = $this->prepareData($error);
        $build = $this->prepareData($build);
        $desc = $this->prepareData($desc);
        $this->sql =
            "INSERT INTO " . $table . " (hall_name, building , error_type , description) VALUES ('" . $hall . "','" . $build . "','" . $error ."','".$desc."');";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    function errortype($table,$hall) {
        $this->sql = "SELECT * FROM $table Where hall_name = '{$hall}'";
        $result = mysqli_query($this->connect, $this->sql);
        if (!$result) {
          return false;
        }
      
        $error="";
        while ($row = mysqli_fetch_assoc($result)) {
            $error.=$row['error_type']." , ";
        }
        return $error;
    }

    function errordes($table,$hall) {
        $this->sql = "SELECT * FROM $table Where hall_name = '{$hall}'";
        $result = mysqli_query($this->connect, $this->sql);
        if (!$result) {
          return false;
        }
      
        $error="";
        while ($row = mysqli_fetch_assoc($result)) {
            $error.=$row['description']." , ";
        }
        return $error;
    }

    function errorhall($table,$num)
    {
        $this->sql = "select * from " . $table . " where error_id = " . $num . ";";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row["hall_name"];
            return $dbfname;
        }        
    }

    function errorbuild($table,$hall)
    {
        $this->sql = "select * from " . $table . " where hall_name = '" . $hall . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0){
            $dbbuild = $row["building"];
            return $dbbuild;
        }        
    }

    function errordone($table,$hall)
    {
        $this->sql = "DELETE FROM {$table} WHERE hall_name  =  '{$hall}' ;";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        }else return false;      
    }

    function errorup($table, $columnName = 'error_id') {
        $this->sql = "SELECT $columnName FROM $table";
        $result = mysqli_query($this->connect, $this->sql);
      
        if (!$result) {
          return false;
        }
      
        $i = 1;
        while ($row = mysqli_fetch_assoc($result)) {
          $this->sql = "UPDATE $table SET $columnName = $i WHERE $columnName = " . $row[$columnName];
          if (!mysqli_query($this->connect, $this->sql)) {
          }
          $i++;
        }
        mysqli_free_result($result); 
        return true;
      }

      function feed($table, $Email, $ID , $Rating , $cat ,$feedback)
    {
        $Fname = $this->prepareData($Rating);
        $ID = $this->prepareData($ID);
        $Email = $this->prepareData($Email);
        $Occ = $this->prepareData($feedback);
        $this->sql =
            "INSERT INTO " . $table . " (Email, ID , Rating , category , feedback) VALUES ('" . $Email . "','" . $ID . "','" . $Rating ."','". $cat ."','".$feedback."');";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function lec_start($table, $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from team where student_id = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbname = $row['first_name'].$row['last_name'];
            $dbsec = $row['Section'];
            $this->sql = "select * from entry where ID = " . $ID . " AND DATE = CURRENT_DATE;";
            $result = mysqli_query($this->connect, $this->sql);
            $row = mysqli_fetch_assoc($result);
            if (mysqli_num_rows($result) != 0) {
                $this->sql =
                "INSERT INTO {$table} (ID, name_, lecture_start, stu_section,entry_lec_)
                SELECT * FROM (
                SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS lecture_start, {$dbsec} AS stu_section,1 AS entry_lec_
                ) AS new_data
                WHERE NOT EXISTS (
                SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
                )
                ON DUPLICATE KEY UPDATE 
                lecture = VALUES(lecture_start),
                entry_lec_ = VALUES(entry_lec_);
                ";
                if (mysqli_query($this->connect, $this->sql)) {
                    return true;
                } else return false;
            }
            else{
                $this->sql =
                "INSERT INTO {$table} (ID, name_, lecture_start, stu_section,entry_lec_)
                SELECT * FROM (
                SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS lecture_start, {$dbsec} AS stu_section,0 AS entry_lec_
                ) AS new_data
                WHERE NOT EXISTS (
                SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
                )
                ON DUPLICATE KEY UPDATE 
                lecture = VALUES(lecture_start),
                entry_lec_ = VALUES(entry_lec_);
                ";
                if (mysqli_query($this->connect, $this->sql)) {
                    return true;
                } else return false;
            }
        }
    }

    function lec_end($table, $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from team where student_id = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbname = $row['first_name'].$row['last_name'];
            $dbsec = $row['Section'];
            $this->sql = "select * from entry where ID = " . $ID . " AND DATE = CURRENT_DATE;";
            $this->sql =
            "INSERT INTO {$table} (ID, name_, lecture_end, stu_section)
            SELECT * FROM (
            SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS lecture_end, {$dbsec} AS stu_section
            ) AS new_data
            WHERE NOT EXISTS (
            SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
            )
            ON DUPLICATE KEY UPDATE 
            lecture = VALUES(lecture_end);
            ";
            if (mysqli_query($this->connect, $this->sql)) {
                return true;
            } else return false;
        }
    }

    function sec_start($table, $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from team where student_id = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbname = $row['first_name'].$row['last_name'];
            $dbsec = $row['Section'];
            $this->sql = "select * from entry where ID = " . $ID . " AND DATE = CURRENT_DATE;";
            $result = mysqli_query($this->connect, $this->sql);
            $row = mysqli_fetch_assoc($result);
            if (mysqli_num_rows($result) != 0) {
                $this->sql =
                "INSERT INTO {$table} (ID, name_, section_start, stu_section, entry_)
                SELECT * FROM (
                SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS section_start, {$dbsec} AS stu_section ,1 AS entry_
                ) AS new_data
                WHERE NOT EXISTS (
                SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
                )
                ON DUPLICATE KEY UPDATE
                section = VALUES(section_start),
                entry_ = VALUES(entry_);";
                if (mysqli_query($this->connect, $this->sql)) {
                 return true;
                } else return false;
            }
            else{
                $this->sql =
                "INSERT INTO {$table} (ID, name_, section_start, stu_section, entry_)
                SELECT * FROM (
                SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS section_start, {$dbsec} AS stu_section ,0 AS entry_
                ) AS new_data
                WHERE NOT EXISTS (
                SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
                )
                ON DUPLICATE KEY UPDATE
                section = VALUES(section_start),
                entry_ = VALUES(entry_);";
                if (mysqli_query($this->connect, $this->sql)) {
                 return true;
                } else return false;
            }
        }
    }

    function sec_end($table, $ID)
    {
        $ID = $this->prepareData($ID);
        $this->sql = "select * from team where student_id = '" . $ID . "';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbname = $row['first_name'].$row['last_name'];
            $dbsec = $row['Section'];
            $this->sql =
            "INSERT INTO {$table} (ID, name_, section_end, stu_section)
            SELECT * FROM (
            SELECT {$ID} AS ID, '{$dbname}' AS name_, 1 AS section_end, {$dbsec} AS stu_section
            ) AS new_data
            WHERE NOT EXISTS (
            SELECT * FROM {$table} WHERE ID = new_data.ID AND date_+7 <= CURRENT_DATE
            )
            ON DUPLICATE KEY UPDATE
            section = VALUES(section_end);";
            if (mysqli_query($this->connect, $this->sql)) {
                return true;
            } else return false;
        }
    }

    function resch($table,$sec,$time)
    { 
        $day = date('w');
        switch($day)
        {  
            case 0 :
                $Day = "Sunday";
                break;
            case 1 :
                $Day = "Monday";
                break;
            case 2 :
                $Day = "Tuseday";
                break;
            case 3 :
                $Day = "Wednesday";
                break;
            case 4 :
                $Day = "Thursday";
                break;
            case 5 :
                $Day = "Friday";
                break;
            case 6 :
                $Day = "Saturday";
                break;
        }
        $this->sql = "SELECT * FROM {$table} WHERE Day = '{$Day}' AND Section = {$sec}";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbav=$row[$time];
            return $dbav;
        }       
    }

    function qaname($table,$num,$sub)
    {
        $this->sql = "select * from " . $table . " where Question_number = $num  AND Subject = '{$sub}';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbfname = $row["Name"];
            return $dbfname;
        }        
    }
    function qaphone($table,$num,$sub)
    {
        $this->sql = "select * from " . $table . " where Question_number = $num  AND Subject = '{$sub}';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbID = $row["Phone_num"];
            return $dbID;
        }        
    }
    function qaemail($table,$num,$sub)
    {
        $this->sql = "select * from " . $table . " where Question_number = $num  AND Subject = '{$sub}';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row["Email"];
            return $dbemail;
        }        
    }
    function qatitle($table,$num,$sub)
    {
        $this->sql = "select * from " . $table . " where Question_number = $num  AND Subject = '{$sub}';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbocc = $row["Title"];
            return $dbocc;
        }        
    }

    function qades($table,$num,$sub)
    {
        $this->sql = "select * from " . $table . " where Question_number = $num  AND Subject = '{$sub}';";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbocc = $row["Description"];
            return $dbocc;
        }        
    }
    function qarows($table,$sub)
    {
        $this->sql = "SELECT * FROM  {$table} WHERE Subject = '{$sub}' ;";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_num_rows($result);
            return $row;
                
    }

    function qastart($table,$sub)
    {
        $this->sql = "SELECT * FROM  {$table} WHERE Subject = '{$sub}' ;";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_num_rows($result);
        $startnum=0;
        While($row = mysqli_fetch_assoc($result)){
            $startnum = $row["Question_number"];
        }
        return $startnum;         
    }
    function qaact($table,$num,$sub)
    {
        
        $this->sql = "DELETE FROM {$table} WHERE Question_number  =  {$num} AND Subject = '{$sub}' ;";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        }else return false;
             
    }

    
    function qaup($table, $columnName,$sub) {
        $this->sql = "SELECT $columnName FROM $table WHERE Subject = '{$sub}'";
        $result = mysqli_query($this->connect, $this->sql);
      
        if (!$result) {
          return false;
        }
      
        $i = 1;
        while ($row = mysqli_fetch_assoc($result)) {
          $this->sql = "UPDATE $table SET $columnName = $i WHERE $columnName = " . $row[$columnName];
          if (!mysqli_query($this->connect, $this->sql)) {
          }
          $i++;
        }
      
        mysqli_free_result($result); 
      
        return true;
      }
      function qasubmit($Email, $phone , $name, $title,$desc,$subj,$qnum)
    {
        $name = $this->prepareData($name);
        $phone = $this->prepareData($phone);
        $Email = $this->prepareData($Email);
        $title = $this->prepareData($title);
        $desc = $this->prepareData($desc);
        $subj = $this->prepareData($subj);
        $qnum = $this->prepareData($qnum);
        $this->sql =
            "INSERT INTO qanda (Name, Phone_num , Email , Subject , Title , Description,Question_number) VALUES ('" . $name . "','" . $phone . "','" . $Email ."','".$subj."','".$title."','".$desc."','".$qnum."');";
        if (mysqli_query($this->connect, $this->sql)){
            return true;
        } else return false;
    }
}
?>