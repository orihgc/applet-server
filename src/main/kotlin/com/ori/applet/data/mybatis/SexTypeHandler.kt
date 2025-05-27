package com.ori.applet.data.mybatis

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.apache.ibatis.type.MappedJdbcTypes
import org.apache.ibatis.type.MappedTypes
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum::class)
class SexTypeHandler : BaseTypeHandler<SexEnum>() {
    override fun setNonNullParameter(ps: PreparedStatement?, idx: Int, sexEnum: SexEnum?, jdbcType: JdbcType?) {
        sexEnum?.id?.let { ps?.setInt(idx, it) }
    }

    override fun getNullableResult(rs: ResultSet?, col: String?): SexEnum? {
        val sex = rs?.getInt(col) ?: return null
        if (sex != 1 && sex != 2) {
            return null
        }
        return SexEnum.getSexById(sex)
    }

    override fun getNullableResult(rs: ResultSet?, idx: Int): SexEnum? {
        val sex = rs?.getInt(idx)
        if (sex != 1 && sex != 2) {
            return null
        }
        return SexEnum.getSexById(sex)
    }

    override fun getNullableResult(cs: CallableStatement?, idx: Int): SexEnum? {
        val sex = cs?.getInt(idx) ?: return null
        if (sex != 1 && sex != 2) {
            return null
        }
        return SexEnum.getSexById(sex)
    }
}