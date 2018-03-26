if(typeof(sBIAOSHSHIFOUYINRU)== 'undefined' ){
	function isMobileDevice(ua){
		if (/(iphone|ios|android|mini|mobile|mobi|Nokia|Symbian|iPod|iPad|Windows\s+Phone|MQQBrowser|wp7|wp8|UCBrowser7|UCWEB|360\s+Aphone\s+Browser|WindowsWechat)/i.test(ua))
		{ 
			return true;
		}
		return false;
	}
function randomString(len) { 
	len = len || 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; 
	var maxPos = $chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
	
var sBIAOSHSHIFOUYINRU='isexist';
var videoareaname = 'videoarea_'+randomString(5);

	sUSER_AGENT = navigator.userAgent;
	if(isMobileDevice(sUSER_AGENT)){
		document.write('<script type="text/javascript" src="http://www.bfvyun.com/html/script/libs/xss.min.js?v=2"></scr'+'ipt>');		
		!window.jQuery && document.write('<script type="text/javascript" src="http://www.bfvyun.com/html/script/libs/jquery-1.8.3.min.js?v=3"></scri'+'pt>');
		document.write('<script src="http://www.bfvyun.com/html/script/player_es5.js?v=1"></scr'+'ipt>');
		document.write('<div id="'+videoareaname+'"></div>');
		  
	}else if(typeof(h5player)!=='undefined' && h5player==true){
		document.write('<div id="wrapper" class="bfvcontainer"><video tabindex="-1"></video></div>');
			document.write('<script type="text/javascript" src="http://www.bfvyun.com/html/script/libs/xss.min.js?v=2"></scr'+'ipt>');		
		!window.jQuery && document.write('<script type="text/javascript" src="http://www.bfvyun.com/html/script/libs/jquery-1.8.3.min.js?v=3"></scri'+'pt>');
		document.write('<script src="http://www.bfvyun.com/html/script/bfvplayer.min.js?v=0"></scr'+'ipt>');
		document.write('<script src="http://www.bfvyun.com/html/script/h5cloudsdk.js?v=0"></scr'+'ipt>');

	}
	else
	{
		document.write('<script type="text/javascript" src="http://www.bfvyun.com/html/script/libs/cloudsdk.js"></scr'+'ipt>');
		document.write('<div id="'+videoareaname+'"></div>');
	}
	
}