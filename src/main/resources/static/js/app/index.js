var main = {
    init: function () {
        var _this = this;

        // When a click event occurs in an html element with an id called btn-save,
        // register the event to execute the save function.
        $('#btn-save').on('click', function () {
            _this.save();
        });
        // When a click event occurs in an html element with an id called btn-update,
        // register the event to execute the update function.
        $('#btn-update').on('click', function () {
            _this.update();
        });
    },

    // save function triggered by 'SAVE' button
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('Successfully Posted');
            window.location.href = '/'; // if successfully posted, go back to the main page(/)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    // update function triggered by 'UPDATE' button
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id, // add id to URL to distinguish which post have to edited
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('Successfully Edited');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();
