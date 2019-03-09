<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 3/5/19
  Time: 13:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitFormUrl" value="/admin/building/"></c:url>
<html>
<head>
    <title>
        <fmt:message bundle="${lang}" key="building.management"/>
    </title>
    <style>
        #buildingImage {
            display: none;
        }

        .imagePreviewWrapper {
            width: 250px;
            height: 250px;
            background: url(<c:url value="/template/admin/images/building-avatar.png"></c:url>) center;
            background-size: cover;
            background-color: #efefef;
            border: 2px solid #909090;
            cursor: pointer;
        }

        #imagePreview {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-lg-12">
        <section class="panel panel-featured">
            <header class="panel-heading">
                <h2 class="panel-title">
                    <fmt:message bundle="${lang}" key="building.info"/>
                </h2>
            </header>
            <div class="panel-body">
                <form class="form-horizontal form-bordered" id="command" action="${submitFormUrl}" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="row mb-lg">
                            <div class="col-xs-12" style="display: flex; justify-content: center">
                                <input type="file" id="buildingImage" name="pojo.image">
                                <div class="imagePreviewWrapper">
                                    <c:if test="${not empty command.pojo.image}">
                                        <c:url value="/resource/${command.pojo.image}"
                                               var="imageUrl"></c:url>
                                    </c:if>
                                    <img src="${imageUrl}" alt="" id="imagePreview">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">
                            <fmt:message bundle="${lang}" key="building.name"/>
                        </label>
                        <div class="col-md-6">
                            <input type="text" name="pojo.name" value="${command.pojo.name}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">
                            <fmt:message bundle="${lang}" key="building.district"/>
                        </label>
                        <div class="col-md-6">
                            <select data-plugin-selectTwo name="district" class="form-control populate">
                                <c:forEach var="districtDto" items="${command.districtList}">
                                    <option value="${districtDto.id}" ${districtDto.id eq command.pojo.district ? 'selected' : ''}>${districtDto.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">
                            <fmt:message bundle="${lang}" key="building.ward"/>
                        </label>
                        <div class="col-md-6">
                            <select data-plugin-selectTwo name="district" class="form-control populate">
                                <c:forEach var="districtDto" items="${command.districtList}">
                                    <option value="${districtDto.id}" ${districtDto.id eq command.pojo.district ? 'selected' : ''}>${districtDto.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">
                            <fmt:message bundle="${lang}" key="building.name"/>
                        </label>
                        <div class="col-md-6">
                            <input type="text" name="pojo.name" value="${command.pojo.name}" class="form-control">
                        </div>
                    </div>
                    <div class="form-group text-center">
                        <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary">
                            <fmt:message bundle="${lang}" key="${empty command.pojo ? 'insert' : 'update'}"/>
                        </button>
                        <button type="reset" class="mb-xs mt-xs mr-xs btn btn-default">
                            <fmt:message bundle="${lang}" key="reset"/>
                        </button>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/fuelux/js/spinner.js'/>"></script>
</content>

<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            getImagePreview();
        })

        function getImagePreview() {
            $(".imagePreviewWrapper").click(function () {
                $("#buildingImage").trigger('click');
            })

            $("#buildingImage").change(function () {
                readURL(this);
                $("#staffPhotoName").val(this.files[0].name);
            })
        }

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#imagePreview').attr('src', reader.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</content>
</body>
</html>
