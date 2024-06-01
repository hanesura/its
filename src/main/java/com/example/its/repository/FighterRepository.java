package com.example.its.repository;

import com.example.its.entity.ChartEntity;
import com.example.its.entity.FighterEntity;
import com.example.its.entity.FighterHistoryEntity;
import com.example.its.entity.FighterTitleHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FighterRepository {
    /**
     * 選手詳細を取得
     * @param fighterId
     * @return
     */
    @Select("select tf.fighter_id as fighterId, tf.fighter_name as fighterName,\n" +
            "       to_char(tf.birthday,'yyyy/MM/dd') as birthday,'('||EXTRACT(YEAR FROM age(tf.birthday))::character varying||')' as age, " +
            "       ROUND(tf.height, 1)||'cm' as height, " +
            "       ROUND(tf.reach, 1)||'cm' as reach, " +
            "       ROUND(tf.weight, 1)||'kg' as weight, " +
            "       sum.total_cnt as totalCnt, sum.win_cnt as winCnt, sum.lose_cnt as loseCnt, " +
            "       coalesce(tf.x_url,'') as xUrl, tf.insta_url as instaUrl, tf.youtube_url as youtubeUrl, " +
            "       case when tc.fighter_id is null then false else true end as champ_flg,\n" +
            "       tf.first_rating, " +
            "       to_char(tf.last_rating , 'FM999,999,999') as point,\n" +
            "       tf.power, tf.speed, tf.stand, tf.submission, tf.fighting_spirit as fightingSpirit,\n" +
            "       mw.weight_name as weightClassName\n" +
            " from  t_fighter tf " +
            " left " +
            " join  t_champ_history tc " +
            "   on  tf.fighter_id = tc.fighter_id " +
            "  and  current_date between tc.start_ymd and tc.end_ymd\n" +
            " left " +
            " join  m_weight mw " +
            "   on  tf.weight_class = mw.weight_id\n" +
            "       ,(select count(*) as total_cnt, " +
            "                sum(case when winner_id=#{fighterId} then 1 else 0 end) as win_cnt, " +
            "                sum(case when losses_id=#{fighterId} then 1 else 0 end) as lose_cnt " +
            "          from  t_fight_history " +
            "          where (fighter_id1=#{fighterId} or fighter_id2=#{fighterId}) " +
            "            and fight_rule='MMA' " +
            "        ) sum " +
            " where tf.fighter_id = #{fighterId};\n")
    FighterEntity findFighterDetails(int fighterId);

    /**
     * 戦績を取得
     * @param fighterId
     * @return
     */
    @Select("select to_char(e.event_ymd ,'YYYY\"年\"MM\"月\"DD\"日\"') as fightDay," +
            "       e.event_id as eventId, e.event_name as eventName,\n" +
            "       case h.fight_order_desc when 1 then '【メインイベント】' when 2 then '【セミファイナル】' else '' end as mainOrSemi,\n" +
            "       case when f.fighter_id = h.fighter_id1 then h.fighter_id2 else h.fighter_id1 end as vsFighterId,\n" +
            "       case when f.fighter_id = h.fighter_id1 then h.fighter_nm2 else h.fighter_nm1 end as vsFighterName,\n" +
            "       case when f.fighter_id = h.winner_id then 'win' when f.fighter_id = h.losses_id then 'lose' else 'ー' end as fightKekka,\n" +
            "       case when e.kekka_sumi = false then '試合前' else case h.method_id when 1 then 'ＫＯ' when 2 then '一本' when 3 then '判定' when 4 then '無効試合' else 'ー' end end methodName,\n" +
            "       h.method_detail as methodDetail\n" +
            " from  t_fighter f\n" +
            " join  t_fight_history h\n" +
            "   on  f.fighter_id = h.fighter_id1 or f.fighter_id = h.fighter_id2\n" +
            " join  t_event e\n" +
            "   on  h.event_id = e.event_id\n" +
            " where f.fighter_id = #{fighterId} \n" +
            " order by e.event_ymd desc,h.fight_order desc;\n")
    List<FighterHistoryEntity> findFighterHistory(int fighterId);

    /**
     * 獲得タイトルを取得
     * @param fighterId
     * @return
     */
    @Select("select tc.start_ymd, tc.end_ymd, \n" +
            "       CONCAT(tc.rekidai , mw.weight_name, 'チャンピオン') champ_name\n" +
            " from  (select CASE \n" +
            "                WHEN ROW_NUMBER() OVER(PARTITION BY weight_id ORDER BY start_ymd) = 1 THEN '初代'\n" +
            "                ELSE CONCAT('第', ROW_NUMBER() OVER(PARTITION BY weight_id ORDER BY start_ymd), '代')\n" +
            "               END as rekidai,*\n" +
            "         from  t_champ_history) tc\n" +
            " join  m_weight mw\n" +
            "   on  tc.weight_id=mw.weight_id\n" +
            " where tc.fighter_id=#{fighterId};\n")
    List<FighterTitleHistoryEntity> findFighterTitleHistory (int fighterId);

    /**
     * 選手ポイント推移グラフ用のデータを取得
     * @param fighterId
     * @return
     */
    @Select("select CONCAT('vs ',vs.fighter_name) as labels, r.rating_after as data" +
            " from  t_rating r" +
            " join  t_fight_history fh on r.fight_id=fh.fight_id" +
            " join  t_event e on fh.event_id=e.event_id" +
            " join  t_fighter vs on r.vs_fighter_id=vs.fighter_id" +
            " where r.fighter_id=#{fighterId}" +
            " order by e.event_ymd, fh.fight_order")
    List<ChartEntity> findFighterChartEntity(int fighterId);
}
