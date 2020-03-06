<?php 
session_start();
if (isset($_SESSION['user']) && isset($_SESSION['password'])){
$_SESSION=array();
}
session_destroy();
include('connexion.html');
?>