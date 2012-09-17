function closed_mon(){
	if(document.frm_basic.mon_chk.checked == true){
		document.getElementById("mon_open").style.visibility = 'hidden';
		document.getElementById("mon_line").style.visibility = 'hidden';
		document.getElementById("mon_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("mon_open").style.visibility = 'visible';
		document.getElementById("mon_line").style.visibility = 'visible';
		document.getElementById("mon_close").style.visibility = 'visible';
	}
 }
 
 
function closed_tue(){
	if(document.frm_basic.tue_chk.checked == true){
		document.getElementById("tue_open").style.visibility = 'hidden';
		document.getElementById("tue_line").style.visibility = 'hidden';
		document.getElementById("tue_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("tue_open").style.visibility = 'visible';
		document.getElementById("tue_line").style.visibility = 'visible';
		document.getElementById("tue_close").style.visibility = 'visible';
	}
 }


function closed_wed(){
	if(document.frm_basic.wed_chk.checked == true){
		document.getElementById("wed_open").style.visibility = 'hidden';
		document.getElementById("wed_line").style.visibility = 'hidden';
		document.getElementById("wed_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("wed_open").style.visibility = 'visible';
		document.getElementById("wed_line").style.visibility = 'visible';
		document.getElementById("wed_close").style.visibility = 'visible';
	}
 }

function closed_thru(){
	if(document.frm_basic.thru_chk.checked == true){
		document.getElementById("thru_open").style.visibility = 'hidden';
		document.getElementById("thru_line").style.visibility = 'hidden';
		document.getElementById("thru_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("thru_open").style.visibility = 'visible';
		document.getElementById("thru_line").style.visibility = 'visible';
		document.getElementById("thru_close").style.visibility = 'visible';
	}
 }

function closed_fri(){
	if(document.frm_basic.fri_chk.checked == true){
		document.getElementById("fri_open").style.visibility = 'hidden';
		document.getElementById("fri_line").style.visibility = 'hidden';
		document.getElementById("fri_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("fri_open").style.visibility = 'visible';
		document.getElementById("fri_line").style.visibility = 'visible';
		document.getElementById("fri_close").style.visibility = 'visible';
	}
 }


function closed_sat(){
	if(document.frm_basic.sat_chk.checked == true){
		document.getElementById("sat_open").style.visibility = 'hidden';
		document.getElementById("sat_line").style.visibility = 'hidden';
		document.getElementById("sat_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("sat_open").style.visibility = 'visible';
		document.getElementById("sat_line").style.visibility = 'visible';
		document.getElementById("sat_close").style.visibility = 'visible';
	}
 }



function closed_sun(){
	if(document.frm_basic.sun_chk.checked == true){
		document.getElementById("sun_open").style.visibility = 'hidden';
		document.getElementById("sun_line").style.visibility = 'hidden';
		document.getElementById("sun_close").style.visibility = 'hidden';	
	}else{
		document.getElementById("sun_open").style.visibility = 'visible';
		document.getElementById("sun_line").style.visibility = 'visible';
		document.getElementById("sun_close").style.visibility = 'visible';
	}
 }
 
 
 function applyAll(){
	 document.getElementById('tue_open').selectedIndex = document.getElementById("mon_open").value;
	 document.getElementById('wed_open').selectedIndex = document.getElementById("mon_open").value;
	 document.getElementById('thru_open').selectedIndex = document.getElementById("mon_open").value;
	 document.getElementById('fri_open').selectedIndex = document.getElementById("mon_open").value;
	 document.getElementById('sat_open').selectedIndex = document.getElementById("mon_open").value;
	 document.getElementById('sun_open').selectedIndex = document.getElementById("mon_open").value;

	 document.getElementById('tue_close').selectedIndex = document.getElementById("mon_close").value;
	 document.getElementById('wed_close').selectedIndex = document.getElementById("mon_close").value;
	 document.getElementById('thru_close').selectedIndex = document.getElementById("mon_close").value;
	 document.getElementById('fri_close').selectedIndex = document.getElementById("mon_close").value;
	 document.getElementById('sat_close').selectedIndex = document.getElementById("mon_close").value;
	 document.getElementById('sun_close').selectedIndex = document.getElementById("mon_close").value;
}


function saveButton(){
	if(document.getElementById('mon_open').value > document.getElementById('mon_close').value){
		alert("Monday closing timeing not correct");
		return false;
	}
	
	if(document.getElementById('tue_open').value > document.getElementById('tue_close').value){
		alert("Tuesday closing timeing not correct");
		return false;
	}
	if(document.getElementById('wed_open').value > document.getElementById('wed_close').value){
		alert("Wednesday closing timeing not correct");
		return false;
	}
	if(document.getElementById('thru_open').value > document.getElementById('thru_close').value){
		alert("Thrusday closing timeing not correct");
		return false;
	}
	if(document.getElementById('fri_open').value > document.getElementById('fri_close').value){
		alert("Friday closing timeing not correct");
		return false;
	}
	if(document.getElementById('sat_open').value > document.getElementById('sat_close').value){
		alert("Saturday closing timeing not correct");
		return false;
	}
	if(document.getElementById('sun_open').value > document.getElementById('sun_close').value){
		alert("Sunday closing timeing not correct");
		return false;
	}
}
