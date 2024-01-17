$(document).on("click", "#btnnuevo", function(){
    $("#actas_form").validate().resetForm();
    $("#actas_form").find('.error').removeClass('error');
    $("#cbotipoacta").empty();
    $("#txtdestinatario").val("");
    $("#txtemisor").val("");
    $("#txtfecha").val("");
    $("#txtdocumento").val("");
    $("#hddidactas").val("0");
    $.ajax({
            type: "GET",
            url: "/administracion/tipoactas/listar",
            dataType: "json",
            success: function(resultado){
                $.each(resultado, function(index, value){
                    $("#cbotipoacta").append(
                    `<option value="${value.id_tipoacta}">${value.descripcion}</option>`)
                });
            }
        });
    $("#modalactas").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#actas_form").validate().resetForm();
    $("#actas_form").find('.error').removeClass('error');
    $("#modalactas").modal("show");
    $("#cbotipoacta").empty();
    $("#txtdestinatario").val($(this).attr("data-destinatario"));
    $("#txtemisor").val($(this).attr("data-emisor"));
    $("#txtfecha").val($(this).attr("data-fecha"));
    $("#txtdocumento").val($(this).attr("data-documento"));
    $("#hddidactas").val($(this).attr("data-id_acta"));

    var id_tipoacta = $(this).attr("data-id_tipoacta");
    $.ajax({
                type: "GET",
                url: "/administracion/tipoactas/listar",
                dataType: "json",
                success: function(resultado){
                    $.each(resultado, function(index, value){
                        $("#cbotipoacta").append(
                        `<option value="${value.id_tipoacta}">${value.descripcion}</option>`)
                    });
                    $("#cbotipoacta").val(id_tipoacta);
                }
            });
    $("#modalactas").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#actas_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/actas/registrar",
        data: JSON.stringify({
            id_acta: $("#hddidactas").val(),
            destinatario: $("#txtdestinatario").val(),
            emisor: $("#txtemisor").val(),
            fecha: $("#txtfecha").val(),
            documento: $("#txtdocumento").val(),
            id_tipoacta: $("#cbotipoacta").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarActas();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalactas").modal("hide")
        }

    })
});


function listarActas(){
    $.ajax({
        type: "GET",
        url: "/administracion/actas/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblactas > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblactas > tbody").append("<tr>" +
                    "<td>"+value.id_acta+"</td>" +
                    "<td>"+value.tipoActas.descripcion+"</td>" +
                    "<td>"+value.destinatario+"</td>" +
                    "<td>"+value.emisor+"</td>" +
                    "<td>"+value.fecha+"</td>" +
                    "<td>"+value.documento+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-id_acta='"+value.id_acta+"'"+
                    " data-id_tipoacta='"+value.tipoActas.id_tipoacta+"'"+
                    " data-destinatario='"+value.destinatario+"'"+
                    " data-emisor='"+value.emisor+"'"+
                    " data-fecha='"+value.fecha+"'"+
                    " data-documento='"+value.documento+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "</tr>");
            })
        }
    })
}