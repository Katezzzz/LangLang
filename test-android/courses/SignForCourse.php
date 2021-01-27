
<?php
  include_once '../db-connect.php';

$db = new DbConnect();


$idUser = "";
    
    $idCourse = "";
    
 
    
    if(isset($_POST['idUser'])){
        
        $idUser = $_POST['idUser'];
        
    }
    
    if(isset($_POST['idCourse'])){
        
        $idCourse = $_POST['idCourse'];
        
    }
    $query = "insert into participants (idUser, idCourse) values ('$idUser', '$idCourse')";
                
    $inserted = mysqli_query($db->getDb(), $query);
    
    if($inserted == 1){
        
        
        $json['message'] = "Dodano do kursu";
        
    }else{
        
        
        $json['message'] = "Error";
        
    }
    
    mysqli_close($db->getDb());

    echo json_encode($json);
        ?>