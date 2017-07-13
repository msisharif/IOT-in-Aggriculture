
<!doctype html>
<html lang="en">
<head>

	<title>Document</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap.min.js"></script>
	<style type="text/css">
			body { background: #5882FA !important; } 
	</style>
	
	<script>
		
	</script>
    
</head>
<body>
	
	<div class="container">
		<div class="jumbotron">
			<h1>Welcome To IOT In Agriculture</h1>
		</div>
		<br><br>
		<div class="well well-lg"><h3>UPAZILA : GAZIPUR SADAR</div>
		<form action="index.php" method="post">
			<?php //$var_value = $_POST['varname'];
				//echo $var_value;
			?>
		
			<br><br>
			<?php
      // database=agri_data;server=sharifserver.mysql.database.azure.com;uid=MSISharif@sharifserver;pwd=******
				$connect=mysqli_connect('sharifserver.mysql.database.azure.com','MSISharif@sharifserver','MD74274385$','agri_data');
				$sql = mysqli_query($connect,"SELECT unions FROM upazila_info");
				$row = mysqli_fetch_assoc($sql);
				$unions = $row['unions'];
				$parts = explode('|', $unions);
			?>
			<div class="form-group">
			  <label>Select Union:</label>
			  <br><br>
			  <div = class="col-xs-5">
				<select name="union" id= "union" onchange="getValue(this)"  class="form-control">
					<option>Please select a union</option>
					<?php
					foreach($parts as $key => $value):
					?>
						<option  value= "<?php echo $value;?>" <?php if( isset($_GET['selected_union']) && $_GET['selected_union'] == $value){ echo "selected";}?>> <?php echo $value;?></option>';
					<?php
					endforeach;
					?>
				</select>
				<script>
					var temp;
					function getValue(obj)
					{
					    temp = obj.value;
					   window.location.href = "index.php?selected_union=" + temp; 
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
                $connect=mysqli_connect('sharifserver.mysql.database.azure.com','MSISharif@sharifserver','MD74274385$','agri_data');
				//$connect=mysqli_connect('localhost','root','','agri_data');
				$sql = mysqli_query($connect,"SELECT * FROM mouza_table WHERE union_name = '$selected_union'");
				}
			?>
			<br><br>
			<div class="form-group">
			  <label>Select mouza:</label>
			  <br><br>
			  <div = class="col-xs-5">
				<select name="mouza" class="form-control">
					<option>Please select a mouza</option>
					<?php while($row  = mysqli_fetch_array($sql)):;?>
					<option value="<?php echo $row[0];?>"><?php echo $row[0];?></option>;
					<?php endwhile; ?>
					
				</select>
			  </div>
			</div>
			<br><br>
			<div class="form-group">
			  <label>Land type:</label>
			  <br><br>
			  <div = class="col-xs-5">
			  <select name="land_type" class="form-control" >
					<option>Please Select Land type</option>
					<option value="hl">High Land</option>
					<option value="mhl">Medium High Land</option>
					<option value="mll">Medium low Land</option>
					<option value="ll">low Land</option>
					<option value="vll">Very low Land</option>
			  </select>
			  </div>
			</div>
			<br><br>
			<div class="form-group">
			  <label>Water Removing Fact:</label>
			  <br><br>
			  <div = class="col-xs-5">
			  
			  <select name="wrf" class="form-control" >
					<option>Please select water removing fact</option>
					<option value="oti agam">oti agam</option>
					<option value="agam">agam</option>
					<option value="shavabik">shavabik</option>
					<option value="deri">deri</option>
					<option value="oti deri">oti deri</option>
			  </select>
			  </div>
			</div>
			
			<br><br>
			<div class="form-group">
			  <label>Soil Solidness:</label>
			  <br><br>
			  <div = class="col-xs-5">
			  
			  <select name="soil_solidness" class="form-control" >
					<option>Please select Soil Solidness</option>
					<option value="alga">alga</option>
					<option value="jhurjhure">jhurjhure</option>
					<option value="driro">driro</option>
			  </select>
			  </div>
			</div>
			
			<br><br>
			<div class="form-group">
				<label>N:</label>
				<br><br>
				<div = class="col-xs-5">
				   <input type="text" name="N" class="form-control"  placeholder="Enter the value of N">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>P:</label>
				<br><br>
				<div = class="col-xs-5">
				   <input type="text" name="P" class="form-control"  placeholder="Enter the value of P">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>K:</label>
				<br><br>
				<div = class="col-xs-5">
				   <input type="text" name="K" class="form-control"  placeholder="Enter the value of K">
				</div>
				<br><br>
			</div>
			
			<div class="form-group">
				<label>PH:</label>
				<br><br>
				<div = class="col-xs-5">
				 <input type="text" name="PH" class="form-control"  placeholder="Enter the value of PH">
				</div>
				<br><br>
			</div>
			
			<?php
	
				$myObj = new \stdClass;
				if( isset( $_POST['union'])) {
				$myObj->union = $_POST['union']; 
				} 
				if( isset( $_POST['mouza'])) {
				$myObj->mouza = $_POST['mouza']; 
				} 
				if( isset( $_POST['land_type'])) {
				$myObj->land_type = $_POST['land_type']; 
				} 
				if( isset( $_POST['wrf'])) {
				$myObj->wrf = $_POST['wrf']; 
				} 
				if( isset( $_POST['soil_solidness'])) {
				$myObj->soil_solidness = $_POST['soil_solidness']; 
				} 
				if( isset( $_POST['N'])) {
				$myObj->N = $_POST['N']; 
				}
				if( isset( $_POST['P'])) {
				$myObj->P = $_POST['P']; 
				}
				if( isset( $_POST['K'])) {
				$myObj->K = $_POST['K']; 
				}
				if( isset( $_POST['PH'])) {
				$myObj->PH = $_POST['PH']; 
				} 
				

				$myJSON = json_encode($myObj);

				$myfile = fopen("dataShare.txt", "w") or die("Unable to open file!");
				fwrite($myfile, $myJSON);
				fclose($myfile);

				/* echo $myJSON; */
			?>
			
			<div class="form-group">
				<br><br>
				<div class="col-xs-7s">
				  <input type="submit" class="btn btn-success" value="Submit">
                    <?php 
//                        shell_exec('java -jar "C:\Users\shane\Documents\My Web Sites\MSISharifApp\ZTest\dist\ZTest.jar"');
                            shell_exec('D:\home\site\wwwroot\ZTest\dist\jdk1.8.0_111\bin\java -jar "D:\home\site\wwwroot\nabilpag\dist\Agriculture.jar"');
                    ?>
				</div>
			</div>
			
			
		</form>

	</div>
	
	
	
</body>
</html>



