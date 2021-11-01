# test project catalog
серверное приложение на spring boot "Каталог Топ Шмота".

Каталог состоит из разделов и продуктов.
Раздел может содержать подразделы. Товар может находиться только в одном разделе.
У приложения должно быть rest api для управления данными каталога. (Добавление / Изменение / Удаление)

Для работы с данными использовать JPA
При проектирование api учитывать что количество разделов и их глубина вложенности может быть не ограничена

API:
local: http://localhost:8080/
remote: https://catalog-clothes.herokuapp.com/

Категории:
1. POST categories/{categoryName}/?parentName={parentCategoryName} - добавление нового подраздела. 
Если раздел корневой, parentCategoryName - пустой
2. PUT: categories/move - перемещение подраздела из одного в другой
body: {
    "name" : "{categoryName}",
    "newCatName": "{newParentCategoryName}"
}
3. GET: categories/{categoryName} - возвращает информацию о разделе
4. DELETE: /categories/{categoryName} - удаляет раздел (только если он не содержит продукты и подразделы)

Продукты:
1. POST: products/add - добавление нового продукта в раздел
body:
{
    "name" : "{productName}",
    "category" : "{categoryName}"
}
2. GET: products/{categoryName} - возвращает список продуктов в разделе
3. PUT: products/move - перемещает продукт из одного раздела в другой 
body:
{
    "name" : "{productName}",
    "category" : "{categoryName}"
}
4. DELETE: products/{productName} - удаляет продукт
