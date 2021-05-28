package ru.testproject.catalog.model

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
    private var version: Long = 1L

) : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Long? = null

    @Column(updatable = false, nullable = false)
    @CreatedDate
    @CreationTimestamp
    open var created = Date()

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    open var modified = Date()

}
