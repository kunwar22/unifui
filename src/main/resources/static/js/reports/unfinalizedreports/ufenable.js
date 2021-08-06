function enbledisble(id) {
   //debugger;

    $("#btnexportpayregmod").css("display", "none");
    $("#btnexportpaymod3").css("display", "none");
    $("#btnexportpaymod3pdf").css("display", "none");
    //$("#btnexportsalbill").css("display", "none");
    $("#btnexportsalbillpdf").css("display", "none");
    $("#btnexportempmod").css("display", "none");
    $("#btnexportempperexcel").css("display", "none");
    $("#btnexportbankmod").css("display", "none");
    $("#btnexportbankpdf").css("display", "none");
    $("#btnufexportsalcard").css("display", "none");
	$("#btnufexportsalcardpdf").css("display", "none");
    $("#btnexportpayrollregister").css("display", "none");	
    $("#btnexportLWPReportxls").css("display", "none");
    $("#btnexportLWPReportpdf").css("display", "none");
    $("#btnexportbirthdayexcel").css("display", "none");
    $("#btnexportbirthdaypdf").css("display", "none");
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#btnexportepfreport").css("display", "none");
    $("#btnexportnpsreport").css("display", "none");
    $("#btnexportpayslip").css("display", "none");
    $("#btnexportvendordet").css("display", "none");
	$("#btnexportcurrentpayrollregister").css("display", "none");
	$("#btnexportpayregmod_3_uf").css("display", "none");
	$("#btnexportpayregmodxl_static").css("display", "none");
	$("#btnexportpayregmodpdf_static").css("display", "none");
	
    $("#txtmsg").css("display", "none");
    $("#txtbdmsg").css("display", "none");
    report_type = $(id).val();
    if (report_type != null) {
        $("#rterr").css("display", "none");
    }
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");
    $("#vendorerr").css("display", "none");
    $("#mntherr").css("display", "none");
	$("#pernoerr").css("display", "none");


    if (report_type == 1) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
		$("#staticcalcode").val("");
        $("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 2) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
		$("#staticcalcode").val("");
        $("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 3) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#natstar").css("display", "none");
        $("#empnoblock").css("display", "none");
		$("#pnostar").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 4) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "block");
		$("#pnostar").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 5) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "none");
        $("#paygrpblock").css("display", "none");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 6) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "none");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 7) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "block");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "block");
		$("#pnostar").css("display", "inline");
		$("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
         tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
             tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 8) {
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#birthmonth").css("display", "block");
        $("#paygrpblock").css("display", "none");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("BIRTHDAY_REPORT_TAB").style.display = "block";
         $(".birthday").addClass("w3-theme");
    } else if (report_type == 9) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 10) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "none");
        $("#paygrpblock").css("display", "none");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "block");
        $("#todatelwp").css("display", "block");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 11) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
    } else if (report_type == 12) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
    } else if (report_type == 13) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
    } else if (report_type == 14 || report_type == 15) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#staticcalcblock").css("display", "none");
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");

        $("#tocalcode").val("");

        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");

        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 16) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
		$("#staticcalcblock").css("display", "none");
        $("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "block");
		$("#pnostar").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "none");
        $("#vendormonth").val("");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    } else if (report_type == 17) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
		$("#staticcalcblock").css("display", "none");
        $("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    }
else if (report_type == 18) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
		$("#staticcalcblock").css("display", "block");
        $("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "block");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    }
else if (report_type == 19) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
		$("#staticcalcblock").css("display", "block");
        $("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    }
else if (report_type == 20) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
		$("#staticcalcblock").css("display", "block");
        $("#fromcalcblock").css("display", "none");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#natstar").css("display", "inline");
        $("#empnoblock").css("display", "none");
        $("#fromdatelwp").css("display", "none");
        $("#todatelwp").css("display", "none");
        $("#banktypeblock").css("display", "none");
        $("#vendorblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#staticcalcode").val("");
		$("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#fdt").val("");
        $("#tdt").val("");
        $("#banktype").val("");
        x = document.getElementsByClassName("rightpane");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
        }
        document.getElementById("PAYROLL_REPORT_TAB").style.display = "block";
        $(".payroll").addClass("w3-theme");
    }
}

function disablebtn() {
   //debugger;

    $("#personno").val("");
    $("#btnexportpayregmod").css("display", "none");
    $("#btnexportpaymod3").css("display", "none");
    $("#btnexportpaymod3pdf").css("display", "none");
    //$("#btnexportsalbill").css("display", "none");
    $("#btnexportsalbillpdf").css("display", "none");
    $("#btnexportempmod").css("display", "none");
    $("#btnexportempperexcel").css("display", "none");
    $("#btnexportbankmod").css("display", "none");
    $("#btnexportbankpdf").css("display", "none");
    $("#btnufexportsalcard").css("display", "none");
	$("#btnufexportsalcardpdf").css("display", "none");
    $("#btnexportpayrollregister").css("display", "none");
    $("#btnexportLWPReportxls").css("display", "none");
    $("#btnexportLWPReportpdf").css("display", "none");
    $("#btnexportbirthdayexcel").css("display", "none");
    $("#btnexportbirthdaypdf").css("display", "none");
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#btnexportepfreport").css("display", "none");
    $("#btnexportnpsreport").css("display", "none");
    $("#btnexportpayslip").css("display", "none");
    $("#btnexportvendordet").css("display", "none");
	$("#btnexportcurrentpayrollregister").css("display", "none");
	$("#btnexportpayregmod_3_uf").css("display", "none");
	$("#btnexportpayregmodxl_static").css("display", "none");
	$("#btnexportpayregmodpdf_static").css("display", "none");
    $("#txtmsg").css("display", "none");
    $("#txtbdmsg").css("display", "none");

    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");
    $("#vendorerr").css("display", "none");
    $("#mntherr").css("display", "none");
}

/* code for enabling export buttons starts */
function enable_export_btn(btn) {
   //debugger;

    if (btn == "btnexportpayregmod") {
        $("#" + btn).css("display", "block");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
	
    } else if (btn == "btnexportpaymod3") {
        $("#btnexportpayregmod").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportpaymod3pdf").css("display", "block");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportsalbillpdf") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        $("#" + btn).css("display", "block");
        //$("#btnexportsalbillpdf").css("display", "block");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportempmod") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportempperexcel") {

    /* FOR REPORTS OF POOJA */
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportbankmod") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbankpdf").css("display", "block");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnufexportsalcard") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#" + btn).css("display", "block");
		$("#btnufexportsalcardpdf").css("display", "block");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportpayrollregister") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportLWPReportxls") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportLWPReportpdf").css("display", "block");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportbirthdayexcel") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbirthdaypdf").css("display", "block");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportempsaltrans") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportbankadvice") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportbankrembadvice") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportepfreport") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportnpsreport") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportpayslip") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    } else if (btn == "btnexportvendordet") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
        $("#btnufexportsalcardpdf").css("display", "none");
		$("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#" + btn).css("display", "block");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    }else if (btn == "btnexportcurrentpayrollregister") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
        $("#btnufexportsalcardpdf").css("display", "none");
		$("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#"+btn).css("display", "block");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    }
	else if (btn == "btnexportpayregmod_3_uf") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#"+btn).css("display", "block");		
		$("#btnexportpayregmodxl_static").css("display", "none");
		$("#btnexportpayregmodpdf_static").css("display", "none");
    }else if (btn == "btnexportpayregmodxl_static") {
        $("#btnexportpayregmod").css("display", "none");
        $("#btnexportpaymod3").css("display", "none");
        $("#btnexportpaymod3pdf").css("display", "none");
        //$("#btnexportsalbill").css("display", "none");
        $("#btnexportsalbillpdf").css("display", "none");
        $("#btnexportempmod").css("display", "none");
        $("#btnexportempperexcel").css("display", "none");
        $("#btnexportbankmod").css("display", "none");
        $("#btnexportbankpdf").css("display", "none");
        $("#btnufexportsalcard").css("display", "none");
		$("#btnufexportsalcardpdf").css("display", "none");
        $("#btnexportpayrollregister").css("display", "none");
        $("#btnexportLWPReportxls").css("display", "none");
        $("#btnexportLWPReportpdf").css("display", "none");
        $("#btnexportbirthdaypdf").css("display", "none");
        $("#btnexportbirthdayexcel").css("display", "none");
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");
        $("#btnexportepfreport").css("display", "none");
        $("#btnexportnpsreport").css("display", "none");
        $("#btnexportpayslip").css("display", "none");
        $("#btnexportvendordet").css("display", "none");
		$("#btnexportcurrentpayrollregister").css("display", "none");
		$("#btnexportpayregmod_3_uf").css("display", "none");
		$("#"+btn).css("display", "block");	
		$("#btnexportpayregmodpdf_static").css("display", "block");			
    }
}

/* code for enabling export buttons ends */
