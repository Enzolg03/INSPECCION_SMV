$(document).on("click", "#btnnuevo", function(){
    $("#tipoactas_form").validate().resetForm();
    $("#tipoactas_form").find('.error').removeClass('error');
    $("#txtdescripcion").val("");
    $("#hddidtipoacta").val("0")
    $("#modaltipoacta").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#tipoactas_form").validate().resetForm();
    $("#tipoactas_form").find('.error').removeClass('error');
    $("#modaltipoacta").modal("show");
    $("#txtdescripcion").val($(this).attr("data-descripcion"));
    $("#hddidtipoacta").val($(this).attr("data-idtipoacta"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#tipoactas_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/tipoactas/registrar",
        data: JSON.stringify({
            id_tipoacta: $("#hddidtipoacta").val(),
            descripcion: $("#txtdescripcion").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarTipoActas();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modaltipoacta").modal("hide")
        }
    })
});

function listarTipoActas(){
    $.ajax({
        type: "GET",
        url: "/administracion/tipoactas/listar",
        dataType: "json",
        success: function(resultado){
            $("#tbltipoacta > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tbltipoacta > tbody").append("<tr>" +
                    "<td>"+value.id_tipoacta+"</td>" +
                    "<td>"+value.descripcion+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idtipoacta='"+value.id_tipoacta+"'"+
                    " data-descripcion='"+value.descripcion+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idtipoacta='"+value.id_tipoacta+"'"+
                    " data-descripcion='"+value.descripcion+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar el tipo acta " +
            $(this).attr("data-descripcion")+"?");
    $("#hddidtipoactaeliminar").val($(this).attr("data-idtipoacta"));
    $("#modaleliminartipoacta").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/administracion/tipoactas/eliminar",
        data: JSON.stringify({
            id_tipoacta: $("#hddidtipoactaeliminar").val()
        }),
         success: function(resultado){
         if(resultado.respuesta){
            listarTipoActas();
            Notiflix.Notify.success(resultado.mensaje);
         } else{
            Notiflix.Notify.failure(resultado.mensaje);
         }
         $("#modaleliminartipoacta").modal("hide")
         }
    })
});
