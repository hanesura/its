package com.example.its.repository;

import com.example.its.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EventRepository {

    /**
     * イベントリストを取得
     * @return
     */
    @Select("select a.event_id as eventId, a.event_name as eventName, to_char(a.event_ymd,'YYYY\"年\"MM\"月\"DD\"日\"') as eventYmd,\n" +
            "       b.name as venueName,a.kekka_sumi as kekkaSumi,a.rating_sumi as ratingSumi,a.event_img_url as eventImgUrl\n" +
            " from  t_event a\n" +
            " left\n" +
            " join  m_venue b\n" +
            "   on  a.venue_id = b.venue_id\n" +
            " order by a.event_ymd desc,a.event_id\n")
    List<EventEntity> findEventList();

    /**
     * イベント詳細を取得
     * @param eventId
     * @return
     */
    @Select("select a.event_id as eventId, a.event_name as eventName, " +
            "       to_char(a.event_ymd ,'YYYY\"年\"MM\"月\"DD\"日\"') as eventYmd," +
            "       b.name as venueName, \n" +
            "       a.kekka_sumi as kekkaSumi, a.rating_sumi as ratingSumi, " +
            "       a.event_img_url as eventImgUrl " +
            " from  t_event a\n" +
            " left\n" +
            " join  m_venue b\n" +
            "   on  a.venue_id = b.venue_id\n" +
            " where a.event_id = #{eventId};\n")
    EventEntity findEventDetails(int eventId);

    /**
     * 対戦カードを取得
     * @param eventId
     * @return
     */
    @Select("select a.fight_order as fightOrder, a.fight_rule as fightRule, a.chatch_weight as catchWeight,\n" +
            "       case when a.winner_id is not null then case when a.fighter_id1=a.winner_id then '勝ち' else '負け' end else 'ー' end as fighterKekka1,\n" +
            "       a.fighter_id1 as fighterId1, a.fighter_nm1 as fighterNm1,\n" +
            "       case when a.winner_id is not null then case when a.fighter_id2=a.winner_id then '勝ち' else '負け' end else 'ー' end as fighterKekka2,\n" +
            "       a.fighter_id2 as fighterId2, a.fighter_nm2 as fighterNm2,\n" +
            "       b.weight_name as titleName, \n" +
            "       case when e.kekka_sumi = false then '試合前' else case a.method_id when 1 then 'ＫＯ' when 2 then '一本' when 3 then '判定' when 4 then '無効試合' else 'ー' end end methodName,\n" +
            "       a.finish_round as finishRound, a.finish_time as finishTime, a.method_detail as methodDetail, a.memo as memo, \n" +
            "       f1.last_rating as lastRating1, f2.last_rating as lastRating2, \n" +
            "       round(1 / (1.0 + 10.0 ^ ((f2.last_rating*1.0 - f1.last_rating*1.0) / 400))*100,0) as winPercent1, \n" +
            "       100 - round(1 / (1.0 + 10.0 ^ ((f2.last_rating*1.0 - f1.last_rating*1.0) / 400))*100,0) as winPercent2" +
            " from  t_fight_history a \n" +
            " join  t_event e \n" +
            "   on  a.event_id = e.event_id \n" +
            " left \n" +
            " join  t_fighter f1 \n" +
            "   on  a.fighter_id1=f1.fighter_id \n" +
            " left \n" +
            " join  t_fighter f2 \n" +
            "   on  a.fighter_id2=f2.fighter_id \n" +
            " left\n" +
            " join  m_weight b\n" +
            "   on  a.title_id=b.weight_id\n" +
            " where a.event_id=#{eventId}\n" +
            " order by a.fight_order\n")
    List<VsCardEntity> findVsCard(int eventId);

}
