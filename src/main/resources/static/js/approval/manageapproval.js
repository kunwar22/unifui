function apr_name(_id) {
    //alert(event.toElement.id);
    var url = "/approvalsetup/manageapprovalsetup/" + _id;
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
}