function compareFields(){
	if($("#password").val()!=$("#confirpassowrd").val()){
		$('#errorIdenticalFields').show();
		return;
	}else{
		$('#errorIdenticalFields').hide();
	}

	$("#formData").submit();
}