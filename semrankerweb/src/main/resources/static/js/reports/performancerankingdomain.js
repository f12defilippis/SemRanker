function addError(id)
{
		$('#' + id).nextAll().remove();
  		$('#' + id).parent().removeClass("has-success").addClass("has-error").addClass("has-feedback");
  		$( "<span class=\"help-block\">Invalid date Range</span>" ).insertAfter( "#" + id );
  		$( "<span class=\"glyphicon glyphicon-remove form-control-feedback\" aria-hidden=\"true\"></span>" ).insertAfter( "#" + id );
}

function addSuccess(id)
{
		$('#' + id).nextAll().remove();
  		$('#' + id).parent().removeClass("has-error").addClass("has-success").addClass("has-feedback");
  		$( "<span class=\"glyphicon glyphicon-ok form-control-feedback\" aria-hidden=\"true\"></span>" ).insertAfter( "#" + id );
}


function validateDateRange(id)
{
	if( $('#dateFrom').val() > $('#dateTo').val() )
	{
		addError(id);
		return false;
	}else
	{
		addSuccess(id);		
		return true;
	}
}



$(document).ready(function() {
    $('#dateFrom').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        format: 'YYYY/MM/DD',
        timePicker: false
    })
	.change(function() {
		validateDateRange('dateFrom');
		validateDateRange('dateTo');
	});

    $('#dateTo').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        format: 'YYYY/MM/DD',
        timePicker: false
    })
	.change(function() {
		validateDateRange('dateFrom');
		validateDateRange('dateTo');
	});


	$( "#performance_ranking_domain_form" ).submit(function( event ) {
		if(!(validateDateRange('dateFrom') && validateDateRange('dateTo')))
		{
			event.preventDefault();
		}
	});

});