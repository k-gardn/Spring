<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex6</title>
<script>
	var xhr;
	function send(){
		xhr = new XMLHttpRequest();
		xhr.open('post', 'ex6')
		xhr.send();
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
<body>
	<button type="button" onclick="send()">실행</button>
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









