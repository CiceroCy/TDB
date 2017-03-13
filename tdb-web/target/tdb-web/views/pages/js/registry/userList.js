function editProfile(id){
	$("#matriculation").val(id);
	$("#formData").attr('target','');
	$("#formData").attr('action','edit-user.htm');
	$("#formData").submit();
}