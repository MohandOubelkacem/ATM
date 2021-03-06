<?php 
/* On protège la page php avec les variables de session 
    On vérifie si elles existent sinon on est redirigés vers la la page de connexion */
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
        <!-- on telecharge une police spéciale (non standard) pour afficher le titre Banquepro d'une autre manière --> 
        <link href="https://bootswatch.com/4/lux/bootstrap.min.css" rel="stylesheet"> <!--theme--> 
        <title>ProBank</title>
    </head>
    
	<body>
            <div class="container">
            <header class="page-header">
                <h1><span class="titre">Banque<sub>pro</sub></span><a href="deconnection.php"><button type="button" class="btn btn-secondary">Deconnexion</button></a></h1>
            </header>
                
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <a class="navbar-brand" href="accueil.php">Accueil</a>
                
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                </button>

                <div class="collapse navbar-collapse" id="navbarColor01">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active"><a class="nav-link" href="operations.php">Opérations</a></li>
                        <li class="nav-item active"><a class="nav-link" href="infoComptes.php">Comptes</a></li>
                        <li class="nav-item active"><a class="nav-link" href="infoclient.php">Clients</a>
                        	<li class="nav-item active"><a class="nav-link" href="infoCartes.php">Cartes</a></li>
                        </li>
                    </ul>
                </div>
            </nav>        
                
    
            <section class="container-fluid banner">
                <div class="ban">
                    <img src="background.jpg" alt="bannière du site"/>
                </div> 
            </section>          
            </div>
        
    </body>
</html>
  
            	
  <?php
}
?> 
                
                
                
                
                
                
            	
                
                
                