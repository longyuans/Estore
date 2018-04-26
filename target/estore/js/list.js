	function addShopCart(bookname) {
		alert(bookname + "  选择成功!");
		var s="/book/viewBookServlet?bookName="+bookname;
		window.location=s;
	}
	function boxdefault() {
		document.getElementById("default").className = "current";
		document.getElementById("sales").className = "";
		document.getElementById("price").className = "";
		document.getElementById("new").className = "";
		document.getElementById("c4_b5_sales").style.display = "none";
		document.getElementById("c4_b5_price").style.display = "";
	}
	function boxsales() {
		document.getElementById("default").className = "";
		document.getElementById("sales").className = "current";
		document.getElementById("c4_b5_price").style.display = "none";
		document.getElementById("price").className = "";
		document.getElementById("new").className = "";
		document.getElementById("c4_b5_sales").style.display = "";
	}
	function boxprice() {
		document.getElementById("default").className = "";
		document.getElementById("sales").className = "";
		document.getElementById("price").className = "current";
		document.getElementById("new").className = "";
		document.getElementById("c4_b5_sales").style.display = "none";
		document.getElementById("c4_b5_price").style.display = "";
	}
	function boxnew() {
		document.getElementById("default").className = "";
		document.getElementById("sales").className = "";
		document.getElementById("price").className = "";
		document.getElementById("new").className = "current";
		document.getElementById("c4_b5_sales").style.display = "none";
		document.getElementById("c4_b5_price").style.display = "";
	}
	function userinfo() {
	var user=document.getElementById("user").value;
		if (user!=null) {
			var s = "userinfo.jsp";
			window.location = s;
		} else {
			alert("请  登入");
		}
	}
	function searching(){
		var bookName=document.getElementById("search").value;
		var s1="/book/CheckBookIsExist?bookName="+bookName;
		window.location=s1;
	}
	
	window.onload=function(){
		var price_rank=$("#prank > a");
		var ul=document.getElementById("showrank");
		var Priceul=document.getElementById("PriceUl");
		var category=document.getElementById("category").innerHTML;
		for(var k=0;k<price_rank.length;k++){
			var click=price_rank[k];
			click.onclick=function(){
				console.log(this.innerHTML+"click");
				var ajax=null;
				if(window.ActiveXObject){
					ajax=new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(window.XMLHttpRequest){
					ajax = new XMLHttpRequest();
				}
				var para=this.innerHTML;
				var url=encodeURI("/book/ListServlet?rank="+para+"&category="+category);
				ajax.open("get", url, true);
				ajax.onreadystatechange=function(){
					if(ajax.readyState==4 && ajax.status==200){
					var data=ajax.responseText;
					ul.innerHTML="<li><span class='search_key'>价格：</span> <span class='search_val'>"+para+"</span> <span onclick='removePrice()' class='search_del'></span></li>";
					Priceul.innerHTML=data;
					//$(ul).append("<>");
					};
				};
				ajax.send();
			};
		};
	};
		$(function(){
		var publish=$("#publishName > a");
		var ul=document.getElementById("showrank");
		var Priceul=document.getElementById("PriceUl");
		var category=document.getElementById("category").innerHTML;
		console.log("begin"+publish);
  		for(var k=0;k<publish.length;k++){
			var click=publish[k];
			click.onclick=function(){
				console.log(this.innerHTML+"click");
 				var ajax=null;
				if(window.ActiveXObject){
					ajax=new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(window.XMLHttpRequest){
					ajax = new XMLHttpRequest();
				}
				var para=this.innerHTML;
				var url=encodeURI("/book/listPublish?publish="+para+"&category="+category);
				ajax.open("get", url, true);
				ajax.onreadystatechange=function(){
					if(ajax.readyState==4 && ajax.status==200){
					var data=ajax.responseText;
					Priceul.innerHTML=data;
					$(ul).append("<li><span class='search_key'>版本：</span> <span class='search_val'>"+para+"</span> <span onclick='removePrice()' class='search_del'></span></li>");
					};
				};
				ajax.send();  
			};
		};  
		});
		
function removePrice(){
		console.log("beginremove");
		var Priceul=document.getElementById("PriceUl");
		var ul=$("#showrank>li");
		//console.log(spanName);
		for(var j=0;j<ul.length;j++){
			var li=ul[j];
			var spanName=$(li).first().text();
			//console.log(spanName);
			li.onclick=function(){
				$(li).remove();
			var url="/book/listRemoveFunction?para="+spanName+"&length="+ul.length;
			var ajax=null;
				if(window.ActiveXObject){
					ajax=new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(window.XMLHttpRequest){
					ajax = new XMLHttpRequest();
				}
				ajax.open("get", url, true);
				ajax.onreadystatechange=function(){
					if(ajax.readyState==4 && ajax.status==200){
					var data=ajax.responseText;
					Priceul.innerHTML=data;
					}
				};
				ajax.send();
			};
		}
		
}