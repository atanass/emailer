function validateEmailFormat(element) {
	var value = element.value;
	var result = value.match(/(.+)@(.+).[a-z]+/g);
	if (result == null) {
		alert("Invalid email format. Example: email-address@domain.com");
		element.value = '';
	}
}

function changeImportant(){
	var importantParam = $('#importantParam');
	var importantCheckbox = $('#importantCheckbox');
	var checked = importantCheckbox.prop("checked");
	importantParam.val(checked);
}
