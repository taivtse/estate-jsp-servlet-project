<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 3/5/19
  Time: 22:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="searchUrl" value="/admin/building/list"></c:url>
<c:url var="editUrl" value="/admin/building/edit"></c:url>
<c:url var="submitFormUrl" value="/admin/building/"></c:url>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="building.management"/>
    </title>
</head>
<body>
<section class="panel panel-featured">
    <header class="panel-heading">
        <h2 class="panel-title">
            <fmt:message bundle="${lang}" key="building.list"/>
        </h2>
    </header>
    <div class="panel-body">
        <div class="row">
            <div class="col-sm-6">
                <div class="mb-md">
                    <a href="${editUrl}" class="btn btn-primary">
                        <fmt:message bundle="${lang}" key="insert"/>
                        <i class="fa fa-plus"></i></a>
                    <button class="btn btn-danger" id="btnDeleteAll" disabled>
                        <fmt:message bundle="${lang}" key="delete"/>
                        <i class="fa fa-trash-o"></i></button>
                </div>
            </div>
        </div>
        <form id="buildingDeleteForm">
            <table class="table table-bordered table-striped mb-none" id="building-table">
                <thead>
                <tr>
                    <th class="nosort">
                        <div class="checkbox-custom checkbox-default">
                            <input type="checkbox" id="chkCheckAll">
                            <label></label>
                        </div>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.date"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.name.table"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.address"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.managerName.table"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.managerPhone.table"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.buildingArea.table"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.rentalArea.table"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.rentalCost"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.serviceCost"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="building.commissionCost"/>
                    </th>
                    <th>
                        <fmt:message bundle="${lang}" key="action"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="districtDto" items="${command.listResult}">
                    <tr>
                        <td>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" name="checkList" value="${districtDto.id}">
                                <label></label>
                            </div>
                        </td>
                        <td>
                            <fmt:formatDate value="${districtDto.createdDate}" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>${districtDto.name}</td>
                        <td>${districtDto.fullAddress}</td>
                        <td>${districtDto.managerName}</td>
                        <td>${districtDto.managerPhone}</td>
                        <td>${districtDto.buildingArea}</td>
                        <td>${fn:join(districtDto.rentalAreaArray, ', ')}</td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${districtDto.rentalCost}"/>
                        </td>
                        <td>${districtDto.serviceCost}</td>
                        <td>${districtDto.commissionCost}</td>
                        <td class="actions">
                            <a href="#" class="on-default edit-row"><i class="fa fa-tasks"></i></a>
                            <a href="<c:url value='${editUrl}/${districtDto.id}'/>" class="on-default edit-row"><i
                                    class="fa fa-pencil"></i></a>
                            <a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</section>

<content tag="specific_html">
    <div id="delete-dialog" class="zoom-anim-dialog modal-block mfp-hide">
        <section class="panel panel-featured">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <fmt:message bundle="${lang}" key="delete.ask.title"/>
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>
                            <fmt:message bundle="${lang}" key="delete.ask.text"/>
                        </p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="confirmDelete" class="btn btn-primary">
                            <fmt:message bundle="${lang}" key="confirm"/>
                        </button>
                        <button class="modal-dismiss btn btn-default">
                            <fmt:message bundle="${lang}" key="cancel"/>
                        </button>
                    </div>
                </div>
            </footer>
        </section>
    </div>

    <div id="assignment-dialog" class="modal-block mfp-hide">
        <section class="panel panel-featured">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <fmt:message bundle="${lang}" key="delete.ask.title"/>
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>
                            <fmt:message bundle="${lang}" key="delete.ask.text"/>
                        </p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="confirmAssign" class="btn btn-primary">
                            <fmt:message bundle="${lang}" key="confirm"/>
                        </button>
                        <button class="modal-dismiss btn btn-default">
                            <fmt:message bundle="${lang}" key="cancel"/>
                        </button>
                    </div>
                </div>
            </footer>
        </section>
    </div>
</content>

<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            $('#building-table').DataTable({
                // tai edited here
                paging: false,
                info: false,
                bAutoWidth: false,
                order: [], //Initial no order.
                aaSorting: [],
                aoColumnDefs: [{
                    bSortable: false,
                    aTargets: [0, -1]
                },
                    {
                        aTargets: [0, -1],
                        className: "align-center"
                    }]
            });

            addEventDeleteButton();

            //TODO: set checkbox to vertical and horizontal center
            // $(".dataTable thead th:first-child").css({"width": "45px", "vertical-align": "middle", "padding": "0"});
            // $(".dataTable tbody td:first-child").css({"vertical-align": "middle", "padding": "0"});
            // $(".dataTable .checkbox-custom").css({"margin-left": "50%", "transform": "translateX(-50%)"});

            //    show pnotify in session storage
            if (sessionStorage.pNotify) {
                new PNotify(JSON.parse(sessionStorage.getItem("pNotify")));
                sessionStorage.removeItem("pNotify");
            }
        });

        function addEventDeleteButton() {
            $('#btnDeleteAll').on('click', function (e) {
                e.preventDefault();
                $.magnificPopup.open({
                    items: {
                        src: '#delete-dialog',
                        type: 'inline'
                    },
                    preloader: false,
                    removalDelay: 300,
                    mainClass: 'my-mfp-zoom-in',
                    modal: true,
                    callbacks: {
                        open: function () {
                            $('#confirmDelete').on('click', function (e) {
                                e.preventDefault();
                                $.magnificPopup.close();

                                var data = $("#buildingDeleteForm").serializeObject();
                                deleteBuilding(data);
                            });

                            $('#cancelDelete').on('click', function (e) {
                                $.magnificPopup.close();
                            });

                        },
                        close: function () {
                            $('#confirmDelete').off('click');
                            $('#cancelDelete').off('click');
                        }
                    }
                });
            })
        }

        function deleteBuilding(data) {
            console.log(JSON.stringify(data))
            $.ajax({
                type: 'DELETE',
                url: '${submitFormUrl}',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    var pnotify = {
                        title: '<fmt:message bundle="${lang}" key="delete.success"/>',
                        text: '<fmt:message bundle="${lang}" key="building.delete.success"/>',
                        type: 'success'
                    };
                    sessionStorage.setItem("pNotify", JSON.stringify(pnotify));

                    window.location.href = '${submitFormUrl}list';
                },
                error: function (error) {
                    new PNotify({
                        title: '<fmt:message bundle="${lang}" key="delete.error"/>',
                        text: '<fmt:message bundle="${lang}" key="error"/>',
                        type: 'error'
                    });
                }
            });
        }
    </script>
</content>
</body>
</html>