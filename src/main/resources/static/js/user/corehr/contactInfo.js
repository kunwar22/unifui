
var drop_disp=false;

function myFunction(){
	if(drop_disp==false){
		$('#myDropdown').css("display","block");
		drop_disp=true;
	}else if(drop_disp==true){
		$('#myDropdown').css("display","none");
		drop_disp=false;
	}
}

window.onclick = function(event) {
  if (!event.target.matches('#COMM_ADD_BTN')) {
    $('#myDropdown').css("display","none");
	drop_disp=false;
    }
  }
