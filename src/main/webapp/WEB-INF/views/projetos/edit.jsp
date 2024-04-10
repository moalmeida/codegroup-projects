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
    <link href="/styles.css" rel="stylesheet" />
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
                    <div class="row">
                        <fieldset>
                            <label class="form-label mt-4 fs-4" htmlFor="input_nome">
                                nome
                            </label>
                            <input class="form-control fs-4" id="input_nome"></textarea>
                        </fieldset>
                    </div>
                    <div class="row">
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_data_inicio">
                                    data de início
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_inicio" type="text" />
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
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_previsao_fim" type="text" />
                            </fieldset>
                        </div>
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_data_fim">
                                    data real de término
                                </label>
                                <input class="form-control fs-4 fmt-date" id="input_data_fim" type="text" />
                            </fieldset>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <fieldset>
                                <label class="form-label mt-4 fs-4" htmlFor="input_orcamento">
                                    orçamento total
                                </label>
                                <input class="form-control fs-4 fmt-money" id="input_orcamento" type="text" />
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
                                <textarea class="form-control fs-4" id="textarea_descricao" rows="5"></textarea>
                            </fieldset>
                        </div>
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
    <script src="https://unpkg.com/jquery-mask-plugin@1.14.16/dist/jquery.mask.min.js" crossorigin></script>
    <script>
        $(document).ready(function () {
            $('.fmt-date').mask('31/12/1111');
            $('.fmt-money').mask('000.000.000.000.000', { reverse: true });
        });
    </script>

</body>

</html>