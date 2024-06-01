function getWeightClassName(weight) {
    switch (Number(weight)) {
        case 4:
            return "フライ級";
        case 2:
            return "フェザー級";
        case 3:
            return "バンタム級";
        case 1:
            return "ライト級";
        case 6:
            return "女子アトム級";
        default:
            return "未知の階級";
    }
}
function isSmartPhone() {
    let ua = navigator.userAgent.toLowerCase();
    if (ua.match(/(iphone|ipod|ipad|android|windows phone|blackberry)/)) {
        return true;
    } else {
        return false;
    }
}
