<!--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
-->
<!DOCTYPE html>
<html lang="pt_br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> :: cadastrar projeto :: </title>
    <link href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://unpkg.com/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet"/>
    <link href="https://unpkg.com/bootswatch@5.3.3/dist/lux/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://unpkg.com/jquery-ui@1.13.2/dist/themes/base/jquery-ui.min.css" rel="stylesheet"/>
</head>

<body>
<nav class="navbar navbar-dark bg-primary">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">cadastrar projeto</span>
    </div>
</nav>
<div class="container mt-5">
    <div class="row">
        <div>
            <a href="/" class="btn btn-primary mb-2">
                listar projetos
            </a>
        </div>
    </div>
    <div class="row">
        <div>
            <form>
                <input type="hidden" id="input_id" value='<c:out value="${projeto.id}" />'/>
                <div class="row">
                    <label class="form-label mt-4 fs-4" for="input_nome">
                        nome
                    </label>
                    <input class="form-control fs-4" id="input_nome"
                           value='<c:out value="${projeto.nome}" />'/>
                </div>
                <div class="row">
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="input_data_inicio">
                            data de início
                        </label>
                        <input value='<c:out value="${projeto.dataInicio}" />'
                               class="form-control fs-4 fmt-date" id="input_data_inicio" type="text"/>
                    </div>
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="select_gerente">
                            gerente responsavel
                        </label>
                        <select value='<c:out value="${projeto.gerente.id}"/>' class="form-select fs-4"
                                id="select_gerente">
                            <option></option>
                            <c:forEach var="ger" items="${gerentes}">
                                <option value='<c:out value=" ${ger.id}"/>'
                                        <c:if test="${ger.id == projeto.gerente.id}">selected</c:if>>
                                    <c:out value="${ger.nome}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label class="form-label mt-4 fs-4 " for="input_data_previsao_fim">
                            previsão de término
                        </label>
                        <input value='<c:out value=" ${projeto.dataPrevisaoFim}" />'
                               class="form-control fs-4 fmt-date" id="input_data_previsao_fim" type="text"/>
                    </div>
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="input_data_fim">
                            data real de término
                        </label>
                        <input value='<c:out value=" ${projeto.dataFim}" />' class="form-control fs-4 fmt-date"
                               id="input_data_fim" type="text"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="input_orcamento">
                            orçamento total
                        </label>
                        <input class="form-control fs-4 fmt-money" id="input_orcamento" type="text"
                               value='<c:out value=" ${projeto.orcamento}" />'/>
                        <label class="form-label mt-4 fs-4" for="select_status">
                            status
                        </label>
                        <select value='<c:out value="${projeto.status}"/>' class="form-select fs-4"
                                id="select_status">
                            <option></option>
                            <c:forEach var="sts" items="${status}">
                                <option value='<c:out value="${sts}"/>' <c:if test="${sts == projeto.status}">
                                    selected</c:if>>
                                    <c:out value="${sts}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="textarea_descricao">
                            descrição
                        </label>
                        <textarea class="form-control fs-4" id="textarea_descricao" rows="5"><c:out
                                value="${projeto.descricao}"/></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="select_risco">
                            risco
                        </label>
                        <select value='<c:out value="${projeto.risco}"/>' class="form-select fs-4" id="select_risco">
                            <option></option>
                            <c:forEach var="rsc" items="${riscos}">
                                <option value='<c:out value="${rsc}"/>' <c:if test="${rsc == projeto.risco}">
                                    selected</c:if>>
                                    <c:out value="${rsc}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col">
                        <label class="form-label mt-4 fs-4" for="input_funcionarios">
                            funcionários
                        </label>
                        <input class="form-control fs-4" id="input_funcionarios" type="text"/>
                        <div id="funcionarios-html" class="mt-3"></div>
                    </div>
                </div>
                <div class="d-flex flex-row-reverse" style="padding-top: 70px; padding-bottom: 50px;">
                    <button type="button" class="btn btn-primary btn-lg" onclick="cadastrar()">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<footer class="bg-light text-left text-lg-start mt-5">
    <div class="text-left p-3">
        &copy; 2024 CodeGroup
    </div>
</footer>
<script src="https://unpkg.com/jquery@3.7.1/dist/jquery.min.js" crossorigin></script>
<script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin></script>
<script src="https://unpkg.com/jquery-ui@1.13.2/dist/jquery-ui.min.js" crossorigin></script>
<script src="https://unpkg.com/jquery-mask-plugin@1.14.16/dist/jquery.mask.min.js" crossorigin></script>
<script>

    const selected = [
        <c:forEach var="func" items="${projeto.funcionarios}">
        {value: '<c:out value="${func.id}" />', label: '<c:out value="${func.nome}" />'},
        </c:forEach>
    ];

    const available = [
        <c:forEach var="func" items="${funcionarios}">
        {value: '<c:out value="${func.id}" />', label: '<c:out value="${func.nome}" />'},
        </c:forEach>
    ];

    $(document).ready(function () {
        $('.fmt-date').mask('0000-00-00');
        //$('.fmt-money').mask('000,000.00', { reverse: true });
        // date.split("/").reverse().join("-");

        $("#input_funcionarios").autocomplete({
            source: available,
            select: (event, ui) => {
                const item = ui.item;
                selected.push(item);
                const index = available.findIndex(el => el.value === item.value);
                available.splice(index, 1);
                refreshDiv();
                event.preventDefault();
                $("#input_funcionarios").focusout();
                $("#input_funcionarios").val('');
            }
        });

    });

    function refreshDiv() {
        $("#funcionarios-html").html(selected.map((item) => {
            return "<span class='btn btn-secondary btn-md border border-primary' style='margin: 3px;'>" + item.label + "&nbsp;&nbsp; <span onclick=\"removeItem('" + item.value + "')\" style='color:black'>X</span></span>";
        }));
    }

    function removeItem(value) {
        const item = selected.find((item) => item.value === value);
        if (item) {
            available.push(item);
            const index = selected.findIndex(el => el.value === value);
            selected.splice(index, 1);
        }
        refreshDiv();
    }

    refreshDiv();

    function cadastrar() {

        const id = $("#input_id").val()
        const nome = $("#input_nome").val()
        const dataInicio = $("#input_data_inicio").val()
        const dataPrevisaoFim = $("#input_data_previsao_fim").val()
        const dataFim = $("#input_data_fim").val()
        const orcamento = $("#input_orcamento").val()
        const descricao = $("#textarea_descricao").val()
        const gerente = $("#select_gerente").val()
        const status = $("#select_status").val()
        const risco = $("#select_risco").val()
        const funcionarios = selected.map((item) => {
            return {id: item.value, nome: item.label}
        })

        const projeto = {
            id: id,
            nome: nome,
            dataInicio: dataInicio,
            dataPrevisaoFim: dataPrevisaoFim,
            dataFim: dataFim,
            orcamento: orcamento && Number(orcamento),
            descricao: descricao,
            gerente: gerente && {id: gerente},
            status: status,
            risco: risco,
            // funcionarios: funcionarios
        };

        console.log(JSON.stringify(projeto));

        if (id) {
            $.ajax({
                url: "/api/projetos/" + id,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(projeto),
                success: function (data) {
                    window.location.replace("/");
                }
            })
        } else {
            $.ajax({
                url: "/api/projetos",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(projeto),
                success: function (data) {
                    window.location.replace("/");
                }
            });
        }


    }

</script>

</body>

</html>