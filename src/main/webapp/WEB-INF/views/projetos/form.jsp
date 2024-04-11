<!-- 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
-->
<!DOCTYPE html>
<html lang="pt_br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> :: cadastrar projeto :: </title>
    <link href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/bootswatch@5.3.3/dist/lux/bootstrap.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/jquery-ui@1.13.2/dist/themes/base/jquery-ui.min.css" rel="stylesheet" />
    <style>
        .ui-autocomplete-input {
            padding: 5px;
        }

        #selected-items {
            padding: 5px;
            border: 1px solid #ccc;
            margin-top: 5px;
        }

        .selected-item {
            display: inline-block;
            padding: 2px 5px;
            margin: 2px;
            background-color: #78C3F3;
            color: white;
            border-radius: 5px;
        }

        .selected-item .remove-item {
            margin-left: 5px;
            cursor: pointer;
        }
    </style>
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
                <form action="/projetos/cadastrar">
                    <div class="row">
                        <fieldset>
                            <label class="form-label mt-4 fs-4" htmlFor="input_nome">
                                nome
                            </label>
                            <input class="form-control fs-4" id="input_nome" value="<c:out value="
                                ${projeto.nome}" />"></textarea>
                        </fieldset>
                    </div>
                    <div class="row">
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_data_inicio">
                                    data de início
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_inicio" type="text"
                                    value="<c:out value=" ${projeto.dataInicio}" />" />
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="select_gerente">
                                    gerente responsavel
                                </label>
                                <select class="form-select fs-4" id="select_gerente">
                                    <option></option>
                                    <c:forEach var="ger" items="${gerentes}">
                                        <option value="<c:out value=" ${ger.id}" />">
                                        <c:out value="${ger.nome}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4 " htmlFor="input_data_previsao_fim">
                                    previsão de término
                                    <c:out value="${projeto.dataPrevisaoFim}" />
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_previsao_fim" type="text"
                                    value="<c:out value=" ${projeto.dataPrevisaoFim}" />" />
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_data_fim">
                                    data real de término
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_fim" type="text"
                                    value="<c:out value=" ${projeto.dataFim}" />" />
                            </fieldset>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_orcamento">
                                    orçamento total
                                </label>
                                <input class="form-control fs-4 fmt-money" id="input_orcamento" type="text"
                                    value="<c:out value=" ${projeto.orcamento}" />" />
                            </fieldset>
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="select_status">
                                    status
                                </label>
                                <select class="form-select fs-4" id="select_status">
                                    <option></option>
                                    <c:forEach var="sts" items="${status}">
                                        <option></option>
                                        <option value="<c:out value=" ${sts}" />">
                                        <c:out value="${sts}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="textarea_descricao">
                                    descrição
                                </label>
                                <textarea class="form-control fs-4" id="textarea_descricao" rows="5"
                                    value="<c:out value=" ${projeto.descricao}" />"></textarea>
                            </fieldset>
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn btn-primary">Salvar</button>
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
        $(document).ready(function () {
            $('.fmt-date').mask('00/00/0000');
            $('.fmt-money').mask('000.000.000.000.000,00', { reverse: true });
            // date.split("/").reverse().join("-");

            const selected = [

            ];
            const available = [
                "ActionScript",
                "AppleScript",
                "Asp",
                "BASIC",
                "C",
                "C++",
                "Clojure",
                "COBOL",
                "ColdFusion",
                "Erlang",
                "Fortran",
                "Groovy",
                "Haskell",
                "Java",
                "JavaScript",
                "Lisp",
                "Perl",
                "PHP",
                "Python",
                "Ruby",
                "Scala",
                "Scheme"
            ];



            $("#tags").autocomplete({
                source: available,
                select: (event, ui) => {
                    var value = ui.item.value;
                    selected.push(value);
                    refreshDiv();
                    var i = available.indexOf(value);
                    available.splice(i, 1);
                    event.preventDefault();
                    $("#tags").focusout();
                    $("#tags").val('');
                }
            });

            function refreshDiv() {
                $("#emails").val(selected.join(','));
                var email_html = selected.map(function (f, i) {
                    return "<span class='btn btn-primary btn-md' style='margin: 3px;'>" + f + "&nbsp;&nbsp; <span onclick=\"removeEmail('" + f + "')\" style='color:red'>x</span></span>";
                });
                $("#email-html").html(email_html);
            }

            function removeEmail(email) {
                availableTags.push(email);
                var i = selected.indexOf(email);
                selected.splice(i, 1);
                refreshDiv();
            }


            // function split(val) {
            //     return val.split(/,\s*/);
            // }

            // function extractLast(term) {
            //     return split(term).pop();
            // }

            // $("#tags")
            //     .on("keydown", function (event) {
            //         if (event.keyCode === $.ui.keyCode.TAB &&
            //             $(this).autocomplete("instance").menu.active) {
            //             event.preventDefault();
            //         }
            //     })
            //     .autocomplete({
            //         minLength: 0,
            //         source: function (request, response) {
            //             response($.ui.autocomplete.filter(
            //                 available, extractLast(request.term)));
            //         },
            //         focus: function () {
            //             return false;
            //         },
            //         select: function (event, ui) {
            //             var terms = split(this.value);
            //             terms.pop();
            //             terms.push(ui.item.value);
            //             terms.push("");
            //             this.value = terms.join(", ");
            //             return false;
            //         }
            //     });

        });
    </script>

</body>

</html>