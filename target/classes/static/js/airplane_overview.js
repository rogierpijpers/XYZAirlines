 $(document).ready(function() {
    $('#airplaneTable').DataTable({
        "processing" : true,
        "ajax" : {
            "url" : "http://localhost:8080/api/airplane/",
            dataSrc : ''
        },
        "columns" : [ {
            "data" : "id"
        }, {
            "data" : "fuelLevel"
        }, {
            "data" : "type"
        }, {
            "data" : "location.name"
        }, {
            "defaultContent":
            `
            <button class="updateAirplane btn btn-primary" type="button" data-toggle="modal" data-target="#airPlaneModal">O</button>
        `}, {
            "defaultContent":
            `
            <button class="flyButton btn btn-primary" type="button" data-toggle="modal" data-target="#flightPathModal">></button>
        `}
        ]
    });

    $.ajax({
        url: '/api/airport/',
        type: 'get',
        success:function(response){
            var len = response.length;
            $("#selectLocation").empty();
            for( var i = 0; i<len; i++){
                var id = response[i]['id'];
                var name = response[i]['name'];
                $("#selectLocation").append("<option value='"+id+"'>"+name+"</option>");
                $("#selectDestination").append("<option value='"+id+"'>"+name+"</option>");
            }
        }
    });

    var table = $('#airplaneTable').DataTable();
    var tableBody = '#airplaneTable tbody';
    $(tableBody).on("click", ".updateAirplane", function () {
         var cursor = table.row($(this).parents('tr'));
         var data=cursor.data();

        $("#selectLocation").val(data.location.id);
        $("#selectLocation").prop("disabled", true);
        $("#inputType").val(data.type);
        $("#inputFuel").val(data.fuelLevel);
        $("#airplaneId").val(data.id);
        $("#insertType").val("update");
    });

    $(tableBody).on("click", ".flyButton", function () {
         var cursor = table.row($(this).parents('tr'));
         var data=cursor.data();
        $("#flightPathAirplaneId").val(data.id);
        $("#currentLocation").val(data.location.name);
    });

    $("#addAirplaneButton").on("click", function(){
        $("#addAirplaneForm")[0].reset();
         $("#selectLocation").prop("disabled", false);
        $("#insertType").val("insert");
    });

    $("#submitAirplane").on("click", function(){
        if($("#editType").val() === "insert"){
            addAirplane();
        }else{
            updateAirplane();
        }
    });

    $("#submitFlightPath").on("click", function(){
        var destinationId = $("#selectDestination").val();
        var airplaneId = $("#flightPathAirplaneId").val();

        var data = {
            airplaneId: airplaneId,
            destinationId: destinationId
        }

        $.ajax({
            url: '/api/airplane/fly/',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json",
            success:function(response){
                console.log(response);
                refresh();
            },
            error: function(error) {
              console.log(error);
            }
        });
    });

    function addAirplane(){
         var data = getDataFromModal();
         $.ajax({
            url: '/api/airplane/',
            type: 'POST',
            data: data,
            contentType: "application/json",
            success:function(response){
                console.log(response);
                refresh();
            },
            error: function(error) {
              console.log(error);
            }
        });
    }

    function updateAirplane(){
         var data = getDataFromModal();
         $.ajax({
            url: '/api/airplane/'+data.id,
            type: 'PUT',
            data: data,
            contentType: "application/json",
            success:function(response){
                console.log(respone);
                refresh();
            },
            error: function(error) {
              console.log(error);
            }
        });
    }

    function getDataFromModal(){
        var locationId = $("#selectLocation").val();
        var type = $("#inputType").val();
        var fuelLevel = $("#inputFuel").val();
        var id = $("#airplaneId").val();

        var data = {
            location: {
                id: locationId
            },
            type: type,
            fuelLevel: fuelLevel,
            id: id
        }

        return JSON.stringify(data);
    }

    function refresh(){
        $('#airplaneTable').DataTable().ajax.reload();
        $("#addAirplaneForm")[0].reset();
    }
});