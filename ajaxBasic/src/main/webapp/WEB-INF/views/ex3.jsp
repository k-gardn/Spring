<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex3</title>
<script>

	function send(){
		var idValue = document.getElementById('id').value
		var pwValue = document.getElementById('pw').value
		//console.log('idValue : ' + idValue)
		//console.log('pwValue : ' + pwValue)
		
		// 자바스크립트 object 라는 자료형으로 여러 데이터를 한 개로 묶음.
		var reqData = {id : idValue, pw : pwValue}
		console.log('reqData : ' + reqData)
		
		// JSON.stringify(reqData) : 자바스크립트 object 자료형을 JSON 문자열 자료형으로 변환
		// 네트워크(인터넷)로 데이터를 전달하기 위해서 변환.
		//console.log('JSON.stringify(reqData) : ' + JSON.stringify(reqData))
		
		reqData = JSON.stringify(reqData);
		xhr = new XMLHttpRequest();
		xhr.open('post', 'ex3');
		xhr.setRequestHeader('content-type', 'application/json');
		xhr.send(reqData);
		
		//onreadystatechange는 이벤트 함수.
		// readyState의 상태가 변하면 알아서 실행하는 콜백 함수.
		// 자바 스크립트가 실행하는 함수.
		xhr.onreadystatechange = resProc;
/* 		console.log(typeof 10)
		console.log(typeof 1.1)
		console.log(typeof '1.1')
		console.log(typeof "1.1")
		console.log(typeof reqData) */
	}
	var xhr;
	function resProc(){
		if(xhr.readyState === 4 && xhr.status === 200){
			var resData = xhr.responseText;
			//console.log(resData) // 응답데이터의 자료형은 JSON String
			// JSON.parse(resData) : JSON 문자열 자료형을 자바스크립트 object 자료형으로 변환
			//console.log(JSON.parse(resData)) 
			
			resData = JSON.parse(resData)
			//console.log(resData.idPrint) 
			//console.log(resData.pwPrint) 
			document.getElementById('idPrint').innerHTML = resData.idPrint
			document.getElementById('pwPrint').innerHTML = resData.pwPrint
		}
	}
</script>
</head>
<body>
	<input type='text' id="id" /><br>
	<span id="idPrint"></span><br>
	<input type='password' id="pw" /><br>
	<span id="pwPrint"></span><br>
	<button type="button" onclick="send()">로그인</button>
</body>
</html>









