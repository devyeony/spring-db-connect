var contextPath;

$(function(){
	/* Context Path */
	contextPath = $('#contextPath').val();
	
	$('#run_rule_btn').click(function(){
		let top_level = $('#top_level').val();
		let middle_level = $('#middle_level').val();
		let bottom_level = $('#bottom_level').val();
		
		if(top_level == '' || top_level == 'top_level_value'){
			$('#run_rule_result_area').empty();
			$('#run_rule_result_area').append(
				'<br/>' +
				'<textarea class="form-control" rows="6" style="resize: none;" readonly>' +
					'대분류를 입력하세요.' + 
				'</textarea>'
			);
			return;
		} else if(middle_level == 'middle_level_value'){
			middle_level = '';
			bottom_level = '';
		} else if(bottom_level == 'bottom_level_value'){
			bottom_level = '';
		}
		document.getElementById('loadingArea').style.display='block';
		runRuleCompiler(top_level, middle_level, bottom_level)
	});
});

function runRuleCompiler(top_level, middle_level, bottom_level){
	$.ajax({
		//요청
		type: "POST",
		url: contextPath + "/rule/runRuleCompiler", 
		data: {
			top_level_id: top_level,
			middle_level_id: middle_level,
			bottom_level_id: bottom_level
		},
		async: false,
			
		//응답
		success : function(response){  
			var json = JSON.parse(response);
			var list = json.item.list;
			
			$('#run_rule_result_area').empty();
			appendRunRuleResultArea(list);
			document.getElementById('loadingArea').style.display='none';
		},
		error : function(request, status, error) {
			//alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error + "서버에러");
		}
	}); //ajax
}

function appendRunRuleResultArea(list){
	if(list == ''){
		$('#run_rule_result_area').append(
			'<br/>' +
			'<textarea class="form-control" rows="6" style="resize: none;" readonly>' +
				'작성된 전사규칙이 없습니다.' + 
			'</textarea>'
		);
	}
	
	for(let i=0; i<list.length; i++){
		var top_level_name = list[i].top_level_name;
				
		var middle_level_name = list[i].middle_level_name;
				
		var bottom_level_id = list[i].bottom_level_id;
				
		var bottom_level_name = list[i].bottom_level_name;
		
		var result = list[i].result;
				
		$('#run_rule_result_area').append(
			'<br xmlns="http://www.w3.org/1999/html"/>' +
			'<div style="margin-bottom:5px;">' +  
				'<b>대분류 : </b>' + top_level_name + '<br/>' +
				'<b>중분류 : </b>' + middle_level_name + '<br/>' +
				'<b>소분류 : </b>' + bottom_level_name + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
				'<form action="'+ contextPath+'/resultRuleDocx' +'" method="get" target="ifrm">' +
				'<input type="hidden" value="'+ bottom_level_id +'" name="bottom_level_id" id="bottom_level_id">'+
				'<button type="submit" class="btn btn-primary btn-icon-split" style="float: right;">'+
					'<span class="icon text-white-50"><i class="fas fa-download fa-sm text-white-50"></i></span>' +
					'<span class="text"> Word</span></button>'+
				'<iframe name="ifrm" width="0" height="0" frameborder="0"></iframe>' +
				'</form>'+
			'</div><br/><br/>' +
			'<textarea class="form-control" rows="6" style="resize: none;" readonly>' +
				result + 
			'</textarea>'
		);
	}
}