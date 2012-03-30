
function checkLocationForm(){
	var name_en = $("#name_en").val();
	var name_cn = $("#name_cn").val();
	var address_en = $("#address_en").val();
	var address_cn = $("#address_cn").val();
	var latitude = $("#latitude").val();
	var longitude = $("#longitude").val();
	var categoryid = $("#category_id").val();
	if(!name_en){
		alert("name_en can't be null");
		return false;
	}
	if(!name_cn){
		alert("name_cn can't be null");
		return false;
	}
	if(!address_en){
		alert("address_en can't be null");
		return false;
	}
	if(!address_cn){
		alert("address_cn can't be null");
		return false;
	}
	if(!latitude){
		alert("latitude can't be null");
		return false;
	}else{
		latitude = latitude - 0.0038935;
		$("#latitude").val(latitude);
	}
	if(!longitude){
		alert("longitude can't be null");
		return false;
	}else{
		longitude = longitude - 0.0107175;
		$("#longitude").val(longitude);
	}
	if(0 == categoryid){
		alert("please choose the category");
		return false;
	}
	return true;
}

//PAGE LOAD
$(document).ready(function() {
	var requestUrl ="/category/getCategory1";
    $.getJSON(requestUrl,
    function(data) {
    	data = eval(data.firstCategory)
    	var appendHtml = "";
    	appendHtml +="<option value=\"0\">---请选择---</option>" 
		$.each(data, function (i, o) {
			appendHtml +="<option value=\""+o.id+"\">"+o.name_cn+"</option>";
		});
     //alert(appendHtml);
    	$("#category1").append(appendHtml);
   
    });

});


function onChangeCategory1(){
	var val = document.form1.category1.value;
    if(val == 0){
       addOneRowCity();
       return;
    }
    $.getJSON("/category/getCategory2",                          
        {
            action: "rand="+Math.random(),                   
            category1_id: val
        },
        function(json) { 
        	json = eval(json.secondCategory)
         if(json != "" && json != null){
        	 addRowCategory2(json);       
           }else{ 
        	 addOneRow();
           }
         }
    );
}

function addOneRow(){
    $("#category_id").empty();
    var appendHtml = "";
    appendHtml+="<option value=\"0\">---请选择---</option>"
    $("#category_id").append(appendHtml);
 }
/*
function addRowCategory2(data){
    $("#category_id").empty();
     var appendHtml = "";
         appendHtml +="<option value=\"0\">---请选择---</option>" 
    	 $.each(data, function (i, o) {
 			appendHtml +="<option value=\""+o.id+"\">"+o.name_cn+"</option>";
 		});
   	$("#category_id").append(appendHtml);
 }
 */

function addRowCategory2(data){
    $("#category2").empty();
     var appendHtml = "";
    	 $.each(data, function (i, o) {
 			appendHtml +="<input type='checkbox' name='category_id' value=\""+o.id+"\" />"+o.name_cn;
 		});
   	$("#category2").append(appendHtml);
 }


	
	
