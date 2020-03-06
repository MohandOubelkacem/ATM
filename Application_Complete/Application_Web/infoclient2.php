<?php 
session_start();
require("protection.php");
if (!connect::protectsess()){
    include("connexion.html");
}
else {

?> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--pour les mobiles,l'affichage occupe tout l'espace disponible sans zoom-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <!--declaration du fichier CSS dans sa version minifiée-->
        <link href="style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">
        <link href="https://bootswatch.com/4/lux/bootstrap.min.css" rel="stylesheet">
        <title>ProBank</title>
    </head>
    
	<body>
            <div class="container">
            <header class="page-header">
                    <h1>Banque<sub>pro</sub></h1>
            </header>
                
                
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="accueil.html">Accueil</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="operations.php">Opérations <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="infoclients.php">Clients</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>        
                
 <?php

    $bdd = new PDO('mysql:host=localhost;dbname=bdd_interne;charset=utf8', 'root', '');
// on met la base de donnée dans une variable 
// PDO: extension orientée objet , classe de php (php data object) qui permet le contenu d'une base de donnée 
    try{
        $bdd = new PDO('mysql:host=localhost;dbname=bdd_interne;charset=utf8', 'root', '');
        }
    catch (Exception $e){
            die('Erreur : ' . $e->getMessage());
                }
    //PDO renvoi une exception qui permet de capturer l'erreur 
    //php execute instructions à l'intérieur du bloc try 
    // si erreur php entre dans le catch et execute le bloc càd arrête l'execution de la page en affichant un message d'erreur 

    $requete = $bdd-> query ("SELECT * FROM client");
    // $requete contient la reponse de mysql càd tout le contenu de la table operation 
        
    // while ($resultat = $requete -> fetch()) // $resultat est un tableau 
        // on affiche son contenu un à un 
   // {
   // echo $resultat['num_client']." ".$resultat['act_oprt']." " .$resultat['date'];
    
   // }

?>
           
<table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">N°Client</th>
          <th scope="col">Nom</th>
          <th scope="col">Prenom</th>
          <th scope="col">Email</th>
          <th scope="col">Adresse</th>
          <th scope="col">Profession</th>
          <th scope="col">Téléphone</th>
        </tr>
      </thead>
        <?php
     while ($resultat = $requete -> fetch()) 
     {
         ?>       
              
      <tbody>
        <tr>
          <th scope="row"><?php echo $resultat['num'] ?></th>
          <td><?php echo $resultat['nom'] ?></td>
          <td><?php echo $resultat['prenom'] ?></td>
          <td><?php echo $resultat['email'] ?></td>
          <td><?php echo $resultat['adresse'] ?></td>
          <td><?php echo $resultat['profession'] ?></td>
          <td><?php echo $resultat['numero_tel'] ?></td>
        </tr>
           <?php 
         }
        ?>
     </tbody>
</table>
                
   

          
        </div>   
	</body>
</html>
<?php 
}
?> 