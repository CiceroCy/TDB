
/**
 * File created for usage of date functions all around the system with a standard way.
 *
 * @since 11/20/2013
 * @author hudson.brito
 * @company NTT Data Curitiba
 */
$(function() {
	nttDatePicker.startDatePicker();

	mask.initMasks();
});


var nttDatePicker = {

		/**
		 * Put an input with class "dateCalendarNtt" attribute set. Then this datepicker will work
		 * <input type="text" id="exampleUsageCalendarDefault" class="customClass myOtherClass dateCalendarNtt" />
		 */
		startDatePicker : function(){

			$('.dateCalendarNtt').datepicker({
				format: "dd/mm/yyyy",
				autoclose: true,
				language: "pt-BR"
			});

			$('.input-append.dateCalendarNtt').datepicker({
				format: "dd/mm/yyyy",
				autoclose: true,
				language: "pt-BR"
			});

			$('.dateCalendarNttToday').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				autoclose: true,
				language: "pt-BR"
			});

			$('.dateCalendarNttMinToday').datepicker({
				format: "dd/mm/yyyy",
				startDate: "today",
				todayHighlight: true,
				autoclose: true,
				language: "pt-BR"
			});

		}
};

var mask = {
	initMasks : function(){
		$.mask.masks = $.extend($.mask.masks, {
			dateTime : {mask: '39/19/9999 29:59'},
			date 	: $.extend($.mask.masks.date,{autoTab: false}),
			time 	: {mask: '29:59', autoTab: false},
			cep		: $.extend($.mask.masks.cep, {autoTab: false})
		});


		$('.dateMask').setMask("date");
		$('.timeMask').setMask("time");
		$('.dateTimeMask').setMask("dateTime");
		$('.zipCodeMask').setMask("cep");

	},

	validateTimeFieldBlur :function(field){
		var time = field.value;

		  if(time == '')
		    return true;

		  if (time == null)
		     return true;

		  if(time.length < 5){
			  return false;
		  }

		  var dtHour= time.substring(0,2);
		  var dtMin = time.substring(3,5);

		  if (dtHour >23)
		      return false;
		  else if (dtMin > 59)
		      return false;

		  return true;
	},

	isValidDate : function( txtDate) {
		var currVal = txtDate;
		  if(currVal == '')
		    return true;
		  var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
		  var dtArray = currVal.match(rxDatePattern); // is format OK?

		  if (dtArray == null)
		     return true;

		  dtDay= dtArray[1];
		  dtMonth = dtArray[3];
		  dtYear = dtArray[5];

		  if (dtMonth < 1 || dtMonth > 12)
		      return false;
		  else if (dtDay < 1 || dtDay> 31)
		      return false;
		  else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
		      return false;
		  else if (dtMonth == 2)
		  {
		     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
		     if (dtDay> 29 || (dtDay ==29 && !isleap))
		          return false;
		  }
		  return true;

	},

	validateDateFieldBlur : function(field, msg) {
		fieldValue = field.value;

		if(fieldValue == ""){
			return true;
		}
		return this.validateDateBlur(field.value, msg);
	},

	validateDateBlur : function(value, msg) {
		if(value.length == 10){
			if(!this.isValidDate(value)){
				return false;
			}else{
				return true;
			}
		}
		return false;
	},

	formatterDateToday : function(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}
		if (mm < 10) {
			mm = '0' + mm;
		}
		var today = dd + '/' + mm + '/' + yyyy;
		return today;
	},


	//Expected dates in format dd/mm/yyyy
	isStartDateBiggerThanEndDate : function ( startDateTxt, endDateTxt){
		//transform from dd/mm/yyyy to yyyymmdd
		var startDate = startDateTxt.substr(6, 4) + startDateTxt.substr(3,2) + startDateTxt.substr(0,2);
		var endDate = endDateTxt.substr(6, 4) + endDateTxt.substr(3,2) + endDateTxt.substr(0,2);

		//transform text to integer
		startDate = parseInt(startDate,10);
		endDate = parseInt(endDate,10);

		//compare dates
		if(startDate <= endDate){
			return true;
		}

		return false;
	}
};

var nttUtil = {
	showLoading : function(){
		$.fancybox({
			content: ' ',
			showCloseButton: false,
			modal : true,
			width : '0.1px',
			height: '0.1px',
			onComplete : function(){
				$.fancybox.showActivity();
				$("#fancybox-content").hide();
			}
		});
	}
};

