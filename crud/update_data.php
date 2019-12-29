<?php  
	include_once('connection.php'); 

	$nip =$_POST['nip'];
	$nama=$_POST['nama'];
	$posisi=$_POST['posisi'];
	$gaji=$_POST['gaji'];
	$alamat=$_POST['alamat'];

	$getdata = mysqli_query($koneksi,"SELECT * FROM gaji WHERE nip ='$nip'"); 
	$rows = mysqli_num_rows($getdata);

	$respose = array();

	if($rows > 0 )
	{
		$query = "UPDATE gaji  SET nama='$nama',posisi='$posisi',gaji='$gaji' ,alamat='$alamat' WHERE nip='$nip'";
		$exequery = mysqli_query($koneksi,$query);
		if($exequery)
		{
				$respose['code'] =1;
				$respose['message'] = "Update Success";
		}
		else
		{
				$respose['code'] =0;
				$respose['message'] = "Failed Update";
		}
	}
	else
	{
				$respose['code'] =0;
				$respose['message'] = "Failed Update : data tidak ditemukan";
	}
	
	echo json_encode($respose);
?>