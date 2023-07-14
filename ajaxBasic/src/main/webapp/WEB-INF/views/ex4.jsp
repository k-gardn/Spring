<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex4</title>
<script type="text/javascript">
	var xhr;
	function send() {
		xhr = new XMLHttpRequest();
		xhr.open('post', 'ex4')
		xhr.send();
		xhr.onreadystatechange = resProc;
	} 
	
	function resProc() {
		if(xhr.readyState === 4 && xhr.status ===200){
			var resData = JSON.parse(xhr.responseText)
			//console.log(resData)
			//console.log(resData.cd[1])
			//console.log(resData.cd[1].title)
			//console.log(resData.cd[1].artist)
			//console.log(resData.cd[1].price)
			var result ="";
			for(i=0 ; i<resData.cd.length;i++){
				result += "<tr>";
				result += "<td>" + resData.cd[i].title + "</td>";
				result += "<td>" + resData.cd[i].artist + "</td>";
				result += "<td>" + resData.cd[i].price + "</td>";
				result += "</tr>";
				}
			document.getElementById("tbody").innerHTML = result;
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
		
		<tbody id="tbody">
		
		</tbody>
	</table>
</body>
</html>









