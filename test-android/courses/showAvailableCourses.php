<?php

include_once '../db-connect.php';

  $db;
        
  $db_table = "courses";

   
   $db = new DbConnect();

//this is our sql query 
$sql = "SELECT distinct name, teacher, language, time, c.idCourse,seats FROM courses c left join participants p on p.idCourse=c.idCourse where c.idCourse not in (SELECT distinct c.idCourse FROM courses c left join participants p on p.idCourse=c.idCourse where idUser=1)";

$result = mysqli_query($db->getDb(), $sql);
 
$courses = array(); 
//looping through all the records
while($row = mysqli_fetch_row($result))
{
  // print_r(array_values($row));
    $temp = array();
    $temp = [
        'name'=>$row[0],
        'teacher'=>$row[1],
        'language'=>$row[2],
        'time'=>$row[3],
        'id'=>$row[4],
        'seats'=>$row[5]
        
        

        ];
    array_push($courses,  $temp);
    
}


mysqli_close($db->getDb());
//displaying the data in json format 
echo json_encode($courses);
?>