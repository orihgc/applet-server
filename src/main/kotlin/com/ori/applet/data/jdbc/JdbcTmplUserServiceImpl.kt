package com.ori.applet.data.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class JdbcTmplUserServiceImpl : JdbcTmpIUserService {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    private fun getUserMapper(): RowMapper<JdbcUser> {
        return RowMapper<JdbcUser> { rs, rowNum ->
            JdbcUser(
                id = rs.getLong("id"),
                userName = rs.getString("user_name"),
                sex = SexEnum.getSexById(rs.getInt("sex")),
                note = rs.getString("note"),
            )
        }
    }

    override fun getUser(id: Long): JdbcUser? {
        val sql = "select id,user_name,sex,note from t_user where id =?";
        val user = jdbcTemplate.queryForObject(sql, getUserMapper(), id)
        return user
    }

    override fun findUsers(userName: String, note: String): List<JdbcUser> {
        val sql = """
            select id,user_name,sex,note from t_user where user_name like concat('%',?,'%')
             and note like concat('%',?,'%')
        """.trimIndent()
        return jdbcTemplate.query(sql, getUserMapper(), userName, note)
    }

    override fun insertUser(jdbcUser: JdbcUser) {
        val sql = """
            insert into t_user (user_name, sex, note) values (?, ?, ?)
        """.trimIndent()
        jdbcTemplate.update(sql, jdbcUser.userName, jdbcUser.sex?.id, jdbcUser.note)
    }

    override fun updateUser(jdbcUser: JdbcUser) {
        val sql = """
            update t_user set user_name = ?, sex = ?, note =? where id = ?;
        """.trimIndent()
        jdbcTemplate.update(sql, jdbcUser.userName, jdbcUser.sex?.id, jdbcUser.note, jdbcUser.id)
    }

    override fun deleteUser(id: Long) {
        val sql = "delete from t_user where id=?";
        jdbcTemplate.update(sql, id)
    }
}