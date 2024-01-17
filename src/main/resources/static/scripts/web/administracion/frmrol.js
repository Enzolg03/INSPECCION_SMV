$(document).on("click", "#btnnuevo", function(){
    $("#rol_form").validate().resetForm();
    $("#rol_form").find('.error').removeClass('error');
    $("#txtnomrol").val("");
    $("#hddidrol").val("0")
    $("#modalrol").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#rol_form").validate().resetForm();
    $("#rol_form").find('.error').removeClass('error');
    $("#modalrol").modal("show");
    $("#txtnomrol").val($(this).attr("data-nomrol"));
    $("#hddidrol").val($(this).attr("data-idrol"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#rol_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/rol/registrar",
        data: JSON.stringify({
            idrol: $("#hddidrol").val(),
            nomrol: $("#txtnomrol").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarRoles();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalrol").modal("hide")
        }

    })
});

function listarRoles(){
    $.ajax({
        type: "GET",
        url: "/administracion/rol/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblroles > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblroles > tbody").append("<tr>" +
                    "<td>"+value.idrol+"</td>" +
                    "<td>"+value.nomrol+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idrol='"+value.idrol+"'"+
                    " data-nomrol='"+value.nomrol+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idrol='"+value.idrol+"'"+
                    " data-nomrol='"+value.nomrol+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar el rol " +
            $(this).attr("data-nomrol")+"?");
    $("#hddidroleliminar").val($(this).attr("data-idrol"));
    $("#modaleliminarrol").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/administracion/rol/eliminar",
        data: JSON.stringify({
            idrol: $("#hddidroleliminar").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarRoles();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }

            $("#modaleliminarrol").modal("hide")
        }
    })
});
