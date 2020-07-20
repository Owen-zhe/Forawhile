/**
 * @param {Object} id 传递调用者的id号 
 * 根据id号更改样式
 */
//优化 通过id更改样式 使每一张图片具有独立性
function change_photo_star_button(id){
	var id_icon=id+"icon";
	//如果是非收藏改为收藏
	if(document.getElementById(id).className=="ui star outline button"){
		document.getElementById(id).setAttribute("class","ui star button");
		document.getElementById(id).setAttribute("data-tooltip","取消收藏");
		document.getElementById(id_icon).setAttribute("class","star icon");
	}
	//如果是收藏改为非收藏
	else{
		document.getElementById(id).setAttribute("class","ui star outline button");
		document.getElementById(id).setAttribute("data-tooltip","收藏");
		document.getElementById(id_icon).setAttribute("class","star outline icon");
	}
}


/**
 * @param {Object} id 传递调用者需要隐藏或显示的lables组件的id号
 * 注意 字符造的传递用‘’包裹 而不是双引号
 */
function show_photo_tags(id){
	if(document.getElementById(id).className=="ui tag labels visible"){
		document.getElementById(id).setAttribute("class","ui tag labels hidden");
	}
	else{
		document.getElementById(id).setAttribute("class","ui tag labels visible");
	}
}

/**
 * @param {Object} id
 * 显示删除提示框
 */
function confirm_delete_photo(id){
	document.getElementById('photo_delete_info').innerText="是否删除id："+id+"的照片";
	document.getElementById('photo_delete_modal').value=id;
	$('.mini.delete.modal').modal('show');
}
function show_import_photo(){
	$('.ui.import.basic.modal').modal('show');
}


//待完善////////////////////////////////////////////////////////////////////////////////////
//传递文件属性
function add_photo_button_fun(){
	var batchUpload=document.getElementById("add_photo_buttonid").click();
	
	var photo_file=document.getElementById("add_photo_buttonid").file;
	
}
//删除后重组控件显示 
function remove_photo(id){
	var id_card=id+"card";
}

//添加图片模块 传递信息
