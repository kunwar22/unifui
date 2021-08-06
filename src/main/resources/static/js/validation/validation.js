
/*******************FUNCTION FOR REFERENCE DATASET VALIDATION*****************/

function validateDataset(dscode,dsname){
	$('#CODE_ERROR').text("");
	$('#NAME_ERROR').text("");
	if(dscode===undefined || dscode===""){
		$('#CODE_ERROR').text("Code can not be empty.");
		return false;
	}
	if(dsname===undefined || dsname===""){
		$('#NAME_ERROR').text("Name can not be empty.");
		return false;
	}
	return true;
}
/*******************FUNCTION FOR REFERENCE DATASET VALIDATION END*****************/


/*******************FUNCTION FOR LOCATION VALIDATION*****************/

function validateLocation(LOC_EFFDT,LOC_NAME,LOC_CODE,LOC_COUNTRY,LOC_STATUS,LOC_ADDRESS1,TELE,PIN,EMAIL,CITY){
$('#ADDR_ERROR').text("");
$('#COUNTRY_ERROR').text("");
$('#STATUS_ERROR').text("");
$('#CODE_ERROR').text("");
$('#NAME_ERROR').text("");
$('#EFFDT_ERROR').text("");
$('#TELE_ERROR').text("");
$('#CR_PIN_ERROR').text("");
$('#CR_MAIL_ERROR').text("");
$('#CR_CITY_ERROR').text("");


LOC_ADDRESS1=LOC_ADDRESS1.trim();
	
	
	if(LOC_EFFDT===undefined || LOC_EFFDT===""){
		$('#EFFDT_ERROR').text("Please select Date.");
		return false;
	}
	
	if(LOC_NAME===undefined || LOC_NAME==="" || LOC_NAME.length>250){
		
		if(LOC_NAME.length>250){
			$('#NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	
	if(LOC_CODE===undefined || LOC_CODE==="" || LOC_CODE.length>250){
		
		if(LOC_CODE.length>250){
			$('#CODE_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#CODE_ERROR').text("Please enter the Code.");
			return false;
		}
	}
	
	if(LOC_STATUS===undefined || LOC_STATUS===""){
		$('#STATUS_ERROR').text("Please select Status.");
		return false;
	}
	if(LOC_COUNTRY===undefined || LOC_COUNTRY===""){
		$('#COUNTRY_ERROR').text("Please select Country.");
		return false;
	}
		
	if(LOC_ADDRESS1===undefined || LOC_ADDRESS1==="" || LOC_ADDRESS1.length>250){
		
		if(LOC_ADDRESS1.length>250){
			$('#ADDR_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#ADDR_ERROR').text("Please enter the Address.");
			return false;
		}
	}
	
	if(EMAIL!=undefined && EMAIL!="" && EMAIL!=null){
		if(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(EMAIL)== false){
			$('#CR_MAIL_ERROR').text("Please enter the valid Email.");
			return false;
		}
	}
	if(/^[0-9]{10}$/.test(TELE)== false){
		$('#TELE_ERROR').text("Please enter the valid Telephone.");
		return false;
	}
	if(CITY!=undefined && CITY!="" && CITY!=null){
		if(/^[A-Za-z]+$/.test(CITY)== false){
			$('#CR_CITY_ERROR').text("Please enter the valid City.");
			return false;
		}
	}
	
	if(/^[1-9]{1}[0-9]{5}$/.test(PIN)== false){
		$('#CR_PIN_ERROR').text("Please enter valid Pincode");
		return false;
	}
	return true;
}
/*******************FUNCTION FOR LOCATION VALIDATION END*****************/

/*******************FUNCTION FOR ENTERPRISE VALIDATION*****************/
function validateEnterprise(ENT_EFFDT,ENT_NAME,ENT_LOC,ENT_STATUS){
	$('#ENT_STATUS_ERROR').text("");
	$('#ENT_LOC_ERROR').text("");
	$('#ENT_NAME_ERROR').text("");
	$('#ENT_EFFDT_ERROR').text("");

	if(ENT_EFFDT===undefined || ENT_EFFDT===""){
		$('#ENT_EFFDT_ERROR').text("Please select Date.");
		return false;
	}
	
	if(ENT_NAME===undefined || ENT_NAME==="" || ENT_NAME.length>250){
		
		if(ENT_NAME.length>250){
			$('#ENT_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#ENT_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	
	
	if(ENT_LOC===undefined || ENT_LOC==="" || ENT_LOC==null ){
		
		$('#ENT_LOC_ERROR').text("Please select Location.");
		return false;
	}
	if(ENT_STATUS===undefined || ENT_STATUS==="" || ENT_STATUS==null){
		
		$('#ENT_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	
	return true;
}

/*******************FUNCTION FOR ENTERPRISE VALIDATION END*****************/

/*******************FUNCTION FOR BUSINESS UNIT VALIDATION*****************/
function validateBusinessUnit(BU_EFFDT,BU_NAME,BU_CODE,BU_DATASET,BU_STATUS){
	$('#BU_DATASET_ERROR').text("");
	$('#BU_STATUS_ERROR').text("");
	$('#BU_CODE_ERROR').text("");
	$('#BU_NAME_ERROR').text("");
	$('#BU_EFFDT_ERROR').text("");

	if(BU_EFFDT===undefined || BU_EFFDT===""){
		$('#BU_EFFDT_ERROR').text("Please select Date.");
		return false;
	}
	
	if(BU_NAME===undefined || BU_NAME==="" || BU_NAME.length>250){
		
		if(BU_NAME.length>250){
			$('#BU_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#BU_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	if(BU_CODE===undefined || BU_CODE==="" || BU_CODE.length>250){
		
		if(BU_CODE.length>250){
			$('#BU_CODE_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#BU_CODE_ERROR').text("Please enter the Code.");
			return false;
		}
	}
	
	
	if(BU_DATASET===undefined || BU_DATASET==="" || BU_DATASET==null ){
		
		$('#BU_DATASET_ERROR').text("Please select Business Unit Set.");
		return false;
	}
	if(BU_STATUS===undefined || BU_STATUS==="" || BU_STATUS==null){
		
		$('#BU_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	
	return true;
}

/*******************FUNCTION FOR BUSINESS UNIT END*****************/



/*******************FUNCTION FOR LEGAL ADDRESS VALIDATION*****************/
function validateLegalAddress(LA_ADDR,LA_PIN,LA_COUNTRY,LA_STATUS,LA_CITY){

	$('#LA_PIN_ERROR').text("");
	$('#LA_STATUS_ERROR').text("");
	$('#LA_COUNTRY_ERROR').text("");
	$('#LA_ADDR_ERROR').text("");
	$('#LA_CITY_ERROR').text("");


	
	if(LA_ADDR===undefined || LA_ADDR==="" || LA_ADDR.length>250){
		
		if(LA_ADDR.length>250){
			$('#LA_ADDR_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LA_ADDR_ERROR').text("Please fill Address Line 1.");
			return false;
		}
	}
	if(LA_STATUS===undefined || LA_STATUS==="" || LA_STATUS==null){
		
		$('#LA_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	if(LA_CITY!=undefined && LA_CITY!="" && LA_CITY!=null){
		if(/^[A-Za-z]+$/.test(LA_CITY)== false){
			$('#LA_CITY_ERROR').text("Please enter the valid City.");
			return false;
		}
	}
	
	if(LA_PIN===undefined || LA_PIN==="" || /^[1-9]{1}[0-9]{5}$/.test(LA_PIN)== false){
		
		if(LA_PIN===undefined || LA_PIN==="" ){
			$('#LA_PIN_ERROR').text("Please enter the Pincode.");
			return false;
		}
		else{
			$('#LA_PIN_ERROR').text("Please enter valid Pincode");
			return false;
		}
	}
	
	
	if(LA_COUNTRY===undefined || LA_COUNTRY==="" || LA_COUNTRY==null ){
		
		$('#LA_COUNTRY_ERROR').text("Please select Country.");
		return false;
	}

	
	return true;
}

/*******************FUNCTION FOR LEGAL ADDRESS END*****************/




/*******************FUNCTION FOR DEPARTMENT VALIDATION*****************/
function validateDepartment(DEPT_NAME,DEPT_CODE,DEPT_DATASET,DEPT_EFFDT,DEPT_STATUS){


	$('#DEPT_STATUS_ERROR').text("");
	$('#DEPT_NAME_ERROR').text("");
	$('#DEPT_DATASET_ERROR').text("");
	$('#DEPT_CODE_ERROR').text("");
	$('#DEPT_EFFDT_ERROR').text("");

	if(DEPT_EFFDT===undefined || DEPT_EFFDT==="" || DEPT_EFFDT==null){
		
		$('#DEPT_EFFDT_ERROR').text("Please select Effective Start Date.");
		return false;
	}
	
	if(DEPT_NAME===undefined || DEPT_NAME==="" || DEPT_NAME.length>250){
		
		if(DEPT_NAME.length>250){
			$('#DEPT_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#DEPT_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	
	
	if(DEPT_CODE===undefined || DEPT_CODE==="" || DEPT_CODE.length>250){
		
		if(DEPT_CODE.length>250){
			$('#DEPT_CODE_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#DEPT_CODE_ERROR').text("Please enter the Code.");
			return false;
		}
	}
	if(DEPT_STATUS===undefined || DEPT_STATUS==="" || DEPT_STATUS==null){
		
		$('#DEPT_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	
	
	if(DEPT_DATASET===undefined || DEPT_DATASET==="" || DEPT_DATASET==null ){
		
		$('#DEPT_DATASET_ERROR').text("Please select Business Unit Set.");
		return false;
	}

	
	return true;
}

/*******************FUNCTION FOR DEPARTMENT END*****************/



/*******************FUNCTION FOR JOB VALIDATION*****************/
function validateJob(JOB_NAME,JOB_EFFDT,JOB_FUNC_ID,JOB_CODE,JOB_DATASET,JOB_STATUS){


	$('#JOB_FUNCTION_ERROR').text("");
	$('#JOB_NAME_ERROR').text("");
	$('#JOB_CODE_ERROR').text("");
	$('#JOB_STATUS_ERROR').text("");
	$('#JOB_BU_ERROR').text("");
	$('#JOB_EFFDT_ERROR').text("");
	
	if(JOB_FUNC_ID===undefined || JOB_FUNC_ID==="" || JOB_FUNC_ID==null){
		
		$('#JOB_FUNCTION_ERROR').text("Please select Job Function.");
		return false;
	}
	
	
	
	if(JOB_NAME===undefined || JOB_NAME==="" || JOB_NAME.length>250){
		
		if(JOB_NAME.length>250){
			$('#JOB_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#JOB_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	
	
	if(JOB_CODE===undefined || JOB_CODE==="" || JOB_CODE.length>250){
		
		if(JOB_CODE.length>250){
			$('#JOB_CODE_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#JOB_CODE_ERROR').text("Please enter the Code.");
			return false;
		}
	}
	if(JOB_STATUS===undefined || JOB_STATUS==="" || JOB_STATUS==null){
		
		$('#JOB_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	
	
	if(JOB_DATASET===undefined || JOB_DATASET==="" || JOB_DATASET==null ){
		
		$('#JOB_BU_ERROR').text("Please select Business Unit Set.");
		return false;
	}
	if(JOB_EFFDT===undefined || JOB_EFFDT==="" || JOB_EFFDT==null){
		
		$('#JOB_EFFDT_ERROR').text("Please select Effective Start Date.");
		return false;
	}

	
	return true;
}

/*******************FUNCTION FOR JOB END*****************/


/*******************FUNCTION FOR JURISDICTION VALIDATION*****************/
function validateJurisdiction(JUDIS_EFFDT,JUDIS_LEGISLATIVE_CODE,JUDIS_LEG_CATOG,JUDIS_COUNTRY,JUDIS_NAME,JUDIS_STATUS,JUDIS_IDENTIFYING){
	
	$('#JUR_IDENTIFYING_ERROR').text("");
	$('#JUR_NAME_ERROR').text("");
	$('#JUR_LEGISLATIVE_CAT_ERROR').text("");
	$('#JUR_STATUS_ERROR').text("");
	$('#JUR_COUNTRY_ERROR').text("");
	$('#JUR_ENTITY_REGN_ERROR').text("");
	$('#JUR_EFFDT_ERROR').text("");
	
	if(JUDIS_EFFDT===undefined || JUDIS_EFFDT==="" || JUDIS_EFFDT==null){
		
		$('#JUR_EFFDT_ERROR').text("Please select Effective Start Date.");
		return false;
	}
	
	if(JUDIS_LEGISLATIVE_CODE===undefined || JUDIS_LEGISLATIVE_CODE==="" || JUDIS_LEGISLATIVE_CODE==null){
		
		$('#JUR_ENTITY_REGN_ERROR').text("Please select Legislative Entity Registration Code.");
		return false;
	}
	if(JUDIS_LEG_CATOG===undefined || JUDIS_LEG_CATOG==="" || JUDIS_LEG_CATOG==null){
		
		$('#JUR_LEGISLATIVE_CAT_ERROR').text("Please select Legislative Category.");
		return false;
	}
	if(JUDIS_COUNTRY===undefined || JUDIS_COUNTRY==="" || JUDIS_COUNTRY==null){
		
		$('#JUR_COUNTRY_ERROR').text("Please select Country.");
		return false;
	}
	
	
	
	if(JUDIS_NAME===undefined || JUDIS_NAME==="" || JUDIS_NAME.length>250){
		
		if(JUDIS_NAME.length>250){
			$('#JUR_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#JUR_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	
	
	if(JUDIS_STATUS===undefined || JUDIS_STATUS==="" || JUDIS_STATUS==null){
		
		$('#JUR_STATUS_ERROR').text("Please select Status.");
		return false;
	}
	if(JUDIS_IDENTIFYING===undefined || JUDIS_IDENTIFYING==="" || JUDIS_IDENTIFYING==null){
		
		$('#JUR_IDENTIFYING_ERROR').text("Please select Identifying.");
		return false;
	}
	
	return true;
}

/*******************FUNCTION FOR JURISDICTION END*****************/

/*******************FUNCTION FOR LEGAL ENTITY VALIDATION*****************/
function validateLegal(LE_COUNTRY,LE_NAME,LE_ADDRESS,LE_IDNTI,LE_PLCREG,LE_TIN_PAN,LE_IT_TAN_NUMBER,LE_IT_TAN_CIRCLE,CR_LE_EFFDT){
	
	$('#LEG_TAN_C_ERROR').text("");
	$('#LEG_TAN_NO_ERROR').text("");
	$('#LEG_TINPAN_ERROR').text("");
	$('#LEG_ADDR_ERROR').text("");
	$('#LEG_REGNPLACE_ERROR').text("");
	$('#LEG_IDENTIFIER_ERROR').text("");
	$('#LEG_NAME_ERROR').text("");
	$('#LEG_COUNTRY_ERROR').text("");
	$('#LEG_EFFDT_ERROR').text("");
	
	if(CR_LE_EFFDT===undefined || CR_LE_EFFDT==="" || CR_LE_EFFDT==null){
		
		$('#LEG_EFFDT_ERROR').text("Please select Effective Start Date.");
		return false;
	}
	if(LE_COUNTRY===undefined || LE_COUNTRY==="" || LE_COUNTRY==null){
		
		$('#LEG_COUNTRY_ERROR').text("Please select Country.");
		return false;
	}
	if(LE_NAME===undefined || LE_NAME==="" || LE_NAME.length>250){
		
		if(LE_NAME.length>250){
			$('#LEG_NAME_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LEG_NAME_ERROR').text("Please enter the Name.");
			return false;
		}
	}
	if(LE_ADDRESS===undefined || LE_ADDRESS==="" || LE_ADDRESS==null){
		
		$('#LEG_ADDR_ERROR').text("Please select Legal Address.");
		return false;
	}
	if(LE_IDNTI===undefined || LE_IDNTI==="" || LE_IDNTI.length>250){
		
		if(LE_IDNTI.length>250){
			$('#LEG_IDENTIFIER_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LEG_IDENTIFIER_ERROR').text("Please enter the Legal Entity Identifier.");
			return false;
		}
	}
	if(LE_PLCREG===undefined || LE_PLCREG==="" || LE_PLCREG.length>250){
		
		if(LE_PLCREG.length>250){
			$('#LEG_REGNPLACE_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LEG_REGNPLACE_ERROR').text("Please enter the Registration Place.");
			return false;
		}
	}
	if(LE_TIN_PAN===undefined || LE_TIN_PAN==="" || LE_TIN_PAN.length>250){
		openTab(event,'IT_INFO');
		$('#IT_BTN').addClass("w3-theme");
		if(LE_TIN_PAN.length>250){
			$('#LEG_TINPAN_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LEG_TINPAN_ERROR').text("Please enter the TIN/PAN Number.");
			return false;
		}
	}
	if(LE_IT_TAN_NUMBER===undefined || LE_IT_TAN_NUMBER==="" || /^[A-Z]{4}[0-9]{5}[A-Z]{1}$/.test(LE_IT_TAN_NUMBER)==false){
		openTab(event,'IT_INFO');
		$('#IT_BTN').addClass("w3-theme");
		
		if(LE_IT_TAN_NUMBER===undefined || LE_IT_TAN_NUMBER===""){
			$('#LEG_TAN_NO_ERROR').text("Please enter the TAN number.");
			return false;
		}
		else{
			$('#LEG_TAN_NO_ERROR').text("Please enter the valid TAN number");
			return false;
		}
	}
	if(LE_IT_TAN_CIRCLE===undefined || LE_IT_TAN_CIRCLE==="" || LE_IT_TAN_CIRCLE.length>250){
		openTab(event,'IT_INFO');
		$('#IT_BTN').addClass("w3-theme");
		if(LE_IT_TAN_CIRCLE.length>250){
			$('#LEG_TAN_C_ERROR').text("Length should not exceed 250 characters.");
			return false;
		}
		else{
			$('#LEG_TAN_C_ERROR').text("Please enter the TAN Circle.");
			return false;
		}
	}
		
	return true;
}

/*******************FUNCTION FOR LEGAL ENTITY END*****************/