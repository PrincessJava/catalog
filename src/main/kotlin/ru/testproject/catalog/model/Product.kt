package ru.testproject.catalog.model

import ru.testproject.catalog.model.BasicEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import com.sun.istack.NotNull
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

}
