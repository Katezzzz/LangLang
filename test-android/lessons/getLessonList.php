<?php

include_once '../db-connect.php';
if(isset($_GET['id'])){
    
    $id = $_GET['id'];
  
  }
  echo $id;
  $db;
        
  $db_table = "subjects";

   
   $db = new DbConnect();

//this is our sql query 
$sql = "SELECT idSubject ,idCourse,title,text,homework,words from subjects  where idCourse=$id";

$result= mysqli_query($db->getDb(), $sql);

$lessons = array(); 
//looping through all the records
while($row = mysqli_fetch_row($result))
{
  // print_r(array_values($row));
    $temp = array();
    $temp = [
        'idSubject'=>$row[0],
        'idCourse'=>$row[1],
        'title'=>$row[2],
        'text'=>$row[3],
        'homework'=>$row[4],
        'words'=>$row[5]
        
        

        ];
    array_push($lessons,  $temp);
    
}


mysqli_close($db->getDb());
//displaying the data in json format 
echo json_encode($lessons);
?>