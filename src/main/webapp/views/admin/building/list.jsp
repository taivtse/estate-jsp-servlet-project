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
<c:url var="assignmentStaffApi" value="/api/assignment/staff"></c:url>
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
                <c:forEach var="buildingDto" items="${command.listResult}">
                    <tr>
                        <td>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" class="checkbox-item" name="checkList" value="${buildingDto.id}">
                                <label></label>
                            </div>
                        </td>
                        <td>
                            <fmt:formatDate value="${buildingDto.createdDate}" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>${buildingDto.name}</td>
                        <td>${buildingDto.fullAddress}</td>
                        <td>${buildingDto.managerName}</td>
                        <td>${buildingDto.managerPhone}</td>
                        <td>${buildingDto.buildingArea}</td>
                        <td>${fn:join(buildingDto.rentalAreaArray, ', ')}</td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${buildingDto.rentalCost}"/>
                        </td>
                        <td>${buildingDto.serviceCost}</td>
                        <td>${buildingDto.commissionCost}</td>
                        <td class="actions">
                            <a href="#" class="btn-assignment" data-building-id="${buildingDto.id}"><i
                                    class="fa fa-tasks"></i></a>
                            <a href="<c:url value='${editUrl}/${buildingDto.id}'/>" class="on-default edit-row"><i
                                    class="fa fa-pencil"></i></a>
                            <a href="#" class=""><i class="fa fa-trash-o"></i></a>
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

    <div id="assignment-dialog" class="zoom-anim-dialog modal-block mfp-hide">
        <section class="panel panel-featured">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <fmt:message bundle="${lang}" key="assignment.staff.list"/>
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <form id="assignmentForm">
                            <input type="hidden" name="buildingId" id="buildingIdHiddenInput">
                            <table class="table table-bordered table-striped mb-none" id="assignment-table">
                                <thead>
                                <tr>
                                    <th>
                                        <fmt:message bundle="${lang}" key="staff.assign"/>
                                    </th>
                                    <th>
                                        <fmt:message bundle="${lang}" key="staff.fullName"/>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="confirmAssign" class="btn btn-primary">
                            <fmt:message bundle="${lang}" key="save"/>
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

            addEventAssignmentButotn();
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

        function addEventAssignmentButotn() {
            $(".btn-assignment").click(function (e) {
                e.preventDefault();

                var buildingId = $(this).data("building-id");
                fillAssignmentStaffList(buildingId);
                $("#buildingIdHiddenInput").val(buildingId);

                $.magnificPopup.open({
                    items: {
                        src: '#assignment-dialog',
                        type: 'inline'
                    },
                    preloader: false,
                    removalDelay: 300,
                    mainClass: 'my-mfp-zoom-in',
                    modal: true,
                    callbacks: {
                        open: function () {
                            $('#confirmAssign').on('click', function (e) {
                                e.preventDefault();
                                $.magnificPopup.close();

                                var data = $("#assignmentForm").serializeObject();
                                saveAssignmentStaff(data);
                            });
                        },
                        close: function () {
                            $('#confirmAssign').off('click');
                        }
                    }
                });
            })
        }

        function fillAssignmentStaffList(buildingId) {
            $.ajax({
                type: 'GET',
                url: '${assignmentStaffApi}?buildingId=' + buildingId,
                dataType: 'json',
                success: function (result) {
                    renderAssignmentTable(result);
                }
            });
        }

        function renderAssignmentTable(data) {
            var tbody = '';
            data.forEach(function (staff) {
                var row = '<tr><td>\n' +
                    '<div class="checkbox-custom checkbox-default">\n' +
                    '<input type="checkbox" name="checkList" value="' + staff.id + '" ' + staff.checked + '><label></label></div></td>\n' +
                    '<td>' + staff.fullName + '</td></tr>';
                tbody += row;
            });

            $("#assignment-table tbody").html(tbody);
        }

        function saveAssignmentStaff(data) {
            $.ajax({
                type: 'POST',
                url: '${assignmentStaffApi}',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function (result) {
                    new PNotify({
                        title: '<fmt:message bundle="${lang}" key="assignment.success"/>',
                        text: '<fmt:message bundle="${lang}" key="assignment.success.text"/>',
                        type: 'success'
                    });
                },
                error: function (error) {
                    new PNotify({
                        title: '<fmt:message bundle="${lang}" key="assignment.error"/>',
                        text: '<fmt:message bundle="${lang}" key="error"/>',
                        type: 'error'
                    });
                }
            });
        }

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
                        },
                        close: function () {
                            $('#confirmDelete').off('click');
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