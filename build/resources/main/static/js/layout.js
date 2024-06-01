
$(function() {
    mainContentHeightAdjust();
});

$(window).resize(function() {
    mainContentHeightAdjust();
});

// ハンバーガーメニュー意外をクリックしたときにメニューを閉じる
/*
$(document).click(function(event) {
    // スマホ表示だと、選手名検索をタップしたときに意図せずメニューが閉じてしまう対応
    if (isSmartPhone()) { return; }

    let $clickover = $(event.target);
    let _opened = $(".navbar-collapse").hasClass("show");

    if ($clickover.parent().get(0).id==='nav-search-area') {
        return;
    }

    if (_opened === true && !$clickover.hasClass("navbar-toggler")) {
        $(".navbar-toggler").click();
    }
});
*/

// nav-barの高さに合わせてmain-contentのmargin-topを調節
function mainContentHeightAdjust() {
    $('#main-content').css('margin-top', $('#page-header .navbar').outerHeight()+'px');
}

//
$(function() {
    $('#search-input').on('input', function() {
        var query = $(this).val();

        if(query.length > 0) {
            $.ajax({
                url: '/search',
                method: 'GET',
                data: { query: query },
                success: function(data) {
                    $('#search-results').empty();
                    data.forEach(player => {
                        $('#search-results').append(`<div class="dropdown-item"><a href="/fighter/${player.fighterId}">${player.fighterName}</a><span class="badge bg-primary rounded-pill">${player.point}<i class="bi bi-p-circle-fill"></i></span></div>`);
                    });
                    if(data.length > 0) {
                        $('#search-results').addClass('show');
                    } else {
                        $('#search-results').removeClass('show');
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error("Ajax request failed: " + textStatus + " , " + errorThrown);
                    // ここでエラー処理やユーザーへのエラーメッセージの表示などを行うことができます。
                },
            });
        } else {
            $('#search-results').empty().removeClass('show');
        }
    });
});