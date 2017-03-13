var NTTCore = {
	init : function() {
		this.loader.init();
	},
	loader : {
		init : function(){
			var loaderParams = {
				loaderClass : "loading_bar_body",
				hasBackground : true
			};
			$.fn.nimbleLoader.setSettings(loaderParams);
		},
		showLoader : function(loadingTarget){
			$("body").nimbleLoader("show");
			var bottom = $('body').outerHeight();
			$(".loading_bar_body").css('height', bottom);
		},
		hideLoader : function(loadingTarget){
			$("body").nimbleLoader("hide");
		}
	},
	postJsonAjaxRequest : function(url, dataParam, showLoading, successCallback, errorCallback){
		var _this = this;
		$.ajax({
			type : 'POST',
			url  : url,
			data : dataParam,
			dataType : "json",
			beforeSend: function( xhr ) {
				if(showLoading){
					_this.loader.showLoader();
				}
			},
			error : function(){
				if(errorCallback){
					errorCallback();
				}
			},
			success : function(data){
				if(successCallback){
					successCallback(data);
				}
			},
			complete : function(){
				if(showLoading){
					_this.loader.hideLoader();
				}
			}
		});
	}

};

$(function() {
	NTTCore.init();
});
