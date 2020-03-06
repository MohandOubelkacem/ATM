<?php 
// code php à inserer sur chaque page html pour une redirection vers la page de connexion sur l'administrateur n'est pas connecté 

// session_start(); //initialisation des sessions php et à placer avant tout contenu 
// charset-utf8 pour gerer les accents 

// if (isset($_SESSION['user']) AND isset($_SESSION['password'])){ // si les variables existent 
   // header('location: infoclients.php');
// }
// else {
  //   header('location: connexion.html');
// }

class connect{
    static function protectsess(){
    if(isset($_SESSION['user']) && isset($_SESSION['password'])){
        return true;
    }
    
    else return false;
}
}
?> 
