$(document).on("click", "#btnnuevo", function(){
    $("#observaciones_form").validate().resetForm();
    $("#observaciones_form").find('.error').removeClass('error');
    $("#txtfecha").val("");
    $("#txtobservacion1").val("");
    $("#txtobservacion2").val("");
    $("#txtobservacion3").val("");
    $("#hddidobservaciones").val("0");
    $("#modalobservaciones").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#observaciones_form").validate().resetForm();
    $("#observaciones_form").find('.error').removeClass('error');
    $("#modalobservaciones").modal("show");
    $("#txtfecha").val($(this).attr("data-fecha"));
    $("#txtobservacion1").val($(this).attr("data-observacion1"));
    $("#txtobservacion2").val($(this).attr("data-observacion2"));
    $("#txtobservacion3").val($(this).attr("data-observacion3"));
    $("#hddidobservaciones").val($(this).attr("data-idobservacion"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#observaciones_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/observaciones/registrar",
        data: JSON.stringify({
            id_observacion: $("#hddidobservaciones").val(),
            fecha: $("#txtfecha").val(),
            observacion1: $("#txtobservacion1").val(),
            observacion2: $("#txtobservacion2").val(),
            observacion3: $("#txtobservacion3").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarObservaciones();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalobservaciones").modal("hide")
        }

    })
});

function listarObservaciones(){
    $.ajax({
        type: "GET",
        url: "/administracion/observaciones/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblobs > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblobs > tbody").append("<tr>" +
                    "<td>"+value.id_observacion+"</td>" +
                    "<td>"+value.fecha+"</td>" +
                    "<td>"+value.observacion1+"</td>" +
                    "<td>"+value.observacion2+"</td>" +
                    "<td>"+value.observacion3+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idobservacion='"+value.id_observacion+"'"+
                    " data-fecha='"+value.fecha+"'"+
                    " data-observacion1='"+value.observacion1+"'"+
                    " data-observacion2='"+value.observacion2+"'"+
                    " data-observacion3='"+value.observacion3+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idobservacion='"+value.id_observacion+"'"+
                    " data-fecha='"+value.fecha+"'"+
                    " data-observacion1='"+value.observacion1+"'"+
                    " data-observacion2='"+value.observacion2+"'"+
                    " data-observacion3='"+value.observacion3+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar las observaciones " +
            $(this).attr("data-fecha")+"?"+
            $(this).attr("data-observacion1")+"?"+
            $(this).attr("data-observacion2")+"?"+
            $(this).attr("data-observacion3")+"?");
    $("#hddidobseliminar").val($(this).attr("data-idobservacion"));
    $("#modaleliminarobs").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/administracion/observaciones/eliminar",
        data: JSON.stringify({
            id_observacion: $("#hddidobseliminar").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarObservaciones();
                Notiflix.Notify.success(resultado.mensaje);
            } else {
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modaleliminarobs").modal("hide")
        }
    })
});
