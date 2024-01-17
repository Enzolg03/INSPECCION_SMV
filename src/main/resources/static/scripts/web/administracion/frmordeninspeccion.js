$(document).on("click", "#btnnuevo", function(){
    $("#ordeninspeccion_form").validate().resetForm();
    $("#ordeninspeccion_form").find('.error').removeClass('error');
    $("#txtnumeroruc").val("");
    $("#txtnombreempresa").val("");
    $("#txtdireccion").val("");
    $("#txtemail").val("");
    $("#txtresponsable").val("");
    $("#hddidorden").val("0");
    $("#modalorden").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#ordeninspeccion_form").validate().resetForm();
    $("#ordeninspeccion_form").find('.error').removeClass('error');
    $("#modalorden").modal("show");
    $("#txtnumeroruc").val($(this).attr("data-numeroruc"));
    $("#txtnombreempresa").val($(this).attr("data-nombreempresa"));
    $("#txtdireccion").val($(this).attr("data-direccion"));
    $("#txtemail").val($(this).attr("data-email"));
    $("#txtresponsable").val($(this).attr("data-responsable"));
    $("#hddidorden").val($(this).attr("data-idorden"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#ordeninspeccion_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/ordeninspeccion/registrar",
        data: JSON.stringify({
            id_orden: $("#hddidorden").val(),
            numeroruc: $("#txtnumeroruc").val(),
            nombreempresa: $("#txtnombreempresa").val(),
            direccion: $("#txtdireccion").val(),
            email: $("#txtemail").val(),
            responsable: $("#txtresponsable").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarOrdenInspeccion();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalorden").modal("hide")
        }

    })
});

function listarOrdenInspeccion(){
    $.ajax({
        type: "GET",
        url: "/administracion/ordeninspeccion/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblorden > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblorden > tbody").append("<tr>" +
                    "<td>"+value.id_orden+"</td>" +
                    "<td>"+value.numeroruc+"</td>" +
                    "<td>"+value.nombreempresa+"</td>" +
                    "<td>"+value.direccion+"</td>" +
                    "<td>"+value.email+"</td>" +
                    "<td>"+value.responsable+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idorden='"+value.id_orden+"'"+
                    " data-numeroruc='"+value.numeroruc+"'"+
                    " data-nombreempresa='"+value.nombreempresa+"'"+
                    " data-direccion='"+value.direccion+"'"+
                    " data-email='"+value.email+"'"+
                    " data-responsable='"+value.responsable+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idorden='"+value.id_orden+"'"+
                    " data-numeroruc='"+value.numeroruc+"'"+
                    " data-nombreempresa='"+value.nombreempresa+"'"+
                    " data-direccion='"+value.direccion+"'"+
                    " data-email='"+value.email+"'"+
                    " data-responsable='"+value.responsable+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar la orden de inspeccion " +
            $(this).attr("data-numeroruc")+"?"+
            $(this).attr("data-nombreempresa")+"?"+
            $(this).attr("data-direccion")+"?"+
            $(this).attr("data-email")+"?"+
            $(this).attr("data-responsable")+"?");
    $("#hddidordeneliminar").val($(this).attr("data-idorden"));
    $("#modaleliminarorden").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/administracion/ordeninspeccion/eliminar",
        data: JSON.stringify({
            id_orden: $("#hddidordeneliminar").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                console.log("actualizado")
                listarOrdenInspeccion();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modaleliminarorden").modal("hide")
        }
    })
});
