var navItemsClicked = [];
var navItemsClickedFN = [];
var divArray = [];
var hoverColor = '';
var hoverColorFN = '';
var themeColor = '';
var backRefId = '';
var loginid = 'E00000000001';
var clickCount = 0;
var prevDivId = '';
var overlayBgNav = document.getElementById("myOverlayNav");

//alert("Hello UserHome");
$(document).on('click', 'b', function(){
	var clickedItemName = $(this).attr("ne");
	var clickedItemId = $(this).attr("id");
	var clickedItemParentFlag = $(this).attr("pf");
	var clickedItemRm = $(this).attr("rm");
	
	navItemsClicked.push({ItemId : clickedItemId, ItemName : clickedItemName, ParentFlag : clickedItemParentFlag});
	
	hoverColor = $(this).attr("class");
	hoverColorFN = $(this).attr("fn");
	themeColor = $(this).attr("theme");
	var parentId = this.id;
	var newNav = '';
	var breadcrumbContent = '';
	
	if(clickedItemParentFlag == 'N'){
		var i = navItemsClicked.length;
		$.each(navItemsClicked, function(o, p){
			if(i > 1){
				i = i-1;
				breadcrumbContent = breadcrumbContent + '<a>&nbsp;' +p.ItemName+ '<span id="bb" pf="' +p.ParentFlag+ '" iid="' +p.ItemId+ '" style="padding-left:8px; cursor: pointer;" class="fa fa-caret-right" aria-hidden="true"></span></a>';
			} else {
				breadcrumbContent = breadcrumbContent + '<a>&nbsp;' +p.ItemName+ '</a>';
			};
		});
		$('#breadcrumb').html(breadcrumbContent);
		navItemsClicked.pop();
		navItemsClickedFN = navItemsClicked;
		w3_close();
		
		// Load page fragment
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
		
	} else {
		clickCount = clickCount + 1;
		$.ajax({
			type : "GET",
			url : "/getchild/" + parentId + "/" + loginid,
			beforeSend : function(){
				$("#loader").show();
				$("#mySidebar").addClass("disabled");
			},
			success : function(result){
				result.forEach(function(n){
					if(n.isparent == 'Y'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" style="padding-top: 0px; padding-bottom: 0px;" fn="' +hoverColorFN+ '" theme="' +themeColor+ '" class="' +hoverColor+ '">' + '<p class="fa fa-folder" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
				});
				result.forEach(function(n){
					if(n.isparent == 'N'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" rm="' + n.requestaddr + '" style="padding-top: 0px; padding-bottom: 0px;" fn="' +hoverColorFN+ '" class="' +hoverColor+ '">' + '<p class="fa fa-dot-circle-o" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
				});
				$('#navitemdiv').html(newNav);
				//alert(navItemsClicked.length);
			},
			complete: function(){
				$("#loader").hide();
				$("#mySidebar").removeClass("disabled");
			},
			error : function(e){
				alert(e);
			}
		});
	};	
});

$(document).on('click', '#gotoroot', function(){
	navItemsClicked = [];
	backRefId = '';
	var newNav = '';
	
	if(clickCount >= 1){
		clickCount = 0;
		$.ajax({
			type : "GET",
			url : "/getrootnav/" + loginid,
			beforeSend : function(){
				$("#loader").show();
				$("#mySidebar").addClass("disabled");
			},
			success : function(result){
				result.forEach(function(n){
					if(n.isparent == 'Y'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" style="padding-top: 0px; padding-bottom: 0px;" class="' +hoverColor+ '">' + '<p class="fa fa-folder" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
				});
				result.forEach(function(n){
					if(n.isparent == 'N'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" style="padding-top: 0px; padding-bottom: 0px;" class="' +hoverColor+ '">' + '<p class="fa fa-dot-circle-o" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
					
				});
				$('#navitemdiv').html(newNav);
			},
			complete: function(){
				
				$("#loader").hide();
				$("#mySidebar").removeClass("disabled");
			},
			error : function(e){
				alert(e);
			}
		});
	};
});

$(document).on('click', '#goback', function(){
	var newNav = '';
	navItemsClicked.pop();
	
	if(clickCount >= 1){
		clickCount = clickCount - 1;
		$.ajax({
			type : "GET",
			url : "/getparentnav/" + backRefId + "/" + loginid,
			beforeSend : function(){
				$("#loader").show();
				$("#mySidebar").addClass("disabled");
			},
			success : function(result){
				result.forEach(function(n){
					if(n.isparent == 'Y'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" style="padding-top: 0px; padding-bottom: 0px;" class="' +hoverColor+ '">' + '<p class="fa fa-folder" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
				});
				result.forEach(function(n){
					if(n.isparent == 'N'){
						newNav = newNav + '<b id="' + n.navbarid + '" ne="' + n.navname + '" pf="' + n.isparent + '" style="padding-top: 0px; padding-bottom: 0px;" class="' +hoverColor+ '">' + '<p class="fa fa-dot-circle-o" style="font-size:15px;">&nbsp;&nbsp;'+ n.navname +'</p></b>';
					}
					backRefId = n.navbarid;
				});
				$('#navitemdiv').html(newNav);
				//alert(navItemsClicked.length);
			},
			complete: function(){
				$("#loader").hide();
				$("#mySidebar").removeClass("disabled");
			},
			error : function(e){
				alert(e);
			}
		});
	};
});

$(document).on('click', '#bb', function(e){
	var clickedCaretNavId = $(this).attr("iid");
	var clickedCaretPFlag = $(this).attr("pf");
	var clickedDivId = $(this).parent().parent().parent().attr("id");
	var clickedLiId = $(this).parent().attr("id");
	var counter = 0;
	var liId = '';
	
	if(clickedDivId == undefined){
		divArray = [];
		$("#rootFloatingNav").html("");
	}
	if(divArray.length > 0){
		if(divArray[divArray.length - 2] == clickedDivId){
			$('#'+divArray[divArray.length - 1]).remove();
			divArray.pop();
			counter = 0;
		}
		var noOfPops = divArray.length - (divArray.indexOf(clickedDivId) + 1);
		//alert(noOfPops);
		if(noOfPops > 0){
			for(var i = 1; i <= noOfPops; i++){
				$('#'+divArray[divArray.length - 1]).remove();
				divArray.pop();
				counter = 0;
			}
		}
	}
	
	var divId = 'div' + divArray.length;
	//var liId = 'li' + divId + '_' + counter;
	var floatingNav = '<div id="'+divId+'" class="w3-theme-l5 w3-card" style="position: absolute; display: none; width: 320px; border: 1px solid #ddd;"><ul class="w3-ul">';
	$.ajax({
		type : "GET",
		url : "/getchild/" + clickedCaretNavId + "/" + loginid,
		success : function(result){
			result.forEach(function(n){
				liId = 'li' + divId + '_' + counter;
				if(n.isparent == 'Y'){
					floatingNav = floatingNav + '<li id="'+liId+'" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;" class="' +hoverColorFN+ '" onclick="getChilds(this.id)">';
					floatingNav = floatingNav + '<span id="fn" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;"  class="fa fa-caret-right w3-right"></span>' + n.navname + '</li>';
				}
				counter = counter + 1;
			});
			result.forEach(function(n){
				liId = 'li' + divId + '_' + counter;
				if(n.isparent == 'N'){
					floatingNav = floatingNav + '<li id="'+liId+'" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;" class="' +hoverColorFN+ '">' + n.navname + '</li>';
				}
				counter = counter + 1;
			});
			floatingNav = floatingNav + '</ul></div>';
			$("#rootFloatingNav").append(floatingNav);
			if(clickedDivId == undefined){
				$("#"+divId).css('top', "76px");
				$("#"+divId).css('left', e.pageX);
			} else {
				//alert(clickedLiId);
				var offset = $("#"+clickedLiId).offset();
				//alert(offset.top);
				//alert(offset.left);
				$("#"+divId).css('top', offset.top + "px");
				$("#"+divId).css('left', (offset.left + 315) + "px");
			}
			
			$("#"+divId).css('display', "block");
			divArray.push(divId);
		},
		error : function(e){
			alert(e);
		}
	});
});

function getChilds(id){
	var clickedLiId = id;
	var clickedLiPFlag = $('#'+clickedLiId).attr("pf");
	var clickedLiDivId = $('#'+clickedLiId).parent().parent().attr("id");
	var clickedLiNavId = $('#'+clickedLiId).attr("iid");
	var clickedLiName = $('#'+clickedLiId).clone().children().remove().end().text();
	//alert(clickedLiName);
	var counter = 0;
	var liId = '';
	
	if(clickedLiPFlag == "Y"){
		if(clickedLiDivId == undefined){
			divArray = [];
			$("#rootFloatingNav").html("");
		}
		if(divArray.length > 0){
			if(divArray[divArray.length - 2] == clickedLiDivId){
				$('#'+divArray[divArray.length - 1]).remove();
				divArray.pop();
			}
			var noOfPops = divArray.length - (divArray.indexOf(clickedLiDivId) + 1);
			//alert(noOfPops);
			if(noOfPops > 0){
				for(var i = 1; i <= noOfPops; i++){
					$('#'+divArray[divArray.length - 1]).remove();
					divArray.pop();
				}
			}
		}
		
		
		
		var divId = 'div' + divArray.length;
		//var liId = 'li' + divId + '_' + counter;
		var floatingNav = '<div id="'+divId+'" class="w3-theme-l5 w3-card" style="position: absolute; display: none; width: 320px; border: 1px solid #ddd;"><ul class="w3-ul">';
		$.ajax({
			type : "GET",
			url : "/getchild/" + clickedLiNavId + "/" + loginid,
			success : function(result){
				result.forEach(function(n){
					liId = 'li' + divId + '_' + counter;
					if(n.isparent == 'Y'){
						floatingNav = floatingNav + '<li id="'+liId+'" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;" class="' +hoverColorFN+ '" onclick="getChilds(this.id)">';
						floatingNav = floatingNav + '<span id="fn" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;"  class="fa fa-caret-right w3-right"></span>' + n.navname + '</li>';
					}
					counter = counter + 1;
				});
				result.forEach(function(n){
					liId = 'li' + divId + '_' + counter;
					if(n.isparent == 'N'){
						floatingNav = floatingNav + '<li id="'+liId+'" pf="' +n.isparent+ '" iid="' +n.navbarid+ '" style="cursor: pointer;" class="' +hoverColorFN+ '" onclick="getChilds(this.id)"><span></span>' + n.navname + '</li>';
					}
					counter = counter + 1;
				});
				floatingNav = floatingNav + '</ul></div>';
				$("#rootFloatingNav").append(floatingNav);
				if(clickedLiDivId == undefined){
					$("#"+divId).css('top', "76px");
					$("#"+divId).css('left', e.pageX);
				} else {
					//alert(clickedLiId);
					var offset = $("#"+clickedLiId).offset();
					//alert(offset.top);
					//alert(offset.left);
					$("#"+divId).css('top', offset.top + "px");
					$("#"+divId).css('left', (offset.left + 315) + "px");
				}
				
				$("#"+divId).css('display', "block");
				divArray.push(divId);
			},
			error : function(e){
				alert(e);
			}
		});
	} else {
		var i = navItemsClickedFN.length;
		$.each(navItemsClickedFN, function(k, v){
			//alert(v.ItemName+ '...' +v.ParentFlag+ '...' +v.ItemId);
		});
	}
}



function nav_close(){
	if(divArray.length > 0){
		divArray = [];
		counter = 0;
		$("#rootFloatingNav").html("");
	}
	overlayBgNav.style.display = "none";
}
function nav_open(){
	overlayBgNav.style.display = "block";
}
//Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");


// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}

function go_home() {
	var url = "page/dashbordFregment";
	$('#replace_div').load(url);
}

$('#personalDetails').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#employmentInfo').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#resignation').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#publicInfo').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#contactInfo').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#docrecord').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#addabsence').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
