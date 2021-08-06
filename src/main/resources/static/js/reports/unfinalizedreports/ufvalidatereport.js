/* VALIDATION OF FORM STARTS 02-04-2021 */

function validateForm(report_type) {
    //debugger;;
    if (report_type == "" || report_type == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");
        return false;
    }
    switch (report_type) {
        case "1":
        case "2":
        case "3":
        case "4":
        case "17":
            if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                return false;
            }
            break;
        case "6":
            if (Number.isNaN(bus_id) && natureemp == null && banktype == null) {
                $("#buerr").css("display", "block");
                $("#noeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && natureemp == null && banktype != null) {
                $("#buerr").css("display", "block");
                $("#noeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && natureemp != null && banktype == null) {
                $("#buerr").css("display", "block");
                //$("#noeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && natureemp != null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#noeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && natureemp == null && banktype == null) {
                //$("#buerr").css("display","block");
                $("#noeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && natureemp == null && banktype != null) {
                //$("#buerr").css("display","block");
                $("#noeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && natureemp != null && banktype == null) {
                //$("#buerr").css("display","block");
                //$("#noeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            }
            break;
		case "7":
            if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            }
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            }
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none`");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && tocal != null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "block");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal == null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "block");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal == null && per_no != "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "block");
				$("#pernoerr").css("display", "none");
                return false;
            } 
			else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal != null && tocal != null && per_no == "") 
			{
                $("#buerr").css("display", "none");
                $("#pgerr").css("display", "none");
                $("#frmcalcodeerr").css("display", "none");
				$("#tocalcodeerr").css("display", "none");
				$("#pernoerr").css("display", "block");
                return false;
            } 
            break;
        case "8":
            if (Number.isNaN(bus_id) && mnth == null) {
                $("#buerr").css("display", "block");
                $("#mntherr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && mnth != null) {
                $("#buerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && mnth == null) {
                $("#mntherr").css("display", "block");
                return false;
            }

            break;
        case "9":
        case "14":
        case "15":
        case "16":
            if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && natureemp == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && natureemp != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && natureemp != null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && natureemp != null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && !paygroupid == null && !fromcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            }
            break;
        case "10":
            if (fdt == "" && tdt == "") {
                $("#frmdterr").css("display", "block");
                $("#todterr").css("display", "block");
                return false;
            } else if (fdt == "" && tdt != "") {
                $("#frmdterr").css("display", "block");
                //$("#todterr").css("display","block");
                return false;
            } else if (fdt != "" && tdt == "") {
                //$("#frmdterr").css("display","block");
                $("#todterr").css("display", "block");
                return false;
            }

            break;
        case "11":
        case "12":
        case "13":
            if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype != null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype != null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            }
            break;
        case "18":
		case "19":
		case "20":
            if (Number.isNaN(bus_id) && paygroupid == null && staticcal == null && natureemp == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && staticcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && staticcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && staticcal != null && natureemp != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && staticcal == null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && staticcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && staticcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && staticcal != null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && staticcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && staticcal == null && natureemp != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && staticcal != null && natureemp == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && staticcal != null && natureemp != null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && staticcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#noeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && staticcal == null && natureemp != null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#noeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && !paygroupid == null && !staticcal == null && natureemp == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#noeerr").css("display", "block");
                return false;
            }
            break;
    }
}

/* VALIDATION OF FORM ENDS 02-04-2021 */
