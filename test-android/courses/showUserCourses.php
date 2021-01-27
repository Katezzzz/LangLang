<?php

include_once '../db-connect.php';
$id = 0;
    
if(isset($_GET['id'])){
    
  $id = $_GET['id'];

}
echo $id;
  $db;
        
  $db_table = "courses";

   
   $db = new DbConnect();

//this is our sql query 
$sql = "SELECT  name, teacher, language, time, c.idCourse,seats  FROM courses c left join participants p on p.idCourse=c.idCourse where idUser=$id";

$result = mysqli_query($db->getDb(), $sql);

$courses = array(); 
//looping through all the records
if($result!=NULL) {
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
}


mysqli_close($db->getDb());
//displaying the data in json format 
echo json_encode($courses);
?>