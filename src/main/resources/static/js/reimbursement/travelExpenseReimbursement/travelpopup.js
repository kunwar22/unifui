$(document).ready(function() {

	var tdate = document.getElementsByClassName("tdate");
	for(var i=0;i<tdate.length;i++)
	{
		var dt = tdate[i].innerHTML;
		var p = dt.split(/\D/g)
		var finaldate = [p[2],p[1],p[0] ].join("/");
		tdate[i].innerHTML = finaldate;
	}
	});
	
	
	function travelDeclearation(claimid) {
    //debugger;
    window.open("../reimbursement/tourDeclaration/" + claimid, 'window', 'width=600,height=600');
};


