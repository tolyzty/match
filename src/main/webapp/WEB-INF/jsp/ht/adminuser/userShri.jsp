<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <jsp:include page="/WEB-INF/jsp/plug/_link.jsp" />
  </head>
 
  <body>
<h2 style="text-align: center;">新增用户角色

</h2>
<input type="hidden" name="uuid" value="${uId}" id="uuid"/>
 <div class="zTreeDemoBackground" style="padding-left:20px;padding-right:20px">
 <input type="hidden" value=""  name="menuObj" id="menuObj"/>
 <input type="hidden" value="" name="menuRid" id="menuRid" />
       <ul id="treeUser" class="ztree"></ul>
    </div>
    <div style="margin-top:10px;padding-left:20px;padding-right:20px;text-align: center;" id="btann">
     
    </div>
    
  </body>

<script type="text/javascript">
var zTree; //定义Ztree对象
  var setting = {
  		 view: {
		    dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
		    showLine: true,//是否显示节点之间的连线
		    selectedMulti: false //设置是否允许同时选中多个节点
  		},async:{
  			enable:true,
  			type:"post",
  			url:"<%=path%>/ht/adminuser/RoleMenu.do"
  		},data:{
  			simpleData: {
				enable:true,
				 idKey: "roleId",    //设置节点唯一标识属性名称  
                 //pIdKey: "menuParId",      //设置父节点唯一标识属性名称  
                 //rootPId: 0
			},
			key:{
				name:"roleName",
			},
		},check:{
			enable : true   //是否有复选框：true?false	
		},callback: {
			onCheck: zTreeOnCheck,
		},edit:{
		  enable : false //是否允许编辑
		},showLine:false  //是否允许有下划线
		
  };
  	 var treeNodes;  //定义Ztree的节点数组


  	 var uuid=$('#uuid').val();
  	 var url = '<%=path%>/ht/adminuser/roleAdd.do';
  	 var urls = '<%=path%>/ht/adminuser/roleAddk.do';
  	 var inp = ' <input class="btn btn-primary radius" type="button" name="butto" value="保存权限信息" id="menuad" />';
  $(document).ready(function(){  
	  $.ajax({
			async : false, 
	        cache:false,  
	        type: "post",  
	        dataType : "json",  //返回json格式	    
	        url: "<%=path%>/ht/adminuser/RoleMenu.do",  //请求的URL路径  
	        beforeSend:function(){}, 
	        success:function(data){ //请求成功后处理函数。  	
	         //请求后返回的json是字符串，需要用eval()函数转换成Object类型
	            treeNodes = data.jsonMaps;
	            if(treeNodes!=''){
	            	 findUid();
	            	$('#btann').append(inp);
	            }           
	        },error: function () {//请求失败处理函数  
	            alert('请求失败');  
			 } 
	     }); 
	  /*    var t = $("#treeDemos");
	     t = $.fn.zTree.init(t,setting,zNodes); */
	     
	     $.fn.zTree.init($("#treeUser"), setting, treeNodes);
    	// zTree = $.fn.zTree.init($("#treeDemo"), setting, treeNodes);

  	 });
  
  /**
  *点击获取选中
  */
  function zTreeOnCheck(e,treeId,treeNode){
         var treeObj=$.fn.zTree.getZTreeObj("treeUser"),
          nodes=treeObj.getCheckedNodes(true),
            v="";
         for(var ii=0;ii<nodes.length;ii++){
            v+=nodes[ii].roleId + ",";
    		};
    		$('#menuObj').val(v);
    		$('#menuRid').val(uuid);
 }; 
 
 
 
 
 
 //获取某角色的权限
 function findUid(){ 
 	$.ajax({
	 	  url:'<%=path%>/ht/adminuser/findUid.do',
	 	  data:{uId:$('#uuid').val()},
	 	  dataType:'JSON',
	 	  type:'post',
	 	  success:function(data){
		 	var newsobj = data.lists;
		 	 if(newsobj==""){
			    $("#menuad").on('click',function(){
			        findparam(urls);
			    });
	 	     }else{
	 	          var zTrees = $.fn.zTree.getZTreeObj("treeUser");//实例化
	 	         
	 	          for(var i=0;i<newsobj.length;i++){
				      var node = zTrees.getNodeByParam("roleId",newsobj[i].rId);//为节点赋值参数
				       zTrees.checkNode(node, true, true); 
	 	           };
	 	           
	 	        var nodes=zTrees.getCheckedNodes(true),v=""; //获取已选
	 	         for(var ir=0;ir<nodes.length;ir++){
	                 v+=nodes[ir].roleId + ",";
	    		  }; 
	    		   $('#menuObj').val(v);
		    	   $('#menuRid').val(uuid);  
	 	    	 $("#menuad").on('click',function(){
			        findparam(url);
			    }); 
	 	     }
	 	     
	 	  },
		});
 };
 
 function findparam(url){
 $.ajax({
   url:url,
   type: "post",  
   dataType : "json",  //返回json格式	    
   data:{menuObj:$('#menuObj').val(),menuRid:$('#menuRid').val()},
   success:function(data){
     if(data.msg=="success"){
        laymsg('保存权限成功',1);
     }else{
         laymsg('保存权限失败',2);
     }
   }
 });
 
 };
 
</script>

</html>