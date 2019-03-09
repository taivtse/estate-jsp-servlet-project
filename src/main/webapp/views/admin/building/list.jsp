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
<html>
<head>
    <title>Quản lý toà nhà</title>
</head>
<body>
<section class="panel panel-featured">
    <header class="panel-heading">
        <h2 class="panel-title">Danh sách toà nhà</h2>
    </header>
    <div class="panel-body">
        <div class="row">
            <div class="col-sm-6">
                <div class="mb-md">
                    <a href="${editUrl}" id="addToTable" class="btn btn-primary">
                        Thêm
                        <i class="fa fa-plus"></i></a>
                </div>
            </div>
        </div>
        <form action="">
            <table class="table table-bordered table-striped mb-none" id="datatable-default">
                <thead>
                <tr>
                    <th>
                        <div class="checkbox-custom checkbox-default">
                            <input type="checkbox" checked="" id="chkDeleteAll">
                            <label for="chkDeleteAll"></label>
                        </div>
                    </th>
                    <th>Ngày</th>
                    <th>Tên</th>
                    <th>Địa chỉ</th>
                    <th>Quản lý</th>
                    <th>SĐT</th>
                    <th>DT sàn</th>
                    <th>DT trống</th>
                    <th>Giá thuê</th>
                    <th>Phí dịch vụ</th>
                    <th>Phí môi giới</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="dto" items="${command.listResult}">
                    <tr>
                        <td>
                            <div class="checkbox-custom checkbox-default">
                                <input type="checkbox" checked="">
                                <label></label>
                            </div>
                        </td>
                        <td>
                            <fmt:formatDate value="${dto.createdDate}" pattern="dd-MM-yyyy"/>
                        </td>
                        <td>${dto.name}</td>
                        <td>${dto.address}</td>
                        <td>${dto.managerName}</td>
                        <td>${dto.managerPhone}</td>
                        <td>${dto.buildingArea}</td>
                        <td>${dto.rentalArea}</td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.rentalCost}"/>
                        </td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.serviceCost}"/>
                        </td>
                        <td>
                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.commissionCost}"/>
                        </td>
                        <td class="actions">
                            <a href="<c:url value='${editUrl}/${dto.id}'/>" class="on-default edit-row"><i
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
    <div id="dialog" class="modal-block mfp-hide">
        <section class="panel panel-featured">
            <header class="panel-heading">
                <h2 class="panel-title">
                    Xác nhận xoá
                </h2>
            </header>
            <div class="panel-body">
                <div class="modal-wrapper">
                    <div class="modal-text">
                        <p>
                            Bạn có chắc chắn muốn toà nhà đã chọn?
                        </p>
                    </div>
                </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <button id="dialogConfirm" class="btn btn-primary">
                            Xác nhận
                        </button>
                        <button id="dialogCancel" class="btn btn-default">
                            Huỷ
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
            $('#datatable-default').DataTable({
                // tai edited here
                paging: false,
                info: false,
                bAutoWidth: false
            });

            addEventDeleteButton();
        });

        function addEventDeleteButton() {
            $('.remove-row').on('click', function (e) {
                e.preventDefault();
                $.magnificPopup.open({
                    items: {
                        src: '#dialog',
                        type: 'inline'
                    },
                    preloader: false,
                    modal: true,
                    callbacks: {
                        open: function () {
                            $('#dialogConfirm').on('click', function (e) {
                                e.preventDefault();
                                $.magnificPopup.close();

                                deleteBuilding();
                            });

                            $('#dialogCancel').on('click', function (e) {
                                $.magnificPopup.close();
                            });

                        },
                        close: function () {
                            $('#dialogConfirm').off('click');
                            $('#dialogCancel').off('click');
                        }
                    }
                });
            })
        }

        function deleteBuilding() {
            $.ajax({
                type: "DELETE",
                url: "/admin/building/",
                success: function (msg) {

                },
                error: function (error) {
                    console.log("ERROR: ", error);
                }
            });
        }
    </script>
</content>
</body>
</html>