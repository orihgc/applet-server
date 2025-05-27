package com.ori.applet.data.jpa

import com.ori.applet.data.jdbc.SexEnum
import javax.persistence.AttributeConverter

class SexConverter: AttributeConverter<SexEnum, Int> {
    override fun convertToDatabaseColumn(attribute: SexEnum): Int {
        return attribute.id
    }

    override fun convertToEntityAttribute(dbData: Int): SexEnum {
        return SexEnum.values()[dbData]
    }
}