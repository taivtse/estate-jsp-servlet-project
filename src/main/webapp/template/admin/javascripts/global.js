(function ($) {

    'use strict';

    PNotify.prototype.options.delay = 5000;

    String.prototype.format = function () {
        var formatted = this;
        for (var arg in arguments) {
            formatted = formatted.replace("{" + arg + "}", arguments[arg]);
        }
        return formatted;
    };

    jQuery.fn.serializeObject = function () {
        var arrayData, objectData;
        arrayData = this.serializeArray();
        objectData = {};

        $.each(arrayData, function () {
            var value;

            if (this.value != null) {
                value = this.value;
            } else {
                value = '';
            }

            if (objectData[this.name] != null) {
                if (!objectData[this.name].push) {
                    objectData[this.name] = [objectData[this.name]];
                }

                objectData[this.name].push(value);
            } else {
                objectData[this.name] = value;
            }
        });

        return objectData;
    };

    $(document).on('click', '.modal-dismiss', function (e) {
        e.preventDefault();
        $.magnificPopup.close();
    });

    bindEventCheckAllCheckbox();
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll();

}).apply(this, [jQuery]);


function bindEventCheckAllCheckbox() {
    $('#chkCheckAll').click(function () {
        $(this).closest("table").find("input[type=checkbox]").prop("checked", this.checked);
        $('#btnDeleteAll').prop('disabled', !this.checked);
    });
}

function enableOrDisableDeleteAll() {
    $('tbody input[type=checkbox]').click(function () {
        if ($('tbody input[type=checkbox]:checked').length == 0) {
            $('#btnDeleteAll').prop('disabled', true);
        } else {
            $('#btnDeleteAll').prop('disabled', false);
        }
    });
}

function autoCheckCheckboxAll() {
    $('tbody input[type=checkbox]').change(function () {
        if ($('tbody input[type=checkbox]').length == $('tbody input[type=checkbox]:checked').length) {
            $('#chkCheckAll').prop("checked", true);
        } else {
            $('#chkCheckAll').prop("checked", false);
        }
    });
}