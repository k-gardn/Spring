<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	span{color:red;}
</style>
<title>ex1</title>
	<script>
		var xhr;
		function send() {
			// Ajax 요청을 초기화합니다
			
			xhr = new XMLHttpRequest();
			xhr.open('post', 'ex2');
			var idCheck = document.getElementById('id').value;
			xhr.send(idCheck); 
			
	 		xhr.onreadystatechange = resProc;
		}
 			function resProc(){
			
			// readyState 4: 완료(성공 여부 상관X)
			if(xhr.readyState !== 4)  
				return; 
			
			if(xhr.status === 200) { //응답의 성공 여부를 알 수 있음.
			 // status 200: 성공
			console.log(xhr.responseText); // '반환된 텍스트'
			var print = document.getElementById('print');
			print.innerHTML = xhr.responseText;
			} else {
				console.log('에러: ' + xhr.status); // 요청 도중 에러 발생
			} 
		}
	</script>
</head>
<body>
아이디 중복 체크하기
	<input type="text" id="id" onclick="send()" /><br>
	<span id="print"></span><br>
</body>
</html>