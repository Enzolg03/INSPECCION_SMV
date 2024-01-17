$(document).on("click", "#btnnuevo", function(){
    $("#memorandum_form").validate().resetForm();
    $("#memorandum_form").find('.error').removeClass('error');
    $("#cbotipo").empty();

    $("#txtdestinatario").val("");
    $("#txtemisor").val("");
    $("#txtasunto").val("");
    $("#txtreferencia").val("");
    $("#txtfecha").val("");
    $("#hddidmemorandum").val("0");

    $.ajax({
        type: "GET",
        url: "/administracion/tipomemorandun/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbotipo").append(
                    `<option value="${value.id_tipomemorandum}">${value.descripcion}</option>`
                );
            });
            $("#modalMemorandum").modal("show");
        }
    });
});


$(document).on("click", ".btnactualizar", function(){
    $("#memorandum_form").validate().resetForm();
    $("#memorandum_form").find('.error').removeClass('error');
    $("#modalMemorandum").modal("show");
    $("#cbotipo").empty();
    $("#txtdestinatario").val($(this).attr("data-destinatario"));
    $("#txtemisor").val($(this).attr("data-emisor"));
    $("#txtasunto").val($(this).attr("data-asunto"));
    $("#txtreferencia").val($(this).attr("data-referencia"));
    $("#txtfecha").val($(this).attr("data-fecha"));
    $("#hddidmemorandum").val($(this).attr("data-idmemorandum"));

    var id_tipomemorandum = $(this).attr("data-idtipomemorandum");
    $.ajax({
                type: "GET",
                url: "/administracion/tipomemorandun/listar",
                dataType: "json",
                success: function(resultado){
                    $.each(resultado, function(index, value){
                        $("#cbotipo").append(
                        `<option value="${value.id_tipomemorandum}">${value.descripcion}</option>`)
                    });
                    $("#cbotipo").val(id_tipomemorandum);
                }
            });
    $("#modalMemorandum").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if (!$("#memorandum_form").valid()) {
        return;
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/administracion/memorandum/registrar",
        data: JSON.stringify({
            id_memorandum: $("#hddidmemorandum").val(),
            id_tipomemorandum: $("#cbotipo").val(),
            destinatario: $("#txtdestinatario").val(),
            emisor: $("#txtemisor").val(),
            asunto: $("#txtasunto").val(),
            referencia: $("#txtreferencia").val(),
            fecha: $("#txtfecha").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarMemorandum();
                Notiflix.Notify.success(resultado.mensaje);
            }else{
                Notiflix.Notify.failure(resultado.mensaje);
            }
            $("#modalMemorandum").modal("hide")
        }

    })
});


function listarMemorandum(){
    $.ajax({
        type: "GET",
        url: "/administracion/memorandum/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblmemorandum > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblmemorandum > tbody").append("<tr>" +
                    "<td>"+value.id_memorandum+"</td>" +
                    "<td>"+value.tipoMemorandun.descripcion+"</td>" +
                    "<td>"+value.destinatario+"</td>" +
                    "<td>"+value.emisor+"</td>" +
                    "<td>"+value.asunto+"</td>" +
                    "<td>"+value.referencia+"</td>" +
                    "<td>"+value.fecha+"</td>" +
                    "<td>"+
                    "<button type='button' class='btn btn-info btnactualizar'"+
                    " data-idmemorandum='"+value.id_memorandum+"'"+
                    " data-idtipomemorandum='"+value.tipoMemorandun.id_tipomemorandum+"'"+
                    " data-destinatario='"+value.destinatario+"'"+
                    " data-emisor='"+value.emisor+"'"+
                    " data-asunto='"+value.asunto+"'"+
                    " data-referencia='"+value.referencia+"'"+
                    " data-fecha='"+value.fecha+"'>"+
                    "<i class='bi bi-pencil-square'></i>"+
                    "</button></td>"+
                    "</tr>");
            })
        }
    })
}