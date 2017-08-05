<?php
	 session_unset();
    session_start();
    $Upazila = "";
    if(isset($_POST['Upazila'])){
         $_SESSION['Upazila'] = $_POST['Upazila'];
		 header('location: index1.php');
    }
 

?>

<!doctype html>

<html lang="en">
<head>

	<title>IOT IN AGRICULTURE</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<style type="text/css">
			body { background: #5882FA !important; } 
	</style>
</head>
<body>
	
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
		<div class="navbar-header">
		  <a class="navbar-brand" href="index.php">IOT In Agriculture</a>
		</div>
		<ul class="nav navbar-nav">
		  <li class="active"><a href="index.php">Home</a></li>
		  <li><a href="index2.php">Outputs</a></li>
		  <li><a href="index4.php">Suggestion</a></li>
		  <li><a href="index3.php">About</a></li>
		</ul>
	  </div>
	</nav>
	
	<div class="container-fluid">
		<div class="jumbotron">
			<h1>Welcome To IOT In Agriculture</h1>
		</div>
		<br><br>
		<div class="jumbotron">
		<form name="input_form" action="index.php" id = "myForm" method="post">

		<?php
				 $connect=mysqli_connect('iotinagriculture-mysqldbserver.mysql.database.azure.com','MSISharif@iotinagriculture-mysqldbserver','MD74274385$','agri_data');
				$sql = mysqli_query($connect,"SELECT upazila_name FROM upazila_info");
			?>
			<div class="form-group">
			  <label>Select Upazila:</label>
			  <br><br>
			  <div = class="col-xs-6">
				<select name="Upazila" id="Upazila" class="form-control">
					<option>Please select a Upazila</option>
					<?php while($row  = mysqli_fetch_array($sql)):;?>
					<option value="<?php echo $row[0];?>"><?php echo $row[0];?></option>;
					<?php endwhile; ?>
					
				</select>
			</div>
			
		</div>
		<div class="form-group">
				<br><br>
				<div = class="col-xs-7s">
				  <input type="submit" class="btn btn-success" name="submit" value="Submit">
				</div>
		</div>
		</div>
		</form>
	</div>

</body>
</html>

