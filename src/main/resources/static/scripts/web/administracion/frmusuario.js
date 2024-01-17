$(document).on("click", "#btnnuevo", function(){
    $("#usuario_form").validate().resetForm();
    $("#usuario_form").find('.error').removeClass('error');
    $("#txtnomusuario").val("");
    $("#txtemail").val("");
    $("#txtpassword").val("");
    $("#txtnombres").val("");
    $("#txtapellidos").val("");
    $("#rol").val("");
    $("#hddidusuario").val("0");
    $("#modalusuario").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#usuario_form").validate().resetForm();
    $("#usuario_form").find('.error').removeClass('error');
    $("#modalusuario").modal("show");
    $("#txtnomusuario").val($(this).attr("data-nomusuario"));
    $("#txtemail").val($(this).attr("data-email"));
    $("#txtnombres").val($(this).attr("data-nombres"));
    $("#txtapellidos").val($(this).attr("data-apellidos"));
    $("#rol").val($(this).attr("data-rol"));
    $("#hddidusuario").val($(this).attr("data-idusuario"));
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#usuario_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        url: "/auth/guardarusuario",
        data: {
            idusuario: $("#hddidusuario").val(),
            nomusuario: $("#txtnomusuario").val(),
            email: $("#txtemail").val(),
            password: $("#txtpassword").val(),
            nombres: $("#txtnombres").val(),
            apellidos: $("#txtapellidos").val(),
            rol: $("#rol").val()
        },
        success: function(resultado){
            if(resultado.respuesta){
                listarUsuarios();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalusuario").modal("hide")
        }
    })
});



function listarUsuarios(){
    $.ajax({
        type: "GET",
        url: "/auth/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblusuario > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblusuario > tbody").append("<tr>" +
                    "<td>"+value.nomusuario+"</td>" +
                    "<td>"+value.email+"</td>" +
                    "<td>"+value.nombres+"</td>" +
                    "<td>"+value.apellidos+"</td>" +
                    "<td>"+value.roles[0].nomrol+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idusuario='"+value.idusuario+"'"+
                    " data-nomusuario='"+value.nomusuario+"'"+
                    " data-email='"+value.email+"'"+
                    " data-nombres='"+value.nombres+"'"+
                    " data-apellidos='"+value.apellidos+"'"+
                    " data-rol='"+value.roles[0].nomrol+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminar'"+
                    " data-idusuario='"+value.idusuario+"'"+
                    " data-nomusuario='"+value.nomusuario+"'"+
                    " data-email='"+value.email+"'"+
                    " data-nombres='"+value.nombres+"'"+
                    " data-apellidos='"+value.apellidos+"'"+
                    " data-rol='"+value.roles[0].nomrol+"'>"+
                    "<i class='bi bi-trash'></i>"+
                    "</button></td></tr>");
            })
        }
    })
}


$(document).on("click", ".btneliminar", function(){
    $("#lblmensajeeliminar").text("¿Está seguro de eliminar el usuario " +
            $(this).attr("data-nomusuario")+"?");
    $("#hddidusuarioeliminar").val($(this).attr("data-idusuario"));
    $("#modaleliminarusuario").modal("show");
});


$(document).on("click", "#btneliminar", function(){
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/auth/eliminarusuario",
        data: JSON.stringify({
            idusuario: $("#hddidusuarioeliminar").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                console.log("actualizado")
                listarUsuarios();
                Notiflix.Notify.success(resultado.mensaje);
            }else {
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modaleliminarusuario").modal("hide")
        }
    })
});
