<?php
    
    include_once 'db-connect.php';
    
    class Lesson{
        
        private $db;
        
        private $db_table = "lessons";
        
        public function __construct(){
            $this->db = new DbConnect();
        }
        public function showCourseLessons($id){
              
            
            
            $query = "select * from ".$this->db_table." where id = '$$id'";
            
            $result = mysqli_query($this->db->getDb(), $query);
            mysqli_close($this->db->getDb());
            return $result;
            
        }
        
        
       
    }
    ?>