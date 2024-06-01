package com.example.its.repository;

import com.example.its.entity.RankingEntity;
import com.example.its.entity.WeightEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RankingRepository {
    @Select("select mw.weight_id as weightId, mw.weight_name as weightName, mw.weight_limit as weightLimit, tc.fighter_id as fighterId, tf.fighter_name as fighterName \n" +
            " from  m_weight mw\n" +
            " left\n" +
            " join  t_champ_history tc\n" +
            "   on  current_date between tc.start_ymd and tc.end_ymd\n" +
            "  and  mw.weight_id = tc.weight_id\n" +
            " left\n" +
            " join  t_fighter tf\n" +
            "   on  tf.fighter_id = tc.fighter_id\n" +
            " where mw.disp_flg=1\n" +
            " order by disp_order;")
    List<WeightEntity> findWeigntList();

    @Select("select ROW_NUMBER() OVER (ORDER BY last_rating desc) AS no, \n" +
            "       tf.fighter_id as fighterId, tf.fighter_name as fighterName, \n" +
            "       case when tc.fighter_id is null then false else true end as champFlg,\n" +
            "       to_char(tf.last_rating , 'FM999,999,999') as point \n" +
            " from  t_fighter tf\n" +
            " left\n" +
            " join  t_champ_history tc\n" +
            "   on  tf.fighter_id = tc.fighter_id\n" +
            "  and  current_date between tc.start_ymd and tc.end_ymd\n" +
            " where tf.weight_class = #{weightClass}\n" +
            " order by last_rating desc\n" +
            " limit 10\n")
    List<RankingEntity> findRankingList(int weightClass);

    @Select("select ROW_NUMBER() OVER (ORDER BY last_rating desc) AS no, \n" +
            "       tf.fighter_id as fighterId, tf.fighter_name as fighterName, \n" +
            "       case when tc.fighter_id is null then false else true end as champFlg,\n" +
            "       to_char(tf.last_rating , 'FM999,999,999') as point \n" +
            " from  t_fighter tf\n" +
            " left\n" +
            " join  t_champ_history tc\n" +
            "   on  tf.fighter_id = tc.fighter_id\n" +
            "  and  current_date between tc.start_ymd and tc.end_ymd\n" +
            " order by last_rating desc\n" +
            " limit 30\n")
    List<RankingEntity> findP4PRankingList();

    @Select("select ROW_NUMBER() OVER (ORDER BY last_rating desc) AS no, \n" +
            "       tf.fighter_id as fighterId, tf.fighter_name as fighterName, \n" +
            "       case when tc.fighter_id is null then false else true end as champFlg,\n" +
            "       to_char(tf.last_rating , 'FM999,999,999') as point \n" +
            " from  t_fighter tf\n" +
            " left\n" +
            " join  t_champ_history tc\n" +
            "   on  tf.fighter_id = tc.fighter_id\n" +
            "  and  current_date between tc.start_ymd and tc.end_ymd\n" +
            " where LOWER(tf.fighter_name) like LOWER(CONCAT('%', #{fighterName}, '%')) \n" +
            " order by last_rating desc \n")
    List<RankingEntity> findRankingListByFighterName(String fighterName);

    @Select("WITH FighterRanks AS (\n" +
            "    SELECT \n" +
            "        a.fighter_id,\n" +
            "        COUNT(*) as fight_cnt,\n" +
            "        ROW_NUMBER() OVER (ORDER BY COUNT(*) DESC) as rnk\n" +
            "    FROM t_fighter a\n" +
            "    JOIN t_fight_history b ON a.fighter_id = b.fighter_id1 OR a.fighter_id = b.fighter_id2\n" +
            "    GROUP BY a.fighter_id\n" +
            "),\n" +
            "Ranked30 AS (\n" +
            "    SELECT fight_cnt\n" +
            "    FROM FighterRanks\n" +
            "    WHERE rnk = 30\n" +
            "    LIMIT 1\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "    a.rnk as no,\n" +
            "    a.fighter_id,\n" +
            "\tb.fighter_name,\n" +
            "\tcase when tc.fighter_id is null then false else true end as champFlg,\n" +
            "    a.fight_cnt as point\n" +
            "FROM FighterRanks a\n" +
            "JOIN t_fighter b\n" +
            "  on  a.fighter_id=b.fighter_id\n" +
            "left\n" +
            "join  t_champ_history tc\n" +
            "on  a.fighter_id = tc.fighter_id\n" +
            "and  current_date between tc.start_ymd and tc.end_ymd\n" +
            "\n" +
            "WHERE a.rnk <= 29\n" +
            "   OR a.fight_cnt = (SELECT fight_cnt FROM Ranked30)\n" +
            "ORDER BY a.rnk, a.fighter_id \n")
    List<RankingEntity> findRankingListByFightCnt();

    @Select("WITH FighterRanks AS (\n" +
            "    SELECT \n" +
            "        a.fighter_id,\n" +
            "        COUNT(*) as fight_cnt,\n" +
            "        ROW_NUMBER() OVER (ORDER BY COUNT(*) DESC) as rnk\n" +
            "    FROM t_fighter a\n" +
            "    JOIN t_fight_history b ON (a.fighter_id = b.fighter_id1 OR a.fighter_id = b.fighter_id2) and b.fight_order_desc=1\n" +
            "    GROUP BY a.fighter_id\n" +
            "),\n" +
            "Ranked30 AS (\n" +
            "    SELECT fight_cnt\n" +
            "    FROM FighterRanks\n" +
            "    WHERE rnk = 10\n" +
            "    LIMIT 1\n" +
            ")\n" +
            "\n" +
            "SELECT \n" +
            "    a.rnk as no,\n" +
            "    a.fighter_id,\n" +
            "\tb.fighter_name,\n" +
            "\tcase when tc.fighter_id is null then false else true end as champFlg,\n" +
            "    a.fight_cnt as point\n" +
            "FROM FighterRanks a\n" +
            "JOIN t_fighter b\n" +
            "  on  a.fighter_id=b.fighter_id\n" +
            "left\n" +
            "join  t_champ_history tc\n" +
            "on  a.fighter_id = tc.fighter_id\n" +
            "and  current_date between tc.start_ymd and tc.end_ymd\n" +
            "\n" +
            "WHERE a.rnk <= 9\n" +
            "   OR a.fight_cnt = (SELECT fight_cnt FROM Ranked30)\n" +
            "ORDER BY a.rnk, a.fighter_id;\n")
    List<RankingEntity> findRankingListByMainCnt();
}
