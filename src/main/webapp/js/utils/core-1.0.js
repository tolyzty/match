$.extend({
	/**
	 * @ 得到url所传参数的值 @ key 变量名 @ frame 获取某帧
	 */
	getURLParameter : function(key, frame) {
		var param = (frame || window).location.search, reg = new RegExp(
				"[&\?]+" + key + "=([^&]*)"), str = "";
		if (reg.test(param))
			str = RegExp.$1;
		return str;
	},
	/**
	 * @ 获取Cookie的值 @ cookieName cookie变量名
	 */
	getCookie : function(cookieName) {
		var getC = document.cookie, reg = new RegExp(cookieName + "=([^;]*)");
		var val = "";
		if (reg.test(getC)) {
			val = RegExp.$1;
		}
		return val;
	},
	/**
	 * @ 计算毫秒，返回毫秒数 @ temer 要计算的字符串
	 */
	getDates : function(temer) {
		// var timeSize = [ "s", "m", "h", "D", "W", "M", "Y" ];
		// var tl = temer.length;
		var str = {};
		var s = 24 * 60 * 60;
		var sum = 0;
		var arra = temer.match(/\d+\w/g);
		if (arra == null)
			return false;
		for (var i = 0, l = arra.length; i < l; i++) {
			new RegExp("^(\\d+)([a-z]+)$", "i").test(arra[i]);
			str[RegExp.$2] = RegExp.$1;
		}
		if (str.s)
			sum = +str.s;
		if (str.m)
			sum += +str.m * 60;
		if (str.h)
			sum += +str.h * 60 * 60;
		if (str.D)
			sum += +str.D * s;
		if (str.W)
			sum += +str.W * s * 7;
		if (str.M)
			sum += +str.M * s * 30;
		if (str.Y)
			sum += +str.Y * s * 365;
		return sum * 1000;
	},
	/**
	 * @ 设置Cookie的方法 @ cookieName Cookie的名字 @ cookieInfo 参数是Cookie内容和要设置的时间
	 */
	setCookie : function(cookieName, cookieInfo) {
		var str = [];
		// 判断参数类型
		if (typeof cookieInfo == "string") {
			str = cookieInfo;
		} else {
			if (typeof cookieInfo.values == "object") {
				for ( var o in cookieInfo.values) {
					str.push(o + "=" + cookieInfo.values[o] + "&");
				}
				str = str.join("").slice(0, -1);
			} else {
				str = cookieInfo.values;
			}
		}
		// 判断时间的存在
		if (cookieInfo.temer) {
			var d = $.getDates(cookieInfo.temer);
			document.cookie = cookieName + "=" + str + ";expires="
					+ new Date(new Date().getTime() + d).toGMTString();
		} else
			document.cookie = cookieName + "=" + str;
	},
	/**
	 * @ 删除Cookie的方法 @ cookieName Cookie的名字
	 */
	delCookie : function(cookieName) {
		var getC = document.cookie, reg = new RegExp(cookieName + "=[^;]?");
		if (reg.test(getC))
			document.cookie = cookieName + "=;expires="
					+ new Date(-1).toGMTString();
	},
	getHostUrl : function() {
		return window.location.protocol + "//" + window.location.host;
	},
	getHostName : function(){
		var pathname = window.location.pathname;
		pathname = pathname.substring(0, pathname.indexOf("/", 1) + 1);
		if(!pathname || pathname === '/auth/'){
			pathname = "/";
		}
		return pathname;
	},
	getBaseHerf : function() {
		return $.getHostUrl() + $.getHostName();
	},
	getNowHref : function() {
		return $.getHostUrl() + window.location.pathname;
	},
	getLocalDataUrl : function() {
		return window.location.href.replace(/^(\#\!)?\#/, '');
	},
	baseHref : function() {
		$("a[href^='#']").baseHref();
		return this;
	},
	toUrl : function(url){
		location.href = url;
	}
});