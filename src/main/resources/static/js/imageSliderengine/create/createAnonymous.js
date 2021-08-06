
alert("hi");

/******************************************************************************************************/


/*
function addDiv()
{
	 $("<div />", { "class":" w3-padding  w3-border wrapper", id:"product"+i })
var i = 1;
var newStatebind='';
alert(i);
newStatebind+='<div class="w3-padding  w3-border" id="product"+i><p><input id="deleteDiv" class="w3-button w3-theme w3-right" type="button" value="X" onclick="deleteDiv();" /></p><div class="w3-container "><p>Employee Id<input class="w3-input w3-border" name="deptName" id="CR_EMP_NAME" type="text"  style="width:350px" ></p></div><div class=w3-half><div class="w3-container"></div></div><div ><table id="EMPLOYEE_VALUE_TBL"class="display compact w3-table" style="width: 100%;"><thead><th style="width: 20%; text-align: center;">*Start Date</th><th style="width: 20%; text-align: center;">*End Date</th><th style="width: 10%; text-align: center;">Level 1</th><th style="width: 10%; text-align: center;">Level 2</th><th style="width: 10%; text-align: center;">Level 3</th><th style="width: 10%; text-align: center;">Level 4</th><th style="width: 10%; text-align: center;">Level 5</th></thead> <tbody><tr><td><input class="w3-input w3-border" name="startdate" type="date"/></td><td><input class="w3-input w3-border" name="startdate" type="date"/></td><td><input class="w3-input w3-border" name="level1" type="text"/></td><td><input class="w3-input w3-border" name="level1" type="text"/></td><td><input class="w3-input w3-border" name="level1" type="text"/></td><td><input class="w3-input w3-border" name="level1" type="text"/></td><td><input class="w3-input w3-border" name="level1" type="text"/></td></tr></tbody></table></div></div></div><br>';
$('#btn1').click(function() {
    $('#id01').append(newStatebind)
 
});
i++;
}


function deleteDiv()
{
	alert("del");
   $(this).parent().remove();
}
*/


/**************************************** */


var i = 0;

$("#addProduct").click(function() {
  $("<div />", { "class":" w3-padding  w3-border wrapper", id:"product"+i })
     

	 .append('<p><input id="deleteDiv'+i+'" name="delete" class="w3-button w3-theme w3-right" type="button" value="X" onclick="deletediv(this.id);" /></p><div class="w3-container "><p>Employee Id<input id="employeeId'+i+'" class="w3-input w3-border" name="EmpId" id="CR_EMP_NAME" type="text"  style="width:350px" ></p></div><div class=w3-half><div class="w3-container"></div></div><div ><table id="EMPLOYEE_VALUE_TBL"class="display compact w3-table" style="width: 100%;"><thead><th style="width: 20%; text-align: center;">*Start Date</th><th style="width: 20%; text-align: center;">*End Date</th><th style="width: 10%; text-align: center;">Level 1</th><th style="width: 10%; text-align: center;">Level 2</th><th style="width: 10%; text-align: center;">Level 3</th><th style="width: 10%; text-align: center;">Level 4</th><th style="width: 10%; text-align: center;">Level 5</th></thead> <tbody><tr><td><input id="startDate'+i+'" class="w3-input w3-border" name="startdate" type="date"/></td><td><input id="enddate'+i+'" class="w3-input w3-border" name="endDate" type="date"/></td><td><input id="level1'+i+'" class="w3-input w3-border" name="level1" type="text"/></td><td><input id="level2'+i+'" class="w3-input w3-border" name="level2" type="text"/></td><td><input id="level3'+i+'" class="w3-input w3-border" name="level3" type="text"/></td><td><input id="level4'+i+'" class="w3-input w3-border" name="level4" type="text"/></td><td><input id="level5'+i+'" class="w3-input w3-border" name="level5" type="text"/></td></tr></tbody></table></div></div>')
	 .append('<br>')
     .appendTo("#someContainer");
	 
  i++;

});



/*function deletediv(clicked_id)
  {
      alert(clicked_id);
  }*/
function deletediv(clicked_id)
  {
      alert(clicked_id);
	  $(this).parent().remove();
  }


/************************************* */


