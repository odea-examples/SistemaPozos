function isNumberKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
    if ((charCode >= 48 && charCode <= 57) || charCode == 8) {
    	return true;
    }
 
    return false;
}
