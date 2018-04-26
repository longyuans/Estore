		function valueadd(i,totalPrice,price,check){
		
			var chek=document.getElementById(check);
			document.getElementById(i).value++;
			var price1=document.getElementById(price).innerHTML;
			var num=document.getElementById(i).value;
			var allprice=price1*num;
			document.getElementById(totalPrice).innerHTML="￥"+allprice+"元";
			var sumprice=document.getElementById("sumprice2").innerHTML;
			sumprice=sumprice*1+price1*1;
		if(chek.checked==true){
			document.getElementById("sumprice2").innerHTML=sumprice;
			}	
		}
		
		function valuesub(i,totalPrice,price,check){
		var chek=document.getElementById(check);
			if(document.getElementById(i).value<2){
				return 0;
			}else{			
			document.getElementById(i).value -=1;
			var price1=document.getElementById(price).innerHTML;
			var num=document.getElementById(i).value;
			var allprice=price1*num;
			document.getElementById(totalPrice).innerHTML="￥"+allprice+"元";
			
			var sumprice=document.getElementById("sumprice2").innerHTML;
			sumprice=sumprice*1-price1*1;
			if(chek.checked==true){
			document.getElementById("sumprice2").innerHTML=sumprice;
				}
			}
		}
		
		
		function deletebook(bbdiv,name){
		var name2=document.getElementById(name).innerHTML;
		var s="shopCartDelete?name="+name2;
		window.location=s;
		document.getElementById(bbdiv).style.display="none";
		}
		
 		function checkbox(i,check,price){
 			var num2=document.getElementById("num22").innerHTML;
			var chek=document.getElementById(check);
			var num=document.getElementById(i).value;
			var sumprice=document.getElementById("sumprice2").innerHTML;
			var price1=document.getElementById(price).innerHTML;
			var allprice=price1*num;
			if(chek.checked==true){
				sumprice=sumprice*1+allprice*1;
				console.log(sumprice);
				num2=num2-1+2;
				document.getElementById("num22").innerHTML=num2;
				document.getElementById("sumprice2").innerHTML=sumprice;
			}else{
				num2-=1;
				document.getElementById("num22").innerHTML=num2;
				sumprice=sumprice*1-allprice*1;
				document.getElementById("sumprice2").innerHTML=sumprice;
			}
		} 
		
		function selectAlls(){
			var selectAll=document.getElementById("selectAll");
			var checkb=document.getElementsByName("checkboxbook");	
			if(selectAll.checked==true){			
				for(k=0;k<checkb.length;k++){
					checkb[k].checked=true;
					document.getElementById("sumprice2").innerHTML=${shopcartSumprice};
					document.getElementById("num22").innerHTML=${AllOrderLine.size()};
				};
			}else{
				for(k=0;k<checkb.length;k++){
					checkb[k].checked=false;
				document.getElementById("sumprice2").innerHTML=0;
				document.getElementById("num22").innerHTML=0;
				}
			};
		};
		