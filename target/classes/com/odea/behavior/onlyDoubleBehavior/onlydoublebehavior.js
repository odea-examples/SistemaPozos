function isNumberDotCommaKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	
	/*Rango de numeros - backspace - punto - e - E - + - "-" */
    if ((charCode >= 48 && charCode <= 57) || charCode == 8 || charCode == 46 || charCode == 44 || charCode == 45) {
    	return true; 	
    }
	
    return false;
}
