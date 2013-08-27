 /*
 * Copyright 2012 trewys GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

var Blocks = {
	Context: {
		path: ""
	},
	Request: {
		buildUrl: function(url, parameter) {
			url = Blocks.Context.path + url;
			
			if (parameter) {
				var first = true;
				jQuery.each(parameter, function(key, value) {
					if (value != null) {
						if (first) {
							url = url + "?" + key + "=" + Blocks.Request.encodeUrl(value);
							first = false;
						} else
							url = url + "&" + key + "=" + Blocks.Request.encodeUrl(value);
					}
				});
			}
			
			return url;
		},
		redirect: function(url, parameters) {
			window.location = Blocks.Request.buildUrl(url, parameters);
		},
		encodeUrl: function(string) {
			
			if (!string.replace)
				return string;
			
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";

			for (var n = 0; n < string.length; n++) {

				var c = string.charCodeAt(n);

				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}

			return jQuery.urlEncode(utftext);
		}
	},
	Ajax: {
		load: function (url, onLoadedFunction) {
			
			jQuery.ajax({
				url: url,
				type: 'GET',
				dataType: "html",
				cache: false,
				success: function(response) {
					response = $(response);
					
					if (onLoadedFunction)
						onLoadedFunction(response);
				},
				error: function (response) {
					Blocks.Ajax.handleError(response);
				}
			});
		},
		postForm: function (form, onPostedFunction) {
			var url = form.attr("action");
			var dataString = form.serialize();
			
			jQuery.ajax({
				url: url,
				type: 'POST',
				dataType: "html",
				data: dataString,
				cache: false,
				success: function(response) {
					response = $(response);
					
					if (onPostedFunction)
						onPostedFunction(response);
				},
				error: function (response) {
					Blocks.Ajax.handleError(response);
				}
			});
		},
		handleError: function(errorData) {
//			if (data.errorState == 4 && tBlocks.defaultRtcHandler["ERROR"])
//				tBlocks.defaultRtcHandler["ERROR"]();
		}
	},
	JSON: {
		post: function (url, dataArray, onPostedFunction) {
			var dataString = null;
			jQuery.each(dataArray, function(key, value) {
				if (value != null) {
					if (!dataString)
						dataString = key + "=" + Blocks.Request.encodeUrl(value);
					else
						dataString = dataString + "&" + key + "=" + Blocks.Request.encodeUrl(value);
				}
			});
			
			jQuery.ajax({
				url: url,
				type: 'POST',
				dataType: "json",
				data: dataString,
				cache: false,
				success: function(response) {
					if (onPostedFunction)
						onPostedFunction(response);
				},
				error: function (data) {
					Blocks.JSON.handleError(data);
				}
			});
		},
		load: function (url, onLoadedFunction) {
			
			$.ajax({
				url: url,
				type: 'GET',
				dataType: "json",
				timeout: 8000,
				cache: false,
				success: function(response) {
					if (onLoadedFunction)
						onLoadedFunction(response);
				},
				error: function (response, status, error) {
					console.log(error);
					Blocks.JSON.handleError(response);
				}
			});
		},
		handleError: function(response) {
			//TODO
		}
	},
	//@deprecated AjaxHelper
	AjaxHelper: {
		ajaxPostForm: function (form, onPostedFunction) {
			var url = form.attr("action");
			var dataString = form.serialize();
			
			jQuery.ajax({
				url: url,
				type: 'POST',
				dataType: "html",
				data: dataString,
				cache: false,
				success: function(data) {
					data = $(data);
					
					if (onPostedFunction)
						onPostedFunction(data);
				},
				error: function (data) {
					Blocks.AjaxHelper.handleError(data);
				}
			});
		},
		ajaxPost: function (url, dataArray, onPostedFunction) {
			var dataString = null;
			jQuery.each(dataArray, function(key, value) {
				if (value != null) {
					if (!dataString)
						dataString = key + "=" + Blocks.AjaxHelper.encodeUrl(value);
					else
						dataString = dataString + "&" + key + "=" + Blocks.AjaxHelper.encodeUrl(value);
				}
			});
			
			jQuery.ajax({
				url: url,
				type: 'POST',
				dataType: "html",
				data: dataString,
				cache: false,
				success: function(data) {
					data = $(data);
					
					if (onPostedFunction)
						onPostedFunction(data);
				},
				error: function (data) {
					Blocks.AjaxHelper.handleError(data);
				}
			});
		},
		ajaxLoad: function (url, onLoadedFunction) {
			
			jQuery.ajax({
				url: url,
				type: 'GET',
				dataType: "html",
				cache: false,
				success: function(data) {
					data = $(data);
					
					if (onLoadedFunction)
						onLoadedFunction(data);
				},
				error: function (data) {
					Blocks.AjaxHelper.handleError(data);
				}
			});
		},
		handleError: function(errorData) {
//			if (data.errorState == 4 && tBlocks.defaultRtcHandler["ERROR"])
//				tBlocks.defaultRtcHandler["ERROR"]();
		},
		redirect: function(url, parameters) {
			window.location = Blocks.AjaxHelper.buildUrl(url, parameters);
		},
		buildUrl: function(url, parameter) {
			url = Blocks.Context.path + url;
			
			if (parameter) {
				var first = true;
				jQuery.each(parameter, function(key, value) {
					if (value != null) {
						if (first) {
							url = url + "?" + key + "=" + Blocks.AjaxHelper.encodeUrl(value);
							first = false;
						} else
							url = url + "&" + key + "=" + Blocks.AjaxHelper.encodeUrl(value);
					}
				});
			}
			
			return url;
		},
		encodeUrl: function(string) {
			
			if (!string.replace)
				return string;
			
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";

			for (var n = 0; n < string.length; n++) {

				var c = string.charCodeAt(n);

				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}

			}

			return jQuery.urlEncode(utftext);
		}
	},
	Controller: Spine.Controller.sub({
		
		ajaxPost: function() {
		    if (this.el.is("form")) {
		    	Blocks.Ajax.postForm(this.el, this.proxy(function (response) {
		    		this.trigger("onPost", response);
		    	}));
		    } else {
		    	
		    }
		},
		
		replaceWith: function(data) {
			var id = this.el.attr("id");
			if (id) {
				if (!data.is("#" + id))
					data = data.find("#" + id);
			}
			this.replace(data);
		},
		
		reload: function () {
			var url = window.location;
			if (this.el.is("form")) {
				url = this.el.attr("action");
		    } else if (this.el.data("reload")) {
		    	url = this.el.data("reload");
		    }
			Blocks.Ajax.load(url, this.proxy(function (response) {
				this.replaceWith(response);
				this.init();
			}));
		}
	})
};



//JQUERY EXTENSIONS ################################################################################

jQuery.fn.extend({
	parentWith: function(selector) {
		var parent = this.parent();
		
		while (parent.length > 0) {
			
			if (parent.is(selector))
				return parent;
			
			parent = parent.parent();
		}
		
		return parent;
	}
	
});


$.extend({urlEncode:function(c){var o='';var x=0;c=c.toString();var r=/(^[a-zA-Z0-9_.]*)/;
while(x<c.length){var m=r.exec(c.substr(x));
  if(m!=null && m.length>1 && m[1]!=''){o+=m[1];x+=m[1].length;
  }else{if(c[x]==' ')o+='+';else{var d=c.charCodeAt(x);var h=d.toString(16);
  o+='%'+(h.length<2?'0':'')+h.toUpperCase();}x++;}}return o;},
URLDecode:function(s){var o=s;var t;var r=/(%[^%]{2})/;
while((m=r.exec(o))!=null && m.length>1 && m[1]!=''){b=parseInt(m[1].substr(1),16);
t=String.fromCharCode(b);o=o.replace(m[1],t);}return o;}
});



