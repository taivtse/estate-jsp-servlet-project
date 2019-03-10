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
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/bootstrap-multiselect/bootstrap-multiselect.css'/>"/>
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css'/>"/>
    </content>

    <style>
        #buildingImage {
            display: none;
        }

        .imagePreviewWrapper {
            width: 250px;
            height: 250px;
            background: #efefef url(<c:url value="/template/admin/images/building-avatar.png"></c:url>) center;
            background-size: cover;
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
                <form class="form-horizontal buildingForm" action="${submitFormUrl}" method="post"
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
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.name"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.name" value="${command.pojo.name}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.structure"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.structure" value="${command.pojo.structure}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.numberOfBasement"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="number" name="pojo.numberOfBasement"
                                           value="${command.pojo.numberOfBasement}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.districtId"/>
                                </label>
                                <div class="col-md-9">
                                    <select data-plugin-selectTwo name="districtId" class="form-control populate">
                                        <c:forEach var="districtDto" items="${command.districtDtoList}">
                                            <option value="${districtDto.id}" ${districtDto.id eq command.pojo.districtId ? 'selected' : ''}>${districtDto.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.wardId"/>
                                </label>
                                <div class="col-md-9">
                                    <select data-plugin-selectTwo name="districtId" class="form-control populate">
                                        <c:forEach var="wardDto" items="${command.wardDtoList}">
                                            <option value="${wardDto.id}" ${wardDto.id eq command.pojo.wardId ? 'selected' : ''}>${wardDto.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.street"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.street" value="${command.pojo.street}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.buildingArea"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="number" name="pojo.buildingArea"
                                           value="${command.pojo.buildingArea}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.direction"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.direction"
                                           value="${command.pojo.direction}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.level"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.level"
                                           value="${command.pojo.level}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label for="tags-input-multiple" class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.rentalArea"/>
                                </label>
                                <div class="col-md-9">
                                    <select name="pojo.rentalAreaArr" id="tags-input-multiple" multiple
                                            data-role="tagsinput" data-tag-class="label label-primary">
                                        <c:forEach var="area" items="${command.pojo.rentalAreaArr}">
                                            <option value="${area}"></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <fmt:message bundle="${lang}" key="building.areaDescription"/>
                                </label>
                                <div class="col-md-9">
                                        <textarea name="pojo.areaDescription" class="form-control" rows="2"
                                                  data-plugin-textarea-autosize>${command.pojo.areaDescription}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label for="tags-input-multiple" class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.rentalCost"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="number" name="pojo.rentalCost"
                                           value="${command.pojo.rentalCost}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <fmt:message bundle="${lang}" key="building.costDescription"/>
                                </label>
                                <div class="col-md-9">
                                        <textarea name="pojo.costDescription" class="form-control" rows="2"
                                                  data-plugin-textarea-autosize>${command.pojo.costDescription}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.serviceCost"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.serviceCost"
                                           value="${command.pojo.serviceCost}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.carCost"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.carCost"
                                           value="${command.pojo.carCost}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.motorbikeCost"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.motorbikeCost"
                                           value="${command.pojo.motorbikeCost}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.deposit"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.deposit"
                                           value="${command.pojo.deposit}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.payment"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.payment"
                                           value="${command.pojo.payment}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.timeContract"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.timeContract"
                                           value="${command.pojo.timeContract}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.timeDecorator"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.timeDecorator"
                                           value="${command.pojo.timeDecorator}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.managerName"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.managerName"
                                           value="${command.pojo.managerName}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.managerPhone"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.managerPhone"
                                           value="${command.pojo.managerPhone}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.commissionCost"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.commissionCost"
                                           value="${command.pojo.commissionCost}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.location"/>
                                </label>
                                <div class="col-md-9">
                                    <input type="text" name="pojo.location"
                                           value="${command.pojo.location}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.type"/>
                                </label>
                                <div class="col-md-9 myMultiSelect">
                                    <select class="form-control" name="pojo.typeArr"
                                            multiple="multiple" data-plugin-multiselect
                                            data-plugin-options='{ "includeSelectAllOption": true }'>
                                        <c:forEach var="buildingType" items="${command.buildingTypeArray}">
                                            <c:set var="found" value="false" scope="request"/>
                                            <c:forEach items="${command.pojo.typeArr}" var="type">
                                                <c:if test="${type == buildingType}">
                                                    <c:set var="found" value="true" scope="request"/>
                                                </c:if>
                                            </c:forEach>
                                            <option value="${buildingType}" ${found ? 'selected' : ''}>${buildingType.typeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="col-md-3 control-label">
                                    <fmt:message bundle="${lang}" key="building.link"/>
                                </label>
                                <div class="col-md-9">
                                    <input typer="text" name="pojo.link"
                                           value="${command.pojo.link}"
                                           class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <fmt:message bundle="${lang}" key="building.note"/>
                                </label>
                                <div class="col-md-9">
                                        <textarea name="pojo.note" class="form-control" rows="2"
                                                  data-plugin-textarea-autosize>${command.pojo.note}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group text-center">
                        <button typer="submit" class="mb-xs mt-xs mr-xs btn btn-primary">
                            <fmt:message bundle="${lang}" key="${empty command.pojo.id ? 'insert' : 'update'}"/>
                        </button>
                        <button typer="reset" class="mb-xs mt-xs mr-xs btn btn-default">
                            <fmt:message bundle="${lang}" key="reset"/>
                        </button>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/bootstrap-multiselect/bootstrap-multiselect.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js'/>"></script>
</content>

<content tag="local_script">
    <script typer="application/javascript">
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
