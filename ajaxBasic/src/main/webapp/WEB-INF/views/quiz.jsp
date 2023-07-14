<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>quiz</title>
<script>
	var xhr;
	function send(){
		xhr = new XMLHttpRequest();
		xhr.open('post', 'quiz')
		
		//input창 입력 값 가져오기.
		var search = document.getElementById('title').value;
		console.log("search : "+ search);
		xhr.send(search);
		xhr.onreadystatechange = resProc;
	}
	
	function resProc(){
		if(xhr.readyState === 4 && xhr.status === 200){
			var resData = JSON.parse(xhr.responseText)
		
			var result = "";
			for(i = 0; i < resData.length; i++){
				result += "<tr>";
				result += "<td>" + resData[i].title + "</td>";
				result += "<td>" + resData[i].artist + "</td>";
				result += "<td>" + resData[i].price + "</td>";
				result += "</tr>";
			}
			
			document.getElementById('tbody').innerHTML = result; 
		}
	}
</script>
</head>
<body onload="send()">
	<input type="text" id="title" onkeyup="send()"> 
	<br><br>
	<table border=1>
		<tr>
			<th>제목</th>
			<th>아티스트</th>
			<th>가격</th>
		</tr>
		<tbody id="tbody"></tbody>
	</table>
</body>
</html>









