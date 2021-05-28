package ru.petproject.catalog.model

import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BasicEntity(

    @Version
    var version: Long = 1L,

    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    var created: Date = Date(),

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    var modified: Date = Date()

) : Serializable
