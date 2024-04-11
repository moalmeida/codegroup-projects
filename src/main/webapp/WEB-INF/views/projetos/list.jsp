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
    <title> :: listar projetos :: </title>
    <link href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet" />
    <link href="https://unpkg.com/bootswatch@5.3.3/dist/lux/bootstrap.min.css" rel="stylesheet" />
    <link href="/styles.css" rel="stylesheet" />
</head>

<body>
    <nav class="navbar navbar-dark bg-primary">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1">listar projetos</span>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="row">
            <div>
                <a href="/projetos/cadastrar" class="btn btn-primary mb-2">
                    cadastrar projeto
                </a>
            </div>
        </div>
        <div class="row row-cols-1 row-cols-md-2 row-cols-xl-3 g-4 pt-5">
            <c:forEach var="projeto" items="${projetos}">
                <div class="col h-100">
                    <div class="card card-hover">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-center pt-1">
                                <span class="badge bg-primary fs-5">
                                    <c:out value="${projeto.status}" />
                                </span>
                                <span class="badge bg-primary fs-5">
                                    <c:out value="${projeto.risco}" />
                                </span>
                            </div>
                            <h4 class="pt-4 pb-2 text-truncate-line-2">
                                <c:out value="${projeto.nome}" />
                            </h4>
                            <h5 class="pb-2 text-truncate-line-2">
                                <c:out value="${projeto.descricao}" />
                            </h5>
                            <div class="pb-2 fs-5 d-flex justify-content-between">
                                <div class=" d-flex align-items-center">
                                    <i class="bi bi-cash-coin" style="font-size: 1.75rem; color: green;"></i>
                                    <span class="p-2">orçamento</span>
                                </div>
                                <div>
                                    <c:out value="${projeto.orcamento}" />
                                </div>
                            </div>
                            <div class="pb-2 fs-5 d-flex justify-content-between">
                                <div class=" d-flex align-items-center">
                                    <i class="bi bi-calendar" style="font-size: 1.75rem; color: black;"></i>
                                    <span class="p-2">data de início</span>
                                </div>
                                <div>
                                    <c:out value="${projeto.dataInicio}" />
                                </div>
                            </div>
                            <div class="pb-2 fs-5 d-flex justify-content-between">
                                <div class=" d-flex align-items-center">
                                    <i class="bi bi-calendar" style="font-size: 1.75rem; color: black;"></i>
                                    <span class="p-2">previsão de término</span>
                                </div>
                                <div>
                                    <c:out value="${projeto.dataPrevisaoFim}" />
                                </div>
                            </div>
                            <div class="pb-2 fs-5 d-flex justify-content-between">
                                <div class=" d-flex align-items-center">
                                    <i class="bi bi-calendar" style="font-size: 1.75rem; color: black;"></i>
                                    <span class="p-2">data de término</span>
                                </div>
                                <div>
                                    <c:out value="${projeto.dataFim}" />
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="row align-items-center g-0">
                                <div class="col">
                                    <i class="bi bi-person-badge" style="font-size: 1.75rem; color: blue;"></i>
                                    <c:out value="${projeto.gerente.nome}" />
                                </div>
                                <div class="col-auto d-flex justify-content-between align-items-center">
                                    <span class="p-2">
                                        <a href='/projetos/formulario/<c:out value="${projeto.id}"/>' class="bi bi-pen-fill" style="font-size: 1.75rem; color: green;"></a>
                                    </span>
                                    <c:if test="${projeto.permitidoRemover}">
                                        <span class="p-2">
                                            <i class="bi bi-trash" style="font-size: 1.75rem; color: red;"
                                                data-bs-toggle="modal" data-bs-target='#confirmModal<c:out value="${projeto.id}"/>'>
                                            </i>
                                        </span>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class='modal modal-lg' id='confirmModal<c:out value="${projeto.id}"/>' tabIndex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">remover projeto</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                            </div>
                            <div class="modal-body">
                                tem certeza que gostaria de remover esse projeto?
                            </div>
                            <div class="modal-footer">
                                <form action='/projetos/remover/<c:out value="${projeto.id}"/>' method="post">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" aria-label="close">cancelar</button>
                                    <button type="submit" class="btn btn-danger">confirmar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>

    <footer class="bg-light text-left text-lg-start mt-5">
        <div class="text-left p-3">
            &copy; 2024 CodeGroup
        </div>
    </footer>
    <script src="https://unpkg.com/jquery@3.7.1/dist/jquery.min.js" crossorigin></script>
    <script src="https://unpkg.com/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin></script>
    <script>
        $(document).ready(function () {

            function handleRemover(projeto) {
                window.alert("projeto id "+ projeto)
                // $.ajax({
                //     url: '/api/projeto/1',
                //     method: 'DELETE',
                //     success: function (response) {
                //         // Handle the success response here
                //     },
                //     error: function (xhr, status, error) {
                //         // Handle the error response here
                //     }
                // });
            }

        });
    </script>

</body>

</html>