
var uploadFile = function () {
    //获取基本表单数据
    var formdata = new FormData(document.getElementById("js-upload-form"));
    console.log(formdata);
    $.ajax({
        url:"qiniu/upload/video",
        type: "POST",
        data: formdata,
        processData:false,
        contentType:false,
        success:function(){
            console.log("over...");
        },
        error: function (e) {
            alert("错误")
        }
    })
}

function ClickInputImg() {
    //动态给文件打开框触发点击事件
    document.getElementById("Inputimg").click();
}

function ClickInputFile() {
    //动态给文件打开框触发点击事件
    document.getElementById("InputFile").click();
}

//显示上传视频，书籍文件
const showFile = function () {
    var file = $("#InputFile").val();
//正则表达式获取文件名，不带后缀
    var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");
//正则表达式获取后缀
    var fileExt=file.replace(/.+\./,"");
    // 文件名称+后缀名
    var fileName = "资源文件: "+strFileName+'.'+fileExt ;

    //隐藏空文件提示
    var noFile = document.getElementById('none-file-upload');
    noFile.style.display='none' ;

    var fileList = document.getElementById('list-files') ;
    var fileTip = document.createElement('span');
    fileTip.setAttribute("class","badge alert-success pull-right");
    fileTip.appendChild(document.createTextNode("Success")) ;

    var fileInfo = document.createElement('a');
    fileInfo.setAttribute("class" , "list-group-item list-group-item-success");
    fileInfo.appendChild(fileTip) ;
    fileInfo.innerText=fileName ;
    fileList.appendChild(fileInfo) ;
};

//显示上传封面
const showImg = function () {
    var file = $("#Inputimg").val();
//正则表达式获取文件名，不带后缀
    var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");
//正则表达式获取后缀
    var fileExt=file.replace(/.+\./,"");
    // 文件名称+后缀名
    var fileName = "封面: "+strFileName+'.'+fileExt ;

    //隐藏空文件提示
    var noFile = document.getElementById('none-file-upload');
    noFile.style.display='none' ;

    var fileList = document.getElementById('list-files') ;
    var fileTip = document.createElement('span');
    fileTip.setAttribute("class","badge alert-success pull-right");
    fileTip.appendChild(document.createTextNode("Success")) ;

    var fileInfo = document.createElement('a');
    fileInfo.setAttribute("class" , "list-group-item list-group-item-success");
    fileInfo.appendChild(fileTip) ;
    fileInfo.innerText=fileName ;
    fileList.appendChild(fileInfo) ;
}







