document.addEventListener("DOMContentLoaded", function () {
    let findAllButton = document.querySelector('.find-all');
    const getByIdBtn = document.getElementById('getByIdBtn');

    getByIdBtn.addEventListener('click', function () {
        getEmployeeById();
    });
    findAllButton.addEventListener('click', function () {
        fetch('/get-all')
            .then(function (response) {
                return response.json();
            })
            .then(function (data) {
                populateTable(data);
            })
            .catch(function (error) {
                alert("error occurred");
            });
    });

    function populateTable(employees) {
        const tableBody = document.getElementById('emp-table');

        while (tableBody.rows.length > 1) {
            tableBody.deleteRow(1);
        }

        employees.forEach(function (employee) {
            const row = tableBody.insertRow();

            let cellId = row.insertCell(0);
            cellId.innerHTML = employee.id;

            let cellName = row.insertCell(1);
            cellName.innerHTML = employee.name;

            let cellRole = row.insertCell(2);
            cellRole.innerHTML = employee.role;

            let cellSalary = row.insertCell(3);
            cellSalary.innerHTML = employee.salary;

            let cellActions = row.insertCell(4);

            let updateButton = document.createElement('button');
            updateButton.textContent = 'Update';
            updateButton.addEventListener('click', function () {
                const rowId = parseInt(row.cells[0].innerHTML);
                updateEmployee(rowId);
            });
            cellActions.appendChild(updateButton);

            let deleteButton = document.createElement('button');
            deleteButton.textContent = 'Delete';
            deleteButton.addEventListener('click', function () {
                const rowId = parseInt(row.cells[0].innerHTML);
                deleteEmployee(rowId);
            });
            cellActions.appendChild(deleteButton);
        });
    }

    function updateEmployee(id) {
        const updateModel = {
            name: prompt('Enter new name'),
            role: prompt('Enter new role'),
            salary: parseFloat(prompt('Enter new salary')),
        };

        fetch(`/update-employee/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updateModel),
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to update employee');
                }
            })
            .then(data => {
                updateTableRow(id, data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function deleteEmployee(id) {
        fetch(`/delete-employee/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to delete employee');
                }
            })
            .then(data => {
                alert(data.message);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
    function updateTableRow(id, updatedData) {
        const tableBody = document.getElementById('emp-table');

        const rows = tableBody.getElementsByTagName('tr');
        for (let i = 1; i < rows.length; i++) { // Start from 1 to skip the header row
            const rowId = parseInt(rows[i].cells[0].innerHTML);
            if (rowId === id) {
                // Update the values in the table row
                rows[i].cells[1].innerHTML = updatedData.name;
                rows[i].cells[2].innerHTML = updatedData.role;
                rows[i].cells[3].innerHTML = updatedData.salary;
                break; // Exit the loop once the row is updated
            }
        }
    }


    function getEmployeeById() {
        const employeeIdInput = document.getElementById('employeeIdInput');
        const employeeId = employeeIdInput.value;

        fetch(`/get-by-id/${employeeId}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    return response.json().then(data => {
                        throw new Error(data.message);
                    });
                }
            })
            .then(employee => {
                displayEmployeeInTable(employee);
            })
            .catch(error => {
                alert(error);
            });
    }


        function displayEmployeeInTable(employee) {

        const tableBody = document.getElementById('emp-table');
        while (tableBody.rows.length > 1) {
            tableBody.deleteRow(1);
        }
        const row = tableBody.insertRow();

        const cellId = row.insertCell(0);
        cellId.innerHTML = employee.id;

        const cellName = row.insertCell(1);
        cellName.innerHTML = employee.name;

        const cellRole = row.insertCell(2);
        cellRole.innerHTML = employee.role;

        const cellSalary = row.insertCell(3);
        cellSalary.innerHTML = employee.salary;

        const cellActions = row.insertCell(4);

        const updateButton = document.createElement('button');
        updateButton.textContent = 'Update';
        updateButton.addEventListener('click', function () {
            updateEmployee(employee.id);
        });
        cellActions.appendChild(updateButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function () {
            deleteEmployee(employee.id);
        });
        cellActions.appendChild(deleteButton);
    }
});