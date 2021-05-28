package ru.petproject.catalog.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sun.istack.NotNull
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "product")
class Product(

    @Column(nullable = false, unique = true)
    var name: String

) : BasicEntity() {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    var category: Category? = null

    @Id
    @GenericGenerator(
        name = "product_id_seq_gen",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "product_id_seq"),
            Parameter(name = "optimizer", value = "pooled-lo"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")]
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq_gen")
    var id: Long? = null
}
