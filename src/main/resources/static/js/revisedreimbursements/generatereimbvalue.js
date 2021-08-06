
function setborder(aid,bid){
    $("#"+aid).css('border-bottom','4px solid #23de01');
    $("#"+bid).css('border-bottom','none');
    BTN_01
    BTN_02
    generatediv
    if(aid=='BTN_01'){
        var url = '/revisedreimbursements/managegeneratetab/GEN';
    }else if(aid='BTN_02'){
        var url = '/revisedreimbursements/managehistorytab';
    }
    $('#generatediv').load(url);
}