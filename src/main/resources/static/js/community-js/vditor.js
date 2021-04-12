const vditor = new Vditor('vditor', {
    "height": 1000,
    //"width": 1000,
    "placeholder": "快来创作吧",
    "theme": "classic",
    //历史记录时间间隔， 
    "undoDelay": 15,

    // markdown 页面设置
    "markdown": {
        // 自动校正术语
        "fixTermTypo": true,
        //插入目录
        "toc": true,
        //段落开头空两个
        "paragraphBeginningSpace": true,

    },
    //自定义工具栏
    toolbar: [
        "emoji",
        "headings",
        "bold",
        "italic",
        "strike",
        "link",
        "|",
        "list",
        "ordered-list",
        "check",
        "outdent",
        "indent",
        "|",
        "quote",
        "line",
        "code",
        "inline-code",
        "insert-before",
        "insert-after",
        "|",
        "upload",
        "record",
        "table",
        "|",
        "undo",
        "redo",
        "|",
        "fullscreen",
        "edit-mode",
        {
            name: "more",
            toolbar: [
                "both",
                "code-theme",
                "content-theme",
                "export",
                "outline",
                "preview",
                "devtools",
                "info",
                "help",
            ],
        },
        ,"|",
        "preview",
        {
            hotkey: "⌘-S",
            name: "save",
            tipPosition: "s",
            tip: "保存",
            className: "right",
            icon: `<img style="height: 16px" src='https://img.58cdn.com.cn/escstatic/docs/imgUpload/idocs/save.svg'/>`,
            click() {
                this.saveDoc();
            }
        },
    ],


    // 缓存
    "cache": {
        "enable": false //是否使用 localStorage 进行缓存
    },
    "tab": "\t",
    // 预览：
    "preview": {
        "theme": {
            "current": "Ant Design"
        },
        //预览最大宽度
        "maxWidth": 950,
        //预览代码设置
        "hljs": {
            // 代码高亮
            "enable": true,
            // 代码主题
            "style": "monokai",
            // 启用行号
            "lineNumber": true,

        },
        "markdown": {
            "toc": true
        }
    },
    "value": `[toc]`,
    // 工具栏配置
    "toolbarConfig": [{
        "pin": true
    }],
    //新增按钮: 保存按钮
    // 计数器
    "counter": {
        "enable": true,

    },
    "comment": [{
        "enable": true
    }],

    // 文件上传
    upload: {
        accept: 'image/*,.mp3, .wav, .rar',     //接受文件类型
        multiple: true ,    // 允许多文件上传
        max: 200 * 1024 * 1024,//上传图片的大小
        fieldName: 'file',
        token: 'test',
        url: '/file/upload',      //请求的接口
        linkToImgUrl: '/file/upload',

        //文件名称处理
        filename(name) {
            return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, '').
            replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, '').
            replace('/\\s/g', '')
        },

        //图片上传处理
        handler(files) {
            function callback(path) {
                let name = files[0] && files[0].name;
                let succFileText = "";
                if (vditor && vditor.vditor.currentMode === "ir") {
                    succFileText += `\n <img alt=${name} src="${path}">`;
                } else {
                    succFileText += `  \n![${name}](${path})`;
                }
                document.execCommand("insertHTML", false, succFileText);
            }
            //this.handleImageUpload(files, callback);
            //this.handleImageUpload()

        },
        //图片链接处理
        url(files, callback) {
            this.handleImageUpload(files, callback);
        },

        //粘贴图片回显处理，如果有图片加了防盗链，则让后台代理替换成自己的图片
        linkToImgFormat(files) {
            let code = 0
            let msg = ''
            let data = JSON.parse(files)
            // let linkImgName = data.result.path
            // //上传图片请求状态
            if(data.ret_code == 200) {
                // let lastTipNum = linkImgName.substr(linkImgName.lastIndexOf('/', linkImgName.lastIndexOf('/') - 1) + 1);
                // let index = lastTipNum.lastIndexOf("\/");
                // let imgUrl = decodeURI(lastTipNum.substring(index + 1, lastTipNum.length));
                let responseData = self.gb.imgOutLinkPath(data.result.path)
                let succUrl = {}
                let originalURL = 'originalURL'
                let url = 'url'
                succUrl[originalURL] = data.result.originalURL
                succUrl[url] = responseData
                let end = JSON.stringify({
                    msg,
                    code,
                    data: succUrl
                })
                return end
            } else {
                self.$message({
                    message: '图片上传失败！',
                    type: 'error'
                })
            }
        },

        //上传图片回显处理
        format(files, responseText){
            // let imageResult = JSON.parse(responseText)
            let code = JSON.parse(responseText)
            let msg = JSON.parse(responseText)
            let data = JSON.parse(responseText)
            let filName = data.result.cover_files ;

            //上传图片请求状态
            if(data.ret_code == 200) {
                let lastTipNum = filName.substr(filName.lastIndexOf('/', filName.lastIndexOf('/') - 1) + 1);
                let index = lastTipNum.lastIndexOf("\/");
                self.imgNameStr = decodeURI(lastTipNum.substring(index + 1, lastTipNum.length));
                let responseData = self.gb.imgPath(data.result.cover_files)
                let succ = {}
                succ[self.imgNameStr] = responseData
                //图片回显
                return JSON.stringify({
                    msg,
                    code,
                    data:{
                        errFiles: [],
                        succMap: succ
                        // succMap: {
                        //   'default.png': `${responseData}`
                        // }
                    }
                })
            } else {
                self.$message({
                    message: '图片上传失败！',
                    type: 'error'
                })
            }

        },
        error(msg) {
            console.log(msg+"上传失败了")
        },


    },

    // 全屏
    "fullscreen": {
        "index": 90
    },
    // 大纲
    "outline": {
        "enable": true
    },

    // hint
    "hint": {
        // 表情
        "emoji": {
            "crown": "👑",
            "cry": "😢",
            "crying_cat_face": "😿",
            "crystal_ball": "🔮",
            "cuba": "🇨🇺",
            "cucumber": "🥒",
            "cupid": "💘",
            "diamonds": "♦️",
            "disappointed": "😞",
            "disappointed_relieved": "😥",
            "dizzy": "💫",
            "dizzy_face": "😵",
            "djibouti": "🇩🇯",
            "dragon_face": "🐲",
            "dress": "👗",
            "dromedary_camel": "🐪",
            "expressionless": "😑",
            "eye": "👁",
            "eye_speech_bubble": "👁‍🗨",
            "eyeglasses": "👓",
            "eyes": "👀",
            "face_with_head_bandage": "🤕",
            "face_with_thermometer": "🤒",
            "fax": "📠",
            "fearful": "😨",
            "first_quarter_moon": "🌓",
            "first_quarter_moon_with_face": "🌛",
            "fish": "🐟",
            "fish_cake": "🍥",
            "fishing_pole_and_fish": "🎣",
            "fist": "✊",
            "fist_left": "🤛",
            "fist_oncoming": "👊",
            "fist_raised": "✊",
            "fist_right": "🤜",
            "fried_egg": "🍳",
            "fried_shrimp": "🍤",
            "fries": "🍟",
            "frog": "🐸",
            "frowning": "😦",
            "frowning_face": "☹️",
            "frowning_man": "🙍‍♂",
            "frowning_woman": "🙍",
            "fu": "🖕",
            "fuelpump": "⛽️",
            "full_moon": "🌕",
            "full_moon_with_face": "🌝",
            "golfing_man": "🏌",
            "grey_question": "❔",
            "grimacing": "😬",
            "grin": "😁",
            "hamburger": "🍔",
            "hammer": "🔨",
            "hammer_and_pick": "⚒",
            "hammer_and_wrench": "🛠",
            "hamster": "🐹",
            "hand": "✋",
            "handbag": "👜",
            "handshake": "🤝",
            "hankey": "💩",
            "hash": "#️⃣",
            "hatched_chick": "🐥",
            "hatching_chick": "🐣",
            "headphones": "🎧",
            "hear_no_evil": "🙉",
            "heart": "❤️",
            "heart_decoration": "💟",
            "heart_eyes": "😍",
            "heart_eyes_cat": "😻",
            "heartbeat": "💓",
            "heartpulse": "💗",
            "hearts": "♥️",
            "hibiscus": "🌺",
            "high_brightness": "🔆",
            "high_heel": "👠",
            "hocho": "🔪",
            "hole": "🕳",
            "honduras": "🇭🇳",
            "honey_pot": "🍯",
            "honeybee": "🐝",
            "hong_kong": "🇭🇰",
            "horse": "🐴",
            "horse_racing": "🏇",
            "hospital": "🏥",
            "hot_pepper": "🌶",
            "hotdog": "🌭",
            "hotel": "🏨",
            "hotsprings": "♨️",
            "hourglass": "⌛️",
            "hourglass_flowing_sand": "⏳",
            "house": "🏠",
            "house_with_garden": "🏡",
            "houses": "🏘",
            "hugs": "🤗",
            "hungary": "🇭🇺",
            "hushed": "😯",
            "ice_cream": "🍨",
            "ice_hockey": "🏒",
            "ice_skate": "⛸",
            "icecream": "🍦",
            "iceland": "🇮🇸",
            "id": "🆔",
            "ideograph_advantage": "🉐",
            "imp": "👿",
            "innocent": "😇",
            "interrobang": "⁉️",
            "iphone": "📱",
            "iran": "🇮🇷",
            "iraq": "🇮🇶",
            "ireland": "🇮🇪",
            "isle_of_man": "🇮🇲",
            "israel": "🇮🇱",
            "it": "🇮🇹",
            "izakaya_lantern": "🏮",
            "jack_o_lantern": "🎃",
            "jamaica": "🇯🇲",
            "japan": "🗾",
            "japanese_castle": "🏯",
            "japanese_goblin": "👺",
            "japanese_ogre": "👹",
            "jeans": "👖",
            "jersey": "🇯🇪",
            "jordan": "🇯🇴",
            "joy": "😂",
            "joy_cat": "😹",
            "joystick": "🕹",
            "jp": "🇯🇵",
            "kenya": "🇰🇪",
            "key": "🔑",
            "keyboard": "⌨️",
            "keycap_ten": "🔟",
            "kick_scooter": "🛴",
            "kimono": "👘",
            "kiribati": "🇰🇮",
            "kiss": "💋",
            "kissing": "😗",
            "kissing_cat": "😽",
            "kissing_closed_eyes": "😚",
            "kissing_heart": "😘",
            "kissing_smiling_eyes": "😙",
            "kiwi_fruit": "🥝",
            "last_quarter_moon_with_face": "🌜",
            "latin_cross": "✝️",
            "latvia": "🇱🇻",
            "laughing": "😆",
            "leaves": "🍃",
            "lebanon": "🇱🇧",
            "ledger": "📒",
            "lemon": "🍋",
            "lion": "🦁",
            "lips": "👄",
            "man": "👨",
            "man_with_gua_pi_mao": "👲",
            "man_with_turban": "👳",
            "mandarin": "🍊",
            "worried": "😟",
            "wrench": "🔧",
            "writing_hand": "✍️",
            "x": "❌",
            "yellow_heart": "💛",
            "yum": "😋",
            "zimbabwe": "🇿🇼",
            "zipper_mouth_face": "🤐",
            "zzz": "💤",
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////


})

/* 前端传送markdown 数据到后台 */
// 保存草稿
$("#save-draft-btn").click(function() {

    //let content = vditor.getValue(); 获取markdown 内容
    let content = vditor.getHTML();
    var title = $('#title-input').val();
    $.ajax({
        url : "user/addDraft",
        type : "POST",
        data : {
            "title" : title ,
            "content" : content ,
            "status" : "draft" ,
        },
        dataType : "json",//后台返回来的数据类型
        ContentType: "application/json;charset=UTF-8",
        success : function(data) {
            //后台返回数据
            if (data.status == "save success") {
                alert("文章草稿保存成功");
            }
        },
    });
})

// 保存、发布文章
$("#save-post-btn").click(function() {
    let content = vditor.getHTML();
    var title = $('#title-input').val();
    $.ajax({
        url : "user/addArticle",
        type : "POST",
        data : {
            "title" : title ,
            "content" : content ,
            "status" : "publish" ,
        },
        dataType : "json",//后台返回来的数据类型
        ContentType: "application/json;charset=UTF-8",
        success : function(data) {
            //后台返回数据
            if (data.status == "save success") {
                alert("文章保存成功");
                window.location.href = "/";
            }else if (data.status == "save failed"){
                alert("保存失败");
            }
        },
    });
})
//还可以写一些正则
//接下来是信息交互ajax



// 图片上传业务处理
//此接口里面调用的是本身的图片上传 业务方自行实现
// handleImageUpload = (file, callback) => {
//     const reader = new FileReader();
//     let formdata = new FormData();
//     formdata.append("files", file[0]);
//     reader.onload = () => {
//         // setTimeout 模拟异步上传图片
//         // 当异步上传获取图片地址后，执行callback回调（参数为imageUrl字符串），便可将图片地址写入markdown
//         new Promise(resolve => {
//             this.props.dispatch({
//                 type: "docManager/imageUpload",
//                 payload: { resolve, username: myInfo.userId, formdata }
//             });
//         }).then(res => {
//             let imgurl = res.result.path;
//             callback(imgurl);
//         });
//     };
//     reader.readAsDataURL(file[0]);
// };


