<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="./sockjs.js"></script>
<script>
	var websocket;
	var testUrl = window.location.host+"/myHandler";
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://"+testUrl);
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://"+testUrl);
	} else {
		websocket = new SockJS("http://"+testUrl+"/sockjs");
	}
	websocket.onopen = function(evnt) {
		
	};
	
	websocket.onmessage = function(evnt) {
		var d = document.getElementById('message');
		d.innerHTML=d.innerHTML+"<br><font color='blue'>" + evnt.data + "</font>";
		document.getElementById('str').value = "";
	};
	
	websocket.onerror = function(evnt) {
		var d = document.getElementById('message');
		d.innerHTML=d.innerHTML+"<br><font color='red'><img src=\"./images/warning.png\">无法建立websocket连接</img></font>";
	};
	
	websocket.onclose = function(evnt) {
	}
	
	function send() {
        if (websocket != null) {
            var message = document.getElementById('str').value;
            websocket.send(message);
        } else {
            alert('未建立连接，请先连接');
        }
    }
	
	function sendByKey (event){
		 var e = event || window.event || arguments.callee.caller.arguments[0];
		 if(e && e.keyCode==13){
			 send();
		 }
	}
</script>
<body>
	登录成功
<br>
<@security.authorize access="hasRole('ROLE_ADMIN')">
	1 ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="hasRole('ROLE_USER')">
	2 ROLE_USER
</@security.authorize>
<br>
<@security.authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')">
	3 ROLE_USER AND ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
 	4 ROLE_USER OR ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="!hasAnyRole('ROLE_USER')">
 	5 ROLE_USER OR ROLE_ADMIN
</@security.authorize>
<br>

<div id="message" style="border:1px solid #00F; width:400px; height:400px; overflow-y: scroll;"></div>
<input type="text" id="str" style="width: 300px" onkeypress="sendByKey()">&nbsp;<input type="button" value="Send" onclick="send();">
</body>
</html>