
<?php
    
    require_once 'user.php';
    
    $username = "";
    
    $password = "";
    
    $email = "";
    $name = "";
    $lastname = "";
    
    if(isset($_POST['userName'])){
        
        $username = $_POST['userName'];
        
    }
    
    if(isset($_POST['password'])){
        
        $password = $_POST['password'];
        
    }
    
    if(isset($_POST['email'])){
        
        $email = $_POST['email'];
        
    }
    if(isset($_POST['name'])){
        
        $name = $_POST['name'];
        
    }
    
    if(isset($_POST['lastName'])){
        
        $lastname = $_POST['lastName'];
        
    }
    
    
    $userObject = new User();
    
    // Registration
    
    if(!empty($username) && !empty($password) && !empty($email)){
        
        $hashed_password = md5($password);
        
        $json_registration = $userObject->createNewRegisterUser($username, $hashed_password, $email,$name,$lastname);
        
        echo json_encode($json_registration);
        
    }
    
    // Login
    
    if(!empty($username) && !empty($password) && empty($email)){
        
        $hashed_password = md5($password);
        
        $json_array = $userObject->loginUsers($username, $hashed_password);
        
        echo json_encode($json_array);
    }
    ?>
