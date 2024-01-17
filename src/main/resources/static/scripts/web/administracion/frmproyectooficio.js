$(document).on("click", "#btnnuevo", function(){
    $("#proyectooficio_form").validate().resetForm();
    $("#proyectooficio_form").find('.error').removeClass('error');
    //alert("Hola estoy con JQUERY");
    $("#cboorden").empty();
    $("#txtasunto").val("");
    $("#txtinfo").val("");
    $("#txtreferencia").val("");
    $("#hddidproyectooficio").val("0");
    $.ajax({
            type: "GET",
            url: "/administracion/ordeninspeccion/listar",
            dataType: "json",
            success: function(resultado){
                $.each(resultado, function(index, value){
                    $("#cboorden").append(
                    `<option value="${value.id_orden}">${value.id_orden}</option>`)
                });
            }
        });
    $("#modalproyectooficio").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#proyectooficio_form").validate().resetForm();
    $("#proyectooficio_form").find('.error').removeClass('error');
    $("#modalproyectooficio").modal("show");
    $("#cboorden").empty();
    $("#txtasunto").val($(this).attr("data-asunto"));
    $("#txtinfo").val($(this).attr("data-informacionrequerida"));
    $("#txtreferencia").val($(this).attr("data-referencia"));
    $("#hddidproyectooficio").val($(this).attr("data-idproyecto"));

    var id_orden = $(this).attr("data-idorden");
    $.ajax({
                type: "GET",
                url: "/administracion/ordeninspeccion/listar",
                dataType: "json",
                success: function(resultado){
                    $.each(resultado, function(index, value){
                        $("#cboorden").append(
                        `<option value="${value.id_orden}">${value.id_orden}</option>`)
                    });
                    $("#cboorden").val(id_orden);
                }
            });
    $("#modalproyectooficio").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#proyectooficio_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/proyectooficio/registrar",
        data: JSON.stringify({
            id_proyecto: $("#hddidproyectooficio").val(),
            id_orden: $("#cboorden").val(),
            asunto: $("#txtasunto").val(),
            informacionrequerida: $("#txtinfo").val(),
            referencia: $("#txtreferencia").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProyectos();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalproyectooficio").modal("hide")
        }

    })
});


function listarProyectos(){
    $.ajax({
        type: "GET",
        url: "/administracion/proyectooficio/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblproyecto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproyecto > tbody").append("<tr>" +
                    "<td>"+value.id_proyecto+"</td>" +
                    "<td>"+value.ordenInspeccion.id_orden+"</td>" +
                    "<td>"+value.asunto+"</td>" +
                    "<td>"+value.informacionrequerida+"</td>" +
                    "<td>"+value.referencia+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idproyecto='"+value.id_proyecto+"'"+
                    " data-idorden='"+value.ordenInspeccion.id_orden+"'"+
                    " data-asunto='"+value.asunto+"'"+
                    " data-informacionrequerida='"+value.informacionrequerida+"'"+
                    " data-referencia='"+value.referencia+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "</tr>");
            })
        }
    })
}