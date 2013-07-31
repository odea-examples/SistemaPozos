function isNumberDotCommaKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	
	/*Rango de numeros - backspace - coma - punto*/
    if ((charCode >= 48 && charCode <= 57) || charCode == 8 || charCode == 44) {
    	return true; 	
    }
	
    return false;
}
