/**
 * Created by yilangli on 11/12/2015.
 */

function typeChanged(value){

    $.post("asset_getModel.action",{type_id:value},callModel,"json");
}

function modelChanged(value){

    $.post("asset_getAsset.action",{model_id:value},callAsset,"json");
}

function assetChanged(value) {

    $.post("asset_getParam.action",{asset_id:value},callParam,"json");
}
function paramChanged() {

	/* $("#param :selected").each(function () {
         alert(this.value+" "+this.text);
     }

 );*/
}


function callModel(data){
    var inner="";
    $(data).each(function(){
        inner=inner+"<option "+"value="+this.asset_id+">"+this.name+"</option>";
    });
  
    $("#assetModel").html(inner);
}

function callAsset(data){
    var inner="";
    $(data).each(function(){
        inner=inner+"<option "+"value="+this.asset_id+">"+this.name+"</option>";
    });
    $("#asset").html(inner);
}

function callParam(data){
    var inner="";
    $(data).each(function(){
        inner=inner+"<option "+"value="+this.param_id+">"+this.param_name+"</option>";
    });
  
    $("#param").html(inner);
}