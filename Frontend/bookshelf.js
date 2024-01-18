let url = "http://localhost:8080/books/";
let url0 = "/books/";
let url1 = "http://localhost:8080/books/{id}";
let url2 = "http://localhost:8080/books/post";
let url3 = "http://localhost:8080/books/{id}";
let url4 = "http://localhost:8080/books/delete/{id}";
let authorUrl = "http://localhost:8080/authors/";
let categoriesUrl = "http://localhost:8080/categories/";
let formatsUrl = "http://localhost:8080/formats/";

let init = {
   mode: 'cors',
   method: 'get',
   headers: {
      'Content-Type': 'application/json; charset=UTF-8',
   }
};

let author;
let publisher;
let book = {
   title: "",
   publisher: [],
   author: [],
   formats: [],
   categories: []
};

function loadAjaxBooks() {
   loadAjaxRequest(url, createTable);
}


function allData() {
   fetch(url)
      .then(response => response.json())
      .then(json => createTable(json));
}

const getFormData = (isCreate) => {
   const defaultFormVal = {
      "title": document.getElementById("title").value,
      "isbn": document.getElementById("isbn").value,
      "year": document.getElementById("year").value,
      "edition": document.getElementById("edition").value,
      "publisher": document.getElementById("publisher_id").value,
      "authors": document.getElementById("author_id").value,
      "categories": document.getElementById("category_id").value,
      "formats": document.getElementById("format_id").value
   }
   if (isCreate) {
      defaultFormVal.id = document.getElementById("book_id").value
   }
   return defaultFormVal
}

function getAllAuthors() {
   fetch(authorUrl)
      .then(response => response.json())
      .then(json => selectAuthors(json));
}
getAllAuthors();

function selectAuthors(json) {
   let select = document.getElementById("author_id");

   for (let i = 0; i < json.length; i++) {
      let opt = json[i];

      let el = document.createElement("option");
      el.text = opt.firstName;
      el.value = opt.id;

      select.add(el);
   }
}

function getAllCategories() {
   fetch(categoriesUrl)
      .then(response => response.json())
      .then(json => selectCategories(json));
}
getAllCategories();

function selectCategories(json) {
   let select = document.getElementById("category_id");

   for (let i = 0; i < json.length; i++) {
      let opt = json[i];

      let el = document.createElement("option");
      el.text = opt.category;
      el.value = opt.id;

      select.add(el);
   }
}
function getAllFormats() {
   fetch(formatsUrl)
      .then(response => response.json())
      .then(json => selectFormats(json));
}
getAllFormats();

function selectFormats(json) {
   let select = document.getElementById("format_id");

   for (let i = 0; i < json.length; i++) {
      let opt = json[i];

      let el = document.createElement("option");
      el.text = opt.format;
      el.value = opt.id;

      select.add(el);
   }
}


// da ja smenam deka e samo za po id
function createTable(json) {
   if (!Array.isArray(json)) {
      json = [json]
   }

   document.getElementById("result").innerHTML = ''
   let resultTable = document.getElementById("result")
   resultTable.innerHTML += "<th>ID</th><th>Title</th><th>ISBN</th><th>Year</th><th>Edition</th><th>Publisher</th><th>Authors</th><th>Categories</th><th>Formats</th>";

   for (let i = 0; i < json.length; i++) {
      resultTable.innerHTML += `<tr><td>${json[i].id}</td><td>${json[i].title}</td><td>${json[i].isbn}</td><td>${json[i].year}</td><td>${json[i].edition}</td>
      <td>${json[i].publisher.publisher}</td><td>${json[i].authors[0].firstName} ${json[i].authors[0].lastName}</td><td>${json[i].categories[0].category}</td><td>${json[i].formats[0].format}</td></tr>`;
   }
}


function loadOneBook() {
   let id = document.getElementById("book_id").value;
   console.log(url + id)
   fetch(url + id)
      .then(response => response.json())
      .then(json => {
         prepareBook(json)
         createTable(json)
      })
   //.catch(() => clearForm());
}

function prepareBook(json) {
   console.log(json.categories[0].category)
   document.getElementById("title").value = json.title;
   document.getElementById("isbn").value = json.isbn;
   document.getElementById("year").value = json.year;
   document.getElementById("edition").value = json.edition;
   document.getElementById("publisher_id").value = json.publisher.publisher;
   document.getElementById("author_id").value = json.authors[0].firstName;
   document.getElementById("category_id").value = json.categories[0].category;
   document.getElementById("format_id").value = json.formats[0].format;
   book = {
      author: json.authors,
      title: json.title,
      publisher: json.publisher,
      formats: json.formats,
      categories: json.categories
   }
   console.log(json.publisher)
}

function clearForm() {
   document.getElementById("book_id").value = '';
   document.getElementById("title").value = '';
   document.getElementById("isbn").value = '';
   document.getElementById("year").value = '';
   document.getElementById("edition").value = '';
   document.getElementById("publisher_id").value = '';
   document.getElementById("author_id").value = '';
   document.getElementById("category_id").value = '';
   document.getElementById("format_id").value = '';
   allData();
}

function addBook() {
   const books = getFormData();
   books.publisher = { id: 1, publisher: books.publisher }
   books.authors = [{ id: document.getElementById("author_id").value }]
   books.categories = [{ id: document.getElementById("category_id").value }]
   books.formats = [{ id: document.getElementById("format_id").value }]

   init.method = 'post';
   init.body = JSON.stringify(books);

   fetch(url2, init).then(() => allData());
}


function updateBook() {
   let books =
   {
      "id": document.getElementById("book_id").value,
      "title": document.getElementById("title").value,
      "isbn": document.getElementById("isbn").value,
      "year": document.getElementById("year").value,
      "edition": document.getElementById("edition").value,
      "publisher": { ...book.publisher, publisher: document.getElementById("publisher_id").value },
      "authors": [{ ...book.author[0], firstName: document.getElementById("author_id").value }],
      "categories": [{ ...book.categories[0], category: document.getElementById("category_id").value }],
      "formats": [{ ...book.formats[0], format: document.getElementById("format_id").value }]
   }
   console.log(books);

   init.method = 'put';
   init.body = JSON.stringify(books);


   fetch(url3.replace("{id}", books.id), init).then(() => allData());

}


function deleteBook() {
   let id = document.getElementById("book_id").value;
   init.method = 'delete';
   fetch(url4.replace("{id}", id), { method: 'delete' }).then(() => {
      allData();
      clearForm();
   });
}
