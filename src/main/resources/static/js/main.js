/**
 *
 */
$(document).ready(function () {






    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('#id').val(user.id);
            $('#username').val(user.username);
            $('#email').val(user.email);
            $('#name').val(user.name);
            $('#age').val(user.age);
            // $('#password').val(user.password);

            for (var i = 0; i < user.roles.length; i++) {
                var role = user.roles[i];
                console.log(user.roles)
                if (role.name === 'ROLE_ADMIN') {
                    $('#adminRole').prop('checked', true);
                }
                if (role.name === 'ROLE_USER') {
                    $('#userRole').prop('checked', true);
                }
            }
        });

        $('.myForm #editModal').modal();
    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user, status) {
            $('#deleteId').val(user.id);
            $('#deleteUsername').val(user.username);
            $('#deleteEmail').val(user.email);
            $('#deleteName').val(user.name);
            $('#deleteAge').val(user.age);

            for (var i = 0; i < user.roles.length; i++) {
                var role = user.roles[i];
                console.log(user.roles)
                if (role.name === 'ROLE_ADMIN') {
                    $('#deleteAdminRole').prop('checked', true);
                }
                if (role.name === 'ROLE_USER') {
                    $('#deleteUserRole').prop('checked', true);
                }
            }
        });

        $('.myDeleteForm #deleteModal').modal();
    });

    $("#incrementAge").click(function () {
        var age = parseInt($("#age").val());
        $("#age").val(age + 1);
    });

    $("#decrementAge").click(function () {
        var age = parseInt($("#age").val());
        if (age > 0) {
            $("#age").val(age - 1);
        }
    });


    console.log("парсим юзера1")
    fetch('/api/user')
        .then(response => response.json())
        .then(user => {
            // Update username
            document.getElementById('username').textContent = user.username;

            // Update roles
            const rolesElement = document.getElementById('roles');
            rolesElement.innerHTML = '';
            user.roles.forEach(role => {
                const roleElement = document.createElement('span');
                roleElement.textContent = role.name;
                rolesElement.appendChild(roleElement);
                rolesElement.appendChild(document.createElement('br'));
            });


            // Update user information table
            const userInfoElement = document.getElementById('user-info');
            userInfoElement.innerHTML = `
              <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>
                  ${user.roles.map(role => role.name).join('<br>')}
                </td>
              </tr>
            `;
        })
        .catch(error => console.error(error));





    console.log("парсим юзера2")
    fetch('/api/user')
        .then(response => response.json())
        .then(user => {
            // Update username
            document.getElementById('username2').textContent = user.username;

            // Update roles
            const rolesElement2 = document.getElementById('roles2');
            rolesElement2.innerHTML = '';
            user.roles.forEach(role => {
                const roleElement2 = document.createElement('span');
                roleElement2.textContent = role.name;
                rolesElement2.appendChild(roleElement2);
                rolesElement2.appendChild(document.createElement('br'));
            });


            // Update user information table
            const userInfoElement = document.getElementById('user-info');
            userInfoElement.innerHTML = `
              <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>
                  ${user.roles.map(role => role.name).join('<br>')}
                </td>
              </tr>
            `;
        })

// парсим предстваление для юзера



})

