<?php 
    
    $user=$_POST['user'];
    $password=$_POST['password'];

if (isset($_POST['user']) && isset ($_POST['password']) ) {
    
    if (!empty($_POST['user']) && !empty($_POST['password'])) {
        $bdd = new PDO('mysql:host=localhost;dbname=bdd_interne;charset=utf8', 'root', '');
        
        $resultat= $bdd-> query ("SELECT * from login where user= '".$user."'and password='".$password."' ");
        
        $res = $resultat ->fetch();
        
        if($res['user']== $user && $res['password']==$password){
            session_start(); 
            $_SESSION['user']=$user;
            $_SESSION['password']=$password;
            header ('location: accueil.php');  /* si les identifiants sont corrects on les stocke dans des variables de sessions */ 
        }
        else {
            echo '<p>Mot de passe incorrect </p>';
        }
    }
    else {
        echo '<p> Veuillez remplir les champs.</p>';
    }
}
else {
    echo 'les variables du formulaire ne sont pas déclarées';
    }
?>
