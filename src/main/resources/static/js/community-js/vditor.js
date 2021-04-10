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

    // 缓存
    "cache": {
        "enable": true //是否使用 localStorage 进行缓存
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
        accept: 'image/*,.mp3, .wav, .rar',
        token: 'test',
        url: '/api/upload/editor',
        linkToImgUrl: '/api/upload/fetch',

        filename(name) {
            return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, '').
            replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, '').
            replace('/\\s/g', '')
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
            "haircut": "💇",
            "haircut_man": "💇‍♂",
            "haircut_woman": "💇",
            "haiti": "🇭🇹",
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
            "huaji": "${emojiSite}/huaji.gif",
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
            "latke": "${emojiSite}/latke.png",
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
// const btn = document.getElementById('save-post-btn');
// btn.addEventListener('click', function() {
//     //获取Markdown ,
//     var Markdown = vditor.getValue();
//     console.log(Markdown) ;
// })


$("#save-post-btn").click(function() {
    let article = vditor.getValue();
    $.ajax({
        url : "user/saveArticle",
        type : "POST",
        data : {
            "article" : article ,
        },
        dataType : "json",//后台返回来的数据类型
        // ContentType: "application/json;charset=UTF-8",
        success : function(data) {
            //后台返回数据
            if (data.status == "save success") {
                alert(data.message);
                window.location.href = "index.html";
            }
        },
        error:function (errorThrown) {
            alert("文章保存失败");
        }
    });
})
//还可以写一些正则
//接下来是信息交互ajax

