//切换插件外链
//document.write("<scri" + "pt type='text/javascript' src='../swaf_plugin.js'></sc" + "ript>");
//document.write("<scri" + "pt type='text/javascript' src='swaf_plugin.js'></sc" + "ript>");

if(window.swaf){
	sdStatusBar.setStyle("Black");
	sdStatusBar.setColor("#FFFFFF");
}

//解决IE不支持console的问题
(function() {
	var method;
	var noop = function () {};
	var methods = [
		'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
		'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
		'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
		'timeStamp', 'trace', 'warn'
	];
	var length = methods.length;
	var console = (window.console = window.console || {});

	while (length--) {
		method = methods[length];

		// Only stub undefined methods.
		if (!console[method]) {
			console[method] = noop;
		}
	}

	//日期修改
	Date.prototype.addDay = function(d){
		return (new Date(this.valueOf() + d * 86400000));
	};
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	// 例子：
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
	Date.prototype.Format = function (fmt) { //author: meizz
		var wd = ['日', '一', '二', '三', '四', '五', '六'];
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		if(/(D)/.test(fmt)) fmt = fmt.replace(RegExp.$1, wd[this.getDay()]);
		for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};
	Date.prototype.FormatWeek = function (day) {
		var wd = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
		var week = wd[day.getDay()];
		return week;
	};
	Date.prototype.FormatWeek2 = function (day) {
		var wd = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
		var week = wd[day.getDay()];
		return week;
	};
	Date.prototype.FormatWeek3 = function (day) {
		var wd = ['星期日Sunday', '星期一Monday', '星期二Tuesday', '星期三Wednesday', '星期四Thursday', '星期五Friday', '星期六Saturday'];
		var week = wd[day.getDay()];
		return week;
	};
}());

/*=======================*/
DATE={};
DATE.setCalendar=function(year,month,num){
	$('.dayShow').html('');
	var str=year+"/"+month+"/1";
	var oneday=new Date(str);
	var dayNum=0;
	var nowYear=new Date().getFullYear();
	var nowMonth=new Date().getMonth();
	var nowDate=new Date().getDate();
	if(month==1|month==3|month==5|month==7|month==8|month==10|month==12){
		dayNum=31;
	}else if(month==4|month==6|month==9|month==11){
		dayNum=30;
	}else{
		if(((year % 100 == 0) && (year % 400 == 0)) || ((year % 100 != 0) && (year % 4 == 0))){
			dayNum=29;
		}else{
			dayNum=28;
		}
	}
	var s="";
	for(var i=1;i<=dayNum;i++){
		if(i==1){
			s+='<div class="main_row1">';
			for(var n=0;n<oneday.getDay();n++){
				s+='<div class="main_day"><div class="day"></div></div>';
			}
		}
		if(nowYear==year && (nowMonth+1)==month){
			if(i==nowDate){
				s+='<div class="main_day"><div class="day day_choice day_today" d="0">'+i+'</div></div>';
			}else{
				if(num){
					if(i>nowDate && i<(nowDate+num)){
						s+='<div class="main_day"><div class="day day_choice day_option" d="1">'+i+'</div></div>';
					}else{
						s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
					}
				}else{
					s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
				}
			}
		}else{
			s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
		}
		if(((oneday.getDay()+i)%7)==0){
			s+='</div>';
			if(i<dayNum){
				s+='<div class="main_row1">';
			}
		}
	}
	$('.dayShow').append(s);
	$('.day_choice').click(function(){
		if($(this).attr('d')=='0'){
			$(this).removeClass('day_today');
			$(this).addClass('day_select');
			$(this).attr('d','2');
		}else if($(this).attr('d')=='1'){
			$(this).removeClass('day_option');
			$(this).addClass('day_select');
			$(this).attr('d','3');
		}else if($(this).attr('d')=='2'){
			$(this).removeClass('day_select');
			$(this).addClass('day_today');
			$(this).attr('d','0');
		}else if($(this).attr('d')=='3'){
			$(this).removeClass('day_select');
			$(this).addClass('day_option');
			$(this).attr('d','1');
		}
	});
};
DATE.setDateTime=function(e,year,month,num){

	var str=year+"/"+month+"/1";
	var oneday=new Date(str);
	var dayNum=0;
	var nowYear=new Date().getFullYear();
	var nowMonth=new Date().getMonth();
	var nowDate=new Date().getDate();
	if(month==1|month==3|month==5|month==7|month==8|month==10|month==12){
		dayNum=31;
	}else if(month==4|month==6|month==9|month==11){
		dayNum=30;
	}else{
		if(((year % 100 == 0) && (year % 400 == 0)) || ((year % 100 != 0) && (year % 4 == 0))){
			dayNum=29;
		}else{
			dayNum=28;
		}
	}
	var s='<div class="dateShow">'+
		'<div class="main_dateheader1">'+
		'	<div class="dateShow_left">'+year+'年'+month+'月</div>'+
		'</div>';
	for(var i=1;i<=dayNum;i++){
		if(i==1){
			s+='<div class="main_row3">';
			for(var n=0;n<oneday.getDay();n++){
				s+='<div class="main_day"><div class="day"></div></div>';
			}
		}
		if(nowYear==year && (nowMonth+1)==month){
			if(i==nowDate){
				s+='<div class="main_day"><div class="day day_choice day_today" d="0">'+i+'</div></div>';
			}else{
				if(num){
					if(i>nowDate && i<(nowDate+num)){
						s+='<div class="main_day"><div class="day day_choice day_option" d="1">'+i+'</div></div>';
					}else{
						s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
					}
				}else{
					s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
				}
			}
		}else{
			s+='<div class="main_day"><div class="day day_nooption">'+i+'</div></div>';
		}
		if(((oneday.getDay()+i)%7)==0){
			s+='</div>';
			if(i<dayNum){
				s+='<div class="main_row3">';
			}
		}
	}
	s+='</div>';
	$(e).append(s);
	$('.day_choice').click(function(){
		$('.day_choice').removeClass('day_select');
		if($(this).attr('d')=='0'){
			$(this).removeClass('day_today');
			$(this).addClass('day_select');
			$(this).attr('d','2');
		}else if($(this).attr('d')=='1'){
			$(this).removeClass('day_option');
			$(this).addClass('day_select');
			$(this).attr('d','3');
		}else if($(this).attr('d')=='2'){
			$(this).addClass('day_today');
			$(this).attr('d','0');
		}else if($(this).attr('d')=='3'){
			$(this).addClass('day_option');
			$(this).attr('d','1');
		}
	});
};


