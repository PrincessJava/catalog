# test project catalog
серверное приложение на spring boot "Каталог Товаров".

Каталог состоит из разделов и продуктов.
Раздел может содержать подразделы. Товар может находиться только в одном разделе.
У приложения должно быть rest api для управления данными каталога. (Добавление / Изменение / Удаление)

Для работы с данными использовать JPA
При проектирование api учитывать что количество разделов и их глубина вложенности может быть не ограничена

ручки:
1. http://localhost:8080/categories/add/{categoryName}/?parentName={parentCategoryName} - добавление нового подраздела. 
Если раздел корневой, parentCategoryName - пустой
2. http://localhost:8080/categories/move - перемещение подраздела из одного в другой
body: {
	"name" : "{categoryName}",
	"newCatName": "{newParentCategoryName}"
}
3. http://localhost:8080/categories/get/{categoryName} - возвращает информацию о разделе
4. http://localhost:8080/products/add - добавление нового продукта в раздел
body:
{
	"name" : "{productName}",
	"category" : "{categoryName}"
}
5. http://localhost:8080/products/get-by-category/{categoryName} - возвращает список продуктов в разделе
6. http://localhost:8080/products/move - перемещает продукт из одного раздела в другой 
body:
{
	"name" : "{productName}",
	"category" : "{categoryName}"
}
7. http://localhost:8080/products/remove/{productName} - удаляет продукт
8. http://localhost:8080/categories/remove/{categoryName} - удаляет раздел (вернет ошибку, если есть подразделы или товары)
