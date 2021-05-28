package ru.testproject.catalog.model

import ru.testproject.catalog.model.BasicEntity
import ru.testproject.catalog.model.Product
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
}
