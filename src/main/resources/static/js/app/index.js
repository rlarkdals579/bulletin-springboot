var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });
    },
    save :  function (){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('Successfully Posted.');
            window.location.href = '/'; // if successfully posted, go back to the main page(/)
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
main.init();
