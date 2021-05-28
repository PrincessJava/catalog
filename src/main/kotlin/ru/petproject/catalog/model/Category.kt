package ru.petproject.catalog.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "category")
class Category(

    @Column(nullable = false, unique = true)
    var name: String

) : BasicEntity() {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: Category? = null

    @OneToMany(mappedBy = "parent")
    var children: MutableList<Category> = mutableListOf()

    @OneToMany(mappedBy = "category")
    var products: MutableList<Product> = mutableListOf()

    @Id
    @GenericGenerator(
        name = "category_id_seq_gen",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "category_id_seq"),
            Parameter(name = "optimizer", value = "pooled-lo"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")]
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq_gen")
    var id: Long? = null
}
