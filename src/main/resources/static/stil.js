const url = 'http://localhost:8080/api/users'
let editId = document.getElementById("editId");
let editName = document.getElementById("editName");
let editLastName = document.getElementById("editLastname");
let editAge = document.getElementById("editAge");
let editPassw = document.getElementById("editParol");
let editEmail = document.getElementById("editEmail");

const all = $.ajax(url, {
    success: function (data) {
        let temp = '';
        data.forEach(user => {
            temp += `
         <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.roles.map(r => r.name === 'ROLE_ADMIN' ? 'ADMIN' : 'USER')}</td>
                 <td>   
                                    <button id="ButtonEdit"
                                     value="${user.id}-${user.name}-${user.lastName}-${user.age}-${user.password}-${user.email}"
                                       type="button" class="btn btn-info" data-bs-toggle="modal"
                                            data-bs-target="#staticBackdrop" onclick="setEdit(this)">Edit
                                    </button>
                                </td>
                                <td>
                                    <button id="ButtonDelete" 
                                  value="${user.id}-${user.name}-${user.lastName}-${user.age}-${user.email}"
                                     type="button" class="btn btn-danger" data-bs-toggle="modal"
                                            data-bs-target="#ModalDelete" onclick="setDelete(this)">Delete
                                    </button>
                                 </td>
         </tr>`
        })
        document.querySelector("#block1").innerHTML = temp;

    }
})

$(document).ready(function () {
    $('#addButton').click(function (e) {
        e.preventDefault();

        const name = $('#name').val();
        const lastname = $('#Lastname').val();
        const age = $('#age').val();
        const email = $('#email').val();
        const password = $('#password').val();
        const roleId = $('#roles').val();

        if (roleId == 1) {
            data = {
                name: name,
                lastName: lastname,
                age: age,
                email: email,
                password: password,
                roles: [
                    {
                        id: 1,
                        name: "ROLE_ADMIN"
                    }
                ]
            };
        } else {
            data = {
                name: name,
                lastName: lastname,
                age: age,
                email: email,
                password: password,
                roles: [
                    {
                        id: 2,
                        name: "ROLE_USER"
                    }
                ]
            };
        }

        const jsonData = JSON.stringify(data);
        $.ajax({
            url: url,
            type: 'POST',
            data: jsonData,
            contentType: 'application/json', // Указываем тип данных как JSON
            success: function (response) {
                // Обработка успешного ответа от сервера
                window.location.replace("http://localhost:8080/users")
            },
        });
    });
});

function setDelete(button) {
    var values = button.value.split("-");
    let id = document.getElementById("deleteID").value = values[0];
    document.getElementById("deleteName").value = values[1];
    document.getElementById("deleteLastname").value = values[2];
    document.getElementById("deleteAge").value = values[3];
    document.getElementById("deleteEmail").value = values[4];

    $(document).ready(function () {
        $('#ButtonDeleteAccept').click(function (e) {
            e.preventDefault();
            $.ajax({
                url: url + "/" + id,
                type: 'DELETE',
                contentType: 'application/json',
                success: function (response) {
                    window.location.replace("http://localhost:8080/users")
                },
            });
        });
    });
}

function setEdit(button) {
    var value = button.value.split("-");
    let editId1 = editId.value = value[0];
    let editName1 = editName.value = value[1];
    let editLastName1 = editLastName.value = value[2];
    let editAge1 = editAge.value = value[3];
    let editPas1 = editPassw.value = value[4];
    let editEmail1 = editEmail.value = value[5];


    $(document).ready(function () {
        $('#editButtonAccept').click(function (e) {
            e.preventDefault();

            const id = $('#editId').val();
            const name = $('#editName').val();
            const lastname = $('#editLastname').val();
            const age = $('#editAge').val();
            const email = $('#editEmail').val();
            const password = $('#editParol').val();
            const roleId = $('#editroles').val();
            // Создаем объект с данными для отправки на сервер
            if (roleId == 1) {
                data = {
                    id: id,
                    name: name,
                    lastName: lastname,
                    age: age,
                    email: email,
                    password: password,
                    roles: [
                        {
                            id: 1,
                            name: "ROLE_ADMIN"
                        }
                    ]
                };
            } else {
                data = {
                    id: id,
                    name: name,
                    lastName: lastname,
                    age: age,
                    email: email,
                    password: password,
                    roles: [
                        {
                            id: 2,
                            name: "ROLE_USER"
                        }
                    ]
                };
            }
            const jsonData = JSON.stringify(data);
            $.ajax({
                url: url + "/" + id,
                type: 'PUT',
                data: jsonData,
                contentType: 'application/json',
                success: function (response) {
                    window.location.replace("http://localhost:8080/users")
                },
            });
        });
    });
}


let userLeftBtn2 = document.getElementById("user-left-btn").value;


$.ajax({
    url: url + "/" + userLeftBtn2,
    method: 'GET',
    success: function (data) {
        let temp = `
            <tr>
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.email}</td>
                <td>${data.roles.map(r => r.name === 'ROLE_ADMIN' ? 'ADMIN' : 'USER')}</td>     
            </tr>`
        document.querySelector("#block2").innerHTML = temp;
    }
})

let princRole = document.getElementById("princRole").innerText.trim();
let adminPage = document.getElementById("admin-page");
let userPage = document.getElementById("user-page");
//Кнопки слева
let userLeftBtn = document.getElementById("user-left-btn")
let adminLeftBtn = document.getElementById("admin-left-btn")

if (princRole == 'ADMIN') {
    adminLeftBtn.classList.add("active");
    adminPage.classList.add("show", "active");
} else {
    userLeftBtn.classList.add("active");
    userPage.classList.add("show", "active");
}






