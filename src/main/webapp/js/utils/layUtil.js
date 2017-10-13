/**
 * @returns
 */
function getSearchForm(){
//	return $(".search_panel form");
	return $("#myform");
}

/**
 * 获取查询条件
 * @returns
 */
function getSearchParams(){
	var $form = getSearchForm();//查询条件表单
	return getFormParams($form);
}

/**
*新增ajax操作
*@param  url 请求的地址
*@param  data form表单的数据
**/
function addajax(url,data,msg,msger,icon,iconer){
	 $.ajax({type:"post",url:url,data:data,dataType:'JSON',
		success:function(data){
		  if(data.msg=="success"){	
			  laymsg(msg,icon);
		  }else if (data.msg="errorException") {
			  laymsg("对不起用户名手机号重复",2);	
		  }else{
			  laymsg(msger,iconer);
		  }
		},
	 }); 
};
function editajax(url,data,msg,msger,icon,iconer){//编辑方法
	   $.ajax({type:"post",url:url,data:data,dataType:"JSON",
			success:function(data){
				if(data.msg=="success"){	
					laymsg(msg,icon);		
				}else if (data.msg="errorException") {
					  laymsg("对不起系统异常",2);	
				}else{
					laymsg(msger,iconer);
				}
				},
		   }); 
};


/**
 * 根据传递的参数执行编辑操作
 * @param url 请求地址
 * @param data 请求参数
 * @param msg  正确信息文本
 * @param msger 错误信息文本
 * @param icon  图标样式 1是正确,2是错误
 * @param iconer 错误图片样式
 */
function editResult(url,data,msg,msger){
	$.ajax({
		type:"POST",
		url:url,
		data:data,
		dataType:"JSON",
		success:function(data){
			if (data.msg=='success') {
				laymsg(msg,1);
			}else{
				laymsg(msger,2);
			}
		}
	});
	
};

/**
*弹出框  新增弹出框
*@param  msg 文本信息
*@param  icons 图标样式 1：v 2:x 3:？ 
**/
function laymsg(msg,icons){
		layer.alert(msg,{icon: icons,
			skin: 'layer-ext-moon',offset:'50px' },function(index){//skin 皮肤样式,offset坐标位置
			var index = parent.layer.getFrameIndex(window.name);//获取当前页面窗口
			window.parent.location.reload(); //刷新父级页面
			parent.layer.close(index);//关闭当前页面
			layer.close(index); //关闭弹出框
	});					
};

/**
 * 信息提示框
 * @param msg 传入一个文本
 * @param icons 图标样式 1：v 2:x 3:？ 
 */
function lay(msg,icons){
	layer.alert(msg,{icon: icons,
		    skin: 'layer-ext-moon',offset:'150px' },function(index){
		layer.close(index);
	});				
};

/**
 *radio样式 
 *@param inputskin input选择器  例$('.skin-minimal input')
 */
function icheck(inputskin){
	 inputskin.iCheck({
	    		checkboxClass: 'icheckbox-blue',
	    		radioClass: 'iradio-blue',
	    		increaseArea: '20%'
	    	});
};
/**
 * @param url 请求地址
 * @param downUrl 下载地址
 * @param table 表格选择器
 * @param th 标题
 * @param varId 标题数组集合
 * @param formId formID选择器
 * @author 13376
 */
function download(url,downUrl,table,th,varId,formId){
	var vas = "";
  	table.find("th").each(function(i){
     	if (i>0) {
			vas+=$(this).text()+",";
		}
      }); 
      varId.val(vas);
	var formdata = formId.serializeArray();
	var index;
	  $.ajax({
		  url:url,
		  data:formdata,
		  type:"POST",
		  beforeSend:function(){
			index = layer.load();  
		  },
		  success:function(data){
			if (data.msg=="success") {
				//layer.close(index); 
				layer.alert("导出成功");
				location.href=downUrl;
			}else{
				layer.alert("导出失败");
			}
		  },complete: function(){
			  layer.close(index); 
		  },error:function(){
			  layer.alert("导出失败");
		  }
		});
};

/**
 * @param time 时间,单位秒
 * @param idSelector id选择器或者Class选择器
 * @author 13376 
 */
function countTime(time,idSelector){
	var time=60;
	var validCode=true;
	if (validCode) {
	validCode=false;
	//$("#verifyYz").addClass("msgs1");
	idSelector.attr("disabled","true");
	var t=setInterval(function(			
	) {time--;idSelector.val(time+"秒");
	if (time==0) {
		//$(".btn").removeClass("msgs1");
		idSelector.removeAttr("disabled");
		clearInterval(t);
		idSelector.val("重新获取");
		validCode=true;
	}},1000);
	
	}
 };
 /**
  * 验证手机号是否存在
  * @param url 请求地址
  * @param data 所有参数
  * @param time 设置倒计时时间;
  * @param idSelector ID选择器
  */
 function validPhone(url,data,time,idSelector){
	 alert(time);
	 alert(idSelector);
	var msg = "手机号不存在";
	$.ajax({
		url:url,
		data:data,
		type:"POST",
		success:function(data){
		  if (data.msg=='error') {
			  lay(msg, 2);
			  return false;
		  }else{
			  countTime(time, idSelector);
		  }
		}
	});
 };
 
 /**
  * 验证手机验证码是否正确
  * @param url
  * @param data
 function validCode(url,data){
	 var msg = "验证码错误";
	 $.ajax({
		  url:url,
		  data:data,
		  type:"POST",
		  success:function(data){
		    if (data.msg!="success") {
		      lay(msg, 2);
			}
		    return false;
		  }
	 });
 };*/