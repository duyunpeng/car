/**
 * Created by YJH on 2016/5/9.
 */

//身份证图片上传
identityCardPic = WebUploader.create({
    // 自动上传。
    auto: true,
    // 文件接收服务端。
    server: '/uploadFile/upload_img',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#identityCardPicUpload',
    // 只允许选择文件，可选。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});
identityCardPic.on('fileQueued', function (file) {
    identityCardPic.makeThumb(file, function (error, src) {
        if (error) {
            return;
        }
//                alert(src);
    });
});
identityCardPic.on('uploadProgress', function (file, percentage) {
    $('html').addClass('.file_upload_mask');
    $('.file_upload_load').show();
});
identityCardPic.on('uploadSuccess', function (file, result) {
    $('html').removeClass('.file_upload_mask');
    $('.file_upload_load').hide();
    layer.msg("上传成功！", {icon: 1});
    var url = result.files[0].url;
    $("#identityCardPic").val(url);
    $("#identityCardPic").parent().find(".img-box").empty();
    $("#identityCardPic").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
});
identityCardPic.on('uploadError', function (handler) {
    $('html').removeClass('.file_upload_mask');
    $('.file_upload_load').hide();
    layer.msg("上传失败！");
});

//驾驶证图片上传
drivingLicencePic = WebUploader.create({
    // 自动上传。
    auto: true,
    // 文件接收服务端。
    server: '/uploadFile/upload_img',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#drivingLicencePicUpload',
    // 只允许选择文件，可选。
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    }
});
drivingLicencePic.on('fileQueued', function (file) {
    drivingLicencePic.makeThumb(file, function (error, src) {
        if (error) {
            return;
        }
//                alert(src);
    });
});
drivingLicencePic.on('uploadProgress', function (file, percentage) {
    $('html').addClass('.file_upload_mask');
    $('.file_upload_load').show();
});
drivingLicencePic.on('uploadSuccess', function (file, result) {
    $('html').removeClass('.file_upload_mask');
    $('.file_upload_load').hide();
    layer.msg("上传成功！", {icon: 1});
    var url = result.files[0].url;
    $("#drivingLicencePic").val(url);
    console.log(url);
    $("#drivingLicencePic").parent().find(".img-box").empty();
    $("#drivingLicencePic").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
});
drivingLicencePic.on('uploadError', function (handler) {
    $('html').removeClass('.file_upload_mask');
    $('.file_upload_load').hide();
    layer.msg("上传失败！");
});

function bindTravelPic() {
    //行驶证图片上传
    travelPic = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/uploadFile/upload_img',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#travelPicUpload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    travelPic.on('fileQueued', function (file) {
        travelPic.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    travelPic.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });
    travelPic.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        $("#travelPic").val(url);
        $("#travelPic").parent().find(".img-box").empty();
        $("#travelPic").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
    });
    travelPic.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });
}

function bindBusinessPic() {
    //营业资格证图片上传
    businessPic = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/uploadFile/upload_img',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#businessPicUpload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    businessPic.on('fileQueued', function (file) {
        businessPic.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    businessPic.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });
    businessPic.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        $("#businessPic").val(url);
        $("#businessPic").parent().find(".img-box").empty();
        $("#businessPic").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
    });
    businessPic.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });
}

function bindWorkPic() {
    //从业资格证图片上传
    workPic = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/uploadFile/upload_img',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#workPicUpload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    workPic.on('fileQueued', function (file) {
        workPic.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    workPic.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });
    workPic.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        $("#workPic").val(url);
        $("#workPic").parent().find(".img-box").empty();
        $("#workPic").parent().find(".img-box").append('<img src=' + url + '/><button type="button" class="btn btn-danger del-img">删除</button>');
    });
    workPic.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });
}

$(".img-box").on("click", "button", function () {
    $(this).parent().parent().find("input").val("");
    $(this).parent().empty();
})
