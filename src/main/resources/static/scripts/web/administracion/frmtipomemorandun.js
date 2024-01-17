$(document).on("click", "#btnnuevo", function(){
    $("#tipomemo_form").validate().resetForm();
    $("#tipomemo_form").find('.error').removeClass('error');
    $("#txtdescripcion").val("");
    $("#hddidtipomemo").val("0")
    $("#modaltipomemo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#tipomemo_form").validate().resetForm();
    $("#tipomemo_form").find('.error').removeClass('error');
    $("#modaltipomemo").modal("show");
    $("#txtdescripcion").val($(this).attr("data-descripcion"));
    $("#hddidtipomemo").val($(this).attr("data-idtipomemo"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#tipomemo_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/tipomemorandun/registrar",
        data: JSON.stringify({
            id_tipomemorandum: $("#hddidtipomemo").val(),
            descripcion: $("#txtdescripcion").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarTipoMemo();
                Notiflix.Notify.success(resultado.mensaje);
            } else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            alert(resultado.mensaje);
            $("#modaltipomemo").modal("hide")
        }

    })
});

function listarTipoMemo(){
    $.ajax({
        type: "GET",
        url: "/administracion/tipomemorandun/listar",
        dataType: "json",
        success: function(resultado){
            $("#tbltipomemo > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tbltipomemo > tbody").append("<tr>" +
                    "<td>"+value.id_tipomemorandum+"</td>" +
                    "<td>"+value.descripcion+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idtipomemo='"+value.id_tipomemorandum+"'"+
                    " data-descripcion='"+value.descripcion+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idtipomemo='"+value.id_tipomemorandum+"'"+
                    " data-descripcion='"+value.descripcion+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar el tipo de memorandun " +
            $(this).attr("data-descripcion")+"?");
    $("#hddidtipomemoeliminar").val($(this).attr("data-idtipomemo"));
    $("#modaleliminartipomemo").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/administracion/tipomemorandun/eliminar",
        data: JSON.stringify({
            id_tipomemorandum: $("#hddidtipomemoeliminar").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarTipoMemo();
                Notiflix.Notify.success(resultado.mensaje);
            } else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modaleliminartipomemo").modal("hide")
        }
    })
});
