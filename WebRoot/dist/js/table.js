/**
 * Created by yilangli on 11/15/2015.
 */
$(document).ready(function() {
    // Example #1
    $('#typeTable').Tabledit({
        url: 'asset_getType.action',
        columns: {
            identifier: [0, 'id'],
            editable: [[1, 'type_name'], [2, 'type_descr']]
        },
     
        onDraw: function() {
            console.log('onDraw()');
        },
        onSuccess: function(data, textStatus, jqXHR) {
            console.log('onSuccess(data, textStatus, jqXHR)');
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
        },
        onFail: function(jqXHR, textStatus, errorThrown) {
            console.log('onFail(jqXHR, textStatus, errorThrown)');
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        },
        onAlways: function() {
            console.log('onAlways()');
        },
        onAjax: function(action, serialize) {
            console.log('onAjax(action, serialize)');
            console.log(action);
            console.log(serialize);
        }
    });


    $('#modelTable').Tabledit({
        url: 'example.php',
        columns: {
            identifier: [0, 'id'],
            editable: [[1, 'type_id', '{"1": "water quality", "2": "HVAC"}'], [2, 'model_name'], [3, 'model_descr',]]
        },
        onDraw: function() {
            console.log('onDraw()');
        },
        onSuccess: function(data, textStatus, jqXHR) {
            console.log('onSuccess(data, textStatus, jqXHR)');
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
        },
        onFail: function(jqXHR, textStatus, errorThrown) {
            console.log('onFail(jqXHR, textStatus, errorThrown)');
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        },
        onAlways: function() {
            console.log('onAlways()');
        },
        onAjax: function(action, serialize) {
            console.log('onAjax(action, serialize)');
            console.log(action);
            console.log(serialize);
        }
    });

    $('#assetTable').Tabledit({
        url: 'example.php',
        columns: {
            identifier: [0, 'id'],
            editable: [[1, 'model_id', '{"1": "gas station", "2": "downtown"}'], [2, 'asset_name'], [3, 'SN',],[4, 'asset_descr']]
        },
        onDraw: function() {
            console.log('onDraw()');
        },
        onSuccess: function(data, textStatus, jqXHR) {
            console.log('onSuccess(data, textStatus, jqXHR)');
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
        },
        onFail: function(jqXHR, textStatus, errorThrown) {
            console.log('onFail(jqXHR, textStatus, errorThrown)');
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        },
        onAlways: function() {
            console.log('onAlways()');
        },
        onAjax: function(action, serialize) {
            console.log('onAjax(action, serialize)');
            console.log(action);
            console.log(serialize);
        }
    });

    $('#paramTable').Tabledit({
        url: 'example.php',
        columns: {
            identifier: [0, 'id'],
            editable: [[1, 'asset_id', '{"1": "ITU1001", "2": "ITU1002", "3": "ITU1003"}'], [2, 'param_name'], [3, 'unit',],[4, 'param_descr']]
        },
        onDraw: function() {
            console.log('onDraw()');
        },
        onSuccess: function(data, textStatus, jqXHR) {
            console.log('onSuccess(data, textStatus, jqXHR)');
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
        },
        onFail: function(jqXHR, textStatus, errorThrown) {
            console.log('onFail(jqXHR, textStatus, errorThrown)');
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        },
        onAlways: function() {
            console.log('onAlways()');
        },
        onAjax: function(action, serialize) {
            console.log('onAjax(action, serialize)');
            console.log(action);
            console.log(serialize);
        }
    });





});











function typeAdd(){
    $("#typetb").append('<tr id=""> <td><span class="tabledit-span tabledit-identifier"></span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="" disabled=""></td> <td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="type_name" value="" style="display: none;" disabled=""></td> <td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="type_descr" value="" style="display: none;" disabled=""></td> <td style="white-space: nowrap; width: 1%;"><div class="tabledit-toolbar btn-toolbar" style="text-align: left;"> <div class="btn-group btn-group-sm" style="float: none;"><button type="button" class="tabledit-edit-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="tabledit-delete-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-trash"></span></button></div> <button type="button" class="tabledit-save-button btn btn-sm btn-success" style="display: none; float: none;">Save</button> <button type="button" class="tabledit-confirm-button btn btn-sm btn-danger" style="display: none; float: none;">Confirm</button> <button type="button" class="tabledit-restore-button btn btn-sm btn-warning" style="display: none; float: none;">Restore</button> </div></td></tr>');
}

function modelAdd(){
    $("#modeltb").append('<tr id=""> <td><span class="tabledit-span tabledit-identifier"></span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="" disabled=""></td> <td class="tabledit-view-mode"><span class="tabledit-span"></span><select class="tabledit-input form-control input-sm" name="type_id" style="display: none;" disabled=""><option value="1">Black Widow</option><option value="2">Captain America</option><option value="3">Iron Man</option></select></td> <td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="model_name" value="" style="display: none;" disabled=""></td> <td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="model_descr" value="" style="display: none;" disabled=""></td> <td style="white-space: nowrap; width: 1%;"><div class="tabledit-toolbar btn-toolbar" style="text-align: left;"> <div class="btn-group btn-group-sm" style="float: none;"><button type="button" class="tabledit-edit-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="tabledit-delete-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-trash"></span></button></div> <button type="button" class="tabledit-save-button btn btn-sm btn-success" style="display: none; float: none;">Save</button> <button type="button" class="tabledit-confirm-button btn btn-sm btn-danger" style="display: none; float: none;">Confirm</button> <button type="button" class="tabledit-restore-button btn btn-sm btn-warning" style="display: none; float: none;">Restore</button> </div></td></tr>');
}

function assetAdd(){
    $("#assettb").append('<tr id=""><td><span class="tabledit-span tabledit-identifier"></span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><select class="tabledit-input form-control input-sm" name="model_id" style="display: none;" disabled=""><option value="1">gas station</option><option value="2">downtown</option></select></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="asset_name" value="" style="display: none;" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="SN" value="" style="display: none;" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="asset_descr" value="" style="display: none;" disabled=""></td><td style="white-space: nowrap; width: 1%;"><div class="tabledit-toolbar btn-toolbar" style="text-align: left;"><div class="btn-group btn-group-sm" style="float: none;"><button type="button" class="tabledit-edit-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="tabledit-delete-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-trash"></span></button></div><button type="button" class="tabledit-save-button btn btn-sm btn-success" style="display: none; float: none;">Save</button><button type="button" class="tabledit-confirm-button btn btn-sm btn-danger" style="display: none; float: none;">Confirm</button><button type="button" class="tabledit-restore-button btn btn-sm btn-warning" style="display: none; float: none;">Restore</button></div></td></tr>');
}

function paramAdd(){
    $("#paramtb").append('<tr  id=""><td><span class="tabledit-span tabledit-identifier"></span><input class="tabledit-input tabledit-identifier" type="hidden" name="id" value="" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><select class="tabledit-input form-control input-sm" name="asset_id" style="display: none;" disabled=""><option value="1">ITU1001</option><option value="2">ITU1002</option><option value="3">ITU1003</option></select></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="param_name" value="" style="display: none;" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="unit" value="" style="display: none;" disabled=""></td><td class="tabledit-view-mode"><span class="tabledit-span"></span><input class="tabledit-input form-control input-sm" type="text" name="param_descr" value="" style="display: none;" disabled=""></td><td style="white-space: nowrap; width: 1%;"><div class="tabledit-toolbar btn-toolbar" style="text-align: left;"><div class="btn-group btn-group-sm" style="float: none;"><button type="button" class="tabledit-edit-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-pencil"></span></button><button type="button" class="tabledit-delete-button btn btn-sm btn-default" style="float: none;"><span class="glyphicon glyphicon-trash"></span></button></div><button type="button" class="tabledit-save-button btn btn-sm btn-success" style="display: none; float: none;">Save</button><button type="button" class="tabledit-confirm-button btn btn-sm btn-danger" style="display: none; float: none;">Confirm</button><button type="button" class="tabledit-restore-button btn btn-sm btn-warning" style="display: none; float: none;">Restore</button></div></td></tr>');
}

function alertAdd(){
    $("#alerttb").append();
}