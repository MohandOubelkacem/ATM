<!--

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">



<div class="container">

    <div class="row">
        <div class="col-md-4 offset-md-4">
        <div class="card text-center card  bg-default mb-3">
          <div class="card-header">
            LOGIN
          </div>
          <div class="card-body">
               <input type="text" id="userName" class="form-control input-sm chat-input" name= "user" placeholder="Utilisateur" />
            </br>
            <input type="password" id="userPassword" class="form-control input-sm chat-input" name="password" placeholder="Mot de passe" />
          </div>
          <div class="card-footer text-muted">
            <input Type=SUBMIT value="Connexion">
          </div>
        </div>
    </div>
    </div>
</div>

-->


<div class="login">
	<h1>Login</h1>
    <form method="POST">
    	<input type="text" name="user" placeholder="User" />
        <input type="password" name="password" placeholder="Password"  />
        <button type="submit" class="btn btn-primary btn-block btn-large">Connexion</button>
    </form>
</div>


<?php 

if (isset($_POST['user']) && isset ($_POST['password']) ) {
    
    if (!empty($_POST['user']) && !empty($_POST['password'])) {
        $bdd = new PDO('mysql:host=localhost;dbname=bdd_interne;charset=utf8', 'root', '');
        
        $resultat= $bdd-> query ("SELECT password form login where id=1");
        
        $res = $resultat -> fetch();
        
        if($res['password']== $password ){
            session_start(); 
            $_SESSION['user']=$user;
            $_SESSION['password']=$password;
            header ('location: accueil.html');
        }
        else {
            header('location: connexion.html');
        }
    }
    else {
        echo '<p> Veuillez remplir les champs.</p>';
        include ('operation.php');
    }
}
else {
    echo 'les variables du formulaire ne sont pas déclarées';
    }
?>

