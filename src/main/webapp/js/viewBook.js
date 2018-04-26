	function boxdefault(){
		document.getElementById("default").className = "current";
		document.getElementById("introduce").className = "";
		document.getElementById("list").className = "";
		document.getElementById("valuement").className = "";
		document.getElementById("default_item").style.display="";
		document.getElementById("introduce_item").style.display="none";
		document.getElementById("list_item").style.display="none";
		document.getElementById("valuement_item").style.display="none";
	}
		function boxintroduce(){
		document.getElementById("default").className = "";
		document.getElementById("introduce").className = "current";
		document.getElementById("list").className = "";
		document.getElementById("valuement").className = "";
		document.getElementById("default_item").style.display="none";
		document.getElementById("introduce_item").style.display="";
		document.getElementById("list_item").style.display="none";
		document.getElementById("valuement_item").style.display="none";
	}
		function boxlist(){
		document.getElementById("default").className = "";
		document.getElementById("introduce").className = "";
		document.getElementById("list").className = "current";
		document.getElementById("valuement").className = "";
		document.getElementById("default_item").style.display="none";
		document.getElementById("introduce_item").style.display="none";
		document.getElementById("list_item").style.display="";
		document.getElementById("valuement_item").style.display="none";
	}
		function boxvaluement(){
		document.getElementById("default").className = "";
		document.getElementById("introduce").className = "";
		document.getElementById("list").className = "";
		document.getElementById("valuement").className = "current";
		document.getElementById("default_item").style.display="none";
		document.getElementById("introduce_item").style.display="none";
		document.getElementById("list_item").style.display="none";
		document.getElementById("valuement_item").style.display="";
	}
	
		function valueadd(){
			document.getElementById("value").value++;
		}
		function valuesub(){
			if(document.getElementById("value").value<1){
				return 0;
			}else{			
			document.getElementById("value").value -=1;
			}
		}
		function version(versionName){
			alert(versionName+"  选择成功!");
			document.getElementById("hversion").value=versionName;
		}