<?php 
	include_once('connection.php'); 

	$nip =$_POST['nip'];
	$nama=$_POST['nama'];
	$posisi=$_POST['posisi'];
	$gaji=$_POST['gaji'];
	$alamat=$_POST['alamat'];


	$insert = "INSERT INTO gaji(nip,nama,posisi,gaji,alamat) VALUES ('$nip','$nama','$posisi','$gaji','$alamat')";

	$exeinsert = mysqli_query($koneksi,$insert);

	$response = array();

	if($exeinsert)
	{
		$response['code'] =1;
		$response['message'] = "Success ! Data di tambahkan";
	}
	else
	{
		$response['code'] =0;
		$response['message'] = "Failed ! Data Gagal di tambahkan";
	}

		echo json_encode($response);

 ?>