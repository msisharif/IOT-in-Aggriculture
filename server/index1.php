
<?php
    session_start();
    $Upazila = "";
    if(isset($_SESSION['Upazila'])){
        $Upazila = $_SESSION['Upazila'];
    }
	$Upazila = strtoupper($Upazila);
	$union = "";
	$mouza = "";
	$land_type = "";
	$wrf = "";
	$soil_solidness = "";
	$N = "";
	$P = "";
	$K = "";
	$PH = "";
	if(isset($_POST['union'])){
        $_SESSION['union'] = $_POST['union'];
    }
	if(isset($_POST['mouza'])){
        echo $_SESSION['mouza'] = $_POST['mouza'];
    }
	if(isset($_POST['land_type'])){
        $_SESSION['land_type'] = $_POST['land_type'];
    }
	if(isset($_POST['wrf'])){
        $_SESSION['wrf'] = $_POST['wrf'];
    }
	if(isset($_POST['soil_solidness'])){
        $_SESSION['soil_solidness'] = $_POST['soil_solidness'];
    }
	if(isset($_POST['N'])){
        $_SESSION['N'] = $_POST['N'];
    }
	if(isset($_POST['P'])){
        $_SESSION['P'] = $_POST['P'];
    }
	if(isset($_POST['K'])){
        $_SESSION['K'] = $_POST['K'];
    }
	if(isset($_POST['PH'])){
        $_SESSION['PH'] = $_POST['PH'];
    }
	if(isset($_POST['submit'])){
        header('location: index2.php');
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
		<div class="well well-lg"><h3>UPAZILA : <?php echo $Upazila;?></div>
		<div class="jumbotron">
		<form name="input_form" action="index1.php" id = "myForm" method="post">
			<br><br>
			<?php
				 $connect=mysqli_connect('iotinagriculture-mysqldbserver.mysql.database.azure.com','MSISharif@iotinagriculture-mysqldbserver','MD74274385$','agri_data');
				$sql = mysqli_query($connect,"SELECT unions FROM upazila_info WHERE upazila_name = '$Upazila'");
				$row = mysqli_fetch_assoc($sql);
				$unions = $row['unions'];
				$parts = explode('|', $unions);
			?>
			<div class="form-group">
			  <label>Select Union:</label>
			  <br><br>
			  <div = class="col-xs-6">
				<select name="union" id= "union1" onchange="getValue(this)"  class="form-control">
					<option>Please select a union</option>
					
					<?php
					foreach($parts as $key => $value):
					?>
						<option  value= "<?php echo $value;?>" <?php if( isset($_GET['selected_union']) && $_GET['selected_union'] == $value){ echo "selected";}?>> <?php echo $value;?></option>';
					<?php
					endforeach;
					?>
				</select>
				<br>
					
				<script>
					var temp;
					function getValue(obj)
					{
					    temp = obj.value;
					   window.location.href = "index1.php?selected_union=" + temp; 
					}
				</script>
				<script type="text/javascript">
				   jQuery(document).ready(function(){

					  jQuery('select#union').val('<?php echo $_POST['union'];?>');

					 });
				</script>
			  </div>
			 
			</div>
			
			<?php
				if(isset($_GET['selected_union'])){
				$selected_union = $_GET['selected_union'];

				 $connect=mysqli_connect('iotinagriculture-mysqldbserver.mysql.database.azure.com','MSISharif@iotinagriculture-mysqldbserver','MD74274385$','agri_data');
				$sql = mysqli_query($connect,"SELECT * FROM mouza_table WHERE union_name = '$selected_union'");
				}
			?>
			<br><br>
			<div class="form-group">
			  <label>Select mouza:</label>
			  <br><br>
			  <div = class="col-xs-6">
				<select name="mouza" class="form-control">
					<option>Please select a mouza</option>
					<?php while($row  = mysqli_fetch_array($sql)):;?>
					<option value="<?php echo $row[0];?>"><?php echo $row[0];?></option>;
					<?php endwhile; ?>
					
				</select>
			  </div>
			  <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal3">Read More</button>
				<div class="modal fade" id="myModal3" role="dialog">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
					 <p><h5>Mouza: <br><br> Mouza is the lowest single-area revenue collection. During the Cadastral survey, each police station was divided into one by one and it was surveyed by identifying each unit with its serial no. Each unit of the Thana area is called mouza. A mouza is recited with one or a few villages or neighborhoods.</p>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				  </div>
				 </div>
				</div>
			</div>

			<div class="form-group">
			  <label>Land type:</label>
			  <br><br>
			  <div = class="col-xs-6">
			  <select name="land_type" class="form-control" >
					<option>Please select Land type</option>
					<option value="High Land">High Land</option>
					<option value="Medium High Land">Medium High Land</option>
					<option value="Medium Low Land">Medium low Land</option>
					<option value="Low Land">low Land</option>
					<option value="Very Low Land">Very low Land</option>
			  </select>
			  </div>
				<button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal">Read More</button>
				<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
					  <p><h5><br>1. High land:<br><br> The land which is not flooded during rainy season. This type of land is divided into two parts such as: 1) good drainage high land where even after heavy rains, the land cannot be stuck water for more than two days and 2) slightly bad drainage high land where land can be stuck water usually during rainy season and T.Aman is possible here.</p>
					  <p><h5><br>2. Medium high land:<br><br> The land which is flooded continuously for more than two weeks to a maximum of 90 cm in the normal flood during rainy season. Medium high land is divided into two parts such as: 1) very little flooded medium high land where Uffshi T.Aman is possible and 2) little flooded medium high land which is considered to be very flooded for Uffshi T.Aman.</p>
					  <p><h5><br>3. Medium low land:<br><br> The land that is flooded continuously for a few months till the depth of 90 cm to 180cm (approximately 3 to 6 feet) cm in the normal flood during rainy season.</p>
					  <p><h5><br>4. Low land:<br><br> The land that is flooded continuously for a few months till the depth of 180cm to 275 (approximately 6 to 9 feet) cm in the normal flood during rainy season.</p>
					  <p><h5><br>5. Very low land:<br><br> The land that is flooded continuously for a few months till the depth of 275 cm (9 feet) in the rainy season.</p>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				  </div>
				 </div>
				</div>
			</div>
			
			<div class="form-group">
			  <label>Water Removing Fact:</label>
			  <br><br>
			  <div = class="col-xs-6">
			  
			  <select name="wrf" class="form-control" >
					<option>Please select water removing fact</option>
					<option value="Immediate">Immediate</option>
					<option value="Too Early">Too Early</option>
					<option value="Advance">Advance</option>
					<option value="Normal">Normal</option>
					<option value="Late">Late</option>
					<option value="Too late">Too late</option>
			  </select>
			  </div>
			  <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal1">Read More</button>
				<div class="modal fade" id="myModal1" role="dialog">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
					 <p><h5><br>1. Immediate:<br><br> Water is removed from the land surface immediately. </p>
					 <p><h5><br>2. Too Early:<br><br> Water is removed from the land surface in the month of Ashin. </p>
					 <p><h5><br>3. Advance:<br><br> After the month of ashin but within the month of kartik water is removed from the land surface. </p>
					 <p><h5><br>4. Normal:<br><br> After the month of kartik but within the month of agrohayon water is removed from the land surface. </p>
					 <p><h5><br>5. Late:<br><br> After the month of agrohayon but within the second week of the month of powsh water is removed from the land surface. </p>
					 <p><h5><br>6. Too late:<br><br>  After the second week of the month of powsh water is removed from the land surface. </p>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				  </div>
				 </div>
				</div>
			</div>
			
			<div class="form-group">
			  <label>Soil Solidness:</label>
			  <br><br>
			  <div = class="col-xs-6">
			  
			  <select name="soil_solidness" class="form-control" >
					<option>Please select Soil Solidness</option>
					<option value="Loose">Loose</option>
					<option value="Crumbly">Crumbly</option>
					<option value="Rigid">Rigid</option>
			  </select>
			  </div>
			  <button type="button" class="btn btn-info btn-md" data-toggle="modal" data-target="#myModal2">Read More</button>
				<div class="modal fade" id="myModal2" role="dialog">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
					 <p><h5><br>1. Loose: <br><br> Soil particles remain separated, or they get separated as soon as touched. Example: Sandy soil.</p>
					 <p><h5><br>2. Crumbly: <br><br> The ability to resist soil wheel is very low. It is very easily broken by the pressure of two fingers. Examples: Sandy loam and loam soil.</p>
					 <p><h5><br>3. Rigid: <br><br> The ability of soil to resist the pressure of wheel is very high. It is not very easy to be broken by the pressure of two fingers. Examples: clay loam and clay soil.</p>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				  </div>
				 </div>
				</div>
			</div>
			
			<div class="form-group">
				<label>N:</label>
				<br><br>
				<div = class="col-xs-6">
				   <input type="text" name="N" class="form-control"  placeholder="Enter the value of N">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>P:</label>
				<br><br>
				<div = class="col-xs-6">
				   <input type="text" name="P" class="form-control"  placeholder="Enter the value of P">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>K:</label>
				<br><br>
				<div = class="col-xs-6">
				   <input type="text" name="K" class="form-control"  placeholder="Enter the value of K">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>pH:</label>
				<br><br>
				<div = class="col-xs-6">
				 <input type="text" name="PH" class="form-control"  placeholder="Enter the value of pH">
				</div>
				<br><br>
			</div>
			<div class="form-group">
				<br><br>
				<div = class="col-xs-7s">
				  <input type="submit" class="btn btn-success" name="submit" value="Submit" onclick = def()>
				</div>
			</div>
		</form>
		</div>
		<script>
			$("#reset").on("click", function def() {
				$('#union1 option').prop('selected', function def() {
					return this.defaultSelected;
				});
			});
		</script>
		<?php
			//shell_exec('java -jar "D:\MSI programing\thesis\Agriculture\dist\Agriculture.jar"');
		?>

	</div>

</body>
</html>



