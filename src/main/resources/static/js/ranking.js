$(function() {
    /*
    $('#appearances-ranking,#main-event-ranking').click(function(event) {
        event.preventDefault();
        showModalMessage('鋭意製作中', '作成中です。完成をお楽しみに！<br>ご意見ご要望は、お問い合わせ画面からどしどし入力してください。');
    });
    */
    // チャンピオンに王冠を付与
    addKingsImg();

    // ランキングリストアニメーション発火
    rankingAnimateIgnition();

    $('.weight-card').click(function() {
        let activeWeight = $(this).attr('data-weight-class');
        let $clickedCard = $(this);
        $.ajax({
            url: '/getRanking',
            method: 'POST',
            data: {
                inputText: activeWeight
            },
            beforeSend: function() {
                $('#ranking-list #king-img').remove(); // 王冠削除
                $('#ranking-list .fighter-name').text(''); // 名前削除
                $('#ranking-list .fighter-point').text(''); // ポイント削除
                $('[data-champ-flg="true"]').attr('data-champ-flg', false); // チャンピオンフラグ削除
                $('.weight-card.active').removeClass('active'); // アクティブカードをクリア
                $clickedCard.addClass('active'); // アクティブカードを再設定
                $('#ranking-detail-title').text(getWeightClassName(activeWeight)+'ポイントランキング'); // ランキングタイトルを設定
            },
            success: function(items) {
                items.forEach(function(item, ix) {
                    // fighterId
                    $($('#ranking-list li')[ix]).attr("data-fighter-id", item.fighterId);
                    // hrefを変更
                    $($('#ranking-list li')[ix]).find('.fighter-link').attr("href", "/fighter/"+item.fighterId);
                    // チャンピオンフラグ
                    $($('#ranking-list li')[ix]).attr('data-champ-flg', item.champFlg);
                    // 選手名
                    $($('#ranking-list li')[ix]).find('.fighter-name').text(item.fighterName);
                    // ポイント
                    $($('#ranking-list li')[ix]).find('.fighter-point').html(item.point+' <i class="bi bi-p-circle-fill"></i>');
                });

                // アニメーションを実行する
                $("#ranking-list .list-group-item .name").removeClass("animated");
                setTimeout(function(){
                    $("#ranking-list .list-group-item .name").addClass("animated");
                },10)

                // チャンピオンに王冠を付与
                addKingsImg();
            }
        });
    });
});

// ランキングリストアニメーション発火
function rankingAnimateIgnition() {
    let delay = 0; // 初期遅延時間
    let increment = 300; // 各アイテム間の増加遅延時間

    $($("#ranking-list .list-group-item .name:lt(4)").get().reverse()).each(function() {
        $(this).css("animation-delay", delay + "ms");
        delay += (increment*2);
    });

    // アニメーションを実行する
    $("#ranking-list .list-group-item .name").addClass("animated");
}

// チャンピオンに王冠を付与
function addKingsImg() {
    let kingImg = $('#king-img').clone();
    $('[data-champ-flg=true]').find('.fighter-name').prepend(kingImg);
}

// モーダルメッセージ表示
function showModalMessage(title, body) {
    event.preventDefault();
    $('#close-only-modal .modal-title').text(title);
    $('#close-only-modal .modal-body').html(body);
    $('#close-only-modal').modal('show');
}
